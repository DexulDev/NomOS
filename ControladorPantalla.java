/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class ControladorPantalla extends DispositivoES
{
    public ControladorPantalla(){
        abierto = false;
    }

    @Override
    public void abrir(){
        abierto = true;
    }

    @Override
    public void escribir(String datos){
        System.out.println(datos);
    }

    @Override
    public String leer(){
        throw new UnsupportedOperationException("La pantalla es solo de salida");
    }

    @Override
    public void cerrar(){
        abierto = false;
    }

    @Override
    public String getTipo(){
        return "Salida";
    }
}
