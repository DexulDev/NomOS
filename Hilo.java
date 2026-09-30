
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Hilo implements Runnable
{
    
    private String nombre;
    private int tiempoRafaga;

    public Hilo(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempoRafaga(int tiempoRafaga){
       this.tiempoRafaga = tiempoRafaga; 
    }
    
    public void restarRafaga(int quantum){
        tiempoRafaga -= quantum;
        if(tiempoRafaga<0) tiempoRafaga = 0;
    }

    @Override
    public void run() {
        for(int i = 1; i <= tiempoRafaga; i++) {
            System.out.println(nombre + " completado: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido.");
            } 
        }
    }
}

