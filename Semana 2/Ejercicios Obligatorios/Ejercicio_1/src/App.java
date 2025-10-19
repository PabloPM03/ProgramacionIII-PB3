import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduzca su año de nacimiento: ");
        int anioNacimiento = scan.nextInt();
        System.out.println("Introduzca el año actual: ");
        int anioActual = scan.nextInt();

        int edad = anioActual - anioNacimiento;

        System.out.println("Su edad es: " + edad);

        scan.close();
    }
}
