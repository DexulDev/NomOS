/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Recurso
{
    private String nombre;
    private String contenido;

    public Recurso(String nombre, String contenido){
        this.nombre = nombre;
        this.contenido = contenido;
    }

    public String getNombre(){
        return nombre;
    }

    public String getContenido(){
        return contenido;
    }
}
