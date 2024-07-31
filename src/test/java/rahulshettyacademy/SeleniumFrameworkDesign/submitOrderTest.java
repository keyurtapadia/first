package rahulshettyacademy.SeleniumFrameworkDesign;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ch.qos.logback.core.joran.action.Action;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class submitOrderTest {
		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			String productName="ADIDAS ORIGINAL";
			WebDriverManager.chromedriver().setup();
			//ChromeDriver driver = new ChromeDriver();
			WebDriver driver = new ChromeDriver();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			LandingPage landingPage= new LandingPage(driver);
			landingPage.goTo();
			ProductCatalogue productCatalogue= landingPage.loginApplication("keyur@gmail.com", "Keyur@123");
				
			List<WebElement>products=productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage= productCatalogue.goToCartPage();
			
			Boolean match= cartPage.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			
			CheckoutPage checkoutPage= cartPage.goToCheckout();
			checkoutPage.selectCountry("India");
			ConfirmationPage confirmationpage=checkoutPage.submitOrder();
			String confirmMessage=confirmationpage.getConfirmationMessage();
			
			
			
			
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			driver.quit();
			}
	}

