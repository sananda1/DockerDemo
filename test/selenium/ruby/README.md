# Selenium Tests in Ruby
Ensure you run bundler install before running to get the right versions of the gems needed to run the tests.

## Environments
- local
- dev
- buildverification
- prod
## Running Tests Locally
```
$> bundle exec rake run_tests['dev','chrome']
```
### Run tests for a tests which have all specified tags 
(As many tags as needed can be specified, separated by a single space)
```
$> bundle exec rake run_tests['dev','chrome','Smoke Homepage']
```
### Run tests for a specific feature only
```
$> bundle exec rake run_tests['dev','chrome','','homepage']
```
### Run tests for a specific scenario only
```
$> bundle exec rake run_tests['dev','chrome','','','Homepage displays default message of the day']
```
### Driver Path
* In order to run the tests in chrome, or to use certain features in firefox, you must specify a DRIVERPATH parameter. This represents the folder where the driver was installed
```
$> bundle exec rake run_tests['dev','chrome','','','','/user/bin']
```

## Running Tests in SauceLabs 
```
$> bundle exec rake run_tests_sauce['dev','Windows 7','chrome','latest']
```
### Run tests for a tests which have all specified tags 
(As many tags as needed can be specified, separated by a single space)
```
$> bundle exec rake run_tests_sauce['dev','OS X 10.9','firefox','45.0','Smoke Homepage']
```
### Run tests for a specific feature only
```
$> bundle exec rake run_tests_sauce['dev','Windows 10','microsoftedge','latest','','homepage']
```
### Run tests for a specific scenario only
```
$> bundle exec rake run_tests_sauce['dev','Windows 7''internet_explorer','11.0','','','Homepage displays default message of the day']
```
### Browsers in SauceLabs
https://saucelabs.com/platforms

## Installation

### Mac Installation
* Install Ruby & Cucumber:
```
$> brew install ruby
$> gem install bundler
$> sudo gem install selenium-webdriver
$> gem install cucumber
$> gem install rspec
$> gem install rake
$> gem install sauce_whisk
```
* Install chromedriver:
```
$> brew install chromedriver
```
* Install geckodriver for Firefox:
```
$> brew install geckodriver
```
### Windows Installation
Use the Windows Ruby Installer: http://rubyinstaller.org/

* Download RubyInstaller for Ruby 2.2.4
* Install in a directory *that does not contain spaces in its path* (such as C:\Ruby, but not C:\Program Files\Ruby)
* Download the Ruby Development Kit (RDK) for your associated Ruby Installer at http://rubyinstaller.org/downloads/ in the section labeled "Development Kit"
* Run the RDK installer and extract it somewhere permanent (for convenience, consider extracting it in the same directory as where you installed Ruby)
* Navigate to the DevKit via command prompt and execute the following two commands:

```
$> ruby dk.rb init
$> ruby dk.rb install
```

* To verify everything works, try installing the necessary gems:

```
$> gem install bundler
$> gem install selenium-webdriver
$> gem install cucumber
$> gem install rspec
$> gem install rake
$> gem install sauce_whisk
```

* Download geckodriver to run tests on firefox https://github.com/mozilla/geckodriver/releases and extract it somewhere in your PATH (recommended to drop it in the same folder you installed ruby)

* Download chromedriver to run tests on chrome https://sites.google.com/a/chromium.org/chromedriver/downloads and extract it somewhere in your PATH (recommended to drop it in the same folder you installed ruby)

(Instructions originally sourced from https://github.com/oneclick/rubyinstaller/wiki/Development-Kit)

### Linux (Ubuntu) Installation
Install Ruby:<br/>
http://tecadmin.net/install-ruby-on-rails-on-ubuntu/
```
$> gem install bundler
$> sudo apt-get install ruby-dev
$> sudo gem install selenium-webdriver
$> gem install cucumber
$> gem install rspec
$> gem install rake
$> gem install sauce_whisk
```
Install headless Xvfb Firefox Browser:<br/>
http://www.installationpage.com/selenium/how-to-run-selenium-headless-firefox-in-ubuntu/