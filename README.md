# studio-moderna-backend

## Prerequisites

Before you begin, ensure that you have the following installed on your machine:

- Java Development Kit (JDK) version 17
- Apache Maven or Gradle (depending on your build tool preference)
- IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

## Setup

1. Clone the project repository from the version control system (e.g., Git) or obtain the source code from a ZIP file.

2. Open the project in your preferred IDE.

3. Build the project using Maven or Gradle:

    - **Maven**: Open a terminal or command prompt, navigate to the project directory, and run the following command:
      ```
      mvn clean install
      ```

    - **Gradle**: Open a terminal or command prompt, navigate to the project directory, and run the following command:
      ```
      gradle clean build
      ```

## Starting the Application

1. Locate the main class of your Spring Boot application, `BookFactoryApplication`.

2. Run the application by executing the `main` method. You can usually find a "Run" or "Debug" button in your IDE next to the `main` method.

   Alternatively, you can use the command line:

    - **Maven**: Open a terminal or command prompt, navigate to the project directory, and run the following command:
      ```
      mvn spring-boot:run
      ```

    - **Gradle**: Open a terminal or command prompt, navigate to the project directory, and run the following command:
      ```
      gradle bootRun
      ```

3. Wait for the application to start. You will see log messages indicating the application startup progress.

4. Once the application has started, you can access it in a web browser or test it using API testing tools (e.g., cURL, Postman) at the specified URL and port: `http://localhost:8080`
