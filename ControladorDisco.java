import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class ControladorDisco extends DispositivoES
{
    private File archivo;

    public ControladorDisco(String ruta){
        archivo = new File(ruta);
        abierto = false;
    }

    @Override
    public void abrir(){
        try {
            if(!archivo.exists()) archivo.createNewFile();
            abierto = true;
        } catch (IOException e) {
            System.out.println("No se pudo abrir el disco...");
        }
    }

    @Override
    public void escribir(String datos){
        try {
            FileWriter fw = new FileWriter(archivo, true); //true = agrega al final
            fw.write(datos + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el disco...");
        }
    }

    @Override
    public String leer(){
        String texto = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();
            while(linea != null){
                texto += linea + "\n";
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No se pudo leer el disco...");
        }
        return texto;
    }

    @Override
    public void cerrar(){
        abierto = false;
    }

    @Override
    public String getTipo(){
        return "Entrada/Salida";
    }
}
