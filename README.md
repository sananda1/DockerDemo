# Website-MessageOfTheDay
This is the source repository for the Node/Express (Website) application layer used by the Agile Trailblazers Message of the Day demo application.

## Core Application Technologies
* [Node.js](https://nodejs.org/en/) (Javascript Framework)
* [Express.js](http://expressjs.com/) (Web Framework for Node.js)
* [npm](https://www.npmjs.com/) (Package Manager for Node.js)

## Core JavaScript Testing Technologies
* [Tap](https://www.npmjs.com/package/tap) (Unit Testing)
* [Istanbul](https://www.npmjs.com/package/istanbul) (Unit Testing / Code Coverage)
* [Proxyquire](https://github.com/thlorenz/proxyquire) (Unit Testing / Mocking)
* [nock](https://github.com/pgte/nock) (Unit Testing / Mocking)
* [Chai](http://chaijs.com/) (Unit Testing / Expectations and Assertions)

## Core Integration (Automated) Testing Technologies
* [Cucumber](https://cucumber.io/)
* [Gherkin](https://github.com/cucumber/cucumber/wiki/Gherkin)
* [Selenium](http://www.seleniumhq.org/)
* [JUnit](http://junit.org/)
* [Maven](https://maven.apache.org/)
* [Java](http://www.oracle.com/technetwork/indexes/downloads/index.html?ssSourceSiteId=ocomen)
* [Selenium Grid](https://code.google.com/p/selenium/wiki/Grid2)

## Installation

1. Download and install [Node.js](https://nodejs.org/en/)
2. Clone the remote Website-MessageOfTheDay repository to your local development directory
3. Change directories to the root application directory: `cd {your-dev-dir}/Website-MessageOfTheDay`
4. Run `npm install` to download all the dependent node.js packages

### Additional Selenium Web Driver Requirements
Use of Selenium Web Driver, depending on the specific browser you wish to test, requires additional installations.  These instructions are specific to installation on a Mac, for other OS's, you are on your own.
- Update to latest version of brew, brew-cask and cask (if not already installed and updated)
  > brew update && brew upgrade brew-cask && brew cleanup && brew cask cleanup
- Install Firefox (if not already installed)
  > brew cask install firefox
- Install Google Chrome (if not already installed)
  > brew cask install google-chrome
- Install Google Chromedriver (if not already installed)
    > brew install chromedriver

## Running Unit Tests
The complete suite of unit tests can be executed by running the npm test script.  All unit tests must pass and the minimum unit test code coverage levels, as defined in the project .istanbul.yml file, must be met in order for a build to be successful.

    npm test

Individual unit test files can be executed as follows

    istanbul cover [relative-path-to-test-js-file]

Upon completion of the test execution, the console will report a summary of the code coverage.  A very detailed, HTML-based,
coverage report is also generated and can be viewed in any browser by navigating to the following file

    {your-dev-dir}/Website-MessageOfTheDay/coverage/index.html

## Running the Website-MessageOfTheDay Application Locally
1. Change directories to the root application directory: `cd {your-dev-dir}/Website-MessageOfTheDay`
2. To start node using the "buildverification" configuration: `NODE_ENV=buildverification node ./bin/www`
3. To start node using the "local" configuration: `NODE_ENV=local node ./bin/www`
4. To start node using the "development" configuration: `NODE_ENV=development node ./bin/www`
5. To start node using the "production" configuration: `NODE_ENV=production node ./bin/www`
6. Access the application via any browser: http://localhost:3000

## Adding Packages
When adding packages please use the `--save` option to add to our list of dependencies in the package.json file. If you add a package please notify the team on SLACK that you have added a package so we can install the dependency on our local machines after syncing with the remote repository.
Using the `-g` option will install the package globally so that you will be able to execute it directly from the commandline.

    Example: npm install [NAME] -g --save

  **_Note_**: Refer to the projects [pom.xml](./test/selenium/pom.xml) for the complete list of dependencies and their versions.

## Running Integration (Selenium) Tests
This project has Selenium, Cucumber, Gherkin tests developed in two different languages: Ruby and Java:

* [Integration Tests in Java](test/src/README.md)
* [Integration Tests in Ruby](test/ruby/README.md

--- This area for testing only ---
> Test MODT-27
