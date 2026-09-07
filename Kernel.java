
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Kernel
{
    protected NomOS sistema;
    public Kernel(){
        sistema = new NomOS();
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
        "||  (1) Login                  ||\n" +
        "||  (2) Registrar              ||\n" +
        "||  (c) Regresar               ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }
}
