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

public class MensajeModificacionSearch {
    private static WebDriver driver;
    private static PageAccesRelevamiento msj;
    //Mensaje esperado
    private By mensaje = By.id("growlError_container");
    //Botones de busqueda
    private By buttonSearchEnlace = By.id("formBusquedaPorEnlaceIdRelevamiento:btnBuscarEnlace");
    private By buttonSearchId = By.id("formBusquedaPorEnlaceIdRelevamiento:btnBuscarIdRelevamiento");
    private By buttonSearchBase = By.id("formBusquedaPorEnlaceIdRelevamiento:btnBuscarBaseInstalada");
    //Campos de ingreso de datos de proytectos SAP
    private By inputEnlace = By.id("formBusquedaPorEnlaceIdRelevamiento:filtroEnlace");
    private By inputId = By.id("formBusquedaPorEnlaceIdRelevamiento:filtroIdRelevamiento");
    private By inputBase = By.id("formBusquedaPorEnlaceIdRelevamiento:filtroBaseInstalada");

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        msj = new PageAccesRelevamiento(driver);
        driver.get("http://latin.claro.amx:8010/WebIngenieria_ADIC-895/pages/login.xhtml");
        driver.manage().window().maximize();
        help.iniciarSeccion();
    }

    @BeforeEach
    public void initCase(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.goToRelevamiento();
        msj.goToModificacion();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSearchId));
    }

    @Test
    public void caseEnlaceInvalido(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.completeProyectSAP(444444444,inputEnlace,buttonSearchEnlace);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("El enlace ingesado es invalido o no existe",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseEnlaceVacio(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.completeProyectSAP(null,inputEnlace,buttonSearchEnlace);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("Debe ingresar un Enlace",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseEnlaceValido(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        By element = By.xpath("//*[@id=\"formBusquedaPorEnlaceIdRelevamiento:tablaProductos_data\"]/tr[1]/td[1]/div/div[2]/span");
        msj.completeProyectSAP(3062684,inputEnlace,buttonSearchEnlace);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(driver.findElement(element).isDisplayed());
    }

    @Test
    public void caseIdInvalido(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.completeProyectSAP(000,inputId,buttonSearchId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("El id relevamiento ingresado es invalido o no existe",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseIdVacio(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.completeProyectSAP(null,inputId,buttonSearchId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("Debe ingresar un id de relevamiento",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseIdValido(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        By element = By.xpath("//*[@id=\"formBusquedaPorEnlaceIdRelevamiento:tablaProductos_data\"]/tr[1]/td[1]/div/div[2]/span");
        msj.completeProyectSAP(230,inputId,buttonSearchId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(driver.findElement(element).isDisplayed());
    }

    @Test
    public void caseBaseInstaladaInvalido(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.completeProyectSAP(000,inputBase,buttonSearchBase);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("La base instalada ingresada es invalida o no existe",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseBaseInstaladaVacio(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        msj.completeProyectSAP(null,inputBase,buttonSearchBase);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje));
        Assert.assertEquals("Debe ingresar una base instalada",driver.findElement(mensaje).getText());
    }

  /*  @Test
    public void caseBaseInstaladaValido(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        By element = By.xpath("//*[@id=\"formBusquedaPorEnlaceIdRelevamiento:tablaProductos_data\"]/tr[1]/td[1]/div/div[2]/span");
        msj.completeProyectSAP(230,inputId,buttonSearchId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(driver.findElement(element).isDisplayed());
    }*/

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
