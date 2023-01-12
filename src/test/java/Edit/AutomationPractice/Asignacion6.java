package Edit.AutomationPractice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;

public class Asignacion6 {
	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	String ruta = "..\\AutomationPractice\\Evidencias\\";
	String documento = "Evidencias.docx";
			
	@BeforeSuite
	public void abrirPagina() throws InvalidFormatException, IOException, InterruptedException {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		CapturaEvidencia.escribirTituloEnDocumento(ruta + documento, "Documento de Evidencias", 16);
	}

	@Test(description = "CP01 - Inicio Sesión y Generar Orden de Compra", dataProvider = "Datos Usuarios")
	public void login(String usuario, String contraseña, String nombre, String apellido, String codigoPostal)
			throws InvalidFormatException, IOException, InterruptedException {
		WebElement txtUser = driver.findElement(By.id("user-name"));
		txtUser.sendKeys(usuario);
		WebElement txtPass = driver.findElement(By.id("password"));
		txtPass.sendKeys(contraseña);
		WebElement txtLogin = driver.findElement(By.id("login-button"));
		txtLogin.click();
		
		try {
		 if(driver.findElement(By.id("user-name")).isDisplayed()){
			 CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "Error_LoginFallido");
			 driver.navigate().refresh();
		  }else {
			  CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "00_Inventario");
		  }
		}catch(Exception e) {
			 CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "00_Inventario");
		}
		
		driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
		driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();

		// Captura Evidencia en docx
		CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "01_Cart");

		// Formulario del Checkout
		driver.findElement(By.name("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys(nombre);
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(apellido);
		driver.findElement(By.cssSelector("#postal-code")).sendKeys(codigoPostal);

		// Captura Evidencia en docx
		CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "02_CheckoutPersonal");
		driver.findElement(By.name("continue")).click();

		// Captura Evidencia en docx
		CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "03_checkoutOverview");
		driver.findElement(By.xpath("//button[@id='finish']")).click();

		// Captura Evidencia en docx
		CapturaEvidencia.capturarPantallaEnDocumento(driver, ruta + "img.jpg", ruta + documento, "04_checkoutComplete");
		
		driver.findElement(By.id("react-burger-menu-btn")).click();
		WebDriverWait espera2 = new WebDriverWait(driver, Duration.ofSeconds(5));
		espera2.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
		driver.findElement(By.id("logout_sidebar_link")).click();
	}

	@DataProvider(name = "Datos Usuarios")
	public Object[][] leerDatosLoginExcel() throws Exception {
		return DatosExcel.leerExcel("..\\AutomationPractice\\Data_Excel\\Datos_Usuarios.xlsx", "Data_Users");
	}

	@AfterSuite
	public void cerrarNavegador() {
		 driver.close();
	}
}
