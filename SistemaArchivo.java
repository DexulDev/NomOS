/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class SistemaArchivo
{
    public static final int MaxArchivos = 10;

    private Archivo[] directorio;
    private DiscoVirtual disco;

    public SistemaArchivo(){
        directorio = new Archivo[MaxArchivos];
        disco = new DiscoVirtual();
    }

    private int buscar(String nombre){
        for(int i = 0;i<MaxArchivos; i++){
            if(directorio[i] != null && directorio[i].getNombre().equals(nombre)) return i;
        }
        return -1;
    }

    public boolean existe(String nombre){
        return buscar(nombre) != -1;
    }

    private void liberar(Archivo archivo){
        int[] ids = archivo.getBloquesAsignados();
        for(int i = 0;i<archivo.getCantidadBloques(); i++){
            disco.getBloque(ids[i]).limpiar();
        }
        archivo.limpiarBloques();
    }

    public String crear(String nombre){
        if(nombre.trim().length() == 0) return "El nombre no puede estar vacío...";
        if(buscar(nombre) != -1) return "Ya existe un archivo llamado " + nombre + ".";
        for(int i = 0;i<MaxArchivos; i++){
            if(directorio[i] == null){
                directorio[i] = new Archivo(nombre);
                return "Archivo " + nombre + " creado.";
            }
        }
        return "El directorio está lleno (máximo " + MaxArchivos + " archivos).";
    }

    //sobrescribe el contenido anterior del archivo
    public String escribir(String nombre, String texto){
        int i = buscar(nombre);
        if(i == -1) return "Archivo inexistente...";
        Archivo archivo = directorio[i];

        int necesarios = (texto.length() + DiscoVirtual.TamBloque - 1) / DiscoVirtual.TamBloque;
        if(necesarios > Archivo.MaxBloqueporAchivo){
            return "El texto excede el límite de " + (Archivo.MaxBloqueporAchivo * DiscoVirtual.TamBloque) + " bytes por archivo.";
        }
        //los bloques que ya tenía el archivo se van a liberar, por eso cuentan como disponibles
        if(necesarios > disco.bloquesLibres() + archivo.getCantidadBloques()){
            return "No hay bloques libres suficientes en el disco.";
        }

        liberar(archivo);
        for(int b = 0;b<necesarios; b++){
            int libre = disco.buscarLibre();
            int inicio = b * DiscoVirtual.TamBloque;
            int fin = Math.min(inicio + DiscoVirtual.TamBloque, texto.length());
            disco.getBloque(libre).escribir(texto.substring(inicio, fin));
            archivo.agregarBloque(libre);
        }
        archivo.setTamano(texto.length());
        return "Se escribieron " + texto.length() + " bytes en " + nombre + " (" + necesarios + " bloques).";
    }

    public String leer(String nombre){
        int i = buscar(nombre);
        if(i == -1) return "Archivo inexistente...";
        Archivo archivo = directorio[i];
        if(archivo.getCantidadBloques() == 0) return "(archivo vacío)";
        String texto = "";
        int[] ids = archivo.getBloquesAsignados();
        for(int j = 0;j<archivo.getCantidadBloques(); j++){
            texto += disco.getBloque(ids[j]).leer();
        }
        return texto;
    }

    public String eliminar(String nombre){
        int i = buscar(nombre);
        if(i == -1) return "Archivo inexistente...";
        liberar(directorio[i]);
        directorio[i] = null;
        return "Archivo " + nombre + " eliminado.";
    }

    public String listar(){
        String tabla = "Nombre          Bytes  Bloques\n";
        int total = 0;
        for(int i = 0;i<MaxArchivos; i++){
            if(directorio[i] != null){
                Archivo a = directorio[i];
                String nombre = a.getNombre();
                while(nombre.length()<15) nombre += " ";
                String tamano = "" + a.getTamano();
                while(tamano.length()<6) tamano += " ";
                String bloques = "";
                int[] ids = a.getBloquesAsignados();
                for(int j = 0;j<a.getCantidadBloques(); j++) bloques += "[" + ids[j] + "]";
                tabla += nombre + " " + tamano + " " + bloques + "\n";
                total++;
            }
        }
        return tabla + "\nArchivos: " + total + "/" + MaxArchivos;
    }

    public String mapa(){
        return disco.mapa();
    }
}
