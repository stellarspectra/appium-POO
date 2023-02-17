package org.rahulshettyacademy.AppiumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pageObjects.android.FormPage;


public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {
		
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Pau\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress("0.0.0.0").usingPort(4723).build();
		service.start();
		
		
		UiAutomator2Options options = new UiAutomator2Options();
		// Appium code -> Appium server -> Mobile
		options.setDeviceName("PauDevice");
		options.setChromedriverExecutable("C:\\Users\\Pau\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//options.setApp("C:\\Users\\Pau\\git\\repository\\Appiumsv\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp("C:\\Users\\Pau\\git\\repository\\Appiumsv\\src\\test\\java\\resources\\General-Store.apk");
		
		//verificar reinstall de apk
		//install > prueba > uninstall
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		FormPage formPage = new FormPage(driver);
	}
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration",2000));
	}
	
	public void swipeAction(WebElement ele, String direction) {
		 ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					"elementId",  ((RemoteWebElement)ele).getId(),
				    "direction", direction,
				    "percent", 3.0
				));
	}
	
	public void dragDropAction(WebElement ele, int endX, int endY) {
		 ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
					"elementId",  ((RemoteWebElement)ele).getId(),
				    "endX", endX,
				    "endY", endY
				));
	}
	
	
	public Double getFormattedAmount(String amountString) {
		Double price = Double.parseDouble(amountString.substring(1));
		return price;
	}
	
	@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\org\\rahulshettyacademy\\testData\\eCommerce.json"), StandardCharsets.UTF_8);
	
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String, String>>>(){});
		
		return data;
	}
	
	
}
