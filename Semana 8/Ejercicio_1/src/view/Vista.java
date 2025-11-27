package view;

import java.util.ArrayList;
import model.furbo.*;

public class Vista {

    public void mostrarQuiniela(ArrayList<Partido> listaPartidos, int numPartidos) {

        Equipo equipo1, equipo2;
        
        System.err.printf("\n----------------------------------------------------------------------------------------------");
        System.err.printf("\n| BIENVENIDO A LA QUINIELA                                                                   |");
        System.err.printf("\n|--------------------------------------------------------------------------------------------|");
        for (int i = 0; i < numPartidos; i++ ) {
            Partido partido = listaPartidos.get(i);
            equipo1 = partido.getEquipo1();
            equipo2 = partido.getEquipo2(); 
            System.out.printf("\n| %-40s  --  %-40s | %c |", equipo1.getNombre(), equipo2.getNombre(), partido.getResultado());
        }
        System.err.printf("\n----------------------------------------------------------------------------------------------");

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