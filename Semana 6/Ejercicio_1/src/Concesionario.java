import static com.coti.tools.Esdia.*;

public class Concesionario {

    private static final int MAX_SIZE = 5;
    private static Coche []coches = new Coche[MAX_SIZE];
    private static int recuentoCoches = 0;

    public static void main(String[] args) throws Exception {
        
        int opcion;
        do {
            System.out.println("\n--- MENU CRUD ---");
            System.out.println("1. Agregar coche");
            System.out.println("2. Eliminar coche");
            System.out.println("3. Modificar coche");
            System.out.println("4. Listar coche");
            System.out.println("5. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1: agregarCoche(); break;
                case 2: eliminarCoche(); break;
                case 3: modificarCoche(); break;
                case 4: listarCoches(); break;
                case 5: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }



    public static void agregarCoche() {

        if (recuentoCoches < MAX_SIZE) {

            String marca = readString("\n\n\nIntroduzca la marca del nuevo coche: ");
            String modelo = readString("Introduzca el modelo del nuevo coche: ");
            int anioMatriculacion = readInt("Introduzca el año de fabricación del nuevo coche: ");

            coches[recuentoCoches] = new Coche(recuentoCoches, marca, modelo, anioMatriculacion);
            recuentoCoches++;
            System.out.println("Nuevo coche añadido correctamente");
        } 
        else {
            System.out.println("\n\n\n¡Bruto! No te entran más coches en el garaje.");
        }
    }

    
    
    public static void modificarCoche() {

        if (recuentoCoches != 0) {
            
            boolean seguro = true;
            int cocheElegido;

            do {
                listarCoches();
                cocheElegido = readInt("\n\n\nIntroduzca el id del coche a modificar: ");

                if (cocheElegido > 0 || cocheElegido < recuentoCoches){
                    seguro = false;
                }
                else {
                    System.out.println("Coche introducido no válido");
                }
                
            } while (seguro);
            
            int opcion;
            do {
                System.out.println("--- MODIFICADOR DE DATOS DEL VEHÍCULO ---");
                System.out.println("1. Cambiar Marca");
                System.out.println("2. Cambiar Modelo");
                System.out.println("3. Cambiar Año de Fabricación");
                System.out.println("4. Volver");
                opcion = readInt("Ingrese una opción: ");

                switch (opcion) {
                    case 1:
                        coches[cocheElegido-1].setMarca(readString("Introduzca la marca modificada: "));
                        System.out.println("Coche modificado correctamente");
                    break;
                    case 2:
                        coches[cocheElegido-1].setModelo(readString("Introduzca la marca modificada: "));
                        System.out.println("Coche modificado correctamente");
                    break;
                    case 3: 
                        coches[cocheElegido-1].setAnioMatriculacion(readInt("Introduzca la marca modificada: "));
                        System.out.println("Coche modificado correctamente");
                    break;
                    case 4:
                        opcion = 4;
                    break;
                    default: System.out.println("Opción no válida.");
                }
            } while (opcion != 4);
        }
        else {
            System.out.println("\n\n\n¡No es posible modificar un coche si no tienes ninguno!");
        }
    }

    

    public static void eliminarCoche() {

        if (recuentoCoches != 0) {
            boolean seguro = true;
            int cocheElegido;
            do {
                listarCoches();
                cocheElegido = readInt("\n\n\nIntroduzca el id del coche a eliminar: ");

                if (cocheElegido > 0 || cocheElegido < recuentoCoches){
                    seguro = false;
                }
                else {
                    System.out.println("Coche introducido no válido");
                }
                
            } while (seguro);

            for (int i = cocheElegido; i < recuentoCoches; i++) {
                coches[i].setId(coches[i+1].getId());
                coches[i].setMarca(coches[i+1].getMarca());
                coches[i].setModelo(coches[i+1].getModelo());
                coches[i].setAnioMatriculacion(coches[i+1].getAnioMatriculacion());
            }
            recuentoCoches--;
        }
        else {
            System.out.println("\n\n\n¡No es posible eliminar un coche si no tienes ninguno!");
        }
    }

    
    
    public static void listarCoches() {
        
        if (recuentoCoches != 0) {
                
            System.out.printf("\n\n\n");
            for (int i = 0; i < recuentoCoches; i++) {

                System.out.println("---------------------------------------------------------------------------");
                System.out.println("COCHE " + (i+1));
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(" - MARCA: " + coches[i].getMarca());
                System.out.println(" - MODELO: " + coches[i].getModelo());
                System.out.println(" - AÑO MATRICULACIÓN: " + coches[i].getAnioMatriculacion());
                System.out.println("---------------------------------------------------------------------------");
            }
        }
        else {
            System.out.println("\n\n\n¿Cómo te voy a mostrar coches?¡Si no tienes ninguno!");
        }
    }


}
