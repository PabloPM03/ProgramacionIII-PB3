package view;

import java.util.List;

import com.coti.tools.Esdia;

import controller.Controlador;
import model.Persona;

public class Vista {
private Controlador c;

    public void setControlador(Controlador c) {
        this.c = c;
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- GESTIÓN DE PERSONAS ---");
            System.out.println("1. Importar datos de otro fichero TSV.");
            System.out.println("2. Mostrar listado de personas.");
            System.out.println("3. Salir.");
            
            int opcion = Esdia.readInt("Seleccione una opción: ", 1, 3);
            
            switch (opcion) {
                case 1:
                    String nombreFichero = Esdia.readString_ne("Introduzca nombre del fichero (ej: nuevos.txt): ");
                    c.importarFicheroUsuario(nombreFichero);
                    break;
                case 2:
                    List<Persona> lista = c.getPersonas(); 
                    
                    if (lista.isEmpty()) {
                        System.out.println("No hay personas registradas.");
                    } else {
                        System.out.println(Persona.encabezadoTabla());
                        for (Persona p : lista) {
                            System.out.println(p.filaTabla());
                        }
                    }
                    break;
                case 3:
                    String confirm = Esdia.readString_ne("¿Seguro que desea salir? (s/n): ");
                    if (confirm.equalsIgnoreCase("s")) {
                        salir = true;
                    }
                    break;
            }
        }
}

    //OTRAS FUNCIONES
    public void mostrarMensaje(String mensaje, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }
            System.out.println(mensaje);
    }
}
