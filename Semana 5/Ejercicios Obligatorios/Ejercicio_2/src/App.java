import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        String [] arrayStrings = new String[5];
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Introduzca una cadena de caracteres para el array[" + i +"]: ");
            arrayStrings[i] = scan.nextLine();
        }

        Arrays.sort(arrayStrings);

        for (int i = 0; i < 5; i++) {
            System.out.println("Array" + i + ": " + arrayStrings[i]);
        }









        scan.close();
    }
}
