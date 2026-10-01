/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class Kernel
{
    protected NomOS sistema;
    private boolean sesion;
    private String usuario;
    private String contra;
    private int quantum;

    public Kernel(){
        sistema = new NomOS();
        sesion = false;
        usuario = null;
        contra = null;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getContra(){
        return ("********"); //Solo por poner un getter, porque el main no debería de saber la contraseña
    }

    public void setContra(String contra){
        this.contra = contra;
    }
    
    public String getSistemaNombre(){
        return sistema.getNombre();
    }

    public boolean getSesion(){
        return sesion;
    }


    public String menu(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||        -BIENVENIDO-         ||\n" +
        "||                             ||\n" +
        "||  (1) Cargar OS              ||\n" +
        "||  (2) BIOS                   ||\n" +
        "||  (3) Acerca de              ||\n" +
        "||  (s) Salir                  ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    public String login(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||          -Sesion-           ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||" + opcion(sesion) + "||\n" +
        "||  (s) Regresar               ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }
      
    private String opcion(boolean sesion){
        if(!sesion) return "  (1) Crear usuario          ";
        return "  (1) Iniciar sesión         ";
    }

    public String inicioSesion(){
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||           -Entrar-          ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa la contraseña:     ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n" 
        );
    }

    public boolean validar(String contra){
        return this.contra != null && this.contra.equals(contra);
    }

    public String crearUsuario(){
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||       -Crear usuario-       ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa el nombre:         ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n" 
        );
    }

    public String crearContra(){
        sesion = true;
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||       -Crear usuario-       ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa la contraseña:     ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n" 
        );
    }

    public String bios(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||           -BIOS-            ||\n" +
        "||                             ||\n" +
        "||  Fabricante: NomOS Inc.     ||\n" +
        "||  Modelo: NomOS Virtual PC   ||\n" +
        "||  Version BIOS: 1.0.0        ||\n" +
        "||  Fecha: 06/09/2026          ||\n" +
        "||                             ||\n" +
        "||  CPU: NomCore i9 Virtual    ||\n" +
        "||  Nucleos: 8                ||\n" +
        "||  RAM: 16384 MB              ||\n" +
        "||  Almacenamiento: 512 GB SSD ||\n" +
        "||                             ||\n" +
        "||  Boot: Habilitado           ||\n" +
        "||  Estado: OK                 ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    public String about(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||         -Acerca de-         ||\n" +
        "||                             ||\n" +
        "||  Materia:                   ||\n" +
        "||  Sistemas Operativos        ||\n" +
        "||                             ||\n" +
        "||  Unidad I                   ||\n" +
        "||                             ||\n" +
        "||  Equipo: Club Penguin       ||\n" +
        "||                             ||\n" +
        "||  Maestra:                   ||\n" +
        "||  Reyna Jarquin Valverde     ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    public String iniciado(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||  Inicio de sesion exitoso!  ||\n" +
        "||                             ||\n" +
        "||  1) Generar procesos.       ||\n" +
        "||                             ||\n" +
        "||  s) Salir                   ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }

    //Apartado de procesos
    
    private boolean sincronizar(Proceso[] colaProcesos, int i){
        if(colaProcesos[i].getEstado().equals("Creado")) colaProcesos[i].setEstado("Listo");
        if(!colaProcesos[i].tieneTrabajo()){
            colaProcesos[i].setEstado("Terminado");
            return false;
        }
        return colaProcesos[i].getEstado().equals("Listo");
    }

    public String planificador(int cantidad, String[] nombres){
        Proceso[] colaProcesos = new Proceso[cantidad];
        Thread[] colaHilos = new Thread[cantidad];
        String salida = "PID  Usuario    CPU     Memoria  Direccion\n";
        int totalRafaga = 0;
        int totalEspera = 0;

        for(int i = 0;i<cantidad; i++){
            colaProcesos[i] = new Proceso(usuario, (int)(Math.random()*300)+ 1, (int)(Math.random()*1500)+ 1, nombres[i]);
            colaProcesos[i].setPID(Integer.toString(i*10+1));
            colaProcesos[i].setUsuario(usuario);
            colaProcesos[i].getHilo().setTiempoRafaga(colaProcesos[i].getTiempoCPU());
            totalRafaga += colaProcesos[i].getTiempoCPU();
            salida += colaProcesos[i].toString() + "\n";
        }

        quantum = totalRafaga/cantidad;
        if(quantum<1) quantum = 1;
        salida += "\nQuantum dinámico: " + quantum + " ms\n\n";

        String barras = "|";
        String tiempos = "0";
        int reloj = 0;
        int activos = cantidad;

        while(activos>0){
            Proceso pr = colaProcesos[0];
            if(sincronizar(colaProcesos, 0)){
                pr.setEstado("Ejecutando");
                pr.getHilo().setQuantum(quantum);
                colaHilos[0] = new Thread(pr.getHilo());
                int inicio = reloj;
                colaHilos[0].start();
                try {
                    colaHilos[0].join();
                } catch (InterruptedException e) {
                }
                int uso = pr.getHilo().getUso();
                reloj += uso;

                salida += "[" + inicio + "-" + reloj + " ms] " + pr.getNombre() + " ejecutó " + uso + " ms, restan " + pr.getHilo().getTiempoRafaga() + " ms";

                pr.setEstado("Listo");
                boolean sigue = sincronizar(colaProcesos, 0);
                rotar(colaProcesos, activos);

                if(!sigue){
                    activos--;
                    totalEspera += reloj - pr.getTiempoCPU();
                    salida += " -> TERMINADO\n";
                }else{
                    salida += " -> vuelve a la cola\n";
                }

                String celda = " " + pr.getNombre();
                while(celda.length()<7) celda += " ";
                celda += "|";
                barras += celda;
                String t = "" + reloj;
                for(int j = 0;j<celda.length()-t.length(); j++) tiempos += " ";
                tiempos += t;
            }else{
                rotar(colaProcesos, activos);
                activos--;
            }
        }

        salida += "ms" + "\nDiagrama de Gantt:\n" + barras + "\n" + tiempos + "\n" + "\nTiempo promedio de espera: " + Math.round(((double)totalEspera/cantidad*100)/100.0);
        return salida;
    }


    private void rotar(Proceso[] colaProcesos, int activos){
        Proceso primero = colaProcesos[0];
        for(int i = 0;i<activos-1; i++) colaProcesos[i] = colaProcesos[i+1];
        colaProcesos[activos-1] = primero;
    }

    //Resto

    public String[] getLogo(){
        return sistema.getLogo();
    }
    
    public String getTerminal(){
        return sesion ? "\n[" + usuario + "@nomOS~]$ " :  "\n- ";
    }
}
