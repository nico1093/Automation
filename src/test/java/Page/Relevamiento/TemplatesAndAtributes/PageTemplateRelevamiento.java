package Page.Relevamiento.TemplatesAndAtributes;

import Help.HelpMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTemplateRelevamiento {
    private WebDriver driver;
    private By addTemplate = By.id("formBuscar:btnCrear");
    private By editTemplate = By.id("formBuscar:btnEditar");
    private By seeTemplate = By.id("formBuscar:btnVer");
    private By activeTemplate = By.id("formBuscar:btnToggle");
    private By deleteButton = By.id("formBuscar:btnEliminar");
    private By cloneTemplate = By.id("formBuscar:btnClonar");
    private By saveButton = By.id("formTemplate:btnGuardarTemplate");
    private HelpMethods help;

    public PageTemplateRelevamiento(WebDriver driver){
        this.driver = driver;
        help = new HelpMethods(driver);
    }

    public void goToAddTemplate(){
        By addGroup = By.id("formTemplate:btnCrearGrupo");
        driver.findElement(addTemplate).click();
        driver.findElement(addGroup).click();
    }

    public void addGruop(String name){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        By addGroup = By.id("formTemplate:btnCrearGrupo");
        By inputName = By.name("formCrearGrupo:j_idt279");
        By confirm = By.id("formCrearGrupo:j_idt281");
        //help.esperaAbsoluta(2);
        driver.findElement(addGroup).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputName));
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(confirm).click();
    }

    public void goToAdd(int positionGroup){
        By add = By.id("formTemplate:repeat:" + (positionGroup) + ":j_idt107");
        driver.findElement(add).click();
    }

    public void defineNameTemplate(String name){
        By inputName = By.id("formTemplate:nombreTemp");
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
    }

    public void defineProduct(String product){
        WebElement productInput = driver.findElement(By.id("formTemplate:filtroProductos"));
        Select select = new Select(productInput);
        select.selectByVisibleText(product);
    }

    public void addAtributte(String atributte){
        /*PARAMETROS:
        *   positionGroup: Se debe pasarse la posicion del grupo del template que se esta trabajando.
        *   atributte: Se le tiene que pasar el nombre de un atributo existen dentro de la WID.
        */
        By filtroAtr = By.id("formModalAgregarAtributosTemplateRelevamiento:filterTablaAtributos");
        By selectAtr = By.xpath("//*[@id=\"formModalAgregarAtributosTemplateRelevamiento:tablaAtributos_data\"]/tr/td[1]/div/div[2]");
        driver.findElement(filtroAtr).clear();
        driver.findElement(filtroAtr).sendKeys(atributte);
        help.esperaAbsoluta(10); //Espera con clase Thread.
        driver.findElement(selectAtr).click();
        help.esperaAbsoluta(5);
    }

    public void confirmAtributes(){
        By confirmAtributte = By.id("formModalAgregarAtributosTemplateRelevamiento:j_idt162");
        driver.findElement(confirmAtributte).click();
    }

    public void finishAddAtributte(){
        By cancelAtributte = By.id("formModalAgregarAtributosTemplateRelevamiento:j_idt163");
        driver.findElement(cancelAtributte).click();
    }

    public void goToEditTemplate(){
        driver.findElement(editTemplate).click();
    }

    public void goToSeeTemplte(){
        driver.findElement(seeTemplate).click();
    }

    public void activeTemplate(){
        driver.findElement(activeTemplate).click();
    }

    public void deleteTemplate(String nameTemplate) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        By seacrhTemplate = By.id("formBuscar:filterTabla");//Input donde se filtran los datos del template por su nombre.
        By selectTemplate = By.xpath("//*[@id='formBuscar:tablaTemplates_data']/tr[1]/td[1]"); //Elemento que posee el template a seleccionar. (El primero de la lista)
        By agreeDelete = By.id("j_idt102");//Elemento del aceptar elemininacion al generarse el pop up.
        driver.findElement(seacrhTemplate).sendKeys(nameTemplate);
        help.esperaAbsoluta(2);
        driver.findElement(selectTemplate).click();
        driver.findElement(deleteButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(agreeDelete));//Espera hasta que se visualice el boton aceptar del pop up.
        driver.findElement(agreeDelete).click();
    }

    public void goToCloneTemplate(){
        driver.findElement(cloneTemplate).click();
    }

    public void saveTemplateWithActive(){
        /*PRECONDICION: Debe haber activo un template del mismo producto vigente.*/
        WebDriverWait wait = new WebDriverWait(driver, 2);
        By confirmSave = By.id("j_idt266");
        driver.findElement(saveButton).click();
        //Espera a que se habra el pop up emergente analizando el titulo de tal.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(), 'Estado Template')]")));
        driver.findElement(confirmSave).click();
    }

    public void editTemplate(String nameTemplate){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        By seacrhTemplate = By.id("formBuscar:filterTabla");//Input donde se filtran los datos del template por su nombre.
        By selectTemplate = By.xpath("//*[@id='formBuscar:tablaTemplates_data']/tr[1]/td[1]"); //Elemento que posee el template a seleccionar. (El primero de la lista)
        driver.findElement(seacrhTemplate).sendKeys(nameTemplate);
        help.esperaAbsoluta(2);
        driver.findElement(selectTemplate).click();
        this.goToEditTemplate();
    }

}
