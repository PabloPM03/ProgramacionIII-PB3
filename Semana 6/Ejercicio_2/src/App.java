import java.util.Scanner;

import modelo.Videoteca;

public class App {
    public static void main(String[] args) throws Exception {

        Videoteca videoteca = new Videoteca(0);
        Scanner scan = new Scanner(System.in);
        
        boolean salirPrograma = false, checkCaracter;
        int opcion, maxPeliculas;
        char opcionChar;


        while (salirPrograma == false) {

        mostrarMenu();
        opcion = scan.nextInt();
        
            switch (opcion) {


                case 1:     //Opción: CREAR VIDEOTECA VACÍA DE TAMAÑO ESPECIFICADO POR EL USUARIO
                    
                    System.out.println("Introduzca la cantidad de peliculas que desea guardar en la videoteca");

                    maxPeliculas = scan.nextInt();
                    videoteca = new Videoteca(maxPeliculas);

                    System.out.println("\n\nVideoteca con un tamaño para almacenar " + maxPeliculas + " película(s) creado");
                    System.out.println();
                    break;


                case 2:     //Opción: Cambiar velocidad de reproducción
                    System.out.println("Introduzca la nueva velocidad de reproducción (Actualmente " + videoteca.getVelocidad() + "X): ");
                    videoteca.setVelocidad(Float.parseFloat(scan.next()));
                    System.out.printf("\n\nVelocidad de reproducción establecida a (" + videoteca.getVelocidad() + "X):");
                    break;


                case 3:     //Opción: AÑADIR PELÍCULA A LA VIDEOTECA
                    
                    if (videoteca.getmaxPeliculas() == 0) {
                        System.err.printf("\n\nERROR: La videoteca no ha sido creada. No se pueden guardar películas aún");
                    }
                    else if (videoteca.añadirPelicula() == 1) {
                        System.err.printf("\n\nERROR: La videoteca está llena (" + videoteca.getnumPeliculas() + "/" + videoteca.getmaxPeliculas() + ")");
                    }
                    else {
                        System.out.printf("\n\nPelícula añadida correctamente");
                    }

                    break;  


                case 4:

                    if (videoteca.getmaxPeliculas() == 0) {
                        System.err.println("\n\nERROR: La videoteca no ha sido creada. No se pueden mostrar datos de péliculas aún");
                    }
                    else if (videoteca.getnumPeliculas() == 0) {
                        System.err.println("\n\nERROR: La videoteca no tiene ninguna película almacenada");
                    }
                    else{
                        videoteca.mostrarPelículas();
                        System.out.printf("\n\nAquí tienes todas las películas que tienes almacenadas");
                    }
                    
                    break;


                case 5:     //Opción: SALIR DEL PROGRAMA
                    checkCaracter = true;
                    while (checkCaracter) {
                        System.out.println("¿Seguro que desea salir del programa? Se borrarán todos los datos (Y/N): ");
                        opcionChar = scan.next().charAt(0);
                        if (opcionChar == 'y' || opcionChar == 'Y') {
                            System.out.println("Muchas gracias por utilizar nuestro programa. ¡Hasta pronto!.");
                            checkCaracter = false;
                            salirPrograma = true;
                        }
                        else if (opcionChar == 'n' || opcionChar == 'N') {
                            checkCaracter = false;

                        }
                        else {
                            System.out.println("ERROR: Carácter introducido no válido");
                        }
                    }
                    break;
            
                default:
                    System.out.println("Opción no reconocida, seleccione una opción válida: 1-5");

                    break;
            }
        }
        scan.close();
    }






    private static void mostrarMenu() {
        System.out.printf("\n|-----------------------------------------------|\n");
        System.out.println("| MI VIDEOTECA                                  |");
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