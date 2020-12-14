package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class PageLoggin {
    private static WebDriver driver;

    public PageLoggin(WebDriver driver) {
        this.driver = driver;
    }

    public void driverDataLoggin(String user, String pass){
        driver.get("http://latin.claro.amx:8010/WebIngenieria/pages/login.xhtml");
        driver.findElement(By.id("j_idt9:user")).clear();
        driver.findElement(By.id("j_idt9:user")).sendKeys(user);
        driver.findElement(By.id("j_idt9:cont")).clear();
        driver.findElement(By.id("j_idt9:cont")).sendKeys(pass);
        driver.findElement(By.id("j_idt9:btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
    }
}
