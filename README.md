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
##### * jenkins files: jfAPI
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

## Content
* Getting Started
* Running Tests with Selenium Server
* Viewing Allure Reports locally
* Running Tests with Jenkins
* Running Tests with Jenkins inside the Docker container
* Viewing Allure Reports in Jenkins
* API Test Validation with JSON Schema
* Logging
* Test Data Configuration


## Getting Started

### Clone the Repository

```bash
git clone https://github.com/maryiasa/Empik.git
```


### Running Tests with Selenium Server
1. Start Selenium Server:

Make sure you have Selenium Server running. You can start it using the following command:
```bash
java -jar selenium-server-standalone-x.xx.x.jar
```
Replace x.xx.x with the version you have downloaded. Run from the folder where Selenium Server is installed.


2. Ensure that your TestNG configuration or your WebDriver setup is pointed to the Selenium Server URL (usually http://localhost:4444/ui/).


3. Run Tests:
```bash
mvn test -Dsuite={testSuiteName}
```
Change the {testSuiteName} before run (e.g. functionalTS, apiTS).

### Viewing Allure Reports locally
After running the tests, you can generate and view Allure reports in your browser:
```bash
cd target
allure serve
```

### Running Tests with Jenkins
1. Start Jenkins:

Make sure you have Jenkins running. You can start it using the following command:
```bash
java -jar jenkins.war
```
Run Jenkins from the folder where it is installed.

2. Go to Jenkins http://localhost:8080/


3. Set up Jenkins:

  - 3.1. Install Allure plugin:

    Manage Jenkins → Plugins → Available plugins → Allure →  Install without restart
  - 3.2. Change the tools configurations:

    Manage Jenkins → Tools → Scroll down to 'Allure Commandline installations' → name: 'Allure' → 'Install automatically' is checked → Version 2.30.0
  - Click on [Save]


4. Job creation:
  - 4.1. Go to Dashboard
  - 4.2. Click on [New Item] → Set up the name (e.g. Empik-FunctionalTS) → Freestyle project
  - 4.3. Fill up the description (optional)
  - 4.4. Choose 'This project is parameterised' → Add Parameter → Choice parameter

→ name:
```bash
browser
```
→ choices:
```bash
chrome
firefox
```

  - 4.5. Go to 'Source Code Management' section → Git → Repository URL: https://github.com/maryiasa/Empik.git → Branches to build: */main
  - 4.6. Go to 'Build Steps' → Add Build step → Execute shell →
```bash
mvn test -Dsuite={testSuiteName} -Dbrowser=${browser}
```
Change the {testSuiteName} before run (e.g. functionalTS, apiTS).

  - 4.7. Go to 'Post-build Actions' section → Add post-build action → Allure Report → Path:
  ```bash
  - target/allure-results
  ```
  - 4.8. Click on [Save]


5. Run the Job:
 - 5.1. Go to Dashboard
 - 5.2. Click on the Name of the job you want to run
 - 5.3. Click on the 'Build with Parameters' on the left side menu → Choose the browser → Click on [Build]


### Running Tests with Jenkins inside the Docker container
1. Build the image from the Dockerfile

```bash
docker build -t empik:1.0 [your-project-directory]/Empik/src/main/resources/
```
OR if you are in the folder where docker file is located, use the next command:
```bash
docker build -t empik:1.0 .
```

2. Run the image
```bash
docker run -d -p 8082:8080 empik:1.0
```
Check if the container has been run and find the container ID
```bash
docker ps
```

3. Go to logs to find the {Jenkins password}
```bash
docker logs {container ID}
```

4. Go to Jenkins http://localhost:8082/


5. Set up Jenkins:
  - 5.1. Paste the {Jenkins password}
  - 5.2. Choose 'Install suggested plugins' → 'Skip and continue as admin' → You will see - http://localhost:8082/ → 'Save and finish'
  
    So, for the next log in you should use:
    - username: admin;
    - password: {Jenkins password}
  - 5.3. Install Allure plugin:

    Manage Jenkins → Plugins → Available plugins → Allure →  Install without restart
  - 5.4. Change the tools configurations:

    Manage Jenkins → Tools:
    - 'Maven installations' → name: 'Maven' → 'Install automatically' is checked → Version 3.9.9 
    - 'Allure Commandline installations' → name: 'Allure' → 'Install automatically' is checked → Version 2.30.0
    - Click on [Save]


6. Job creation:
  - 6.1. Go to Dashboard
  - 6.2. Click on [New Item] → Set up the name (e.g. Empik-FunctionalTS) → Pipeline
  - 6.3. Fill up the description (optional)
  - 6.4. (For web tests only) Choose 'This project is parameterised' → Add Parameter → Choice parameter (browser: chrome, firefox)
  - 6.5. Go to 'Pipeline' section to define the pipeline:
    - Definition: 'Pipeline script from SCM'
      - SCM: 'Git'
        - Repositories
          - Repository URL: https://github.com/maryiasa/Empik.git
        - Branches to build
          - Branch Specifier: */main
      - Script Path: 
      ```
      src/test/resources/jenkinsFiles/{JenkinsFileName}
      ```
      {JenkinsFileName} (e.g. jfAPITS, jfFuncTS and etc.)

    - Click on [Save]

7. Run the Job:
  - 7.1. Go to Dashboard
  - 7.2. Click on the Name of the job you want to run
  - 7.3. Click on the 'Build Now' on the left side menu


### Viewing Allure Reports in Jenkins
After running the tests, click on the latest job execution in the Build history menu.
Click on 'Allure Report' to see the results.


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

### Logging
This project uses Log4j2 for logging during test execution. Configure the logging settings in the log4j2.properties file located in the src/java/resources directory.
You can find the execution logs in the logs folder in the project directory.

### Test Data Configuration
testConfiguration.properties file is used for managing test data for UI tests.

JavaFaker is employed to generate realistic fake data during tests. You can customize the fake data generation as needed in your test classes.

JSON files are utilized for managing test data for Api tests.

