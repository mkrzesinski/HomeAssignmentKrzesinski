# Home Assignement, Mend.io, Maciej Krzesiński

#### Table of Contents
- [General description](#gendescr)
- [Test Framework](#testframework)
  - [Description](#descr)
  - [Tech stack](#techstack)
  - [Test run and reporting](#howtorun)
  - [Test domain](#testdomain)
  - [Test scenarios](#testscenarios)

## General description <a id="gendescr"></a>

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

## Test framework <a id="testframework"></a>

### 1. Description <a id="descr"></a>
Purpose of provided tests is to provide basic picture of MVP capabilities for Github service.<br>
To achieve this goal test plan focuses on sanity tests for basic Github functionalities, implements high priority test cases.<br>
Implementation is scalable and allows user to extend tests scope for further initiatives like extended sanity, smoke and regression.

### 2. Tech stack <a id="techstack"></a>
**Language:** Java 17,<br>
**Project build:** Maven,<br>
**Testing Framework:** TestNG,<br>
**Testing domains:** API, (implemented example of UI tests as well),<br>
**Test execution by:** GitHub actions, dedicated xml runners, test classes, test methods,<br>
**Automation Framework:** Selenium for UI testing, REST Assured for API testing,<br>
**Concurrency:** provided by TestNG,<br>
**Reporting:** Allure, integrated also with GitHub actions<br>
**Logging:** Log4j,<br>
**CI/CD:** Github Actions - to run tests automatically when commit to main branch,<br>
**Concurency:** Provided with TestNG by configuring xml runner.

### 3. How to run <a id="howtorun"></a>

**Note: Secrets needed to execute tests are not kept in the repository. They are provided by Github under below link, but it may work only for repository owner.<br>
To reach secrets please email me directly with `maciej.krzesinski@gmail.com`**


> https://github.com/mkrzesinski/HomeAssignmentKrzesinski/settings/secrets/actions

<br>

#### 1. Run by Github Actions 
Repository has configured CI aspect using Github actions. Trigger for test run is push to master branch.
Beside of that you can re run last workflow run for master branch so this will trigger new tests run.<br>
GitHub actions for this repository can be reached here:
> https://github.com/mkrzesinski/HomeAssignmentKrzesinski/actions

Github actions and repository are also integrated with allure reporting plugin, so for each run via GitHub you can analize test result on dedicated page:
>https://mkrzesinski.github.io/HomeAssignmentKrzesinski/

<br>

#### 2. Run directly in Intellij
**IMPORTNANT**
 - if you want to run tests via Intellij don't forget to get secrets and edit run configurations by adding environment variables.
 - local run also provides allure reports by populating `allure-results` folder, but as a user you need to install `Allure CLI` to use command `allure serve` in terminal.

**- General runner for all tests is TestNG framework is xml file**

> src/test/resources/runners/**githubmvptests.xml**

XML runner defines scope of run and rules of concurency. In current configuration it provides concurency on class level with factor 2.
To test right click on xml file -> Run.

**- Run particular test class, test method**

Test cases are able to be run separately so there is also possibility to run separate test class or test method.

**- Run in terminal using `mvn clean test`**

### 4. Test domain <a id="testdomain"></a>

All implemented tests are API tests using REST Assured library. GitHub service provides an API to easily conduct all needed operations.
Proposed framework should be easy to extend by adding next tests in API or UI domain.<br>
There is also a possibility to create hybrid form of tests using both API and UI code.<br>

Github blocks all activities identified as a automation scripts executed by browsers.
Because of this UI aspect is created only in initial form to show potential Selenium framework shape.
None of existing UI test is included into proposed MVP test suite.

### 5. Test scenarios <a id="testscenarios"></a>

**Test scenarios focus on following Github MVP functionalities.**

- Logging, authentication,
- Repository workflow,
- Issue workflow,
- Pull Requests workflow.

**Predicates:**
- **User credentials:** stored in GitHub
- **Repository data:** stored in GitHub

**Expected results, validations:**
- based on checking for proper response, i.e. response codes.

<br>

#### GitHub MVP capabilities and test scenarios:

#### **Logging, authentication**

- Successful login with proper credentials,
- Unsuccessful login with wrong token,

#### **Repository workflow**,

- Create repository,
- Delete repository,
- Clone repository,


#### Issue Workflow,

- Create issue,
- Close issue,
- Edit issue,
- Delete issue,

#### Pull Requests workflow,

- Create PR,
- Merge PR,
- Close PR.