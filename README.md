# Home Assignement, Mend.io, Maciej Krzesiński

## General description

This repository is an implementation of an assignment described in file 'Automation and Quality Home Assignement':

> Guidelines<br>
> As the first Automation Developer testing Github.com you are required to develop
> Sanity tests of 4 MVP capabilities.<br>
> When you Create the tests focusing on :
> - Priority - try to take the most commonly use case you can think of when
> working with github.com
> - Code Quality - try to keep you code as self explanatory as possible
> - Test Execution - assuming we want to execute the test as quick as
possible
> - Extendability (there are talks of adding the same test to azure devops or
Gitlab)<br><br>
> Guidelines:<br>
> - Project Management: Utilize Maven or an equivalent tool to manage your project,
including dependencies, build processes, and testing configuration
> - Testing: Employ TestNG or an equivalent to create a comprehensive suite of
tests that ensure your application functions as expected under various scenarios.
> - Concurrency: Implement concurrency in your application to improve performance
and efficiency, particularly when dealing with external API requests.<br><br>
> Submission:<br>
Please create and share a github repository with your assignment solution in the
solution add a readme file describing how can we use the project in order to verify
working

## Test Plan

### 1. Description
Purpose of provided tests is to provide basic picture of MVP capabilities for Github service.<br>
To achieve this goal test plan focuses on sanity tests for basic Github functionalities, implements high priority test cases.<br>
Implementation is scalable and allows user to extend tests scope for further initiatives like extended sanity, smoke and regression.

### 2. Tech stack
**Language:** Java 17,<br>
**Testing Framework:** TestNG,<br>
**Testing domains:** UI, API, possible hybrid model,<br>
**Test execution by:** dedicated xml runners, test classes, test methods,<br>
**Automation Framework:** Selenium for UI testing, REST Assured for API testing,<br>
**Concurrency:** provided by TestNG,<br>
**Reporting:** Allure,<br>
**Logging:** Log4j,<br>
**CI/CD:** Github Actions - to run tests automatically when commit to main branch,<br>

### 3. Test data
> TODO

### 4. Configuration
> TODO 

### 5. Test scenarios
> TODO

### 6. How to run
> TODO