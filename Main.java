
/**
 *
 * @author Club Penguin @version 0.1
 */
import java.util.Scanner;

public class Main{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        Kernel k = new Kernel();
        pantallaLogo(k);
        System.out.print("Presione enter para continuar.");
        sc.nextLine();
        while(true){
            clear();
            System.out.println(k.menu());
            terminal(k);
            switch(sc.nextLine()){
                case "1":
                    login(k);
                    break;
                case "2":
                    BIOS(k);
                    break;
                case "3":
                    about(k);
                    break;
                case "s":
                    clear();
                    System.out.println("Gracias por usar NomOS..."); return;
                default:
                    clear();
                    System.out.println("Opción incorrecta...");
                    System.out.println("Digite enter...");
                    terminal(k);
                    sc.nextLine();
            }    
        }
    }

    private static void clear(){
        for(int i = 0;i<200;i++){
            System.out.println();
        }
    }
    
    private static void login(Kernel k){
        String c;
        while(true){
            clear();
            System.out.println(k.login());
            terminal(k);
            switch(sc.nextLine()){
                case "1":
                    if(k.getSesion()){
                        clear();
                        System.out.println(k.inicioSesion());
                        terminal(k);
                        c = sc.nextLine();
                        if (!k.validar(c)){
                            while(!k.validar(c)){
                                clear();
                                System.out.print("Ups... Intenta otra vez: ");
                                terminal(k);
                                c = sc.nextLine();
                            }
                        }
                        clear();
                        iniciado(k);
                    }else{
                        clear();
                        System.out.println(k.crearUsuario());
                        terminal(k);
                        k.setUsuario(sc.nextLine());
                        clear();
                        System.out.println(k.crearContra());
                        terminal(k);
                        k.setContra(sc.nextLine());
                    }
                    break;
                case "s":
                    return;
                default:
                    clear();
                    System.out.println("Opción incorrecta...");
                    System.out.println("Ingrese enter...");
                    terminal(k);
                    sc.nextLine();
                    break;
            }
        } 
    }
    private static void iniciado(Kernel k){
        String r = "";
        int cantidadProcesos = 0;
        int quantum;
        while(true){
            clear();
            System.out.println(k.iniciado());
            terminal(k);
            switch(sc.nextLine()){
                case "1":
                    r = "";
                    cantidadProcesos = 0;
                    while(!r.equals("n") && !r.equals("N")){
                        cantidadProcesos++;
                        clear();
                        System.out.println("Procesos actuales: " + cantidadProcesos + "\nQuiere crear otro proceso? (s/n)");
                        terminal(k);
                        r = sc.nextLine();
                    }
                    String[] nombres = new String[cantidadProcesos];
                    int[] rafagas = new int[cantidadProcesos];
                    for(int i = 0;i<cantidadProcesos;i++){
                        clear();
                        System.out.print("Digite el nombre del proceso " + (i+1) + ": ");
                        terminal(k);
                        nombres[i] = sc.nextLine();
                        clear();
                        System.out.println("Digite la rafaga del proceso " + (i+1) + ": ");
                        terminal(k);
                        rafagas[i] = Integer.parseInt(sc.nextLine());
                    }
                    clear();
                    System.out.println("Digite el quantum: ");
                    quantum = Integer.parseInt(sc.nextLine());
                    clear();
                    System.out.println(k.planificador(cantidadProcesos, rafagas, nombres, quantum));
                    System.out.println("Ingrese enter para continuar...");
                    terminal(k);
                    sc.nextLine();
                    break;
                case "s":
                    return;
                default:
                    System.out.println("Ingrese una opción valida...\nDigite enter.");
                    sc.nextLine();
                    clear();
                    break;
            }
        }
    }    
 
    private static void BIOS(Kernel k){
        clear();
        System.out.println(k.bios());
        System.out.println("Ingrese enter para continuar...");
        terminal(k);
        sc.nextLine();
    }

    private static void about(Kernel k){
        clear();
        System.out.println(k.about());
        System.out.println("Ingrese enter para continuar...");
        terminal(k);
        sc.nextLine();
    }
    
    private static void pantallaLogo(Kernel k){
        for(int i = 0;i<k.getLogo().length;i++){
            System.out.println(k.getLogo()[i]);
        }
        System.out.println("\n");
    }
    
    private static void terminal(Kernel k){
        System.out.print(k.getTerminal());
    }
}
