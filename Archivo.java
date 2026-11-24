
/**
Anyer valverder Jarquin
 */

import java.io.Serializable;

public class Archivo implements Serializable 
// Archivo: Inodo / Metadatos
{
    public static final int MaxBloqueporAchivo = 10; // Límite 

    private String nombre;
    private int tamano;
    private int[] bloquesAsignados;
    private int cantidadBloques;

    public Archivo(String nombre) {
        this.nombre = nombre;
        this.tamano = 0;
        this.bloquesAsignados = new int[MaxBloqueporAchivo];
        this.cantidadBloques = 0;
        limpiarBloques();
    }

    public boolean agregarBloque(int idBloque) {
        if (cantidadBloques >= MaxBloqueporAchivo) {
            return false;
        }
        bloquesAsignados[cantidadBloques] = idBloque;
        cantidadBloques++;
        return true;
    }

    public void limpiarBloques() {
        for (int i = 0; i < MaxBloqueporAchivo; i++) {
            bloquesAsignados[i] = -1; // -1 indica espacio sin asignar
        }
        cantidadBloques = 0;
        tamano = 0;
    }

    public String getNombre() {
        return nombre; 
    }
    public int getTamano() { 
        return tamano; 
    }
    public void setTamano(int tamano) { 
        this.tamano = tamano; 
    }
    public int[] getBloquesAsignados() {
        return bloquesAsignados;
    }
    public int getCantidadBloques() { 
        return cantidadBloques; 
    }

    
}
