require 'selenium-webdriver'

# global variables
$driver
$wait
$config
$url

Given(/^the user is on the message of the day week page$/) do
  $driver.navigate.to $url + "/week"
  expect($driver.title).to include("Message of the Week")
end

Then(/^confirm we see the following days$/) do |table|
  data = table.hashes
  data.each do |row|
    row.each do |key, value|
      #locate each value from the table on the page
      $driver.find_element(:id, "daysOfWeek").find_element(:xpath, './/*[contains(., "' + value + '")]')
    end
  end
end
