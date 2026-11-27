/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Usuario
{
    private String nombre;
    private String contra;

    public Usuario(String nombre, String contra){
        this.nombre = nombre;
        this.contra = contra;
    }

    public String getNombre(){
        return nombre;
    }

    public String getContra(){
        return contra;
    }

    public boolean validar(String contra){
        return this.contra.equals(contra);
    }
}
