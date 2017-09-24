Before do |scenario|
  puts 'Executing scenario: ' + scenario.name
  $config = eval(File.open('options.config') {|f| f.read })
  $url = getSiteURL
  if(!ENV['SAUCE'].nil? && !ENV['SAUCE'].empty? && ENV['SAUCE']=='true')
	caps = {
	  :platform => "#{ENV['PLATFORM']}",
	  :browserName => "#{ENV['BROWSER']}",
	  :version => "#{ENV['BROWSER_VERSION']}",
	  :name => "#{scenario.feature.name } - #{scenario.name}"
	}
	sauceUrl = "https://AtbSauceLabs:d24365e8-f1f2-4697-b254-b560235332fb@ondemand.saucelabs.com:443/wd/hub".strip
	$driver = Selenium::WebDriver.for(:remote, :url => sauceUrl, :desired_capabilities => caps)
  else  
    $driver = getLocalDriver
  end
  
  $wait = Selenium::WebDriver::Wait.new(:timeout => 5) # seconds
end

After do |scenario|
  $driver.quit
end
