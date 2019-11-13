package Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	private static PropertyManager pm;
    private String propertyFilePath = System.getProperty("user.dir")+
            "\\resources\\configuration.properties";
    private String url;
    private String Username;
    private String Password;
    private String Testpaymentcard;
    public  String browser;
    
    
    
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
        url = prop.getProperty("url");
        Username = prop.getProperty("Username");
        Password = prop.getProperty("Password");
        Testpaymentcard = prop.getProperty("Testpaymentcard");
        browser = prop.getProperty("browser");
    }
    
    public String getURL () {
        return url;
      }
   
    public String getUsername () {
        return Username;
    }
   
    public String getPassword () {
        return Password;
    }
    
    public String getTestpaymentcard () {
        return Testpaymentcard;
    }

}
