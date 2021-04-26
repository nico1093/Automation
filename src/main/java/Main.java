import java.util.ArrayList;

public class Main {

    public static void main(String [] args) throws Exception {
        ExcelWork excel = new ExcelWork();
        ArrayList<String> valores = new ArrayList<String>();
        int position = 6;
        valores = excel.prueba("src/test/Files/Ingenierias.xlsx","Ing",position);
        for (int i = 0; i < valores.size(); i++){
            System.out.println(valores.get(i));
        }
    }
}

