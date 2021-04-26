package Test.Login;

import Page.loggin.PageLoggin;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LogginWID {
    private static WebDriver driver;
    private static PageLoggin log;

    @BeforeAll //Se aplica antes de correr los metodos con anotation @Test
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        log = new PageLoggin(driver);
        driver.manage().window().maximize();
        driver.get("http://latin.claro.amx:8010/WebIngenieria_ADIC-895/pages/login.xhtml");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void LogginCorrectCase(){
        log.loginWID("exa57039","Febrero2021");
        Assert.assertEquals("Se tiene que loggear correctamente a la WID.","Web Ingeniería", driver.findElement(By.xpath("/html/body/div[1]/form/div/div/nav/div[1]/div")).getText());
        log.salirWID();
    }

    @Test
    public void LogginIncorrectCase(){
        /* Verifica que se habilite correctamente mensaje de error al ingresar un usser y pass incorrectos. */
        log.loginWID("asdasd","asdasd");
        Assert.assertEquals("Se ingresan datos erroneos y se tiene que validar con una alerta.","El usuario no tiene autorizado el acceso a la aplicacion", driver.findElement(By.id("growlError_container")).getText());
    }

    @Test
    public void LogginNullUserCase(){
        /* Verifica si se habilita correctamente el mensaje de error al no ingresar un user. */
        log.loginWID("","Dici2020");
        Assert.assertEquals("Se ingresan datos erroneos y se tiene que validar con una alerta.","Debe completar el Usuario", driver.findElement(By.id("growlError_container")).getText());
    }

    @Test
    public void LogginNullPassCase(){
        /* Verifica si se habilita correctamente el mensaje de error al no ingresar una pass. */
        log.loginWID("exa57039","");
        Assert.assertEquals("Se ingresan datos erroneos y se tiene que validar con una alerta.","Debe completar la contraseña", driver.findElement(By.id("growlError_container")).getText());
    }

    @Test
    public void LogginNullFullCase(){
        /* Verifica si se habilita el mensaje de error al no ingresar ningun campo en el loggin. */
        log.loginWID("","");
        Assert.assertEquals("Se ingresan datos erroneos y se tiene que validar con una alerta.","Credenciales Inválidas", driver.findElement(By.id("growlError_container")).getText());
    }

    @AfterAll //Se aplica luego de terminar todos los metodos con anotacion @Test
    public static void tearDown(){
        /* Finaliza el driver del test y se devuelven los resultados de los test */
        driver.close();
    }
}
