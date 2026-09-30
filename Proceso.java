
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Proceso
{
    private String pid, nombre, estado, usuario;
    private int prioridad;
    private long tiempoCPU = 0; 
    private long direccionPrograma, limiteMemoria;
    private int rafaga
    public Proceso(String pid, String nombre, String estado, String usuario, int prioridad, long direccionPrograma, long limiteMemoria){
        this.pid = pid;
        this.nombre = nombre;
        this.estado = estado;
        this.usuario = usuario;
        this.prioridad = prioridad;
        this.direccionPrograma = direccionPrograma;
        this.limiteMemoria = limiteMemoria;
        rafaga = (int)((Math.random()*100)+ 1);
    }
    
    //getters
    
    public String getPid(){
        return pid;
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

    public Hilo getHilo(){
        return hilo;
    }
    
    //setters
    
    public void setPid(String pid){
        this.pid = pid;
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

    public void setHilo(Hilo hilo){
        this.hilo = hilo;
    }
    
    public String toString(){
        return pid + "  " + 
        usuario + "    " + 
        prioridad + "  " + 
        tiempoCPU + "     " + 
        limiteMemoria + "  " + 
        direccionPrograma;
    }
}
