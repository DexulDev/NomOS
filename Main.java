
/**
 *
 * @author Club Penguin
 * @version 0.1
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
                        System.out.println(k.bienvenido());
                        terminal(k);
                        sc.nextLine();
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
                case "d":
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
