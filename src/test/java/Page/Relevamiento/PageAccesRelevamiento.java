package Page.Relevamiento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class PageAccesRelevamiento {
    public WebDriver driver;

    public PageAccesRelevamiento(WebDriver driver){
        this.driver = driver;
    }

    public void goToRelevamiento(){
        By relevamientoButton = By.xpath("//a[contains(text(),'Relevamiento')]");
        driver.findElement(relevamientoButton).click();
    }
    public void goToCarga(){
        By cargaButton = By.id("formCabecera:j_idt61");
        driver.findElement(cargaButton).click();
    }

    public void goToModificacion(){
        By modificacionButton = By.id("formCabecera:j_idt63");
        driver.findElement(modificacionButton).click();
    }

    public void goToAdministration(){
        Actions build = new Actions(driver);
        By admin = By.xpath("//*[@class='btn navbar-btn botonTransparente itenmenu botonCabecera dropdownOption']");//Elemento By que posee el elemento despegable Administracion dentro de la seccion Relevamiento Consultoria y Diseño.
        build.moveToElement(driver.findElement(admin)).perform();//Pocisiona el corsor en el medio del elemeneto.
    }

    public void goToTemplate(){
        By template = By.id("formCabecera:j_idt57");
        driver.findElement(template).click();
    }

    public void completeProyectSAP(Integer proyecto, By input, By buttonSearch){
        if(proyecto != null) {
            driver.findElement(input).sendKeys(Integer.toString(proyecto));
        }else{
            driver.findElement(input).sendKeys("");
        }
        driver.findElement(buttonSearch).click();
    }
}
