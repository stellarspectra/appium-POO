package org.rahulshettyacademy.AppiumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.android.CartPage;
import pageObjects.android.FormPage;
import pageObjects.android.ProductsCatalogue;

public class eCommerce_tc_3 extends BaseTest{

	//@BeforeMethod
	//public void preSetup() throws InterruptedException {
	//Thread.sleep(10000);
	//Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
	//driver.startActivity(activity);
		
	//}
	
	
		@Test(dataProvider = "getData")
		public void FillForm(HashMap<String, String> input) throws InterruptedException {
			
			FormPage formPage = new FormPage(driver);
			formPage.setNameField(input.get("name"));
			formPage.setGender(input.get("gender"));
			
			formPage.setCountry(input.get("country"));
			ProductsCatalogue productCatalogue = formPage.submitForm();
						
			productCatalogue.addItemToCartByIndex(0);
			productCatalogue.addItemToCartByIndex(0);
			CartPage cartPage = productCatalogue.goToCartPage();
			
			
			Thread.sleep(2000);
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
			
			double totalsum = cartPage.getProductsSum();
			Double displayFormattedSum = cartPage.getTotalAmountDisplayed();
			
					
			Assert.assertEquals(totalsum, displayFormattedSum);
						
			cartPage.acceptTermsConditions();
			cartPage.submitOrder();
			
			Thread.sleep(5000);
			
			
			
		}
		
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			List<HashMap<String, String>> data = getJsonData((System.getProperty("user.dir")+"\\src\\test\\java\\org\\rahulshettyacademy\\testData\\eCommerce.json"));
			//return new Object[ ][ ] { {"paula almenar", "female", "Argentina"}, {"pau", "female", "Argentina"}   };
			return new Object[ ][ ] { {data.get(0)}  };
		}
		
		
}
