package rahulshettyacademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ch.qos.logback.core.joran.action.Action;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class standAloneTest {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String procitem="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		//LandingPage landingPage= new LandingPage(driver);
		
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("keyur@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Keyur@123");
		driver.findElement(By.id("login")).click();
		List<WebElement>products= driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement proc=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(procitem)).findFirst().orElse(null);
		proc.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();
		
		List<WebElement>cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(procitem));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Thread.sleep(3000);
		
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")),"India" ).build().perform();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.ta-item:nth-child(3) > span.ng-star-inserted")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String ConfirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		}
}