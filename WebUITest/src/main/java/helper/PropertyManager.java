package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	private static PropertyManager pm;
    private static String propertyFilePath = System.getProperty("user.dir")+"\\resources\\configuration.properties";
    private String server;
    private String username;
    private String password;
    private String testpaymentcard;
    private String browser;
    
    
    
    public static PropertyManager getInstance () {
        if (pm == null) {
                pm = new PropertyManager();
                pm.loadData();            
        }
        return pm;
    }
    
    private void loadData() {
        Properties prop = new Properties();
 
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
 
        //Get properties from configuration.properties
        server = prop.getProperty("server");
        username = prop.getProperty("Username");
        password = prop.getProperty("Password");
        testpaymentcard = prop.getProperty("Testpaymentcard");
        browser = prop.getProperty("browser");
    }
    
    public String getServer () {
        return server;
      }
   
    public String getUsername () {
        return username;
    }
   
    public String getPassword () {
        return password;
    }
    
    public String getTestpaymentcard () {
        return testpaymentcard;
    }
    
    public String getBroswer () {
        return browser;
    }
}
