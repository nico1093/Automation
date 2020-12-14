package Test;

import Page.PageLoggin;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogginWIDAutomation {
    private static WebDriver driver;

    @BeforeAll //Se aplica antes de correr los metodos con anotation @Test
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void LogginCorrectCase(){
        PageLoggin log = new PageLoggin(driver);
        log.driverDataLoggin("exa57039","Dici2020");
        Assert.assertEquals("Web Ingeniería", driver.findElement(By.xpath("/html/body/div[1]/form/div/div/nav/div[1]/div")).getText());
    }

    @Test
    public void LogginIncorrectCase(){
        /* Verifica que se habilite correctamente mensaje de error al ingresar un usser y pass incorrectos. */
        PageLoggin log = new PageLoggin(driver);
        log.driverDataLoggin("asdasd","asdasd");
        Assert.assertEquals("El usuario no tiene autorizado el acceso a la aplicacion", driver.findElement(By.id("growlError_container")).getText());

    }

    @Test
    public void LogginNullUserCase(){
        /* Verifica si se habilita correctamente el mensaje de error al no ingresar un user. */
        PageLoggin log = new PageLoggin(driver);
        log.driverDataLoggin("","Dici2020");
        Assert.assertEquals("Debe completar el Usuario", driver.findElement(By.id("growlError_container")).getText());
    }

    @Test
    public void LogginNullPassCase(){
        /* Verifica si se habilita correctamente el mensaje de error al no ingresar una pass. */
        PageLoggin log = new PageLoggin(driver);
        log.driverDataLoggin("exa57039","");
        Assert.assertEquals("Debe completar la contraseña", driver.findElement(By.id("growlError_container")).getText());
    }

    @Test
    public void LogginNullFullCase(){
        /* Verifica si se habilita el mensaje de error al no ingresar ningun campo en el loggin. */
        PageLoggin log = new PageLoggin(driver);
        log.driverDataLoggin("","");
        Assert.assertEquals("Credenciales Inválidas", driver.findElement(By.id("growlError_container")).getText());
    }

    @AfterAll //Se aplica luego de terminar todos los metodos con anotacion @Test
    public static void tearDown(){
        /* Finaliza el driver del test y se devuelven los resultados de los test */
        driver.close();
    }
}
