# DSM API Application

## Overview

This is a Java Spring Boot application that serves as an API backend, interacting with a frontend application and sending data to a SQL database. It uses JDBC for database connectivity and is built using Maven.

## Features

- RESTful API endpoints for data submission and retrieval
- Integration with SQL database using JDBC driver
- Built with Spring Boot for easy setup and configuration
- Maven-based project for dependency management and building

## Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 11 or later
- Maven 3.6 or later
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)
- SQL Server (for database)

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/your-username/dsm-api.git
   ```

2. Navigate to the project directory:
   ```
   cd dsm-api
   ```

3. Copy the `application.properties.template` file to `application.properties` and update it with your database credentials:
   ```
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   ```

4. Open `src/main/resources/application.properties` and update the following properties:
   ```
   spring.datasource.url=jdbc:sqlserver://your-server.database.windows.net:1433;database=your-database
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

## Building the Application

To build the application, run the following Maven command:

```
mvn clean install
```

This will compile the code, run tests, and package the application into a JAR file.

## Running the Application

To run the application, use the following command:

```
java -jar target/dsm-api-0.0.1-SNAPSHOT.jar
```

Alternatively, you can use the Spring Boot Maven plugin:

```
mvn spring-boot:run
```

The application will start, and you should see output indicating that the server is running, typically on `http://localhost:8080`.

## API Endpoints

- `POST /api/submissions`: Submit form data
  - Accepts form data including file uploads

- `GET /api/submissions`: Retrieve all submissions
  - Returns a list of all submitted data

(Add more endpoints as needed)

## Configuration

The main configuration file is `src/main/resources/application.properties`. Key configurations include:

- `spring.datasource.url`: JDBC URL for your SQL database
- `spring.datasource.username`: Database username
- `spring.datasource.password`: Database password
- `spring.datasource.driver-class-name`: JDBC driver class

## Development

To develop and extend this application:

1. Import the project into your IDE as a Maven project.
2. Make your changes to the Java code.
3. Use `mvn test` to run unit tests.
4. Use `mvn spring-boot:run` to run the application for local testing.

## Deployment

For production deployment:

1. Update `application.properties` with production database credentials.
2. Build the application: `mvn clean package`
3. Deploy the generated JAR file to your server or cloud platform.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
