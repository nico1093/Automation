package Test.Relevamiento.Administracion;

import Help.HelpMethods;
import Page.Relevamiento.PageAccesRelevamiento;
import Page.Relevamiento.TemplatesAndAtributes.PageTemplateRelevamiento;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Template {
    private static WebDriver driver;
    private static PageTemplateRelevamiento template;
    private static HelpMethods help;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        template = new PageTemplateRelevamiento(driver);
        help = new HelpMethods(driver);
        driver.get("http://latin.claro.amx:8010/WebIngenieria_ADIC-895/pages/login.xhtml");
        driver.manage().window().maximize();
        help.iniciarSeccion();
    }

    @BeforeEach
    public void initCase(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        PageAccesRelevamiento acces = new PageAccesRelevamiento(driver);
        acces.goToRelevamiento();
        acces.goToAdministration();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formCabecera:j_idt57")));
        acces.goToTemplate();
    }

    @Test
    public void caseCreateTemplate(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        By mensajeEsperado = By.id("growlInfo_container");
        template.goToAddTemplate();
        template.addGruop("ejemplo automation");
        //Espera a que se encuentre el titulo de los templates, este caso Datos de Templates.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Datos de Template')]")));
        template.defineNameTemplate("Prueba de automatizacion del Template 2");
        template.defineProduct("Internet Dedicado");
        template.goToAdd(0);
        //Espera a que se encuentre el titulo de agregar atributos.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(), 'Atributos')]")));
        template.addAtributte("telefono");
        template.addAtributte("mensaje de ayuda");
        template.addAtributte("Nombre y Apellido");
        template.addAtributte("Observaciones");
        template.addAtributte("usa mas de cien macs");
        template.confirmAtributes();
        template.finishAddAtributte();
        //Espera a que se encuentre el titulo de los templates, este caso Datos de Templates.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Datos de Template')]")));
        template.saveTemplateWithActive();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeEsperado));
        Assert.assertEquals("Se guardo con exito el template", driver.findElement(mensajeEsperado).getText());
    }

    @Test
    public void caseEditTemplate(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        By mensajeEsperado = By.id("growlInfo_container");
        template.goToEditTemplate();
        template.editTemplate("Prueba de automatizacion del Template 2");
        //template.goToEditTemplate();
        template.defineNameTemplate("Prueba de automatizacion del Template 2 - Editado");
        template.saveTemplateWithActive();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeEsperado));
        Assert.assertEquals("Se guardo con exito el template", driver.findElement(mensajeEsperado).getText());
    }

    @Test
    public void caseDeleteTemplate(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        By mensajeEsperado = By.id("growlInfo_container");
        template.deleteTemplate("Prueba de automatizacion del Template 2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeEsperado));
        Assert.assertEquals("Se elimino el template correctamente", driver.findElement(mensajeEsperado).getText());
    }

    @AfterEach
    public void finishCase(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        By inicioButton = By.id("formCabecera:j_idt28");
        driver.findElement(inicioButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formCabecera:j_idt27")));//Espera hasta que se vuelva al inicio, el boton inicio es dinamico. (index tiene un valor y en las demas secciones otro)
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }


}
