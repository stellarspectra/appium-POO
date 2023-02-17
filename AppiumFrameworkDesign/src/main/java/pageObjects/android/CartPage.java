package pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utilities.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	AndroidDriver driver;
	
	@FindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	
	@FindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@FindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@FindBy(id="android:id/button1")
	private WebElement acceptButton;
	
	@FindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@FindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public List<WebElement> getProductList(){
		
		return productList;
	}
	
	public double getProductsSum() {
		int count = productList.size();
		double totalsum = 0;
		for(int i = 0; i < count; i++) {
			String amountString = productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalsum = totalsum + price;
		}
		
		return totalsum;
	}
	
	
	public Double getTotalAmountDisplayed() {
		
		return getFormattedAmount(totalAmount.getText());
	}
	
	public Double getFormattedAmount(String amountString) {
		Double price = Double.parseDouble(amountString.substring(1));
		return price;
	}
	
	public void acceptTermsConditions() {
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(ele);
		acceptButton.click();
	}
	
	public void submitOrder() {
		checkBox.click();
		proceed.click();
	}
	
	
}
