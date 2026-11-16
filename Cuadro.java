/**
 * @author Club Penguin
 */
public class Cuadro
{
    private boolean ocupado;
    private int tamaño;
    private Proceso proceso;

    public Cuadro(int tamaño){
        ocupado = false;
        this.tamaño = tamaño;
    }

    public Proceso getProceso(){
        return proceso;
    }

    public boolean estaOcupado(){
        return ocupado;
    }

    public void Ocupar(Proceso proceso){
        this.proceso = proceso;
        this.tamaño = proceso.getTamaño();
        ocupado = true;
    }

    public int getTamaño(){
        return tamaño;
    }
}
