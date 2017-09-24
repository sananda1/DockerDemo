# global variables
$driver
$wait
$config
$url

Given(/^the user is on the message of the day homepage$/) do
  $driver.navigate.to $url
  expect($driver.title).to include("Message of the Day")
end

Then(/^the user sees the message of the day$/) do
  element = $wait.until { $driver.find_element(:id, 'messageOfTheDay') }
  expect(element.text.length).to be > 0
end

When(/^the user enters a previous date that is (\d+)\/(\d+)\/(\d+)$/) do |month,day,year|
  date_field = $wait.until { $driver.find_element(:id => "datepicker") }
  date_field.send_keys(month + "/" + day + "/" + year)
  element = $wait.until { $driver.find_element(:id, 'messageOfTheDay') } # click elsewhere to dismiss date modal
  element.click
  sleep(1) # wait for modal to be dismissed
end

When(/^the user clicks the get message button$/) do
  element = $wait.until { $driver.find_element(:id => "messageBtn") }
  element.click
end

Then(/^the user sees the message of the day for a (.*)$/) do |dayOfWeek|
  element = $wait.until { $driver.find_element(:id, "messageByDateMessage").text.include?(dayOfWeek) }
end

