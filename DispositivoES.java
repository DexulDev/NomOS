/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public abstract class DispositivoES
{
    protected boolean abierto;

    public abstract void abrir();
    public abstract void escribir(String datos);
    public abstract String leer();
    public abstract void cerrar();
    public abstract String getTipo();

    public boolean estaAbierto(){
        return abierto;
    }
}
