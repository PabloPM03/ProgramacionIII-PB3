public class Libro {

    private String isbn, titulo, escritor;
    private int anioPublicacion;


    public Libro(String isbn, String titulo, String escritor, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.escritor = escritor;
        this.anioPublicacion = anioPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getEscritor() {
        return escritor;
    }
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
