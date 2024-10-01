# Automation Testing Project

This project is designed for automated testing of UI and APIs using a combination of cutting-edge technologies for [Empik.com](https://www.empik.com/) site. The primary focus is on providing a robust, scalable, and maintainable testing framework.

## Technologies Used

- **Java**: The primary programming language used for the project.
- **Maven**: For managing project dependencies and build lifecycle.
- **Selenium**: For browser automation and UI testing.
- **TestNG**: For test execution and management.
- **Lombok**: For reducing boilerplate code in Java.
- **Log4j**: For logging information during test execution.
- **JavaFaker**: For generating fake data for testing purposes.
- **RestAssured**: For API testing.
- **Docker**: For containerization, ensuring a consistent testing environment.
- **Jenkins**: For continuous integration and continuous deployment (CI/CD).
- **Allure Report**: For generating comprehensive test reports.
- **GitHub**: For version control and collaboration.

## Project Structure

### src
### main
#### java:
##### * enums: PropertiesValues, TestValues
##### * exceptions: CaptchaException, CaptchaNotFound
##### * listeners: SeleniumListener, TestNGListener
##### * pages: CartPage, Header, HomePage, ItemPage, LogInPage, Page, RegistrationPage, SearchPage
##### * utils: ActionsUtil, ConfigurationReader, CookieHandlerUtil, DriverFactory, DriverManager, JSExecutorUtil, ScreenshotUtil, Waiters
##### * Constants
#### resources:
##### * allure.properties
##### * configuration.properties
##### * Dockerfile
##### * log4j2.properties
##### * testConfiguration.properties

### test
#### java:
##### * apiTest: ApiTest
##### * functionalTest: CartTest, LoginTest, RegistrationTest, SearchTest
##### * CommonTest
#### resources:
##### * json: addProductsToCart, getChatBotJsonSchema, getGamJsonSchema, getLimitsConfigurationJsonSchema, getPopularCategoriesJsonSchema, postAddItemToCartJsonSchema, postCartItemQuantityIncreaseJsonSchema, putSnapshotsJsonSchema, quantityDecrease, quantityIncrease, snapshots, testBody
##### * testSuites: apiTS, functionalTS

### .gitignore
### pom.xml
### README.md

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java** (version 21 or higher)
- **Maven** (for managing dependencies and builds)
- **Selenium Server** (for running Remote Selenium WebDriver)
- **Docker** (for containerization)
- **Jenkins** (for CI/CD)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/maryiasa/Empik.git
cd [your-project-directory]
```

### Running Tests with Selenium Server
1. Start Selenium Server:

Make sure you have Selenium Server running. You can start it using the following command:
```bash
java -jar selenium-server-standalone-x.xx.x.jar
```
Replace x.xx.x with the version you have downloaded.


2. Ensure that your TestNG configuration or your WebDriver setup is pointed to the Selenium Server URL (usually http://localhost:4444/ui/).


3. Run Tests:
```bash
mvn test -Dsuite=functionalTS
mvn test -Dsuite=apiTS
```

### API Test Validation with JSON Schema
This project validates API responses using JSON Schemes. Ensure that your JSON Schema files are located in the src/test/resources/json directory.

Create JSON Schema: Define your JSON Schema to match the expected structure of the API response.

Validate Response: In your API test classes, use the RestAssured library to validate the JSON response against the schema. Example code snippet:
```bash
given()
    .when()
    .get("/api/endpoint")
    .then()
    .assertThat()
    .body(matchesJsonSchemaInClasspath("schema.json"));
```

### Viewing Allure Reports
After running the tests, you can generate and view Allure reports in your browser:
```bash
cd target
allure serve
```

### Logging
This project uses Log4j2 for logging during test execution. Configure the logging settings in the log4j2.properties file located in the src/java/resources directory.

### Test Data
testConfiguration.properties file is used for managing test data for UI tests.
JavaFaker is employed to generate realistic fake data during tests. You can customize the fake data generation as needed in your test classes.
JSON files are utilized for managing test data for Api tests.