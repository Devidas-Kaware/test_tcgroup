# Weather Details Application
This is a Spring Boot application that fetches current weather data based on a given US postal code and stores the request history in a MySQL database. The application utilizes a public weather API to retrieve weather information.

## Features
- Fetch weather data using a US postal code.
- Store request history in a MySQL database.
- View weather request history for a specific user or postal code.
- Validation for user and postal code input.
- Custom error handling for various scenarios.

## Tech Stack
- Java 17 or later
- Spring Boot
- Spring Data JPA
- MySQL
- RestTemplate for API integration
- JUnit and Mockito for testing

## Prerequisites
- Java Development Kit (JDK) 17 or later
- Maven
- MySQL Server
- An account with a public weather API provider (e.g., OpenWeatherMap) to get an API key.
- Link : https://openweathermap.org/

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/yourusername/weather-details-app.git
cd weather-details-app
```

### Create Database and Configure in application.properties
1. Create a MySQL database named weather_app_db.
2. Configure your application properties:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/weather_app_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

weather.api.url=http://api.openweathermap.org/data/2.5/weather
weather.api.key=your_api_key
weather.api.country=us
```

### Run the Application 

#### The application will start on http://localhost:8081.

#### Postman Collection link : https://github.com/Devidas-Kaware/test_tcgroup/blob/master/postman_collection

## API Endpoints
### Fetch Weather Data
- URL: /v1/api/weatherDetails/fetch
- Method: POST
- Request Body :
```bash
{
  "postalCode": "90210",
  "userName": "John Doe"
}
```
- Response :
```bash
{
    "data": {
        "weatherDescription": "mist",
        "temperature": 16.4,
        "temperatureMaximum": 19.84,
        "temperatureMinimum": 13.82,
        "humidity": 85
    },
    "status": "success",
    "timestamp": "2024-09-22T22:03:54.7289407"
}
```
### Fetch Weather History
- URL: /v1/api/weatherDetails/history
- Method: GET
- Query Parameters:
1. postalCode (optional)
2. userName (optional)
- Response:
```bash
{
    "data": [
        {
            "id": 1,
            "userName": "Shyam",
            "postalCode": "94040",
            "temperature": 16.02,
            "temperatureMinimum": 18.2,
            "temperatureMaximum": 0.0,
            "humidity": 90,
            "weatherDescription": "mist",
            "timestamp": "2024-09-22T21:39:07.077038"
        },
        {
            "id": 2,
            "userName": "Shyam",
            "postalCode": "94040",
            "temperature": 16.25,
            "temperatureMinimum": 13.42,
            "temperatureMaximum": 18.2,
            "humidity": 89,
            "weatherDescription": "mist",
            "timestamp": "2024-09-22T21:53:15.832543"
        },
        {
            "id": 3,
            "userName": "Shyam",
            "postalCode": "94040",
            "temperature": 16.25,
            "temperatureMinimum": 13.42,
            "temperatureMaximum": 18.2,
            "humidity": 89,
            "weatherDescription": "mist",
            "timestamp": "2024-09-22T22:02:24.76719"
        },
        {
            "id": 4,
            "userName": "Shyam",
            "postalCode": "94040",
            "temperature": 16.4,
            "temperatureMinimum": 13.82,
            "temperatureMaximum": 19.84,
            "humidity": 85,
            "weatherDescription": "mist",
            "timestamp": "2024-09-22T22:03:54.382937"
        }
    ],
    "status": "success",
    "timestamp": "2024-09-22T23:15:56.971833"
}
``` 
