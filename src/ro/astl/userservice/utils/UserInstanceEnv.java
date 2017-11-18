package ro.astl.userservice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserInstanceEnv {
	
	public static final String PROPFILE = "userinstance.properties";
	
	public static Properties getProperties() {
		Properties properties = new Properties();
		try(InputStream input = UserInstanceEnv.class.getClassLoader().getResourceAsStream(PROPFILE)){
			if(input!=null) {
				properties.load(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String key : properties.stringPropertyNames()) {
			  String value = properties.getProperty(key);
			  System.out.println(key + " => " + value);
		}

		return properties;
	}

}
