package Test.Administracion;

import Help.HelpMethods;
import Page.Administracion.PageTemplate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrearTemplates {
    private static WebDriver driver;
    private static PageTemplate pageAdmin;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        pageAdmin = new PageTemplate(driver);
        driver.get("http://latin.claro.amx:8010/WebIngenieria/pages/buscarIngenieria.xhtml");
        help.iniciarSeccion();
    }

    @BeforeEach
    public void initCase(){
        By waitElement = By.id("formBuscar:filterTabla"); // Espera hasta que se encuentre el campo buscar.
        WebDriverWait wait = new WebDriverWait(driver,360);
        pageAdmin.goToTemplate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
    }

    @Test
    public void caseCreateTemplate(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        By elementWait = By.id("formTemplate:comboTecnologiaA");
        pageAdmin.goToCreateTemplate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
        pageAdmin.completeNameTemplate("prueba Automation");
        pageAdmin.completeCRMTemplate("SAP");
        pageAdmin.completeTecnologyATemplate("Cobre");
        pageAdmin.completeAccesATemplate("DWDM");
        pageAdmin.completeTecnologyBTemplate("Cobre");
        pageAdmin.completeAccesBTemplate("SDH");
        pageAdmin.uploadImage();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("formTemplate:j_idt91"))));// Espera hasta que se visualice la imagen en la pantalla
        pageAdmin.addCriterio();
        pageAdmin.saveTemplate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlInfo_container"))); // Espera hasta que se genere el mensaje correspondiente de guuardado exito o no.
        Assert.assertEquals(driver.findElement(By.id("growlInfo_container")).getText(), "El template se ha guardado exitosamente");
    }

    @AfterEach
    public void finishCase(){
        HelpMethods help = new HelpMethods(driver);
        WebDriverWait wait = new WebDriverWait(driver,15);
        By inicioWait = By.id("formCabecera:j_idt27");
        help.inicio();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inicioWait));
    }
    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}
