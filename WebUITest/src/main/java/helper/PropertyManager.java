package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyManager {

	private static PropertyManager pm;
	private static String propertyFilePath = System.getProperty("user.dir") + "\\resources\\configuration.properties";
	public static String logFilePath = System.getProperty("user.dir") + "\\resources\\log4j2.xml";
	private String server;
	private String username;
	private String password;
	private String testpaymentcard;
	private String browser;

	static {
		System.setProperty("log4j.configurationFile", logFilePath);
	}

	private final Logger log = LogManager.getLogger(PropertyManager.class);

	public static PropertyManager getInstance() {
		if (pm == null) {
			pm = new PropertyManager();
			pm.loadData();
		}
		return pm;
	}

	private void loadData() {
		Properties prop = new Properties();
		log.info("Read configuration.properties file");

		try {
			prop.load(new FileInputStream(propertyFilePath));
		} catch (IOException e) {
			log.error("Configuration properties file cannot be found");
		}

		server = prop.getProperty("server");
		username = prop.getProperty("Username");
		password = prop.getProperty("Password");
		testpaymentcard = prop.getProperty("Testpaymentcard");
		browser = prop.getProperty("browser");
	}

	public String getServer() {
		return server;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTestpaymentcard() {
		return testpaymentcard;
	}

	public String getBroswer() {
		return browser;
	}

	public String getURL() {
		return "http://" + username + ":" + password + "@" + server;
	}
}
