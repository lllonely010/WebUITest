package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;;

@RunWith(JUnitPlatform.class)
public class RegisterTests extends BaseTest {
		

    @Test
    public void test01 () {
    	basepage.navigateTo_RegisterPage();
    }
	
}
