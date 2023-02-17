package pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utilities.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{

	AndroidDriver driver; 
	

	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@FindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@FindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	@FindBy(id="android:id/text1")
	private WebElement countryOption;
	
	@FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	public void setNameField(String field) {
		nameField.sendKeys(field);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		
		if (gender.contains("female")) 
			
			femaleOption.click();
		else 
			maleOption.click();	
			
		
	}
	
	public void setCountry (String country) {
		
		countryOption.click();
		scrollToText("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
		
		
	}
	
	public ProductsCatalogue submitForm() {
		shopButton.click();
		
		return new ProductsCatalogue(driver);
	}

	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
