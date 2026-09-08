
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class NomOS
{
    private String nombre;
    private String version;
    private String[] logo = {
        " _   _                   _____ _____  ",
        "| \\ | |                 |  _  /  ___| ",
        "|  \\| | ___  _ __ ___ | | | \\ `--.   ",
        "| . ` |/ _ \\| '_ ` _ \\| | | |`--. \\   ",
        "| |\\  | (_) | | | | | \\ \\_/ /\\__/ /   ",
        "\\_| \\_/\\___/|_| |_| |_|\\___/\\____/    ",
        "                              ______ ",
        "                             |______|"
    };

    //private String[] imagen = {
        
    //};

    public NomOS(){
        this.nombre = "NomOS";
        this.version = "CP.1.1";
           
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
