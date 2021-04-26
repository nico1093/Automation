package Page.Administracion;

import Help.HelpMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.DocFlavor;

public class PageAtributtes {
    private static WebDriver driver;
    //@FindBy(id = "formBuscar:tipofiltro")
    private By tipo = By.id("formBuscar:tipofiltro");
    //@FindBy(id = "formBuscar:estadofiltro")
    private By estado = By.id("formBuscar:estadofiltro");
    //@FindBy(id = "formBuscar:descripsionAgregar")
    private By descripcion = By.id("formBuscar:descripsionAgregar");
    //@FindBy(id = "formBuscar:labelAgregar")
    private By label = By.id("formBuscar:labelAgregar");
    //@FindBy(id = "formBuscar:origenfiltro")
    private By origen = By.id("formBuscar:origenfiltro");
    //@FindBy(id = "formBuscar:aliasfiltro")
    private By alias = By.id("formBuscar:aliasfiltro");
    //@FindBy(id = "formBuscar:tipoDato")
    private By tipoDato = By.id("formBuscar:tipoDato");
    //@FindBy(id = "formBuscar:j_idt129")
    private By msjAyuda = By.id("formBuscar:j_idt129");

    public PageAtributtes(WebDriver d){
        driver = d;
    }

    public void goToAtributtes(){
        WebElement admin = driver.findElement(By.xpath("//*[@id='formCabecera']/div/div/nav/div[2]/ul[2]/li[4]/a"));
        WebElement atr = driver.findElement(By.id("formCabecera:j_idt41"));
        admin.click();
        atr.click();
    }

    public void goCreate(){
        WebElement createButton = driver.findElement(By.id("formBuscar:btnCrear"));
        createButton.click();
    }

    public void goEdit(){
        /* Se debe existir solo un atributo seleccionado. */
        WebElement editButton = driver.findElement(By.id("formBuscar:btnEditar"));
        editButton.click();
    }

    public void goActiveAndDesactive(){
        /* Se debe existir solo un atributo seleccionado. */
        WebElement activeButton = driver.findElement(By.id("formBuscar:btnToggleHabilitar"));
        activeButton.click();
    }

    private void completarCampoSelect(String value, WebElement webElement){
        Select select = new Select(webElement);
        select.selectByVisibleText(value);
    }

    public void completarAtributoManual(String desc, String lab, String valueEstado){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement campoTipo = driver.findElement(tipo);
        WebElement campoState = driver.findElement(estado);
        Select selectEstado = new Select(campoState);
        selectEstado.selectByVisibleText(valueEstado);// Cambia el select del campo estado (Activo o Inactivo)
        Select selectTipo = new Select(campoTipo);
        selectTipo.selectByVisibleText("Manual"); // Cambia el select a manual.
        wait.until(ExpectedConditions.visibilityOfElementLocated(tipoDato));
        Select selectTipoDato = new Select(driver.findElement(tipoDato));
        selectTipoDato.selectByVisibleText("LISTA");// Le pone valor lista al input Tipo de Dato.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formBuscar:origenesListafiltro"))); // Espera hasta que se habilita el campo origen
        driver.findElement(descripcion).sendKeys(desc);
        driver.findElement(label).sendKeys(lab);
        driver.findElement(msjAyuda).sendKeys(" ");
    }

    private void completeAtributtesText(String d, String l, String m){
        driver.findElement(descripcion).sendKeys(d);
        driver.findElement(label).sendKeys(l);
        driver.findElement(msjAyuda).sendKeys(m);
    }

    public void completarAtributoAutomatico(String origin, String alias, String desc, String label, String state) {
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement campoState = driver.findElement(estado);
        WebElement campoOrigen = driver.findElement(origen);
        WebElement campoTipo = driver.findElement(tipo);
        Select selectTipo = new Select(campoTipo);
        selectTipo.selectByVisibleText("Automatico"); // Cambia el select a manual.
        Select selectState = new Select(campoState);
        selectState.selectByVisibleText(state);
        Select selectOrigen = new Select(campoOrigen);
        selectOrigen.selectByVisibleText(origin);
        completeAtributtesText(desc, label, "");
        //wait.until(ExpectedConditions.(By.id("formBuscar:origenfiltro")));
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        WebElement campoAlias = driver.findElement(By.id("formBuscar:aliasfiltro"));//Se soluciona aplicando
        Select selectAlias = new Select(campoAlias);
        selectAlias.selectByValue(alias);
    }

    public void confirmAtributte(){
        WebElement buttonConfirm = driver.findElement(By.id("formBuscar:j_idt132"));
        buttonConfirm.click();
    }

    public void cancelAtributte(){
        WebElement buttonCancel = driver.findElement(By.id("formBuscar:j_idt133"));
        buttonCancel.click();
    }

    public void confirmActivity(){
        WebElement buttonConfirm = driver.findElement(By.id("formBuscar:j_idt136"));
        buttonConfirm.click();
    }

    public void cancelActivity() {
        WebElement buttonCancel = driver.findElement(By.id("formBuscar:j_idt137"));
        buttonCancel.click();
    }
}
