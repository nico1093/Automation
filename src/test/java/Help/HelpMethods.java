package Help;

import Page.loggin.PageLoggin;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HelpMethods {
    private final WebDriver driver;

    public HelpMethods(WebDriver driver){
        this.driver = driver;
    }

    public void esperar(int seconds, By element){
        WebDriverWait ewait = new WebDriverWait(driver, seconds);
        ewait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void esperaAbsoluta(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }
        catch (Exception e){
            System.out.println("Error en el tiempo espera");
        }
    }
    public void inicio(){
        WebElement inicio = driver.findElement(By.id("formCabecera:j_idt28"));
        inicio.click();
    }

    public String getDate(){
        DateFormat dateF = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date date = new Date();
        return dateF.format(date);
    }

    public void captura(String nameFile){
        File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screen, new File(nameFile + "_" + this.getDate() + ".jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValorSelect(By element){
        WebElement listaValores = driver.findElement(element);
        Select select = new Select(listaValores);
        return select.getFirstSelectedOption().getText();
    }

    public void iniciarSeccion(){
        PageLoggin login = new PageLoggin(driver);
        login.loginWID("EXA57039", "Abril2021");
    }

    public boolean isLastElement(int index, List list){
        return list.size() == index;
    }

}
