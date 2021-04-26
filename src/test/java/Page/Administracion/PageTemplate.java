package Page.Administracion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class PageTemplate {
    private WebDriver driver;
    //@FindBy(id = "formTemplate:nombreTemp")
    private By nombre = By.id("formTemplate:nombreTemp");
    //@FindBy(id = "formTemplate:filtroCrm")
    private By crm = By.id("formTemplate:filtroCrm");
    //@FindBy(id = "formTemplate:comboTecnologiaA")
    private By tecnologiaA = By.id("formTemplate:comboTecnologiaA");
    //@FindBy(id = "formTemplate:comboAccesoA")
    private By accesoA = By.id("formTemplate:comboAccesoA");
    //@FindBy(id = "formTemplate:comboTecnologiaB")
    private By tecnologiaB = By.id("formTemplate:comboTecnologiaB");
    //@FindBy(id = "formTemplate:comboAccesoB")
    private By accesoB = By.id("formTemplate:comboAccesoB");

    public PageTemplate(WebDriver driver){
        this.driver = driver;
    }

    public void goToTemplate(){
        WebElement administracion = driver.findElement(By.xpath("//*[@id='formCabecera']/div/div/nav/div[2]/ul[2]/li[4]/a"));
        WebElement template = driver.findElement(By.id("formCabecera:j_idt43"));
        administracion.click();
        template.click();
    }

    public void goToCreateTemplate(){
        By create = By.id("formBuscar:btnCrear");
        WebElement createButton = driver.findElement(create);
        createButton.click();
    }

    public void completeNameTemplate(String value){
        WebElement campoNombre = driver.findElement(nombre);
        campoNombre.clear();
        campoNombre.sendKeys(value);
    }

    public void completeCRMTemplate(String value){
        Select selectCRM = new Select(driver.findElement(crm));
        selectCRM.selectByVisibleText(value);
    }

    public void completeTecnologyATemplate(String value){
        Select selectTecA = new Select(driver.findElement(tecnologiaA));
        selectTecA.selectByVisibleText(value);
    }

    public void completeAccesATemplate(String value){
        Select selectAccA = new Select(driver.findElement(accesoA));
        selectAccA.selectByVisibleText(value);
    }

    public void completeTecnologyBTemplate(String value){
        Select selectTecB = new Select(driver.findElement(tecnologiaB));
        selectTecB.selectByVisibleText(value);
    }

    public void completeAccesBTemplate(String value){
        Select selectAccB = new Select(driver.findElement(accesoB));
        selectAccB.selectByVisibleText(value);
    }

    public void uploadImage(){
        //Sube una imagen, para que funcione se debe acceder a la creacion de un template.
        File file = new File("C:\\Users\\nicolas.martin\\Desktop\\Capasitaciones\\ejemplo para Selenium.jpg");
        String searchFile = file.getAbsolutePath();
        WebElement subir = driver.findElement(By.id("formTemplate:j_idt88_input")); //Busca el elemento subir imagen.
        subir.sendKeys(searchFile);
    }

    public void addCriterio(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement agregar = driver.findElement(By.id("formTemplate:btnCrearCriterio"));
        WebElement confirmCriterio = driver.findElement(By.id("formTemplate:j_idt246"));
        agregar.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formTemplate:j_idt246")));
        completeCriterio("0800","ACCESO PRUEBA","Ethernet","Anillado");
        confirmCriterio.click();
    }

    public void saveTemplate(){
        WebElement guardar = driver.findElement(By.id("formTemplate:btnGuardarTemplate"));
        guardar.click();
    }

    private void completeCriterio(String producto, String acceso, String tecno, String tipo){
        Select selectProd = new Select(driver.findElement(By.id("formTemplate:filtroProducto")));
        selectProd.selectByVisibleText(producto);
        Select selectAcc = new Select(driver.findElement(By.id("formTemplate:filtroAcceso")));
        selectAcc.selectByVisibleText(acceso);
        Select selectTecno = new Select(driver.findElement(By.id("formTemplate:filtroTecnologia")));
        selectTecno.selectByVisibleText(tecno);
        Select selectTipo = new Select(driver.findElement(By.id("formTemplate:filtroTipo")));
        selectTipo.selectByVisibleText(tipo);
    }
}
