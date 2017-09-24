package com.atb.motd.runners.homepage;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:feature/HomePage.feature"
)
public class HomePageTest {
}
