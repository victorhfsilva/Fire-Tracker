#include <Ethernet.h>

//Ethernet Configuration
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
EthernetClient client;
int HTTP_PORT = 49152;
char HOST_NAME[] = "192.168.12.13";
String PATH_NAME;
IPAddress ip(192, 168, 12, 27);

//Pin Configuration
//int led = 13; // define the LED pin
int irDigitalPin = 2; // KY-026 digital interface
int irAnalogPin = A0; // KY-026 analog interface
int thermistorPin = A1; // KY-013 analog interface

//Main Variables
int isOnFire; // infrared digital readings
int irLevel; //infrared analog readings
int temperatureVoltage; //temperature analog readings
float temperatureCelcius; //temperature in Celcius
float temperatureKelvin; //temperature in Kelvin

//Thermistor Variables
float R1 = 10000; // value of R1 on board
float logR2, R2; // value of thermistor resistance
float c1 = 0.001129148, c2 = 0.000234125, c3 = 0.0000000876741; //steinhart-hart coeficients for thermistor


void setup()
{
  //pinMode(led, OUTPUT);
  pinMode(irDigitalPin, INPUT);
  pinMode(irAnalogPin, INPUT);
  pinMode(thermistorPin, INPUT);
  Serial.begin(9600);

  initializeEthernetShield();
}


void loop()
{
  isOnFire = readIrDigitalInterface();
  irLevel = readIrAnalogInterface();
  temperatureCelcius = readTemperature();

  Serial.print(isOnFire);
  Serial.print(",");
  Serial.print(irLevel); 
  Serial.print(",");
  Serial.println(temperatureCelcius);
  
  postMethod(isOnFire, irLevel, temperatureCelcius);
  
  delay(1000);
}


bool readIrDigitalInterface(){
  // Read the IR digital interface
  isOnFire = digitalRead(irDigitalPin); 
  /*
  if(isOnFire == HIGH) // if flame is detected
  {
    digitalWrite(led, HIGH); // turn ON Arduino's LED
  }
  else
  {
    digitalWrite(led, LOW); // turn OFF Arduino's LED
  }
  */
  return isOnFire;
}

int readIrAnalogInterface(){
  // Read the IR analog interface
  irLevel = analogRead(irAnalogPin);
  return irLevel;
}

float readTemperature(){
  temperatureVoltage = analogRead(thermistorPin);
  R2 = R1 * (1023.0 / (float)temperatureVoltage - 1.0); //calculate resistance on thermistor
  //R2 = R1 * (float) temperatureVoltage / (1023 - (float) temperatureVoltage);
  logR2 = log(R2);
  temperatureKelvin = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2)); // temperature in Kelvin
  temperatureCelcius = temperatureKelvin - 273.15; //convert Kelvin to Celcius
  return temperatureCelcius;
}

void postMethod(bool isOnFire, int irLevel, float temperature){

  if (client.connect(HOST_NAME, HTTP_PORT)) { 
    
    PATH_NAME = "/firetracker/data/new?isOnFire=" + (String) isOnFire+"&irLevel=" + (String) irLevel + "&temperature=" + (String)temperature;
    client.print("POST " + PATH_NAME + " HTTP/1.1\r\nHost: 192.168.12.13\r\n\r\n"); // Send an HTTP POST request to the server
    Serial.println("Data saved.");

  } else {// if not connected:
    
    Serial.println("connection failed");
  
  }  
    
    client.stop(); // Disconnect from the server
}

void initializeEthernetShield(){
  
  Ethernet.begin(mac, ip);

  while (!Serial) {
    ; // wait for serial port to connect
  }
  Serial.println();
  Serial.println("----------------------------------------------------------------");

  if (client.connect(HOST_NAME, HTTP_PORT)) {
    client.print("GET /firetracker HTTP/1.1\r\nHost: 192.168.12.13\r\n\r\n"); // Send an HTTP GET request to the server
    Serial.println("SERVER IS UP");
  } else {// if not connected:
    Serial.println("SERVER IS DOWN");
  }
  client.stop(); // Disconnect from the server
  Serial.println("----------------------------------------------------------------");
}
