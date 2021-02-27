package runner;

import org.testng.annotations.Test;

import base.BaseClass;
import cucumber.api.CucumberOptions;


@CucumberOptions(features = "src/test/java/features",glue = "pages",
				 monochrome = true)

public class run_assignment  extends BaseClass {

	
	
	
	
}
