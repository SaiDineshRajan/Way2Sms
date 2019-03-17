package tests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"E:\\batch239\\org.Way2ms\\src\\test\\resources\\mypack\\feature1.feature"},
plugin={"pretty","html:target/result1","rerun:target/failed.txt"},monochrome=true)

public class Runner 
{
 
 
}
        
          