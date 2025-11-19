import java.util.Scanner;
import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {

        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        int repeticiones = 0;

        while (flag == true) {
            System.out.println("Introduzca la cantidad de números que va a introducir: ");
            repeticiones =Esdia.readInt(scan.next());
            System.out.println("NUMERO INTRODUCIDO: "+ repeticiones);

            if (repeticiones <= 0) {
                System.err.println("ERROR: Cantidad introducida no válida: " + repeticiones + " <= 0");
            }
            else {
                flag = false;
            }
        }

        float media = 0;

        for (int i = 0; i < repeticiones; i++) {
            System.out.println("Introduzca el número " + (i+1) +": ");
            media += Esdia.readFloat(scan.next());
        }

        media = media / repeticiones;
        System.out.println("La media es:" + media);

        scan.close();
    }
}
