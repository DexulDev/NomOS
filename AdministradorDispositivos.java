/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class AdministradorDispositivos
{
    private DispositivoES[] dispositivos;
    private String[] nombres;
    private int cantidad;

    public AdministradorDispositivos(int capacidad){
        dispositivos = new DispositivoES[capacidad];
        nombres = new String[capacidad];
        cantidad = 0;
    }

    public boolean registrar(String nombre, DispositivoES dispositivo){
        if(cantidad == dispositivos.length || buscar(nombre) != -1) return false;
        nombres[cantidad] = nombre;
        dispositivos[cantidad] = dispositivo;
        cantidad++;
        return true;
    }

    private int buscar(String nombre){
        for(int i = 0;i<cantidad; i++){
            if(nombres[i].equals(nombre)) return i;
        }
        return -1;
    }

    public String abrir(String nombre){
        int i = buscar(nombre);
        if(i == -1) return "Dispositivo inexistente...";
        if(dispositivos[i].estaAbierto()) return nombre + " ya está abierto.";
        dispositivos[i].abrir();
        return nombre + " abierto.";
    }

    public String escribir(String nombre, String datos){
        int i = buscar(nombre);
        if(i == -1) return "Dispositivo inexistente...";
        if(!dispositivos[i].estaAbierto()) return nombre + " no está abierto.";
        try {
            dispositivos[i].escribir(datos);
        } catch (UnsupportedOperationException e) {
            return nombre + ": " + e.getMessage();
        }
        return "Escritura en " + nombre + " realizada.";
    }

    public String leer(String nombre){
        int i = buscar(nombre);
        if(i == -1) return "Dispositivo inexistente...";
        if(!dispositivos[i].estaAbierto()) return nombre + " no está abierto.";
        try {
            return dispositivos[i].leer();
        } catch (UnsupportedOperationException e) {
            return nombre + ": " + e.getMessage();
        }
    }

    public String cerrar(String nombre){
        int i = buscar(nombre);
        if(i == -1) return "Dispositivo inexistente...";
        if(!dispositivos[i].estaAbierto()) return nombre + " ya está cerrado.";
        dispositivos[i].cerrar();
        return nombre + " cerrado.";
    }

    public String tabla(){
        String tabla = "Nombre            Tipo            Estado\n";
        for(int i = 0;i<cantidad; i++){
            String nombre = nombres[i];
            while(nombre.length()<17) nombre += " ";
            String tipo = dispositivos[i].getTipo();
            while(tipo.length()<15) tipo += " ";
            tabla += nombre + " " + tipo + " " + (dispositivos[i].estaAbierto() ? "Abierto" : "Cerrado") + "\n";
        }
        return tabla;
    }
}
