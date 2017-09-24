#Cucumber & Selenium for Javascript
## Running Tests
The default configuration, specified in [config.properties](./test/selenium/config.properties), will execute the entire test suite using the Firefox browser, targeting the [Message of the Day Website](https://github.com/agiletrailblazers/Website-MessageOfTheDay) running in the **local** environment.

_Example 1 - Run all tests using the default configuration_
> mvn test

The properties defined in [config.properties](./test/selenium/config.properties) can also be overwritten during command-line execution.

_Example 2 - Run **all tests** targeting the [Message of the Day Website](https://github.com/agiletrailblazers/Website-MessageOfTheDay) running in the **Build Verification** environment_
> mvn test -Dconfig.environment=buildverification

_Example 3 - Run **all tests** targeting the [Message of the Day Website](https://github.com/agiletrailblazers/Website-MessageOfTheDay) running in the **Development** environment_
> mvn test -Dconfig.environment=dev

_Example 4 - Run **all tests** targeting the [Message of the Day Website](https://github.com/agiletrailblazers/Website-MessageOfTheDay) running in the **Production** environment_
> mvn test -Dconfig.environment=prod

_Example 5 - Run **a specific test** targeting the [Message of the Day Website](https://github.com/agiletrailblazers/Website-MessageOfTheDay) running in the **Local** environment_
> mvn test -Dtest=**/HomePageTest

_Example 6 - Run all tests **excluding** a specific test targeting the [Message of the Day Website](https://github.com/agiletrailblazers/Website-MessageOfTheDay) running in the **Local** environment_
> mvn test -Dtest.exclude=**/HomePageTest**

## Selenium Grid Requirements
By default, this project runs locally. However, in Jenkins, these tests are run remotely using Selenium Grid and passed to node(s). To run the tests against a local Selenium Grid, you will need to override the grid configuration.
  **_Note_**: This requires downloading and running the Selenium Server .jar locally, refer to the [instructions](https://code.google.com/p/selenium/wiki/Grid2).

_Example 7 - Run **all tests** against Selenium Grid, with a locally hosted Selenium Hub_
> mvn test -Donfig.grid=true -Dconfig.hub=http://localhost:4444/wd/hub

The Grid URL in Jenkins is http://jenkins.agiletrailblazers.com:4444/wd/hub
