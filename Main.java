
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
        while(true){
            clear();
            System.out.println(k.menu());
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
                    System.out.println("Digite cualquier tecla...");
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
            switch(sc.nextLine()){
                case "1":
                    if(k.getSesion()){
                        clear();
                        System.out.println(k.inicioSesion());
                        c = sc.nextLine();
                        if (!k.validar(c)){
                            while(!k.validar(c)){
                                clear();
                                System.out.print("Ups... Intenta otra vez: ");
                                c = sc.nextLine();
                            }
                        }
                        clear();
                        System.out.println(k.bienvenido());
                        sc.nextLine();
                    }else{
                        clear();
                        System.out.println(k.crearUsuario());
                        k.setUsuario(sc.nextLine());
                        clear();
                        System.out.println(k.crearContra());
                        k.setContra(sc.nextLine());
                    }
                    break;
                case "d":
                    return;
                default:
                    clear();
                    System.out.println("Opción incorrecta...");
                    System.out.println("Ingrese cualquier tecla...");
                    sc.nextLine();
                    break;
            }
        } 
    }
    
    private static void BIOS(Kernel k){
        clear();
        System.out.println(k.bios());
        System.out.println("Ingrese cualquier tecla para continuar...");
        sc.nextLine();
    }

    private static void about(Kernel k){
        clear();
        System.out.println(k.about());
        System.out.println("Ingrese cualquier tecla para continuar...");
        sc.nextLine();
    }
}
