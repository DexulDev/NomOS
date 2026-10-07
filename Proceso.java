
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Proceso
{
    private String pid, nombre, estado, usuario;
    private long direccionPrograma, limiteMemoria;
    private int tiempoCPU;
    private Hilo hilo;
    
    public Proceso(String usuario, long direccionPrograma, long limiteMemoria, String nombre, int tiempoCPU){
        this.estado = "Creado";
        this.direccionPrograma = direccionPrograma;
        this.limiteMemoria = limiteMemoria; 
        this.tiempoCPU = tiempoCPU; //rafaga
        this.nombre = nombre;
        hilo = new Hilo(nombre);
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
    
    public long getDireccionPrograma(){
        return direccionPrograma;
    }
    
    public long getLimiteMemoria(){
        return limiteMemoria;
    }
    
    public String getUsuario(){
        return usuario;
    }

    public int getTiempoCPU(){
        return tiempoCPU;
    }

    public int getTiempoRestante(){
        return tiempoCPU;
    }

    public Hilo getHilo(){
        return hilo;
    }
    
    //setters
    
    public void setPID(String pid){
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

    public void setTiempoCPU(int tiempoCPU){
        this.tiempoCPU = tiempoCPU;
    }

    public void setHilo(Hilo hilo){
        this.hilo = hilo;
    }

    public boolean tieneTrabajo(){
        return hilo.hayTrabajo();
    }
    
    public String toString(){
        return pid + "  " + 
        usuario + "    " + 
        tiempoCPU + "     " + 
        limiteMemoria + "  " + 
        direccionPrograma;
    }
}
