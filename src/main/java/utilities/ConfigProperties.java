package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

	private Properties properties;
	private final String propertyFilePath = "configs\\Configuration.properties";

	public ConfigProperties() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public String getDriverPath() {
		String driverPath = properties.getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public String getDriver() {
		String driverPath = properties.getProperty("browser");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("browser name not specified in the Configuration.properties file.");
	}

	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	public String getMobileWidth() {
		String Mobile_Width = properties.getProperty("Mobile_Width");
		if (Mobile_Width != null)
			return Mobile_Width;
		else
			throw new RuntimeException("Mobile_Width not specified in the Configuration.properties file.");
	}

	public String getMobileHeight() {
		String Mobile_Height = properties.getProperty("Mobile_Height");
		if (Mobile_Height != null)
			return Mobile_Height;
		else
			throw new RuntimeException("Mobile_Height not specified in the Configuration.properties file.");
	}

	public String getDeviceName() {
		String Mobile_Height = properties.getProperty("DeviceName");
		if (Mobile_Height != null)
			return Mobile_Height;
		else
			throw new RuntimeException("DeviceName not specified in the Configuration.properties file.");
	}

	public String getTestingType() {
		String TestingType = properties.getProperty("testingType");
		if (TestingType != null)
			return TestingType;
		else
			throw new RuntimeException("testingType not specified in the Configuration.properties file.");
	}

}
