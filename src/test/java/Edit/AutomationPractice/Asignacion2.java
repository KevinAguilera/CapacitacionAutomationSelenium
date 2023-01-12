package Edit.AutomationPractice;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Asignacion2 {
	String url = "https://www.saucedemo.com/";
	
	@Test
	public void inicioSesionChrome () {
		
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		WebElement txtUser = driver.findElement(By.id("user-name"));
		txtUser.sendKeys("standard_user");
		WebElement txtPass = driver.findElement(By.id("password"));
		txtPass.sendKeys("secret_sauce");
		WebElement txtLogin = driver.findElement(By.id("login-button"));
		txtLogin.click();
		//driver.close();
	}
	
}
