package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import Helper.PropertyManager;

@RunWith(JUnitPlatform.class)
public class TestCase {
	
	@Test
	public void Test01()
    {

		String baseURL = PropertyManager.getInstance().getURL();
		System.out.println(baseURL);
		
	}

}
