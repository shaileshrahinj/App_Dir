package pkg;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

	public class PropertiesReader {
		
		public static Properties testProperties=new Properties();
		public static void loadProperties() throws IOException{
			InputStream fis =PropertiesReader.class.getClassLoader().getResourceAsStream("pkg/app.properties");
			testProperties.load(fis);
		
		}
		
	}
	