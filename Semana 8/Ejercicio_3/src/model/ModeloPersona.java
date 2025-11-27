package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.coti.tools.Rutas;

public class ModeloPersona {

    private ArrayList<Persona> listaPersonas;

    public ModeloPersona() {
        this.listaPersonas = new ArrayList<>();
    }



    public int cargarDatosFichero(String nombreFichero) {
        Path ruta = Rutas.pathToFileOnDesktop(nombreFichero);
        
        if (!Files.exists(ruta)) {
            return 0;
        }

        try {
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            int i = 0;
            for (String linea : lineas) {
                if (!linea.isBlank()) {
                    Persona p = Persona.cadenaTabulada(linea);
                    if (p != null) {
                        listaPersonas.add(p);
                        i++;
                    }
                }
            }
            return i;
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
            return -1;
        }
    }

    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }
}
