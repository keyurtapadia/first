package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

WebDriver driver;
	
	
	

	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	

	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//initialization
		super(driver);
				this.driver=driver;
				PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMesssage;
	
//	public String getConfirmationMessage()
//	{
//		return confirmationMesssage.getText();
//	}

	public String getConfirmationMessage() {
		// TODO Auto-generated method stub
		return confirmationMesssage.getText();
	}
	
}
