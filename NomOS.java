
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class NomOS
{
    private String nombre;
    private String version;
    
    public NomOS(String nombre, String version){
        this.nombre = "NomOS";
        this.version = "CP.1.1";
    }
    
    public void iniciar(){
        //llamar a kernel
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getVersion(){
        return version;
    }
    
    public void setVersion(String version){
        this.version = version;
    }
}