import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el primer número: ");
        int num1 = scanner.nextInt();
        System.out.println("Introduzca el segundo número: ");
        int num2 = scanner.nextInt();

        int sum = num1 + num2;

        System.out.println("La suma de los dos números introducidos es: " + sum);

        scanner.close();
    }
}
