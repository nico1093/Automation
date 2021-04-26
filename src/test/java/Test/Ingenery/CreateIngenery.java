package Test.Ingenery;

import Help.HelpMethods;
import Page.Ingenierias.PageCreate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class CreateIngenery {
    private static WebDriver driver;
    private static PageCreate ingenieria;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        ingenieria = new PageCreate(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://latin.claro.amx:8010/WebIngenieria_ADIC-895/pages/login.xhtml");
        help.iniciarSeccion();
    }

   @BeforeEach
   public void initCase(){
        ingenieria.irCreacion();
        ingenieria.limpiar();
   }
    @Test
    public void crearIC(){
        /*
         * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
         */
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Thread.sleep(50000);
        ingenieria.completarNombre("Prueba Automatizada");
        ingenieria.completarProyecto(60241342,3059083);
        ingenieria.buscarProyecto();
        //Se realiza una espera para que se visualicen los checkboxes.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'ui-radiobutton-icon ui-icon ui-icon-blank ui-c']")));
        ingenieria.seleccionarProyecto();
        ingenieria.crearProyecto();
        //Se realiza una espera para que se visualice la pagina de creacion.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formIngDet:numeroIng")));
        ingenieria.completarPlanilla("prueba", "ALTA","prueba@claro.com.ar");
        ingenieria.crearIngenieria();
        Assert.assertEquals(driver.findElement(By.id("growlError_container")).getText(),"");
    }

    @Test
    public void crearSinOrden(){
        /*
         * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
         */
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ingenieria.completarNombre("Prueba Automatizada");
        ingenieria.crearProyecto();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlError_container")));
        Assert.assertEquals("Debe seleccionar una Ingeniería", driver.findElement(By.id("growlError_container")).getText());
    }

    @Test
    public void buscarProyectoConOrdenInvalida(){
        /*
         * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
         */
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ingenieria.completarProyecto(0,0);
        ingenieria.buscarProyecto();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlError_container")));
        Assert.assertEquals("No existen datos en CRM para los datos ingresados", driver.findElement(By.id("growlError_container")).getText());
        //helper.captura("buscarProyectoConOrdenInvalida");
        //driver.findElement(By.xpath("//a[text()='Salir']")).click();
    }

    @Test
    public void buscarProyectoSinCompletarOrden(){
        /*
         * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
         */
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ingenieria.buscarProyecto();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlError_container")));
        Assert.assertEquals("Debe ingresar al menos un campo de busqueda.", driver.findElement(By.id("growlError_container")).getText());
        //helper.captura("buscarProyectoSinCompletarOrden");
    }

    @Test
    public void crearSinCompletarNombre(){
        /*
         * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
         */
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ingenieria.completarProyecto(60241342, 3059083);
        ingenieria.crearProyecto();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growlError_container")));
        Assert.assertEquals("Debe completar el nombre",driver.findElement(By.id("growlError_container")).getText());
        //helper.captura("crearSinCompletarNombre");
    }

    @AfterEach
    public void finishCase(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        HelpMethods help = new HelpMethods(driver);
        By elementWait = By.id("formCabecera:j_idt27");
        help.inicio();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}
