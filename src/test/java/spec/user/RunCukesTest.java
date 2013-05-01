package spec.user;

import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(
  format = { 
        "pretty", "html:build/cucumber-html-report",
        "json-pretty:build/cucumber-json-report.json" },
  glue = {"spec.user"})
public class RunCukesTest {
    
}
