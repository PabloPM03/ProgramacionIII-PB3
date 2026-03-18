package view;

import java.util.HashMap;

import com.coti.tools.Esdia;

import model.Libro;

public class Vista {

    public char mostrarMenuPrincipal() {

        System.out.println("\n--- MENU CRUD ---");
        System.out.println("1. Agregar libro");
        System.out.println("2. Eliminar libro");
        System.out.println("3. Modificar libro");
        System.out.println("4. Listar libro");
        System.out.println("5. Salir");
        String opcion = Esdia.readString("Introduzca una opción: ");

        return opcion.charAt(0);
    }


    public char mostrarMenuModificacion() {
        
        System.out.println("--- MODIFICADOR DE DATOS DEL VEHÍCULO ---");
        System.out.println("1. Cambiar Titulo");
        System.out.println("2. Cambiar Escritor");
        System.out.println("3. Cambiar Año de Publicación");
        System.out.println("4. Volver");
        String opcion = Esdia.readString("Introduzca una opción: ");

        return opcion.charAt(0);
    }

    
    public void listarLibros(HashMap<String, Libro> libros) {
        

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




    //OTRAS FUNCIONES
    public void mostrarMensaje(String mensaje, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }
        System.out.println(mensaje);
    }

    public String leerTeclado(String mensaje, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }
        return com.coti.tools.Esdia.readString(mensaje);
    }
}
