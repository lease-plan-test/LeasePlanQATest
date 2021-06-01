# LEASE PLAN TAKE HOME TEST
Contains Cucumber tests written using Java, RestAssured and JUnit, build and executed using Maven.

## Overview     
Behavior Driven scenarios for IP Stack API functionality.    

RestAssured Library is used to make API calls and Junit assertions are used to verify the response and response codes.    
     
Following are the scenarios covered:    
* Happy Path scenario by sending valid IP and verifying response    
* Negative Scenario with Invalid and Malformed IPs    
* Authentication scenarios with invalid and blank access key    

## Requirements    
### Local Execution    
1. Java 16 (can be verified using java -version)    
2. Maven 3.8.1 (can be verified using mvn -v)    

### Docker based execution
1. Docker
       
**Note: To avoid installing all these above dependencies on your local machine, use docker based execution or execute on Github Actions.**    

## Setup
1. Install all above local execution or docker based execution dependencies or both on your system
2. Clone this repository    
   ..and that is it, we are good to go..    
   All other dependencies will be fetched and installed by maven..   
   
    
## Execution
There are 2 ways to execute the tests here.
### 1) Local Execution using script :
```bash run.sh```    
and follow the steps in the file.    
It will ask you type of execution where you can interactively select an option.

### 2) Local execution using commands :
This is where you can execute the tests using maven or docker commands.    

#### a) Local execution with UI
Here the tests will download dependecy locally and execute on host machine.    
```
mvn clean test
```    
    
#### b) Docker based execution
Here the tests will run on a docker container.    .
```
docker pull maven:3.8.1-openjdk-16-slim  #only needed once
docker run --rm -it -v "$PWD":/usr/src -w /usr/src maven:3.8.1-openjdk-16-slim mvn clean test;
```    

### 3) CI execution :
Here the tests will execute using GitHub actions.    
There are 3 ways to run the tests using Github Actions
* Login to Github and manuall trigger build from Actions tab    
* Do a empty dummy commit to master branch    
* Create a pull request to master branch    

**Note: Reports are uploaded as Github page [here](https://lease-plan-test.github.io/LPTestReport/)**

## Reporting
There are 2 reports created from this execution.
### a) Default Cucumber HTML/JSON reports
Cucumber provides an option to create basic html report when executing tests.     
These reports are by default created in the target directory.    
Filename for these reports are as follows:
```
lp_report.html
lp_report.json
```     
The json file is not easily readable for obvious reasons but the html file can be opened in any browser.   

Note: When Executed using Github Actions, cucumber report is automatically updated to another public repository where it can be viewed using Github pages. 
Here is the link to the [public repo](https://github.com/lease-plan-test/LPTestReport) and [report](https://lease-plan-test.github.io/LPTestReport/)

### b) Maven Cucumber Reporting
This is an additional plugin/dependency added into the pom file to create more readable reports for better readability.    
This is completely optional and tests can run without it too.

**Note: There are other 3rd party cucumber reporting libraries which can be used depending on which give the tech and product team better readability and faster debugging, improving overall efficiency.**     

Thanks you!! :) 
