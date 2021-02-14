package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LearnToReadDataFromProperties {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");//from Root under src/main/resources
	
		Properties prop = new Properties();
		
		prop.load(fis);
		
		String str_property = prop.getProperty("login");//Returned NULL when the property is not found in prop file
		System.out.println(str_property);
		
	}

}
