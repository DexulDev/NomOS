import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
    private MonitorSeguridad monitor;
    private static final int MaxUsuarios = 10;
    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private File archivoUsuarios;

    public Kernel(){
        sistema = new NomOS();
        sesion = false;
        usuario = null;
        contra = null;
        archivos = new SistemaArchivo();
        usuarios = new Usuario[MaxUsuarios];
        cantidadUsuarios = 0;
        archivoUsuarios = new File("usuarios.txt");
        if(archivoUsuarios.exists()) cargarUsuarios();
        if(cantidadUsuarios == 0){
            //solo la primera vez: los 3 perfiles que pide la práctica
            agregar("admin", "admin123");
            agregar("alumno", "alumno123");
            agregar("invitado", "invitado123");
            guardarUsuarios();
        }
        monitor = new MonitorSeguridad("seguridad.txt", usuarios);
        String[] nombresRecursos = monitor.getNombresRecursos();
        for(int i = 0;i<nombresRecursos.length; i++){
            archivos.crear(nombresRecursos[i]);
            archivos.escribir(nombresRecursos[i], monitor.getContenido(nombresRecursos[i]));
        }
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
        "||  (1) Iniciar sesión         ||\n" +
        "||  (2) Guardar usuario        ||\n" +
        "||  (s) Regresar               ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||                             ||\n" +
        "||=============================||\n" 
        );
    }
      
    private String opcion(boolean sesion){
        return "  (1) Iniciar sesión         ";
    }

    public String pantallaUsuario(){
        return(
            "||=============================||\n"+
            "|| " + sistema.getNombre() + "               v" + sistema.getVersion() + " ||\n"+
            "||                             ||\n" +
            "||           -Entrar-          ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||  Ingresa el usuario:        ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||                             ||\n" +
            "||=============================||\n"
        );
    }

    public boolean iniciarSesion(String usuario, String contra){
        if(!monitor.autenticar(usuario, contra)) return false;
        this.usuario = usuario;
        sesion = true;
        return true;
    }

    public void cerrarSesion(){
        usuario = null;
        sesion = false;
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
        "||  Unidad VI                  ||\n" +
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

    //Usuarios
    private int buscarUsuario(String nombre){
        for(int i = 0;i<cantidadUsuarios; i++){
            if(usuarios[i].getNombre().equals(nombre)) return i;
        }
        return -1;
    }

    private void agregar(String nombre, String contra){
        usuarios[cantidadUsuarios] = new Usuario(nombre, contra);
        cantidadUsuarios++;
    }

    private void cargarUsuarios(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
            String linea = br.readLine();
            while(linea != null && cantidadUsuarios < MaxUsuarios){
                String[] partes = linea.split(";");
                if(partes.length == 2) agregar(partes[0], partes[1]);
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            cantidadUsuarios = 0;
        }
    }

    private boolean guardarUsuarios(){
        try {
            FileWriter fw = new FileWriter(archivoUsuarios);
            for(int i = 0;i<cantidadUsuarios; i++){
                fw.write(usuarios[i].getNombre() + ";" + usuarios[i].getContra() + "\n");
            }
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String guardarUsuario(String nombre, String contra){
        if(nombre.trim().length() == 0 || contra.length() == 0) return "El usuario y la contraseña no pueden estar vacíos...";
        if(nombre.contains(" ") || nombre.contains(";") || contra.contains(";")) return "El usuario no puede llevar espacios ni ';', y la contraseña no puede llevar ';'.";
        if(buscarUsuario(nombre) != -1) return "El usuario " + nombre + " ya existe.";
        if(cantidadUsuarios == MaxUsuarios) return "Ya no caben más usuarios (máximo " + MaxUsuarios + ").";
        agregar(nombre, contra);
        monitor.usuarioAgregado();
        if(!guardarUsuarios()) return "Usuario " + nombre + " guardado, pero no se pudo escribir en el disco...";
        return "Usuario " + nombre + " guardado.";
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
        "||  7) Ejecutar                ||\n" +
        "||  8) Ver permisos            ||\n" +
        "||  9) Comando (chmod)         ||\n" +
        "||  s) Regresar                ||\n" +
        "||                             ||\n" +
        "||=============================||\n"
        );
    }
   
    public String crearArchivo(String nombre){
        String respuesta = archivos.crear(nombre);
        if(archivos.existe(nombre) && monitor.indiceRecurso(nombre) == -1){
            monitor.registrarRecurso(nombre, usuario);
        }
        return respuesta;
    }

    public String escribirArchivo(String nombre, String texto){
        String error = monitor.verificar(usuario, nombre, 'w');
        if(!error.equals("")) return error;
        return archivos.escribir(nombre, texto);
    }

    public String leerArchivo(String nombre){
        String error = monitor.verificar(usuario, nombre, 'r');
        if(!error.equals("")) return error;
        return archivos.leer(nombre);
    }

    public String eliminarArchivo(String nombre){
        String error = monitor.verificar(usuario, nombre, 'w');
        if(!error.equals("")) return error;
        String respuesta = archivos.eliminar(nombre);
        if(!archivos.existe(nombre)) monitor.eliminarRecurso(nombre);
        return respuesta;
    }

    public String ejecutarArchivo(String nombre){
        String error = monitor.verificar(usuario, nombre, 'x');
        if(!error.equals("")) return error;
        return "Ejecutando " + nombre + "...";
    }

    public String tablaPermisos(){
        return monitor.tabla();
    }
    
    public String listarArchivos(){
        return archivos.listar();
    }

    public String mapaDisco(){
        return archivos.mapa();
    }

    public boolean esSudo(String comando){
        return comando.trim().startsWith("sudo ");
    }

    public String chmod(String comando, String contra){
        String[] partes = comando.trim().split("\\s+");
        int inicio = 0;
        boolean sudo = false;
        if(partes[0].equals("sudo")){
            sudo = true;
            inicio = 1;
        }
        int resto = partes.length - inicio;
        String uso = "Comando no reconocido. Uso: [sudo] chmod <modo> <archivo> o [sudo] chmod <usuario> <rwx> <archivo>";
        if(resto != 3 && resto != 4) return uso;
        if(!partes[inicio].equals("chmod")) return uso;
        if(!sudo) return "chmod: permiso denegado. Usa sudo para cambiar permisos.";
        if(!monitor.autenticar(usuario, contra)) return "sudo: contraseña incorrecta.";
        if(!monitor.esAdmin(usuario)) return usuario + " no está en el archivo sudoers.";
        if(resto == 3) return monitor.chmod(partes[inicio+1], partes[inicio+2]);
        return monitor.chmodUsuario(partes[inicio+1], partes[inicio+2], partes[inicio+3]);
    }
}
