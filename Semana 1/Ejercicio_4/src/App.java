import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        int num1, num2, num3;
        Scanner scan = new Scanner(System.in);

        System.out.println("Introduzca el primer número:");
        num1 = scan.nextInt();
        System.out.println("Introduzca el segundo número:");
        num2 = scan.nextInt();
        System.out.println("Introduzca el tercer número:");
        num3 = scan.nextInt();    

        if (num1 == num2) {
            if (num1 == num3) {
                System.out.println("Los tres números son iguales - num1(" + num1 + ") - num2(" + num2 + ") - num3(" + num3 + ")");
            }
            else if(num1 < num3) {
                System.out.println("El número más grande es el tercero - num3(" + num3 + ")");
            }
            else {
                System.out.println("Los números más grandes son el primero y el segundo - num1(" + num1 + ") - num2(" + num2 + ")");
            }
        }
        else if (num1 == num3) {
            if(num1 < num2) {
                System.out.println("El número más grande es el segundo - num2(" + num2 + ")");
            }
            else {
                System.out.println("Los números más grandes son el primero y el tercero - num1(" + num1 + ") - num3(" + num3 + ")");
            }
        }
        else if (num2 == num3) {
            if(num2 < num1) {
                System.out.println("El número más grande es el primero - num1(" + num1 + ")");
            }
            else {
                System.out.println("Los números más grandes son el segundo y el tercero - num2(" + num2 + ") - num3(" + num3 + ")");
            }
        }
        else {
            if (num1 > num2) {
                if (num1 > num3) {
                    System.out.println("El número más grande es el primero - num1(" + num1 + ")");
                }
            }
            if (num2 > num1) {
                if (num2 > num3) {
                    System.out.println("El número más grande es el segundo - num2(" + num2 + ")");
                }
            }
            if (num3 > num1) {
                if (num3 > num2) {
                    System.out.println("El número más grande es el tercero - num3(" + num3 + ")");
                }
            }
        }

        scan.close();
    }
}
