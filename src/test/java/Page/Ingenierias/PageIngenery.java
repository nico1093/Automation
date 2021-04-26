package Page.Ingenierias;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageIngenery {
    private final WebDriver driver;

    @FindBy(id = "formCrear:nomIng")
    WebElement campoName;

    @FindBy(id = "formCrear:ordenIng")
    WebElement campoOrden;

    @FindBy(id = "formCrear:nroIng")
    WebElement campoServ;

    public PageIngenery(WebDriver driver){
        this.driver = driver;
    }

    public void selectIngenery(int position){
        WebElement selectIng = driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/div/div/div/div[2]/div[2]/table/tbody/tr["+ position +"]/td[1]/div/div[2]/span"));
        selectIng.click();
    }

    public void irAClonar(){
        WebElement clonarBotton = driver.findElement(By.id("formBuscar:btnClonarIng"));
        clonarBotton.click();
    }

    public void datosDeClonacion(String name, int orden, int servicio){
        campoName.clear();
        campoOrden.clear();
        campoServ.clear();
        campoName.sendKeys(name);
        campoOrden.sendKeys(Integer.toString(orden));
        campoServ.sendKeys(Integer.toString(servicio));
    }

    public void clonar(){
        WebElement cloneButton = driver.findElement(By.id("formCrear:btnCrear"));
        cloneButton.click();
    }
}
