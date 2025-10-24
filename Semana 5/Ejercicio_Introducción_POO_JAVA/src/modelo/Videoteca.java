package modelo;

import java.util.Scanner;

public class Videoteca {

    Scanner scan = new Scanner(System.in);
    Pelicula peliculas[];
    int numPeliculas;

    String titulo, nombre, apellidos;
    boolean oscarGanado;
    int anioEstreno, duracion;
    Director director;
    Double valoracion;



    public Videoteca (int numPeliculas) {
        this.numPeliculas = numPeliculas;
        this.peliculas = new Pelicula[numPeliculas];
    }

    public void añadirPelicula() {
        for (int i = 0; i < numPeliculas; i++) {
            if (peliculas[i] == null) {

                System.out.println("Introduzca el titulo de la pelicula: ");
                titulo = scan.next();
                System.out.println("Introduzca el nombre del director: ");
                nombre = scan.next();
                System.out.println("Introduzca los apellidos del director: ");
                apellidos = scan.next();
                System.out.println("¿El director ha ganado un Óscar?: ");
                oscarGanado = scan.nextBoolean();
                System.out.println("¿En qué año se estrenó la película: ");
                anioEstreno = scan.nextInt();
                System.out.println("¿Cuánto dura la película?: ");
                duracion = scan.nextInt();
                System.out.println("¿Qué valoración le das a la película?: ");
                valoracion = scan.nextDouble();



                peliculas[i].director.setDatosDirector(nombre, apellidos, oscarGanado);
                peliculas[i].setDatosPelicula(titulo, anioEstreno, duracion, valoracion);

                i = numPeliculas;
            }
        }
    }











    public Pelicula[] getPeliculas(){
        return this.peliculas;
    }
    public int getNumPeliculas() {
        return numPeliculas;
    }

}
