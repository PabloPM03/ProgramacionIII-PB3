import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduzca su nombre: ");
        String nombre = scan.nextLine();
        System.out.println("Introduzca su apellido: ");
        String apellido = scan.nextLine();

            //LINEA DE * DE ARRIBA
        System.out.printf("\n*");
        for (int j=0; j<8; j++) {System.out.printf("*");}
        if (nombre.length() > 6) {
            for(int i=0; i<(nombre.length() - 6); i++) {System.out.printf("*");}
        }
        System.out.printf("*");
        for (int j=0; j<10; j++) {System.out.printf("*");}
        if (apellido.length() > 8) {
            for(int i=0; i<(apellido.length() - 8); i++) {System.out.printf("*");}
        }
        System.out.printf("*");

            //LINEA DE LEYENDA
        System.out.printf("\n* NOMBRE ");
        if (nombre.length() > 6) {
            for(int i=0; i<(nombre.length() - 6); i++) {System.out.printf(" ");}
        }
        System.out.printf("* APELLIDO ");
        if (nombre.length() > 8) {
            for(int i=0; i<(apellido.length() - 8); i++) {System.out.printf(" ");}
        }
        System.out.printf("*");

            //LINEA DE * DEL MEDIO
        System.out.printf("\n*");
        for (int j=0; j<8; j++) {System.out.printf("*");}
        if (nombre.length() > 6) {
            for(int i=0; i<(nombre.length() - 6); i++) {System.out.printf("*");}
        }
        System.out.printf("*");
        for (int j=0; j<10; j++) {System.out.printf("*");}
        if (apellido.length() > 8) {
            for(int i=0; i<(apellido.length() - 8); i++) {System.out.printf("*");}
        }
        System.out.printf("*");

            //LINEA DE NOMBRE Y APELLIDO INTRODUCIDOS
        System.out.printf("\n* %s ", nombre);
        if (nombre.length() < 6) {
            for(int i=0; i<(6 - nombre.length()); i++) {System.out.printf(" ");}
        }
        System.out.printf("* %s ", apellido);
        if (nombre.length() < 8) {
            for(int i=0; i<(8 - apellido.length()); i++) {System.out.printf(" ");}
        }
        System.out.printf("*");

            //LINEA DE * DE ABAJO
        System.out.printf("\n*");
        for (int j=0; j<8; j++) {System.out.printf("*");}
        if (nombre.length() > 6) {
            for(int i=0; i<(nombre.length() - 6); i++) {System.out.printf("*");}
        }
        System.out.printf("*");
        for (int j=0; j<10; j++) {System.out.printf("*");}
        if (apellido.length() > 8) {
            for(int i=0; i<(apellido.length() - 8); i++) {System.out.printf("*");}
        }
        System.out.printf("*");

        scan.close();
    }
}
