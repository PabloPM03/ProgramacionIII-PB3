package modelo;

import java.util.Scanner;

public class Videoteca {

    Scanner scan = new Scanner(System.in);
    Pelicula peliculas[];
    int maxPeliculas, numPeliculas = 0;

    String titulo, nombre, apellidos;
    boolean oscarGanado;
    int anioEstreno, duracion, sumaVisionado;
    Director director;
    Float valoracion, mediaValoracion;
    char opcionChar;
    Float velocidad = 1.0f;



    public Videoteca (int maxPeliculas) {
        this.maxPeliculas = maxPeliculas;
        this.peliculas = new Pelicula[maxPeliculas];
    }

    public int añadirPelicula() {
            
        if (numPeliculas < maxPeliculas) {

            System.out.println("Introduzca el titulo de la pelicula: ");
            titulo = scan.next();

            System.out.println("Introduzca el nombre del director: ");
            nombre = scan.next();

            System.out.println("Introduzca los apellidos del director: ");
            apellidos = scan.next();

            boolean checkCaracter = true;
                while (checkCaracter) {
                    System.out.println("¿El director ha ganado un Óscar?(Y/N): ");
                    opcionChar = scan.next().charAt(0);
                    if (opcionChar == 'y' || opcionChar == 'Y') {
                        checkCaracter = false;
                        oscarGanado = true;
                    }
                    else if (opcionChar == 'n' || opcionChar == 'N') {
                        checkCaracter = false;
                        oscarGanado = false;
                    }
                    else {
                        System.out.println("ERROR: Carácter introducido no válido");
                    }
                }

            System.out.println("¿En qué año se estrenó la película?: ");
            anioEstreno = scan.nextInt();

            System.out.println("¿Cuánto dura la película? (minutos): ");
            duracion = scan.nextInt();

            System.out.println("¿Qué valoración le das a la película?: ");
            valoracion = Float.parseFloat(scan.next());


            peliculas[numPeliculas] = new Pelicula(titulo, anioEstreno, duracion, valoracion);
            peliculas[numPeliculas].director.setDatosDirector(nombre, apellidos, oscarGanado);
            numPeliculas++;

        }
        else if (numPeliculas == maxPeliculas) {
            return 1;   //ERROR: VIDEOTECA LLENA
        }
    
        return 0;
    }

    public void mostrarPelículas() {
        mediaValoracion = 0f;

        System.out.println("|------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| PELÍCULAS EN LA VIDEOTECA                                                                                                    |");                   
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| Título                | Año Estreno | Director         | Oscar ganado | Duración (min) | Tiempo visionado (min) | Valoración |");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------|");

        for (int i = 0; i < numPeliculas; i++) {

            if (i!=0) {System.out.printf("\n");}
                //TÍTULO
            if (peliculas[i].titulo.length() < 21){
                System.out.printf("| "+peliculas[i].titulo);
                for(int j = 0; j<(21 - peliculas[i].titulo.length()); j++) {System.out.printf(" ");}
            }
            else {
                System.out.print("| "+peliculas[i].titulo.substring(0, 19)+"..");
            }


                //AÑO ESTRENO
            System.out.printf(" | 000"+peliculas[i].anioEstreno+"       ");


                //DIRECTOR
            if ((peliculas[i].director.nombre.length() + peliculas[i].director.apellidos.length()) < 16){
                System.out.printf(" | "+peliculas[i].director.nombre +" "+peliculas[i].director.apellidos);
                for(int j = 0; j<(15 - (peliculas[i].director.nombre.length() + peliculas[i].director.apellidos.length())); j++) {System.out.printf(" ");}
            }
            else if (peliculas[i].director.nombre.length() < 16){
                System.out.printf(" | "+peliculas[i].director.nombre+" "+peliculas[i].director.apellidos.substring(0, (13 - peliculas[i].director.nombre.length()))+"..");
            }
            else {
                System.out.printf(" | "+peliculas[i].director.nombre.substring(0, 14) +"..");
            }


                //ÓSCAR
            if (peliculas[i].director.oscarGanado == true){System.out.printf("| Sí            ");}
            else{System.out.printf("| No           ");}


                //DURACIÓN
            System.out.printf("| "+peliculas[i].duracion+"            ");
            if (duracion / 100 < 1) {System.out.printf(" ");}
            if (duracion / 10 < 1) {System.out.printf(" ");}


                //DURACIÓN / VELOCIDAD
            System.out.printf("| "+(peliculas[i].duracion / velocidad)+"                  ");
            if ((duracion/velocidad) / 100 < 1) {System.out.printf(" ");}
            if ((duracion/velocidad) / 10 < 1) {System.out.printf(" ");}


                //VALORACIÓN
            System.out.printf("| "+peliculas[i].valoracion+"        |");
            
            sumaVisionado += peliculas[i].duracion;
            mediaValoracion += valoracion; 
        }

        mediaValoracion = mediaValoracion / numPeliculas;
        
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| Tiempo de visionado total de la videoteca:                                                                      | "+sumaVisionado+" minutos|");
        System.out.println("| Valoración total de la videoteca:                                                                                      | "+mediaValoracion+" |");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------|");
        













    }








    public Pelicula[] getPeliculas(){
        return this.peliculas;
    }
    public int getmaxPeliculas() {
        return maxPeliculas;
    }
    public int getnumPeliculas() {
        return numPeliculas;
    }
    public Float getVelocidad() {
        return velocidad;
    }


    public void setVelocidad(Float velocidad) {
        this.velocidad = velocidad;
    }

}
