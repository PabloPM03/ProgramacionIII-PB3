import static com.coti.tools.Esdia.*;

import java.util.HashMap;

public class Libreria {

    private static final int MAX_SIZE = 5;
    private static HashMap<String, Libro> libros = new HashMap<>();

    public static void main(String[] args) throws Exception {
        
        int opcion;
        do {
            System.out.println("\n--- MENU CRUD ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Modificar libro");
            System.out.println("4. Listar libro");
            System.out.println("5. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1: agregarLibro(); break;
                case 2: eliminarLibro(); break;
                case 3: modificarLibro(); break;
                case 4: listarLibros(); break;
                case 5: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }



    public static void agregarLibro() {

        if (libros.size() < MAX_SIZE) {

            String isbn = readString("Introduzca el ISBN del nuevo libro");

            if (libros.containsKey(isbn)) {
                System.out.println("Ya existe un libro con ese ISBN.");
                return;
            }

            String titulo = readString("\n\n\nIntroduzca el titulo del nuevo libro: ");
            String escritor = readString("Introduzca el escritor del nuevo libro: ");
            int anioPublicacion = readInt("Introduzca el año de publicación del nuevo libro: ");

            libros.put(isbn, new Libro(isbn, titulo, escritor, anioPublicacion));
            System.out.println("Nuevo libro añadido correctamente");
        } 
    }

    
    
    public static void modificarLibro() {

        if (libros.isEmpty()) {
            System.out.println("\n\n\n¡No es posible modificar un libro si no tienes ninguno!");
            return;
        }
            
        String isbn;

        listarLibros();
        isbn = readString("\n\n\nIntroduzca el ISBN del libro a modificar: ");

        if (!libros.containsKey(isbn)){
            System.out.println("No tienes ningún libro con ese ISBN!");
            return;
        }

        Libro libroElegido = libros.get(isbn);
        
        int opcion;
        do {
            System.out.println("--- MODIFICADOR DE DATOS DEL VEHÍCULO ---");
            System.out.println("1. Cambiar Titulo");
            System.out.println("2. Cambiar Escritor");
            System.out.println("3. Cambiar Año de Publicación");
            System.out.println("4. Volver");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    libroElegido.setTitulo(readString("Introduzca el titulo modificado: "));
                    System.out.println("Título modificado correctamente");
                break;
                case 2:
                    libroElegido.setEscritor(readString("Introduzca el escritor del libro modificado: "));
                    System.out.println("Escritor modificado correctamente");
                break;
                case 3: 
                    libroElegido.setAnioPublicacion(readInt("Introduzca el año de publicación modificado: "));
                    System.out.println("Año de publicación modificado correctamente");
                break;
                case 4:
                    opcion = 4;
                break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    

    public static void eliminarLibro() {

        if (libros.isEmpty()) {
            System.out.println("\n\n\n¡No es posible modificar un libro si no tienes ninguno!");
            return;
        }
            
        String isbn;

        listarLibros();
        isbn = readString("\n\n\nIntroduzca el ISBN del libro a modificar: ");

        if (!libros.containsKey(isbn)){
            System.out.println("No tienes ningún libro con ese ISBN!");
            return;
        }

        libros.remove(isbn);
        System.out.println("El libro ha sido eliminado correctamente");
        
    }

    
    
    public static void listarLibros() {
        
        if (libros.isEmpty()) {
            System.out.println("\n\n\n¡No es posible modificar un libro si no tienes ninguno!");
            return;
        }

        System.out.printf("\n\n\n");
        for (Libro libro : libros.values()) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("ISBN " + libro.getIsbn());
            System.out.println("---------------------------------------------------------------------------");
            System.out.println(" - TITULO: " + libro.getTitulo());
            System.out.println(" - ESCRITOR: " + libro.getEscritor());
            System.out.println(" - AÑO PUBLICACIÓN: " + libro.getAnioPublicacion());
            System.out.println("---------------------------------------------------------------------------");
        }
    }


}
