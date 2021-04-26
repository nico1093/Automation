package Test.Relevamiento.Administracion;

import Help.HelpMethods;
import Page.Relevamiento.TemplatesAndAtributes.PageAtributteRelevamiento;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAtributtes {
    private static WebDriver driver;
    private static PageAtributteRelevamiento pageA;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        pageA = new PageAtributteRelevamiento(driver);
        HelpMethods help = new HelpMethods(driver);
        help.iniciarSeccion();
    }

    @BeforeEach
    public void initCase(){
        pageA.irAAtributtos();
        pageA.iniciarCreacion();
    }

    @Test
    public void caseCrearAtributoAlfanumerico(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By mensaje = By.id("growlInfo_container");
        By botonAceptar = By.id("formAtributo:j_idt112");
        wait.until(ExpectedConditions.visibilityOfElementLocated(botonAceptar)); //Espera a que se habilitre el pop up
        pageA.completarCampos("ejemplo alfanumerico Automation 3","automation", "","ALFANUMERICO");
        pageA.confirmarAtributo();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje)); //Espera a que se habilite el mensaje
        Assert.assertEquals("Se guardo correctamente el atributo",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseCrearAtributoNumerico(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By mensaje = By.id("growlInfo_container");
        By botonAceptar = By.id("formAtributo:j_idt112");
        wait.until(ExpectedConditions.visibilityOfElementLocated(botonAceptar)); //Espera a que se habilitre el pop up
        pageA.completarCampos("ejemplo numerico Automation 3","automation", "","NUMERICO");
        pageA.confirmarAtributo();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje)); //Espera a que se habilite el mensaje
        Assert.assertEquals("Se guardo correctamente el atributo",driver.findElement(mensaje).getText());
    }

    @Test
    public void caseCrearAtributoValorDeLista(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By mensaje = By.id("growlInfo_container");
        By botonAceptar = By.id("formAtributo:j_idt112");
        wait.until(ExpectedConditions.visibilityOfElementLocated(botonAceptar)); //Espera a que se habilitre el pop up
        pageA.completarCampos("ejemplo lista Automation 3","automation", "","LISTA");
        pageA.confirmarAtributo();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensaje)); //Espera a que se habilite el mensaje
        Assert.assertEquals("Se guardo correctamente el atributo",driver.findElement(mensaje).getText());
    }

    @AfterEach
    public void finishCase(){
        By inicio = By.id("formCabecera:j_idt27");
        WebDriverWait wait = new WebDriverWait(driver,3); // Instancia de espera de 3 seg.
        HelpMethods helper = new HelpMethods(driver);
        helper.inicio(); //Se direcciona al Index del programa.
        wait.until(ExpectedConditions.visibilityOfElementLocated(inicio)); //Espera a que se visualice el boton inicio dinamico.
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}
