package modelo;

public class Pelicula {

    Director director = new Director();
    String titulo;
    int anioEstreno, duracion;
    Double valoracion;

    public void setDatosPelicula(String titulo, int anioEstreno, int duracion, Double valoracion) {
        this.titulo = titulo;
        this.anioEstreno = anioEstreno;
        this.duracion = duracion;
        this.valoracion = valoracion;
    }
}
