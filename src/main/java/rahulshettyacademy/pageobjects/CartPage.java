package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;

	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//initialization
		super(driver);
				this.driver=driver;
				PageFactory.initElements(driver,this);
	}



	public Boolean verifyProductDisplay(String productName) 
	{
		// TODO Auto-generated method stub
		boolean match=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

	

		
}
