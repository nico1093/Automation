package Test.Relevamiento.Planilla.Message;

import Help.HelpMethods;
import Page.Relevamiento.PageAccesRelevamiento;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MensajeCargaSearch {
    private static WebDriver driver;
    private static PageAccesRelevamiento msj;
    private By mensaje = By.id("growlError_container");
    private By inputProyect = By.id("formCargaRelevamiento:filtroProyecto");
    private By searchButton = By.id("formCargaRelevamiento:btnBuscarProyecto");
    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        msj = new PageAccesRelevamiento(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://latin.claro.amx:8010/WebIngenieria_ADIC-895/pages/login.xhtml");
        help.iniciarSeccion();
    }

    @BeforeEach
    public void initCase(){
        msj.goToRelevamiento();
        msj.goToCarga();
    }

    @Test
    public void caseProyectoInvalido(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputProyect));
        msj.completeProyectSAP(12345678,inputProyect,searchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("El numero de proyecto es invalido",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseProyectoVacio(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputProyect));
        msj.completeProyectSAP(null,inputProyect,searchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("Ingrese un n�mero de proyecto",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseProyectoValido(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        By elementWait = By.xpath("//td[contains(text(),'0000000010')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputProyect));
        msj.completeProyectSAP(50006132,inputProyect,searchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
        Assert.assertEquals(driver.findElement(elementWait).getText(), "0000000010");
    }

    @AfterEach
    public void finishCase(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        HelpMethods helper = new HelpMethods(driver);
        By elementWait = By.id("formCabecera:j_idt27");
        helper.inicio();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}
