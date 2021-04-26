package Page.Relevamiento.TemplatesAndAtributes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAtributteRelevamiento {
    private static WebDriver driver;


    public PageAtributteRelevamiento(WebDriver d){
        driver = d;

    }

    public void irAAtributtos(){
        Actions builder = new Actions(driver);
        WebElement relevamiento = driver.findElement(By.xpath("//a[contains(string(), 'Relevamiento Consultoría y Diseño')]"));
        WebElement admin = driver.findElement(By.xpath("//li[@class = 'dropdown-submenu']/a"));
        WebElement atributos = driver.findElement(By.id("formCabecera:j_idt53"));
        relevamiento.click();
        builder.moveToElement(admin).perform();
        atributos.click();
    }

    public void iniciarCreacion(){
        WebElement crearButton = driver.findElement(By.id("formBuscar:btnCrear"));
        crearButton.click();
    }

    public void completarCampos(String desc, String label, String msj, String tipo){
        WebDriverWait wait = new WebDriverWait(driver,5);
        By lista = By.id("formAtributo:j_idt106");
        By tipoDeDato = By.id("formAtributo:j_idt94");
        By categoria = By.id("formAtributo:j_idt100");
        completeDescripcion(desc);
        completeLabel(label);
        completeMensajeAyuda(msj);
        completarDespegableCampo_(tipoDeDato, tipo);
        if (this.getValorSelect(tipoDeDato).equals("LISTA")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(lista));
            completarDespegableCampo_(categoria,"Detalles Sitio Instalación");
            wait.until(ExpectedConditions.textToBePresentInElementLocated(lista, "Altura Torre"));
            completarDespegableCampo_(lista,"Altura Torre");
        }
    }

    public void confirmarAtributo(){
        WebElement aceptarButton = driver.findElement(By.id("formAtributo:j_idt112"));
        aceptarButton.click();
    }

    private void completeDescripcion(String desc){
        WebElement campoDesc = driver.findElement(By.id("formAtributo:j_idt90"));
        campoDesc.clear();
        campoDesc.sendKeys(desc);
    }
    private void completeLabel(String label){
        WebElement campoLabel = driver.findElement(By.id("formAtributo:j_idt92"));
        campoLabel.clear();
        campoLabel.sendKeys(label);
    }

     private void completeMensajeAyuda(String msj){
        WebElement campoMsjAyuda = driver.findElement(By.id("formAtributo:j_idt110"));
        campoMsjAyuda.clear();
        campoMsjAyuda.sendKeys(msj);
     }

     public String getValorSelect(By element){
         WebElement listaValores = driver.findElement(element);
         Select select = new Select(listaValores);
         return select.getFirstSelectedOption().getText();
     }

     private void completarDespegableCampo_(By elemento, String valor){
        /*
            Para funcionar este method se debe haber seleccionado anteriormente la categoria. Caso
            contrario fallara.
        */
         WebElement listaValores = driver.findElement(elemento);
         Select select = new Select(listaValores);
         select.selectByVisibleText(valor);
     }

}
