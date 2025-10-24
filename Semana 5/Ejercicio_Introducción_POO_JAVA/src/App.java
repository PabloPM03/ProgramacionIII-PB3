import java.util.Scanner;

import modelo.Videoteca;

public class App {
    public static void main(String[] args) throws Exception {

        Videoteca videoteca = new Videoteca(0);
        Scanner scan = new Scanner(System.in);
        
        int opcion, numPeliculas;
        Double velocidad = 1.0;

        mostrarMenu();
        opcion = scan.nextInt();

        switch (opcion) {


            case 1:     //Opción: CREAR VIDEOTECA VACÍA DE TAMAÑO ESPECIFICADO POR EL USUARIO
                
                System.out.println("Introduzca la cantidad de peliculas que desea guardar en la videoteca");

                videoteca = null;
                numPeliculas = scan.nextInt();
                videoteca = new Videoteca(numPeliculas);

                System.out.println("Videoteca de " + numPeliculas + " creado");
                System.out.println();
                break;


            case 2:     //Opción: Cambiar velocidad de reproducción
                System.out.println("Introduzca la nueva velocidad de reproducción (Actualmente " + velocidad + "X): ");
                velocidad = scan.nextDouble();
                System.out.println("Velocidad de reproducción establecida a (" + velocidad + "X):");
                System.out.println();
                break;


            case 3:     //Opción: AÑADIR PELÍCULA A LA VIDEOTECA
                
                videoteca.añadirPelicula();

                break;  


            case 4:
                
                break;


            case 5:     //Opción: SALIR DEL PROGRAMA
                

                scan.close();
                break;
        
            default:
                System.out.println("Opción no reconocida, seleccione una opción válida: 1-5");

                break;
        }
    }






    private static void mostrarMenu() {

        System.out.println("|-----------------------------------------------|");
        System.out.println("| 1)   Nueva videoteca de películas             |");
        System.out.println("| 2)   Configurar velociad de reproducción      |");
        System.out.println("| 3)   Añadir una nueva película a la videoteca |");
        System.out.println("| 4)   Mostrar información actual de películas  |");
        System.out.println("| 5)   Salir (se borrará toda la información)   |");
        System.out.println("|-----------------------------------------------|");
        System.out.printf("\nSeleccione una opción (1-5): ");

    }
}