import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelWork {
    public ExcelWork(){

    }

    public ArrayList<String> getListCellValues(String ruta, String sheetName,int column) throws Exception{
        File file = new File(ruta);
        FileInputStream stream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(stream);
        XSSFSheet sheet = wb.getSheet(sheetName);
        ArrayList<String> datosExcel = new ArrayList<String>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        String valueActual;
        int cont = 0;
        Cell cellValue;
        while(!sheet.getRow(cont).getCell(column).equals(null)){
            cellValue = sheet.getRow(cont).getCell(column);
            cont++;
            //valueActual = cellValue.getStringCellValue();//cellValue.getCellType() != CellType.STRING ? String.valueOf(cellValue) : cellValue.getStringCellValue(); //Lo obtiene directamente.
            if(!rowIterator.hasNext()){
                System.out.println("Proceso Terminado");
                break;
            }
            else{
                valueActual = cellValue.getStringCellValue();
                //Este else saca el punto decimal, ya que se registran como estructura Double.
                String cellTemp = valueActual;
                char c;
                valueActual = "";
                for(int j = 0; j < cellTemp.length(); j++){
                    c = cellTemp.charAt(j);
                    if(c == '.'){
                        break;
                    }else{
                        valueActual += c;
                    }
                }
            }

            datosExcel.add(valueActual);
            //System.out.println(datosExcel.get(cont));
        }
        return datosExcel;
    }

    public String elementCell(Cell c, Row r){
        if(c.getCellType().equals(CellType.STRING)){
            return c.getStringCellValue();
        }else {
            return String.valueOf(c);
        }
    }

    public ArrayList<String> getListCellValues2(String ruta, String sheetName,int column) throws Exception{
        File file = new File(ruta);
        FileInputStream stream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(stream);
        XSSFSheet sheet = wb.getSheet(sheetName);
        ArrayList<String> datosExcel = new ArrayList<String>();
        String valueCellActual;
        Row rowValue;
        Cell cellValue;
        for(int i = 0;i<sheet.getPhysicalNumberOfRows();i++){
            rowValue = sheet.getRow(i);
            cellValue = rowValue.getCell(column);
            //valueCellActual = pagina1.getRow(i).getCell(0).toString();
            //Investigar el porque funciona asi.
            //valueCellActual = cellValue.getStringCellValue();
            valueCellActual = cellValue.getCellType() != CellType.STRING ? String.valueOf(cellValue) : cellValue.getStringCellValue(); //Lo obtiene directamente.
            //Se declara un iterador, el cual trabaja sobre las filas
            Iterator<Row> rowIterator = sheet.rowIterator();
            //Se declara la variable que almacena la informacion de la fila.
            Row row;

            if(!rowIterator.hasNext()){// Condicion true si hay datos en la siguente celda de la fila.
                break;
            } else {
                //Este else saca el punto decimal, ya que se registran como estructura Double.
                String cellTemp = valueCellActual;
                char c;
                valueCellActual = "";
                for(int j = 0; j < cellTemp.length(); j++){
                    c = cellTemp.charAt(j);
                    if(c == '.'){
                        break;
                    }else{
                        valueCellActual += c;
                    }
                }
            }
            datosExcel.add(valueCellActual);
        }
        return datosExcel;
    }

    public ArrayList<String> tercerIntento(String ruta, String sheetName) throws IOException {
        File file = new File(ruta);
        FileInputStream stream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(stream);
        XSSFSheet sheet = wb.getSheet(sheetName);
        ArrayList<String> datosExcel = new ArrayList<String>();
        return datosExcel;
    }

    public ArrayList<String> prueba(String ruta,String sheetName, int column){
        File file = new File(ruta);
        ArrayList<String> datosExcel = new ArrayList<String>();
        try {
            FileInputStream stream = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(stream);
            XSSFSheet sheet = wb.getSheet(sheetName);
            Cell cell;
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                cell = sheet.getRow(i).getCell(column);
                if (cell.getCellType() == CellType.STRING){
                    datosExcel.add(cell.getStringCellValue());
                }else{
                    System.out.println("RUPTURA DE AUTOMATIZACION");
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
        return datosExcel;
    }

}
