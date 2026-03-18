package view;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.coti.tools.Esdia;
import model.Factura;

public class Vista {

    
    public int mostrarMenuPrincipal(int saltosAdicionales) {

        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }

        System.out.println("--- MENU FACTURAS ---");
        System.out.println("1. Listado facturas con valor superior al introducido");
        System.out.println("2. Listado total de facturas");
        System.out.println("3. Exportar a HTML Simple");
        System.out.println("4. Exportar a CSV (delimitado por comas)");
        System.out.println("5. Salir");
        return Esdia.readInt("Introduzca una opción: ");
    }


    public void mostrarFacturas(List<Factura> listaFacturas) {

        if (listaFacturas.isEmpty()) System.err.println("[ERROR : mostrarFacturas] La lista recibida está vacía");
        else{
            int i = 0;

            System.out.printf("\n-----------------------------------------------------------------VISUALIZADOR DE FACTURAS-----------------------------------------------------------------");
            for (Factura f : listaFacturas) {
                i++;
                System.out.printf("\n|  [ Factura %2d ]  %-20s |  %-5.2f |  %-11s  |  %7.2f  |  %-9s  |  %-17s  |  %-26s  |  %-2.2f  |", i,
                    f.getConcepto(), f.getDescuento(), f.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    f.getImporte(), f.getNif(), f.getNombreCliente(), f.getDireccion(), f.getIva());
            }
            System.out.printf("\n----------------------------------------------------------------------------------------------------------------------------------------------------------");

        }



    }





    //OTRAS FUNCIONES
    public void mostrarMensaje(String mensaje, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }
            System.out.println(mensaje);
    }

    public void mostrarError(String mensaje, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.err.printf("\n");
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