package Edit.AutomationPractice;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Asignacion4 {
	String url = "http://automationpractice.pl";
	String email = "kevin" + Math.floor(Math.random() * 1000) + "@gmail.com";
	
	@Test
	public void registrarUsuario () {
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
		//Agrego espera explicita
		WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(10));
		espera.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id_gender1']")));
		//Comienza resolucion del formulario
		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
		driver.findElement(By.name("customer_firstname")).sendKeys("Kevin");
		driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Aguilera");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		Select dia = new Select(driver.findElement(By.id("days")));
		dia.selectByIndex(24);
		Select mes = new Select(driver.findElement(By.id("months")));
		mes.selectByValue("7");
		Select año = new Select(driver.findElement(By.xpath("//select[@id='years']")));
		año.selectByValue("1994");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.xpath("//input[@id='optin']")).click();
		driver.findElement(By.cssSelector("#submitAccount")).click();

	}
}
