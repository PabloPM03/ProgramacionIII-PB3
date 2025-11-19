package modelo;

public class Pelicula {

    Director director = new Director();
    String titulo;
    int anioEstreno, duracion;
    Float valoracion;

    public Pelicula(String titulo, int anioEstreno, int duracion, Float valoracion) {
        this.titulo = titulo;
        this.anioEstreno = anioEstreno;
        this.duracion = duracion;
        this.valoracion = valoracion;
    }
}
