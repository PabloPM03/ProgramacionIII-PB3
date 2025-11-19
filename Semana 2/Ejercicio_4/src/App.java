import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scan = new Scanner(System.in);

        System.out.println("Introduzca su edad: ");
        int edad = scan.nextInt();
        scan.nextLine();
        System.out.println("Introduzca su nombre: ");
        String nombre = scan.nextLine();
        scan.nextLine();

        System.out.println("Su edad es " + edad + " años");
        System.out.println("Su nombre es " + nombre);

        scan.close();
    }
}
