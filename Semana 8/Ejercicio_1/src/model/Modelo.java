package model;

import java.util.ArrayList;
import java.util.Random;

import controller.Controlador;
import model.furbo.*;

public class Modelo {

    private Controlador controlador;
    ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
    ArrayList<Partido> listaPartidos = new ArrayList<Partido>();

    public ArrayList<Partido> getListaPartidos() {
        return listaPartidos;
    }
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void almacenarEquipo(String nombre) {
        Equipo equipo = new Equipo(nombre);
        listaEquipos.add(equipo);
    }

    public void configurarPartidos(int numPartidos) {
        
        int j = listaEquipos.size();

        for (int i = 0; i < numPartidos; i++) {
            j--;
            boolean seguro = false;
            char resultado;
            Equipo equipo1 = listaEquipos.get(i);
            Equipo equipo2 = listaEquipos.get(j);
            do {
                resultado = controlador.solicitarResultado(equipo1.getNombre(), equipo2.getNombre());
                if (resultado != '1' && resultado != 'x' && resultado != 'X' && resultado != '2'){
                    controlador.mostrarMensaje("[ERROR] Carácter introducido no válido. Introduzca una opción (1 - X - 2)", 0);
                }
                else {
                    seguro = true;
                }
            } while (!seguro);
            
            Partido partido = new Partido(equipo1, equipo2, resultado);

            listaPartidos.add(partido);
            System.out.println("wqdewfewfewfewf");
        }

    }
}
