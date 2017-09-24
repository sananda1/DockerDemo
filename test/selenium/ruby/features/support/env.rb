require 'selenium-webdriver'
require 'sauce-whisk'

def getSiteURL
  if ENV['ENVIRONMENT'] == "local"
    return $config[:config_url_local]
  elsif ENV['ENVIRONMENT'] == "dev"
    return $config[:config_url_dev]
  elsif ENV['ENVIRONMENT'] == "buildverification"
    return $config[:config_url_buildverification]
  elsif ENV['ENVIRONMENT'] == "prod"
    return $config[:config_url_prod]
  else
    raise Exception.new("must specify a valid ENVIRONMENT param")
  end
end

def getLocalDriver
  driverPath = ENV['DRIVERPATH']
  if ENV['BROWSER'] == "firefox"
    if ENV['DRIVERPATH']
      driverPath = ENV['DRIVERPATH'] + '/geckodriver'
      return Selenium::WebDriver.for :firefox, driver_path: driverPath
    else
      return Selenium::WebDriver.for :firefox
    end
  elsif ENV['BROWSER'] == "chrome"
    if ENV['DRIVERPATH']
      driverPath = ENV['DRIVERPATH'] + '/chromedriver'
      return Selenium::WebDriver.for :chrome, driver_path: driverPath
    else
      # This may not work with newer versions of Selenium and firefox
      return Selenium::WebDriver.for :chrome
    end
  else
    raise Exception.new("Unsupported browser: " + browser)
  end
end