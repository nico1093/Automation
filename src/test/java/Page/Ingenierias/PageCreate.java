package Page.Ingenierias;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PageCreate {
    private final WebDriver driver;

    public PageCreate(WebDriver driver){
        this.driver = driver;
    }

    public void irCreacion(){
        WebElement selectCreacion = driver.findElement(By.xpath("//a[contains(string(),'Administración Ingeniería')]"));
        WebElement ingresarCreacion = driver.findElement(By.name("formCabecera:j_idt31"));
        selectCreacion.click();
        ingresarCreacion.click();
    }

    public void completarNombre(String name){
        WebElement campoNombre = driver.findElement(By.id("formCrear:nomIng"));
        campoNombre.clear();
        campoNombre.sendKeys(name);
    }

    public void completarProyecto(int orden, int servicio){
        WebElement campoOrden = driver.findElement(By.id("formCrear:ordenIng"));
        WebElement campoServicio = driver.findElement(By.id("formCrear:nroIng"));
        campoOrden.clear();
        campoServicio.clear();
        campoOrden.sendKeys(Integer.toString(orden));
        campoServicio.sendKeys(Integer.toString(servicio));
    }

    public void buscarProyecto(){
        WebElement bottonBuscar = driver.findElement(By.id("formCrear:btnBuscar"));
        bottonBuscar.click();
    }

    public void seleccionarProyecto(){
        /*El parametro index no debe ser mayor a la cantidad de elementos que posee la lista checkboxes*/
        WebElement checkboxIC = driver.findElement(By.xpath("//span[@class = 'ui-radiobutton-icon ui-icon ui-icon-blank ui-c']"));
        checkboxIC.click();
    }

    public void crearProyecto(){
        WebElement crear = driver.findElement(By.id("formCrear:btnCrear"));
        crear.click();
    }

    public void completarPlanilla(String nombre, String accion, String mail){
        WebElement campoNombre = driver.findElement(By.id("formIngDet:cont1"));
        WebElement campoFecha = driver.findElement(By.id("formIngDet:button_input"));
        WebElement campoAccion = driver.findElement(By.name("formIngDet:j_idt130"));
        WebElement campoMail = driver.findElement(By.id("formIngDet:email1"));
        Select select = new Select(campoAccion);
        campoNombre.sendKeys(nombre);
        campoFecha.sendKeys(getDateNextDay(new Date()));//creo la instancia date al momento de
        select.selectByVisibleText(accion);
        campoMail.sendKeys(mail);
    }

    public void crearIngenieria(){
        WebElement botonGuardar = driver.findElement(By.id("formIngDet:btnGuardarCambios"));
        botonGuardar.click();
    }

    private String getDateNextDay(Date date){
        DateFormat dateF = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return dateF.format(calendar.getTime());
    }

    public void limpiar(){
        WebElement campoNombre = driver.findElement(By.id("formCrear:nomIng"));
        WebElement campoOrden = driver.findElement(By.id("formCrear:ordenIng"));
        WebElement campoServicio = driver.findElement(By.id("formCrear:nroIng"));
        WebElement campDNI = driver.findElement(By.id("formCrear:numDocIng"));
        campoNombre.clear();
        campoOrden.clear();
        campoServicio.clear();
        campDNI.clear();
    }

}
