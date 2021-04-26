package Test.Ingenery;

import Help.HelpMethods;
import Page.Ingenierias.PageIngenery;

import Page.Ingenierias.PageSearch;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CloneIngenery {
    private static WebDriver driver;
    private static PageSearch search;
    private static PageIngenery clone;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        search = new PageSearch(driver);
        clone = new PageIngenery(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://latin.claro.amx:8010/WebIngenieria_ADIC-895/pages/login.xhtml");
        help.iniciarSeccion();
    }

    @BeforeEach
    public void initCase(){
        search.ingresarABuscar();
    }

    @Test
    public void clonarIngenieriaProyectoSAP(){
        /*
        * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
        */
        WebDriverWait wait = new WebDriverWait(driver, 60);
        By elementWait = By.id("formCrear:nomIng");
        search.buscarPorIngenieria("2010");
        clone.selectIngenery(1);
        clone.irAClonar();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
        clone.datosDeClonacion("Prueba Automatizada clon",60243282,3060640);
        clone.clonar();
        Assert.assertNotNull(driver.findElement(By.id("formIngDet:numeroIng")));
    }

    @Test
    public void clonarIngenieriaProyectoVTV(){
        /*
         * PRECONDICION: El usuario debe estar con un perfil administrador o ingeniero de Proyecto.
         */
        WebDriverWait wait = new WebDriverWait(driver, 60);
        By elementWait = By.id("formCrear:nomIng");
        search.buscarPorIngenieria("1516");
        clone.selectIngenery(1);
        clone.irAClonar();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
        clone.datosDeClonacion("Prueba Automatizada clon",17520842,2941550);
        clone.clonar();
        Assert.assertNotNull(driver.findElement(By.id("formIngDet:numeroIng")));
    }

    @AfterEach
    public void finishCase(){
        HelpMethods helper = new HelpMethods(driver);
        helper.inicio();
        helper.esperar(3,By.id("formCabecera:j_idt27"));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}
