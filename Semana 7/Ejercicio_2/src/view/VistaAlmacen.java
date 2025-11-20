package view;

public class VistaAlmacen {
    
    //MENU PRINCIPAL
    public char mostrarMenuPrincipal() {
        
        String opcion;

        System.out.println("----------------GESTOR ALMACÉN----------------");
        System.out.println("1. Leer tabla de ventas");
        System.out.println("2. Leer tabla de precios");
        System.out.println("3. Calcular ingresos totales");
        System.out.println("4. Mostrar resultados");
        System.out.println("q. Salir");
        opcion = com.coti.tools.Esdia.readString("Introduzca una opción: ");

        return opcion.charAt(0);
    }


    //FUNCIONES DEL ENUNCADO
    public void MostrarResultados(Double ingresosTotales){
        System.out.printf("\n\n\nINGRESOS TOTALES DE LA EMPRESA: " + ingresosTotales + "\n");

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
