
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Proceso
{
    private String id, nombre, estado, usuario;
    private int prioridad;
    private long tiempoCPU = 0; 
    private long direccionPrograma, limiteMemoria;
    
    public Proceso(String id, String nombre, String estado, String usuario, int prioridad, long direccionPrograma, long limiteMemoria){
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.usuario = usuario;
        this.prioridad = prioridad;
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
    
    public String getUsuario(){
        return usuario;
    }
    
    public int getPriodidad(){
        return prioridad;
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
    
    public void setDireccionPrograma(long direccionPrograma){
        this.direccionPrograma = direccionPrograma;
    }
    
    public void setLimiteMemoria(long limiteMemoria){
        this.limiteMemoria = limiteMemoria;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }
    
    public String toString(){
        return id + "  " + 
        usuario + "    " + 
        prioridad + "  " + 
        tiempoCPU + "     " + 
        limiteMemoria + "  " + 
        direccionPrograma;
    }
}