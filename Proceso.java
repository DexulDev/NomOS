
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Proceso
{
    private String id;
    private String nombre;
    private String estado;
    private long tiempoCPU;
    private long direccionPrograma;
    private long limiteMemoria;
    
    public Proceso(String id, String nombre, String estado, long tiempoCPU, long direccionPrograma, long limiteMemoria){
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.tiempoCPU = tiempoCPU;
        this.direccionPrograma = direccionPrograma;
        this.limiteMemoria = limiteMemoria;
    }
    
    //getters
    
    public String getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public long getTiempoCPU(){
        return tiempoCPU;
    }
    
    public long getDireccionPrograma(){
        return direccionPrograma;
    }
    
    public long getLimiteMemoria(){
        return limiteMemoria;
    }
    
    //setters
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public void setTiempoCPU(long tiempoCPU){
        this.tiempoCPU = tiempoCPU;
    }
    
    public void setDireccionPrograma(long direccionPrograma){
        this.direccionPrograma = direccionPrograma;
    }
    
    public void setLimiteMemoria(long limiteMemoria){
        this.limiteMemoria = limiteMemoria;
    }
    
    public String toString(){
        return "";
    }
}