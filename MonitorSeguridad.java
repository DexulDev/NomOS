import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Club Penguin
 * @version 0.2
 */
public class MonitorSeguridad
{
    public static final int MaxRecursos = 10;

    private Usuario[] usuarios; //es el mismo arreglo que guarda el Kernel
    private Recurso[] recursos;
    private String[][] matriz; //filas = usuarios, columnas = recursos
    private int cantidadRecursos;
    private File archivo;

    public MonitorSeguridad(String ruta, Usuario[] usuarios){
        archivo = new File(ruta);
        this.usuarios = usuarios;
        valoresIniciales();
        if(archivo.exists()){
            try {
                cargar();
            } catch (RuntimeException e) {
                valoresIniciales(); //si el archivo está dañado se regresa a lo inicial
                guardar();
            }
        }else{
            guardar();
        }
    }

    //cuenta los usuarios que hay en el arreglo del Kernel
    private int cantidadUsuarios(){
        int n = 0;
        while(n < usuarios.length && usuarios[n] != null) n++;
        return n;
    }

    private String permisoInicial(String usuario, int recurso){
        String[] admin = {"rwx", "rwx", "rwx"};
        String[] alumno = {"rw-", "r-x", "r--"};
        String[] invitado = {"---", "---", "r--"};
        if(recurso >= 3) return "r--";
        if(usuario.equals("admin")) return admin[recurso];
        if(usuario.equals("alumno")) return alumno[recurso];
        if(usuario.equals("invitado")) return invitado[recurso];
        return "r--"; //cualquier usuario nuevo
    }

    private void valoresIniciales(){
        recursos = new Recurso[MaxRecursos];
        matriz = new String[usuarios.length][MaxRecursos];
        recursos[0] = new Recurso("notas.txt", "Calificaciones: 95 90 100");
        recursos[1] = new Recurso("sistema.exe", "Programa del sistema NomOS");
        recursos[2] = new Recurso("publico.txt", "Bienvenidos a NomOS");
        cantidadRecursos = 3;
        for(int i = 0;i<cantidadUsuarios(); i++){
            for(int j = 0;j<3; j++) matriz[i][j] = permisoInicial(usuarios[i].getNombre(), j);
        }
    }

    //Persistencia
    private boolean guardar(){
        try {
            FileWriter fw = new FileWriter(archivo);
            String linea = "RECURSOS";
            for(int j = 0;j<cantidadRecursos; j++) linea += ";" + recursos[j].getNombre();
            fw.write(linea + "\n");
            for(int i = 0;i<cantidadUsuarios(); i++){
                linea = usuarios[i].getNombre();
                for(int j = 0;j<cantidadRecursos; j++) linea += ";" + matriz[i][j];
                fw.write(linea + "\n");
            }
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void cargar(){
        Recurso[] base = {recursos[0], recursos[1], recursos[2]};
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String[] encabezado = br.readLine().split(";");
            int total = encabezado.length - 1;
            if(total < 0 || total > MaxRecursos) throw new RuntimeException("Archivo de seguridad dañado");

            for(int j = 0;j<total; j++){
                recursos[j] = new Recurso(encabezado[j+1], "");
                for(int k = 0;k<base.length; k++){
                    if(base[k].getNombre().equals(encabezado[j+1])) recursos[j] = base[k];
                }
            }
            for(int j = total;j<MaxRecursos; j++) recursos[j] = null;
            cantidadRecursos = total;

            String[] lineas = new String[usuarios.length];
            int leidas = 0;
            String linea = br.readLine();
            while(linea != null && leidas < lineas.length){
                lineas[leidas] = linea;
                leidas++;
                linea = br.readLine();
            }
            br.close();

            //cada usuario busca su fila por nombre; si no la encuentra, usa los permisos iniciales
            for(int i = 0;i<cantidadUsuarios(); i++){
                for(int j = 0;j<total; j++) matriz[i][j] = permisoInicial(usuarios[i].getNombre(), j);
                for(int k = 0;k<leidas; k++){
                    String[] fila = lineas[k].split(";");
                    if(fila[0].equals(usuarios[i].getNombre())){
                        if(fila.length != total + 1) throw new RuntimeException("Archivo de seguridad dañado");
                        for(int j = 0;j<total; j++){
                            if(fila[j+1].length() != 3) throw new RuntimeException("Archivo de seguridad dañado");
                            matriz[i][j] = fila[j+1];
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Archivo de seguridad dañado");
        }
    }

    //Búsquedas
    private int indiceUsuario(String nombre){
        for(int i = 0;i<cantidadUsuarios(); i++){
            if(usuarios[i].getNombre().equals(nombre)) return i;
        }
        return -1;
    }

    public int indiceRecurso(String nombre){
        for(int j = 0;j<cantidadRecursos; j++){
            if(recursos[j].getNombre().equals(nombre)) return j;
        }
        return -1;
    }

    //Autenticación
    public boolean autenticar(String usuario, String contra){
        int u = indiceUsuario(usuario);
        return u != -1 && usuarios[u].validar(contra);
    }

    public boolean esAdmin(String usuario){
        return usuario != null && usuarios[0] != null && usuario.equals(usuarios[0].getNombre());
    }

    //se llama después de que el Kernel guarda un usuario nuevo en su arreglo
    public void usuarioAgregado(){
        int u = cantidadUsuarios() - 1;
        for(int j = 0;j<cantidadRecursos; j++) matriz[u][j] = permisoInicial(usuarios[u].getNombre(), j);
        guardar();
    }

    //Autorización: devuelve "" si está permitido, o el mensaje de error
    public String verificar(String usuario, String recurso, char permiso){
        int r = indiceRecurso(recurso);
        if(r == -1) return "Archivo inexistente...";
        int u = indiceUsuario(usuario);
        if(u == -1) return "Usuario inexistente...";
        String celda = matriz[u][r];
        if(celda.charAt("rwx".indexOf(permiso)) == permiso) return "";
        return "Permiso denegado: " + usuario + " no tiene '" + permiso + "' sobre " + recurso + " (" + celda + ").";
    }

    //Recursos
    public boolean registrarRecurso(String nombre, String creador){
        if(cantidadRecursos == MaxRecursos || indiceRecurso(nombre) != -1) return false;
        recursos[cantidadRecursos] = new Recurso(nombre, "");
        for(int i = 0;i<cantidadUsuarios(); i++){
            boolean dueño = i == 0 || usuarios[i].getNombre().equals(creador);
            matriz[i][cantidadRecursos] = dueño ? "rwx" : "r--";
        }
        cantidadRecursos++;
        guardar();
        return true;
    }

    public boolean eliminarRecurso(String nombre){
        int r = indiceRecurso(nombre);
        if(r == -1) return false;
        for(int j = r;j<cantidadRecursos-1; j++){
            recursos[j] = recursos[j+1];
            for(int i = 0;i<cantidadUsuarios(); i++) matriz[i][j] = matriz[i][j+1];
        }
        recursos[cantidadRecursos-1] = null;
        for(int i = 0;i<cantidadUsuarios(); i++) matriz[i][cantidadRecursos-1] = null;
        cantidadRecursos--;
        guardar();
        return true;
    }

    public String[] getNombresRecursos(){
        String[] nombres = new String[cantidadRecursos];
        for(int j = 0;j<cantidadRecursos; j++) nombres[j] = recursos[j].getNombre();
        return nombres;
    }

    public String getContenido(String nombre){
        int r = indiceRecurso(nombre);
        return r == -1 ? "" : recursos[r].getContenido();
    }

    //chmod con 3 dígitos o 9 letras: 1.º admin, 2.º alumno, 3.º todos los demás
    public String chmod(String modo, String nombre){
        int r = indiceRecurso(nombre);
        if(r == -1) return "chmod: no existe el archivo " + nombre;
        String[] nuevos = convertir(modo);
        if(nuevos == null) return "chmod: modo inválido (usa 3 dígitos como 750 o 9 letras como rwxr-x---).";
        for(int i = 0;i<cantidadUsuarios(); i++) matriz[i][r] = nuevos[i < 2 ? i : 2];
        if(!guardar()) return "chmod: se aplicó, pero no se pudo guardar en disco...";
        return "Permisos de " + nombre + ": " + nuevos[0] + nuevos[1] + nuevos[2] + " (admin, alumno, demás).";
    }

    //chmod para un solo usuario
    public String chmodUsuario(String usuario, String permiso, String nombre){
        int r = indiceRecurso(nombre);
        if(r == -1) return "chmod: no existe el archivo " + nombre;
        int u = indiceUsuario(usuario);
        if(u == -1) return "chmod: no existe el usuario " + usuario;
        if(permiso.length() != 3 || !valido(permiso)) return "chmod: permiso inválido (usa 3 letras como rw-).";
        matriz[u][r] = permiso;
        if(!guardar()) return "chmod: se aplicó, pero no se pudo guardar en disco...";
        return "Permisos de " + usuario + " sobre " + nombre + ": " + permiso + ".";
    }

    private String[] convertir(String modo){
        String[] nuevos = new String[3];
        if(modo.length() == 3){
            for(int i = 0;i<3; i++){
                char d = modo.charAt(i);
                if(d < '0' || d > '7') return null;
                int n = d - '0';
                String p = "";
                if(n >= 4){ p += "r"; n -= 4; }else p += "-";
                if(n >= 2){ p += "w"; n -= 2; }else p += "-";
                p += (n == 1) ? "x" : "-";
                nuevos[i] = p;
            }
            return nuevos;
        }
        if(modo.length() == 9){
            for(int i = 0;i<3; i++){
                String p = modo.substring(i*3, i*3+3);
                if(!valido(p)) return null;
                nuevos[i] = p;
            }
            return nuevos;
        }
        return null;
    }

    private boolean valido(String p){
        return (p.charAt(0) == 'r' || p.charAt(0) == '-') &&
               (p.charAt(1) == 'w' || p.charAt(1) == '-') &&
               (p.charAt(2) == 'x' || p.charAt(2) == '-');
    }

    private String rellenar(String texto, int largo){
        while(texto.length()<largo) texto += " ";
        return texto;
    }

    public String tabla(){
        String tabla = rellenar("Archivo", 16);
        for(int i = 0;i<cantidadUsuarios(); i++) tabla += rellenar(usuarios[i].getNombre(), 12);
        tabla += "\n";
        for(int j = 0;j<cantidadRecursos; j++){
            tabla += rellenar(recursos[j].getNombre(), 16);
            for(int i = 0;i<cantidadUsuarios(); i++) tabla += rellenar(matriz[i][j], 12);
            tabla += "\n";
        }
        return tabla;
    }
}
