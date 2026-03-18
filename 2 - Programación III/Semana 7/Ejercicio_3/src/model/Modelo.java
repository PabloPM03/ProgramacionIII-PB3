package model;

import controller.Controlador;
import java.util.HashMap;


public class Modelo {

    private Controlador controlador;
    private static final int MAX_SIZE = 5;
    private HashMap<String, Libro> libros = new HashMap<>();

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }


    public void agregarLibro() {

        if (libros.size() < MAX_SIZE) {

            String isbn = controlador.leerTeclado("Introduzca el ISBN del nuevo libro", 3);

            if (libros.containsKey(isbn)) {
                controlador.mostrarMensaje("Ya existe un libro con ese ISBN.", 0);
                return;
            }

            String titulo = controlador.leerTeclado("Introduzca el titulo del nuevo libro: ", 3);
            String escritor = controlador.leerTeclado("Introduzca el escritor del nuevo libro: ", 0);
            int anioPublicacion = Integer.parseInt(controlador.leerTeclado("Introduzca el año de publicación del nuevo libro: ", 0));

            libros.put(isbn, new Libro(isbn, titulo, escritor, anioPublicacion));
            System.out.println("Nuevo libro añadido correctamente");
        } 
    }


    public void eliminarLibro(String isbn) {

        libros.remove(isbn);
        System.out.println("El libro ha sido eliminado correctamente");
        
    }
   
    public boolean checkNoLibros() {
        if (libros.isEmpty()) {
            controlador.mostrarMensaje("¡No es posible modificar un libro si no tienes ninguno!", 3);
            return false;
        }
        return true;
    }

    public String seleccionarLibro() {

        String isbn = controlador.leerTeclado("Introduzca el ISBN del libro a modificar: ", 0);

        if (!libros.containsKey(isbn)){
            controlador.mostrarMensaje("No tienes ningún libro con ese ISBN!", 3);
            isbn = "fallo";
        }
        return isbn;
    }
        
    public void modificarLibro(String isbn, char opcion) {

        Libro libroElegido = libros.get(isbn);
        
        do {
            switch (opcion) {
                case '1':
                    libroElegido.setTitulo(controlador.leerTeclado("Introduzca el titulo modificado: ", 3));
                    System.out.println("Título modificado correctamente");
                break;
                case '2':
                    libroElegido.setEscritor(controlador.leerTeclado("Introduzca el escritor del libro modificado: ", 0));
                    System.out.println("Escritor modificado correctamente");
                break;
                case '3': 
                    libroElegido.setAnioPublicacion(Integer.parseInt(controlador.leerTeclado("Introduzca el año de publicación modificado: ", 0)));
                    System.out.println("Año de publicación modificado correctamente");
                break;
                case '4':
                    opcion = 4;
                break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != '4');
    }

    public HashMap<String, Libro> getLibros() {
        return libros;
    }

}
