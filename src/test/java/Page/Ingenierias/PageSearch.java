package Page.Ingenierias;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageSearch {
    private final WebDriver driver;

    public PageSearch(WebDriver driver){
        this.driver = driver;
    }
    public void ingresarABuscar(){
        WebElement buscar = driver.findElement(By.id("formCabecera:j_idt29"));
        buscar.click();
    }
    public void buscarPorIngenieria(String ing){
        WebElement campoIng = driver.findElement(By.id("formBuscar:numeroIngenieria"));
        WebElement  buscarButton = driver.findElement(By.id("formBuscar:btnBuscar"));
        campoIng.clear();
        campoIng.sendKeys((ing));
        buscarButton.click();
    }

    public void buscarPorOrden(int orden){
        WebElement campoOrden = driver.findElement(By.id("formBuscar:numeroOrden"));
        WebElement  buscarButton = driver.findElement(By.id("formBuscar:btnBuscar"));
        campoOrden.sendKeys(Integer.toString(orden));
        buscarButton.click();
    }

    public void buscarPorServicio(int servicio){
        WebElement campoServ = driver.findElement(By.id("formBuscar:numeroServicio"));
        WebElement  buscarButton = driver.findElement(By.id("formBuscar:btnBuscar"));
        campoServ.sendKeys(Integer.toString(servicio));
        buscarButton.click();
    }

    public void buscarPorCliente(String cliente){
        WebElement campoRazonSoc = driver.findElement(By.id("formBuscar:razonSocial"));
        WebElement  buscarButton = driver.findElement(By.id("formBuscar:btnBuscar"));
        campoRazonSoc.sendKeys(cliente);
        buscarButton.click();
    }

    public void buscarPorNombreIng(String nombreIng){
        WebElement campoNombreIng = driver.findElement(By.id("formBuscar:nombreIngenieria"));
        WebElement  buscarButton = driver.findElement(By.id("formBuscar:btnBuscar"));
        campoNombreIng.sendKeys(nombreIng);
        buscarButton.click();
    }

    public void buscarPorCreador(String creador){
        WebElement campoUsCreador = driver.findElement(By.id("formBuscar:UsuarioCreacion"));
        WebElement  buscarButton = driver.findElement(By.id("formBuscar:btnBuscar"));
        campoUsCreador.clear();
        campoUsCreador.sendKeys(creador);
        buscarButton.click();
    }

    public void limpiar(){
        WebElement limpiarButton = driver.findElement(By.id("formBuscar:btnLimpiarFiltro"));
        limpiarButton.click();
    }
}
