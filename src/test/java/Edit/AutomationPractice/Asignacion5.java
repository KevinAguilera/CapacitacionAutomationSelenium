package Edit.AutomationPractice;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Asignacion5 {

	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	File screen;
	String imagenes = "..\\AutomationPractice\\Evidencias\\";

	@BeforeSuite
	public void abrirPagina() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test(description = "CP01 - Inicio Sesión", priority = 1)
	public void inicioSesionChrome() {
		WebElement txtUser = driver.findElement(By.id("user-name"));
		txtUser.sendKeys("standard_user");
		WebElement txtPass = driver.findElement(By.id("password"));
		txtPass.sendKeys("secret_sauce");
		WebElement txtLogin = driver.findElement(By.id("login-button"));
		txtLogin.click();
	}

	@Test(description = "CP02 - Generar Orden de Compra", priority = 2)
	public void confirmarOrdenCompra() throws IOException {
		// Imagen de Evidencia
		screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(imagenes + "01_inventory.jpg"));

		driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
		driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();

		// Imagen de Evidencia
		screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(imagenes + "02_cart.jpg"));
		
		
		//Compruebo estar situado en el cart
		String urlActual = driver.getCurrentUrl();
		String urlEsperada = "https://www.saucedemo.com/cart.html";
		Assert.assertEquals(urlActual, urlEsperada);
		
		// Formulario del Checkout
		driver.findElement(By.name("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Kevin");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Aguilera");
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("1846");

		// Imagen de Evidencia
		screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(imagenes + "03_checkoutPersonal.jpg"));

		driver.findElement(By.name("continue")).click();

		// Imagen de Evidencia
		screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(imagenes + "04_checkoutOverview.jpg"));

		driver.findElement(By.xpath("//button[@id='finish']")).click();

		// Imagen de Evidencia
		screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(imagenes + "05_checkoutComplete.jpg"));
	
		// Compruebo si se encuentra presente el texto THANK YOU FOR YOUR ORDER
		String texto = driver.findElement(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")).getText();
		Assert.assertTrue(texto.contains("THANK YOU FOR YOUR ORDER"));
	}

	@AfterSuite
	public void cerrarNavegador() {
		 driver.close();
	}
}
