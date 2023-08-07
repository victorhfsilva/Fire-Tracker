# Fire Tracker

Fire Tracker is a project that aims to create an API that stores values obtained from a fire sensor and a temperature sensor in a database. These data can be very useful in fire detection projects. In addition, a console and a app were created to interact with the API, triggering a siren and sending notifications in case of fire.

## App

Firetracker App is an Android application that provides real-time monitoring of a server to detect indications of fire. The app is designed to read data from the server's API, which stores values obtained from fire and temperature sensors. If a fire indication is detected, the app launches notifications and sends SMS alerts to the specified contact.

## API

The Firetracker API is built using Spring Boot, Spring Data JPA, and Spring Web frameworks. The API stores data from fire and temperature sensors in a PostgreSQL database. It offers endpoints to request, post, and delete data.

## Hardware

The hardware setup consists of a computer acting as the server and an Arduino device acting as the client. The Arduino is connected to the network using an Ethernet HR911105A shield. The KY-026 sensor is used to detect the level of infrared emission, while the KY-013 sensor measures the temperature.

## Functionality

The Firetracker App provides the following functionality:

1. Real-time Monitoring: The app continuously fetches data from the server's API to detect any indications of fire.
2. Notification: When the app identifies a potential fire situation, it launches a notification on the user's device to alert them about the event.
3. SMS Alert: If configured, the app can send SMS alerts to a predefined contact, informing them about the potential fire situation.
4. User Interaction: The app allows users to interact with the API through various controls and settings, such as setting alert preferences, server address and contact number.

## Future Enhancements

The Firetracker App can be further improved by implementing the following features:

1. User Authentication: Implement user authentication and authorization to ensure secure access to the app and prevent unauthorized access to sensitive data.
2. Remote Control: Allow users to remotely control the siren and other connected devices from the app.
3. Data Analytics: Add data analytics features to analyze historical data and provide insights into potential fire patterns.