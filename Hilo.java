
/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Hilo implements Runnable
{
    private int tiempoRafaga; private int quantum;
    private int uso;
    private String nombre;
    private String registro;
    private int paso = 25;
    
    public Hilo(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempoRafaga(int tiempoRafaga){
        this.tiempoRafaga = tiempoRafaga;
    }

    public int getTiempoRafaga(){
        return tiempoRafaga;
    }

    public void setQuantum(int quantum){
        this.quantum = quantum;
    }

    public int getUso(){
        return uso;
    }

    public boolean hayTrabajo(){
        return tiempoRafaga>0;
    }

    @Override
    public void run() {
        uso = Math.min(quantum, tiempoRafaga);
        System.out.println(">> " + nombre + " inicia");
        int hecho = 0;
        while(hecho<uso) {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
            }
            hecho += paso;
            if(hecho>uso) hecho = uso;
            System.out.println("   " + nombre + " completado: " + hecho + "/" + uso + " ms");
        }
        tiempoRafaga -= uso;
    }
}
