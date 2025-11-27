package controller;

import view.Vista;
import model.Modelo;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador (Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }


    public void iniciar() {

        int nEquipos;
        boolean seguro = false;
        do {
            nEquipos = Integer.parseInt(vista.leerTeclado("Introduzca un número de equipos para hacer la quiniela (Debe ser un número par mayor que 0): ", 0));
            if (nEquipos != 0 && (nEquipos % 2) == 0) {
                seguro = true;
            }
        } while (!seguro);

        for (int i = 0; i < nEquipos; i++) {
            modelo.almacenarEquipo(vista.leerTeclado("Introduzca un equipo: ",0));
        }
        modelo.configurarPartidos(nEquipos/2);

        vista.mostrarQuiniela(modelo.getListaPartidos(), nEquipos/2);

    }


    public void mostrarMensaje(String mensaje, int saltosAdicionales) {
        vista.mostrarMensaje(mensaje, saltosAdicionales);
    }


    public char solicitarResultado(String nombreEquipo1, String nombreEquipo2) {

        String resultado = vista.leerTeclado("Introduzca el resultado del partido | " + nombreEquipo1 + " - " + nombreEquipo2 + " | --> ", 1);

        return resultado.charAt(0);
    }
}
