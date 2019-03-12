Simple Performance and Load Test
=========================================

This repo is to run performance and load test with gatling locally.

Simple test has been made using only Get request to demostrate the idea of Gatling and Maven as a base template

It does following things

* Performance Test on simple applicaiton using Scala simulation file
* Generate report in HTML


Advisable to run it locally rather using it in jenkins.

This download/checkout Gatling with maven plugin 3.4.4v

### Usage

Pre-requisite is to have JDK(Java) installed as scala being JVM based language.

If you are opening the project in IntelliJ, it is recommeded to install Scala SDK

### Execution

To Run all test, use the below command:

 ```sh
 $mvn gatling:test
 ```

 (OR)

To run individual test, execute the following command:

    $mvn gatling:test -Dgatling.simulationClass=<PackageName>.<ClassName>


At the end you will see link to HTML report. Open it in browser


### Defining Number of Users

If you would like to change the number of users, change the user count in simulation file

```
user_1.inject(rampUsers(100) during (30 seconds)).protocols(httpProtocol)
```

Currently, it is set to 100 users as shown above.


*Note: If the user count is changed, then it should be balanced with ramp-up time to avoid any errors due unrealistic scenario*

### Reports:

Reports will be generated at the following location

```sh
/simple-performance-test-demo/target/gatling/
```

The above location will be generated only after execution

View the 'index.html' file to see the full report
