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
    private AdministradorDispositivos admin;
    private SistemaArchivo archivos;

    public Kernel(){
        sistema = new NomOS();
        sesion = false;
        usuario = null;
        contra = null;
        archivos = new SistemaArchivo();
        admin = new AdministradorDispositivos(10);
        admin.registrar("/dev/teclado", new ControladorTeclado());
        admin.registrar("/dev/pantalla", new ControladorPantalla());
        admin.registrar("/dev/disco0", new ControladorDisco("disco0.txt"));
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
        "||  2) Dispositivos E/S        ||\n" +
        "||  3) Sistema de archivos     ||\n" +
        "||                             ||\n" +
        "||  s) Salir                   ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }
    
    public String postProceso(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||  Inicio de sesion exitoso!  ||\n" +
        "||                             ||\n" +
        "||  1) Round Robin.            ||\n" +
        "||  2) Transición              ||\n" +
        "||  3) Memoria                 ||\n" +
        "||  4) Dispositivos E/S        ||\n" +
        "||  s) Salir                   ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }

    public String menuTransicion(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||        -Transicion-         ||\n" +
        "||                             ||\n" +
        "||  1) Admitir                 ||\n" +
        "||  2) Despachar               ||\n" +
        "||  3) Expulsar                ||\n" +
        "||  4) Bloquear                ||\n" +
        "||  5) Desbloquear             ||\n" +
        "||  6) Terminar                ||\n" +
        "||  s) Regresar                ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }

    public String tablaEstados(Proceso[] procesos){
        String tabla = "No.  Proceso    Estado\n";
        for(int i = 0;i<procesos.length; i++){
            String nombre = procesos[i].getNombre();
            while(nombre.length()<10) nombre += " ";
            tabla += (i+1) + "    " + nombre + " " + procesos[i].getEstado() + "\n";
        }
        return tabla;
    }

    public String transicion(Proceso[] procesos, int indice, String opcion){
        Proceso pr = procesos[indice];
        String antes = pr.getEstado();
        String requerido = "";
        String nuevo = "";

        switch(opcion){
            case "1": requerido = "Creado"; nuevo = "Listo"; break;
            case "2": requerido = "Listo"; nuevo = "Ejecutando"; break;
            case "3": requerido = "Ejecutando"; nuevo = "Listo"; break;
            case "4": requerido = "Ejecutando"; nuevo = "Bloqueado"; break;
            case "5": requerido = "Bloqueado"; nuevo = "Listo"; break;
            case "6": requerido = "Ejecutando"; nuevo = "Terminado"; break;
            default: return "Opción incorrecta...";
        }

        if(!antes.equals(requerido)){
            return "Transición no válida: " + pr.getNombre() + " está " + antes + " y esta acción pide " + requerido + ".";
        }

        if(nuevo.equals("Ejecutando")){
            for(int i = 0;i<procesos.length; i++){
                if(procesos[i].getEstado().equals("Ejecutando")){
                    return "Transición no válida: " + procesos[i].getNombre() + " ya está Ejecutando (solo hay una CPU).";
                }
            }
        }

        pr.setEstado(nuevo);
        return cambio(pr, antes);
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
    
    public Proceso[] crearProcesos(int cantidad, int[] rafagas, String[] nombres, int quantum){
        Proceso[] colaProcesos = new Proceso[cantidad];

        for(int i = 0;i<cantidad; i++){
            colaProcesos[i] = new Proceso(usuario, (int)(Math.random()*300)+ 1, (int)(Math.random()*1500)+ 1, nombres[i], rafagas[i]);
            colaProcesos[i].setPID(Integer.toString(i*10+1));
            colaProcesos[i].setUsuario(usuario);
            colaProcesos[i].getHilo().setTiempoRafaga(colaProcesos[i].getTiempoCPU());
        }

        this.quantum = quantum;
        if(quantum<1) quantum = 1;
        return colaProcesos;
    }
    
    public String planificador(Proceso[] colaProcesos, Thread[] colaHilos, int quantum){
        String salida = "PID  Usuario    Ráfaga     Memoria  Direccion\n";
        Proceso[] orden = new Proceso[colaProcesos.length];
        int totalRafaga = 0;
        int totalEspera = 0;

        for(int i = 0;i<colaProcesos.length; i++){
            orden[i] = colaProcesos[i];
            totalRafaga += colaProcesos[i].getTiempoCPU();
            salida += colaProcesos[i].toString() + "\n";
        }
        salida += "\nQuantum: " + quantum + " ms\n\n";

        salida += "Transición de estados:\n" + estados(orden);
        for(int i = 0;i<colaProcesos.length; i++){
            String antes = colaProcesos[i].getEstado();
            sincronizar(colaProcesos, i);
            salida += cambio(colaProcesos[i], antes);
        }
        salida += estados(orden) + "\n";
        String barras = "|";
        String tiempos = "0";
        int reloj = 0;
        int activos = colaProcesos.length;

        while(activos>0){
            Proceso pr = colaProcesos[0];
            if(sincronizar(colaProcesos, 0)){
                String antes = pr.getEstado();
                pr.setEstado("Ejecutando");
                salida += cambio(pr, antes) + estados(orden);
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
                salida += cambio(pr, "Ejecutando") + estados(orden) + "\n";

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

        salida += "ms" + "\nDiagrama de Gantt:\n" + barras + "\n" + tiempos + "\n" + "\nTiempo promedio de espera: " + (double)totalEspera/colaProcesos.length + "ms";
        return salida;
    }
    private void rotar(Proceso[] colaProcesos, int activos){
        Proceso primero = colaProcesos[0];
        for(int i = 0;i<activos-1; i++) colaProcesos[i] = colaProcesos[i+1];
        colaProcesos[activos-1] = primero;
    }

    
    private String cambio(Proceso pr, String antes){
        return "   " + pr.getNombre() + ": " + antes + " -> " + pr.getEstado() + "\n";
    }

    private String estados(Proceso[] procesos){
        String linea = "   Estados:";
        for(int i = 0;i<procesos.length; i++) linea += " " + procesos[i].getNombre() + "=" + procesos[i].getEstado();
        return linea + "\n";
    }

    private int elegir(Proceso[] procesos, String estado){
        int cuantos = 0;
        for(int i = 0;i<procesos.length; i++){
            if(procesos[i].getEstado().equals(estado)) cuantos++;
        }
        if(cuantos==0) return -1;
        int sorteo = (int)(Math.random()*cuantos);
        for(int i = 0;i<procesos.length; i++){
            if(procesos[i].getEstado().equals(estado)){
                if(sorteo==0) return i;
                sorteo--;
            }
        }
        return -1;
    }

    private int bloqueadoMasAntiguo(Proceso[] procesos, int[] bloqueadoDesde){
        int elegido = -1;
        for(int i = 0;i<procesos.length; i++){
            if(procesos[i].getEstado().equals("Bloqueado") && (elegido==-1 || bloqueadoDesde[i]<bloqueadoDesde[elegido])) elegido = i;
        }
        return elegido;
    }

    private String despachar(Proceso[] procesos, int i){
        String antes = procesos[i].getEstado();
        procesos[i].setEstado("Ejecutando");
        return cambio(procesos[i], antes);
    }

    public String transicionAutomatica(Proceso[] procesos, int quantum){
        int paso = quantum;
        if(paso<1) paso = 1;
        int[] bloqueadoDesde = new int[procesos.length];
        int activos = procesos.length;
        int tick = 0;
        String salida = "Transición automática (1 tick = " + paso + " ms)\n\n";

        salida += "Tick 0\n" + estados(procesos);
        for(int i = 0;i<procesos.length; i++){
            String antes = procesos[i].getEstado();
            procesos[i].setEstado("Listo");
            salida += cambio(procesos[i], antes);
        }
        salida += estados(procesos);
        salida += despachar(procesos, elegir(procesos, "Listo")) + estados(procesos) + "\n";

        while(activos>0){
            tick++;
            salida += "Tick " + tick + "\n";
            int actual = elegir(procesos, "Ejecutando");
            Proceso pr = procesos[actual];
            int dado = (int)(Math.random()*100)+1;

            if(dado<30){
                salida += pr.getNombre() + " se bloquea\n";
                pr.setEstado("Bloqueado");
                bloqueadoDesde[actual] = tick;
                salida += cambio(pr, "Ejecutando");
                int nuevo = elegir(procesos, "Listo");
                if(nuevo!=-1) salida += despachar(procesos, nuevo);
            }else{
                int antes = pr.getHilo().getTiempoRafaga();
                int uso = Math.min(paso, antes);
                pr.getHilo().setTiempoRafaga(antes-uso);
                salida += "   Dado: " + dado + " -> " + pr.getNombre() + " ejecutó " + uso + " ms, restan " + (antes-uso) + " ms\n";
                if(!pr.tieneTrabajo()){
                    pr.setEstado("Terminado");
                    activos--;
                    salida += cambio(pr, "Ejecutando");
                    int nuevo = elegir(procesos, "Listo");
                    if(nuevo!=-1) salida += despachar(procesos, nuevo);
                    int liberado = bloqueadoMasAntiguo(procesos, bloqueadoDesde);
                    if(liberado!=-1){
                        procesos[liberado].setEstado("Listo");
                        salida += cambio(procesos[liberado], "Bloqueado");
                    }
                }
            }

            if(activos>0 && elegir(procesos, "Ejecutando")==-1){
                int nuevo = elegir(procesos, "Listo");
                if(nuevo!=-1){
                    salida += despachar(procesos, nuevo);
                }else{
                    int rescatado = elegir(procesos, "Bloqueado");
                    procesos[rescatado].setEstado("Listo");
                    salida += "   Todos están bloqueados, se libera uno al azar\n" + cambio(procesos[rescatado], "Bloqueado");
                    salida += despachar(procesos, rescatado);
                }
            }
            salida += estados(procesos) + "\n";
        }

        salida += "Todos los procesos terminaron en " + tick + " ticks.";
        return salida;
    }

    //Dispositivos
    public String menuDispositivos(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||       -Dispositivos-        ||\n" +
        "||                             ||\n" +
        "||  1) Abrir                   ||\n" +
        "||  2) Escribir                ||\n" +
        "||  3) Leer                    ||\n" +
        "||  4) Cerrar                  ||\n" +
        "||  s) Regresar                ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }

    public String tablaDispositivos(){
        return admin.tabla();
    }

    public String abrirDispositivo(String nombre){
        return admin.abrir(nombre);
    }

    public String escribirDispositivo(String nombre, String datos){
        return admin.escribir(nombre, datos);
    }

    public String leerDispositivo(String nombre){
        return admin.leer(nombre);
    }

    public String cerrarDispositivo(String nombre){
        return admin.cerrar(nombre);
    }

    //Resto

    public String[] getLogo(){
        return sistema.getLogo();
    }

    public String getTerminal(){
        return sesion ? "\n[" + usuario + "@nomOS~]$ " :  "\n- ";
    }


    //Memoria
    public String menuMemoria(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||          -Memoria-          ||\n" +
        "||                             ||\n" +
        "||  1) Primer ajuste           ||\n" +
        "||  2) Mejor ajuste            ||\n" +
        "||  3) Peor ajuste             ||\n" +
        "||  4) Todos                   ||\n" +
        "||  s) Regresar                ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }

    public void asignarTamaños(Proceso[] procesos, int[] tamaños){
        for(int i = 0; i<procesos.length; i++) procesos[i].setTamaño(tamaños[i]);
    }

    //se crea una Memoria nueva en cada uno
    public String memoria(String opcion, int[] cuadros, Proceso[] procesos, int total){
        switch(opcion){
            case "1": return new Memoria(cuadros.length, cuadros).primerAjuste(procesos, total);
            case "2": return new Memoria(cuadros.length, cuadros).mejorAjuste(procesos, total);
            case "3": return new Memoria(cuadros.length, cuadros).peorAjuste(procesos, total);
            case "4":
                return new Memoria(cuadros.length, cuadros).primerAjuste(procesos, total) + "\n\n" +
                       new Memoria(cuadros.length, cuadros).mejorAjuste(procesos, total) + "\n\n" +
                       new Memoria(cuadros.length, cuadros).peorAjuste(procesos, total);
            default: return "Opción incorrecta...";
        }
    }
    
    //sistema de archivos
    public String menuArchivos(){
        return(
        "||=============================||\n"+
        "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
        "||                             ||\n" +
        "||     -Sistema de archivos-   ||\n" +
        "||                             ||\n" +
        "||  1) Crear                   ||\n" +
        "||  2) Escribir                ||\n" +
        "||  3) Leer                    ||\n" +
        "||  4) Eliminar                ||\n" +
        "||  5) Listar directorio       ||\n" +
        "||  6) Mapa del disco          ||\n" +
        "||  s) Regresar                ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }

    public String crearArchivo(String nombre){
        return archivos.crear(nombre);
    }

    public String escribirArchivo(String nombre, String texto){
        return archivos.escribir(nombre, texto);
    }

    public String leerArchivo(String nombre){
        return archivos.leer(nombre);
    }

    public String eliminarArchivo(String nombre){
        return archivos.eliminar(nombre);
    }

    public String listarArchivos(){
        return archivos.listar();
    }

    public String mapaDisco(){
        return archivos.mapa();
    }
}
