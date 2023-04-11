# Fire Tracker

Fire Tracker is a project that aims to create an API that stores values obtained from a fire sensor and a temperature sensor in a database. These data can be very useful in fire detection projects. In addition, a console was created to interact with the API, triggering a siren in case of fire.

## API

To build the API, the Spring Boot, Spring Data JPA, and Spring Web frameworks were used. The programming language used was Java.

The API has controllers to request, post, and delete data. Both requests and removals can also be made by inserting a time period.

## Data

The PostgreSQL database was used to store the values. It stores the date/time of data insertion, the level of infrared emission, the temperature, and a boolean indicating whether a flame was detected or not.

## Hardware

The hardware used was a computer as a server and an Arduino as a client. The Ethernet HR911105A shield was used to connect the Arduino to the network. The KY-026 and KY-013 sensors were used to detect the infrared emission level and temperature, respectively.

## Console

In addition to the API, a Python console was developed to interact with the API and control a siren in case of fire.

The console uses the Requests library to make requests to the API and the multiprocessing library to execute the siren in a separate process from the main process.

## Execution

To run the project, you need to have Java JDK 17 and PostgreSQL installed.

After cloning the repository, you need to configure the database access credentials and the server's IP and port information.

## Future Improvements

Some improvements that can be made to the project include:

* Implementation of authentication and authorization in the API
* Addition of an alert system via email or SMS
* Use of a more robust microcontroller to handle more data and more sensors
* Development of a mobile application to monitor data and control the siren from a smartphone.