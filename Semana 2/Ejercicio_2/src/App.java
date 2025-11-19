import java.io.Console;

public class App {
    public static void main(String[] args) throws Exception {

        Console console = System.console();

        if (console == null) {
            System.out.println("ERROR: No hay una consola disponible");
            return;
        }

        System.out.println("Introduzca su año de nacimiento: ");
        int anioNacimiento = Integer.parseInt(console.readLine());
        System.out.println("Introduzca el año actual: ");
        int anioActual = Integer.parseInt(console.readLine());

        int edad = anioActual - anioNacimiento;

        System.out.println("Su edad es: " + edad);

    }
}
