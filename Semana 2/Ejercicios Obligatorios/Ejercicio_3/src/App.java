import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        int repeticiones = 0;

        while (flag == true) {
            System.out.println("Introduzca la cantidad de números que va a introducir: ");
            repeticiones = scan.nextInt();

            if (repeticiones <= 0) {
                System.err.println("ERROR: Cantidad introducida no válida: " + repeticiones + " <= 0");
            }
            else {
                flag = false;
            }
        }

        int media = 0;

        for (int i = 0; i < repeticiones; i++) {
            System.out.println("Introduzca el número " + (i+1) +": ");
            media += scan.nextInt();
        }

        media = media / repeticiones;
        System.out.println("La media es:" + media);

        scan.close();
    }
}
