
/**Anyer Valverde Jarquin */

// Nota:tanto el número de bloques en disco como 
//el número máximo de archivos en el directorio raíz tienen un límite definido

import java.io.Serializable;
//import java.util.Scanner;

public class Bloque implements Serializable 
{
    // Bloque: es la unidad física mínima
    private int id;
    private String datos;
    private boolean ocupado;

    public Bloque(int id) {
        this.id = id;
        this.datos = "";
        this.ocupado = false;
    }

    public void escribir(String fragmento) {
        this.datos = fragmento;
        this.ocupado = true;
    }

    public String leer() {
        return this.datos;
    }

    public void limpiar() {
        this.datos = "";
        this.ocupado = false;
    }

    public boolean siOcupado() {
        return ocupado;
    }

    public int getId() {
        return id;
    }
}

    

