
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Kernel
{
    protected NomOS sistema;
    private boolean sesion;
    private String usuario;
    private String contra;
    //TODO: hacer una pantalla que renderize el logo con un timer y ya gg

    public Kernel(){
        sistema = new NomOS();
        sesion = false;
        usuario = null;
        contra = null;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getContra(){
        return ("********"); //Solo por poner un getter, porque el main no debería de saber la contraseña
    }

    public void setContra(String contra){
        this.contra = contra;
    }

    public String getSistemaNombre(){
        return sistema.getNombre();
    }

    public boolean getSesion(){
        return sesion;
    }


    public String menu(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||        -BIENVENIDO-         ||\n" +
        "||                             ||\n" +
        "||  (1) Cargar OS              ||\n" +
        "||  (2) BIOS                   ||\n" +
        "||  (3) Acerca de              ||\n" +
        "||  (s) Salir                  ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    public String login(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||          -Sesion-           ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||" + opcion(sesion) + "||\n" +
        "||  (d) Regresar               ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }
      
    private String opcion(boolean sesion){
        if(!sesion) return "  (1) Crear usuario          ";
        return "  (1) Iniciar sesión         ";
    }

    public String inicioSesion(){
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||           -Entrar-          ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa la contraseña:     ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n" 
        );
    }

    public boolean validar(String contra){
        return this.contra != null && this.contra.equals(contra);
    }

    public String crearUsuario(){
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||       -Crear usuario-       ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa el nombre:         ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n" 
        );
    }

    public String crearContra(){
        sesion = true;
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||       -Crear usuario-       ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa la contraseña:     ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n" 
        );
    }

    public String bios(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||           -BIOS-            ||\n" +
        "||                             ||\n" +
        "||  Fabricante: NomOS Inc.     ||\n" +
        "||  Modelo: NomOS Virtual PC   ||\n" +
        "||  Version BIOS: 1.0.0        ||\n" +
        "||  Fecha: 06/09/2026          ||\n" +
        "||                             ||\n" +
        "||  CPU: NomCore i9 Virtual    ||\n" +
        "||  Nucleos: 8                ||\n" +
        "||  RAM: 16384 MB              ||\n" +
        "||  Almacenamiento: 512 GB SSD ||\n" +
        "||                             ||\n" +
        "||  Boot: Habilitado           ||\n" +
        "||  Estado: OK                 ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    public String about(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||         -Acerca de-         ||\n" +
        "||                             ||\n" +
        "||  Materia:                   ||\n" +
        "||  Sistemas Operativos        ||\n" +
        "||                             ||\n" +
        "||  Unidad I                   ||\n" +
        "||                             ||\n" +
        "||  Equipo: Club Penguin       ||\n" +
        "||                             ||\n" +
        "||  Maestra:                   ||\n" +
        "||  Reyna Jarquin Valverde     ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    public String bienvenido(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||  Inicio de sesion exitoso!  ||\n" +
        "||                             ||\n" +
        "||  Ingrese cualquier tecla... ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

}
