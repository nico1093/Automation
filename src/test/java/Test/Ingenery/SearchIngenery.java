package Test.Ingenery;

import Help.ExcelWork;
import Help.HelpMethods;
import Page.Ingenierias.PageSearch;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class SearchIngenery {
    private static WebDriver driver;
    private static PageSearch search;
    private static ExcelWork excel;

    @BeforeAll
    public static void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        HelpMethods help = new HelpMethods(driver);
        search = new PageSearch(driver);
        excel = new ExcelWork();
        driver.get("http://latin.claro.amx:8010/WebIngenieria/pages/buscarIngenieria.xhtml");
        help.iniciarSeccion();
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }
    }

    @BeforeEach
    public void initCase(){
        search.ingresarABuscar();
        search.limpiar();
    }

    @Test
    public void caseIngenieria() throws Exception {
        By elementSearch = By.id("formBuscar:tablaIngenierias_data");        WebDriverWait wait = new WebDriverWait(driver, 5);
        search.buscarPorIngenieria("2010");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSearch));
        Assert.assertNotNull(driver.findElement(elementSearch));
    }

    @Test
    public void caseOrden(){
        By elementSearch = By.id("formBuscar:tablaIngenierias_data");        WebDriverWait wait = new WebDriverWait(driver, 5);
        search.buscarPorOrden(60244430);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSearch));
        Assert.assertNotNull(driver.findElement(elementSearch));
    }

    @Test
    public void caseServicio(){
        By elementSearch = By.id("formBuscar:tablaIngenierias_data");        WebDriverWait wait = new WebDriverWait(driver, 5);
        search.buscarPorServicio(3062348);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSearch));
        Assert.assertNotNull(driver.findElement(elementSearch));
    }

    @Test
    public void caseRazonSocial(){
        By elementSearch = By.id("formBuscar:tablaIngenierias_data");        WebDriverWait wait = new WebDriverWait(driver, 5);
        search.buscarPorCliente("Cliente IPOne");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSearch));
        Assert.assertNotNull(driver.findElement(elementSearch));
    }

    @Test
    public void caseNombreIng(){
        By elementSearch = By.id("formBuscar:tablaIngenierias_data");        WebDriverWait wait = new WebDriverWait(driver, 5);
        search.buscarPorNombreIng("prueba");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSearch));
        Assert.assertNotNull(driver.findElement(elementSearch));
    }

    @Test
    public void  caseCreador() {
        By elementSearch = By.id("formBuscar:tablaIngenierias_data");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        search.buscarPorCreador("Nicolás Martin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementSearch));
        Assert.assertNotNull(driver.findElement(elementSearch));
    }


    @AfterEach
    public void finishCase(){
        HelpMethods helper = new HelpMethods(driver);
        helper.inicio();
        helper.esperar(5,By.id("formCabecera:j_idt27"));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}
