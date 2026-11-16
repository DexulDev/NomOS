import java.util.Scanner;

/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class ControladorTeclado extends DispositivoES
{
    private Scanner sc;

    public ControladorTeclado(){
        abierto = false;
    }

    @Override
    public void abrir(){
        sc = new Scanner(System.in);
        abierto = true;
    }

    @Override
    public void escribir(String datos){
        throw new UnsupportedOperationException("El teclado es solo de entrada");
    }

    @Override
    public String leer(){
        return sc.nextLine();
    }

    @Override
    public void cerrar(){
        //no se cierra el Scanner porque cerraría System.in para todo el programa
        sc = null;
        abierto = false;
    }

    @Override
    public String getTipo(){
        return "Entrada";
    }
}
