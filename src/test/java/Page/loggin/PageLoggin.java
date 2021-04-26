package Page.loggin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class PageLoggin {
    private final WebDriver driver;
    private final By user;
    private final By password;
    private final By loginButton;


    public PageLoggin(WebDriver driver) {
        this.driver = driver;
        user = By.id("j_idt9:user");
        password = By.id("j_idt9:cont");
        loginButton = By.id("j_idt9:btnLogin");
    }

    public void loginWID(String user, String pass){
        driver.get("http://latin.claro.amx:8010/WebIngenieria/pages/login.xhtml");
        driver.findElement(this.user).clear();
        driver.findElement(this.user).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
    }

    public void salirWID() {
        /*
        Se debe encontrar loggeado en la aplicacion.
        */
        WebElement config = driver.findElement(By.xpath("//a[@class = 'dropdown-toggle red']/span"));
                // By.xpath("/html/body/div[1]/form/div/div/nav/div[2]/ul[1]/li[3]/a/span");
        WebElement logOut = driver.findElement(By.xpath("//li/a[contains(string(),'Salir')]"));
                // By.xpath("/html/body/div[1]/form/div/div/nav/div[2]/ul[1]/li[3]/ul/li[7]/a");
        config.click();
        logOut.click();
    }
}
