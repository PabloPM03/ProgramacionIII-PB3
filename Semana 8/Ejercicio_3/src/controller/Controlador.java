package controller;

import java.util.ArrayList;

import view.Vista;
import model.ModeloPersona;
import model.Persona;

public class Controlador {
    
    ModeloPersona m;
    Vista v;

    public Controlador(ModeloPersona m, Vista v) {
        this.m = m;
        this.v = v;
        v.setControlador(this);
    }

    public void init() {
        int cargados = m.cargarDatosFichero("datos.txt");
        
        if (cargados > 0) {
            v.mostrarMensaje("Carga automática: Se han cargado " + cargados + " registros desde datos.txt.", 3);
        } else if (cargados == 0) {
            v.mostrarMensaje("ERROR: No hay datos cargados de datos.txt", 3);
        } else {
            v.mostrarMensaje("Error leyendo datos.txt.", 3);
        }

        v.mostrarMenu();
    }

    
    public void importarFicheroUsuario(String nombreFichero) {
        int n = m.cargarDatosFichero(nombreFichero);
        if (n > 0) {
            v.mostrarMensaje(n + " registros añadidos.", 3);
        } else {
            v.mostrarMensaje("No se pudo importar", 3);
        }
    }

    public ArrayList<Persona> getPersonas() {
        return m.getListaPersonas();
    }
}
