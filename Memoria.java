
/**
 * Write a description of class Memoria here.
 *
 * @author Club Penguin
 */
public class Memoria
{
    private Cuadro[] cuadros;
    private boolean quedanLibres;
    //private int memoriaTotal;
    
    public Memoria(int cantidadCuadros, int[] valorCuadros){
        cuadros = new Cuadro[cantidadCuadros];
        quedanLibres = true;
        
        for(int i = 0; i<cantidadCuadros; i++){
            cuadros[i] = new Cuadro(valorCuadros[i]);
        }
    }
    
    public String primerAjuste(Proceso[] procesos, int memoriaTotal){
        String respuesta = "";
        
        respuesta += "PRIMER AJUSTE...\nProcesos: ";
        
        for(int i = 0; i<procesos.length; i++){
            respuesta +=  "[" + procesos[i].getNombre() + "]";
        }
        
        respuesta += "\nTamaños: ";
        
        for(int i = 0; i<procesos.length; i++){
            respuesta += "[" + procesos[i].getTamaño() + "]";
        }
        
        respuesta += "\nCuadros: ";
        
        for(int i = 0; i<cuadros.length; i++){
            respuesta += !cuadros[i].estaOcupado() ? "[" + cuadros[i].getTamaño() + "]" : "[" + cuadros[i].getProceso() + "]";
        }

        ///
        
        respuesta += "\n\n";
        
        
       for(int j = 0;j<procesos.length;j++){
            for(int i = 0;i<cuadros.length;i++){
                if(cuadros[i].getTamaño() >= procesos[j].getTamaño() && !cuadros[i].estaOcupado()){
                    cuadros = meter(i, j, cuadros, procesos);
                    break;
                }
            }
        }
        
        
        
        respuesta += "PRIMER AJUSTE TERMINADO...\nCuadros: ";
               
        for(int i = 0; i<cuadros.length; i++){
            respuesta += cuadros[i].estaOcupado() ? "[" + cuadros[i].getProceso().getNombre() + "]" : "[" + cuadros[i].getTamaño() + "]";
        }
        
        return respuesta;
    }

    public String mejorAjuste(Proceso[] procesos, int memoriaTotal){
        String respuesta = "";
        
        respuesta += "MEJOR AJUSTE...\nProcesos: ";
        
        for(int i = 0; i<procesos.length; i++){
            respuesta +=  "[" + procesos[i].getNombre() + "]";
        }
        
        respuesta += "\nTamaños: ";
        
        for(int i = 0; i<procesos.length; i++){
            respuesta += "[" + procesos[i].getTamaño() + "]";
        }
        
        respuesta += "\nCuadros: ";
        
        for(int i = 0; i<cuadros.length; i++){
            respuesta += !cuadros[i].estaOcupado() ? "[" + cuadros[i].getTamaño() + "]" : "[" + cuadros[i].getProceso().getNombre() + "]";
        }
        
        respuesta += "\n\n";
        
        for(int j = 0;j<procesos.length;j++){
            int mejor = -1;
            for(int i = 0;i<cuadros.length;i++){
                if(cuadros[i].getTamaño() >= procesos[j].getTamaño() && !cuadros[i].estaOcupado()){
                    if(mejor == -1 || cuadros[i].getTamaño() < cuadros[mejor].getTamaño()) mejor = i;
                }
            }
            if(mejor != -1) cuadros = meter(mejor, j, cuadros, procesos);
        }
        
        respuesta += "MEJOR AJUSTE TERMINADO...\nCuadros: ";
               
        for(int i = 0; i<cuadros.length; i++){
            respuesta += cuadros[i].estaOcupado() ? "[" + cuadros[i].getProceso().getNombre() + "]" : "[" + cuadros[i].getTamaño() + "]";
        }
        
        return respuesta;
    }

    public String peorAjuste(Proceso[] procesos, int memoriaTotal){
        String respuesta = "";
        
        respuesta += "PEOR AJUSTE...\nProcesos: ";
        
        for(int i = 0; i<procesos.length; i++){
            respuesta +=  "[" + procesos[i].getNombre() + "]";
        }
        
        respuesta += "\nTamaños: ";
        
        for(int i = 0; i<procesos.length; i++){
            respuesta += "[" + procesos[i].getTamaño() + "]";
        }
        
        respuesta += "\nCuadros: ";
        
        for(int i = 0; i<cuadros.length; i++){
            respuesta += !cuadros[i].estaOcupado() ? "[" + cuadros[i].getTamaño() + "]" : "[" + cuadros[i].getProceso().getNombre() + "]";
        }
        
        respuesta += "\n\n";
        
        for(int j = 0;j<procesos.length;j++){
            int peor = -1;
            for(int i = 0;i<cuadros.length;i++){
                if(cuadros[i].getTamaño() >= procesos[j].getTamaño() && !cuadros[i].estaOcupado()){
                    if(peor == -1 || cuadros[i].getTamaño() > cuadros[peor].getTamaño()) peor = i;
                }
            }
            if(peor != -1) cuadros = meter(peor, j, cuadros, procesos);
        }
        
        respuesta += "PEOR AJUSTE TERMINADO...\nCuadros: ";
               
        for(int i = 0; i<cuadros.length; i++){
            respuesta += cuadros[i].estaOcupado() ? "[" + cuadros[i].getProceso().getNombre() + "]" : "[" + cuadros[i].getTamaño() + "]";
        }
        
        return respuesta;
    }
    
    private Cuadro[] meter(int indiceCuadro, int indiceProceso, Cuadro[] cuadros, Proceso[] procesos){
        Cuadro[] nuevoCuadros = new Cuadro[cuadros.length + 1];
        for(int i = 0; i<cuadros.length;i++){
            if(i <= indiceCuadro) nuevoCuadros[i] = cuadros[i];
            else nuevoCuadros[i+1] = cuadros[i];
        }
        nuevoCuadros[indiceCuadro+1] = new Cuadro(cuadros[indiceCuadro].getTamaño() - procesos[indiceProceso].getTamaño());
        cuadros[indiceCuadro].Ocupar(procesos[indiceProceso]);
        return nuevoCuadros;
    }
}
