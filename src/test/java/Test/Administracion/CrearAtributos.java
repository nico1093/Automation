package Test.Administracion;

import Help.HelpMethods;
import Page.Administracion.PageAtributtes;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CrearAtributos {
    private static WebDriver driver;
    private static PageAtributtes pageAdmin;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        pageAdmin = new PageAtributtes(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://latin.claro.amx:8010/WebIngenieria/pages/buscarIngenieria.xhtml");
        help.iniciarSeccion();
    }

   @BeforeEach
    public void initCase(){
        By waitElement = By.id("formBuscar:filterTabla");
        WebDriverWait wait = new WebDriverWait(driver,360);
        pageAdmin.goToAtributtes();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("")),""));
    }

    @Test
    public void caseCreateManualActiveAtributte(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By waitElement = By.id("formBuscar:tipofiltro");
        pageAdmin.goCreate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
        pageAdmin.completarAtributoManual("Manual Activo 3", "activo 3", "Activo");
        pageAdmin.confirmAtributte();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlInfo_container")));
        Assert.assertEquals("El atributo ha sido guardado exitosamente", driver.findElement(By.id("growlInfo_container")).getText());
    }

    @Test
    public void caseCreateManualInactiveAtributte(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By waitElement = By.id("formBuscar:tipofiltro");
        pageAdmin.goCreate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
        pageAdmin.completarAtributoManual("Manual Inactivo 3", "inactivo 3", "Inactivo");
        pageAdmin.confirmAtributte();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlInfo_container")));
        Assert.assertEquals("El atributo ha sido guardado exitosamente", driver.findElement(By.id("growlInfo_container")).getText());
    }

    @Test
    public void caseCreateAutomaticActiveAttibutte(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By waitElement = By.id("formBuscar:tipofiltro");
        pageAdmin.goCreate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
        pageAdmin.completarAtributoAutomatico("SAP","ADMIN_LANADM--CR_ADICIONALES","Automatico Activo 3","prueba auto 3", "Activo");
        pageAdmin.confirmAtributte();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlInfo_container")));
        Assert.assertEquals("El atributo ha sido guardado exitosamente", driver.findElement(By.id("growlInfo_container")).getText());
    }

    @Test
    public void caseCreateAutomaticInctiveAttibutte(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By waitElement = By.id("formBuscar:tipofiltro");
        pageAdmin.goCreate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
        pageAdmin.completarAtributoAutomatico("SAP","ADMIN_LANADM--CR_ADICIONALES","Automatico Inactivo 3","prueba inac 3", "Inactivo");
        pageAdmin.confirmAtributte();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlInfo_container")));
        Assert.assertEquals("El atributo ha sido guardado exitosamente", driver.findElement(By.id("growlInfo_container")).getText());
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
        driver.quit();
    }
}
