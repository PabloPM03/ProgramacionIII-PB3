package controller;

import view.Vista;

import java.io.IOException;

import model.Modelo;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador (Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }


    public void iniciar() {

        String ficheroTSV = "facturas.tsv";
        String ficheroCSV = "facturas.csv";
        String ficheroHTML = "facturas.html";

        int numFacturas;

        vista.mostrarMensaje("[INFO] Iniciando conversión del fichero " + ficheroTSV + " ...", 3);

        try {       //MANEJO DEL CÓDIGO PRINCIPAL DEL PROGRAMA

                    //CARGA DEL FICHERO TSV EN MEMORIA
            modelo.leerTSV(ficheroTSV);
            numFacturas = modelo.getNumFacturas();
            vista.mostrarMensaje("[INFO] ¡Lectura Correcta!, se han cargado " + numFacturas + " facturas", 0);
        }
        catch (IOException e) {
            vista.mostrarError("[ERROR : " + e.getMessage(), 3);
        }

        boolean salir = false;
        do {
            try {
                switch (vista.mostrarMenuPrincipal(3)) {
                    case 1:
                        vista.mostrarFacturas(modelo.getFacturasFiltradasImporte(Double.parseDouble(vista.leerTeclado("Introduce el importe mínimo para filtrar las facturas: ", 3)))); 
                        break;
                
                    case 2:
                        vista.mostrarFacturas(modelo.getFacturas());
                        break;
                
                    case 3:         //ALMACENAMIENTO EN FICHERO HTML
                        vista.mostrarMensaje("[INFO] Generando archivo HTML", 1);
                        modelo.generarHTML(ficheroHTML);
                        vista.mostrarMensaje("[INFO] Archivo " + ficheroHTML + " creado correctamente", 0);
                        break;
                
                    case 4:         //ALMACENAMIENTO EN FICHERO CSV
                        vista.mostrarMensaje("[INFO] Generando archivo CSV", 1);
                        modelo.generarCSV(ficheroCSV);
                        vista.mostrarMensaje("[INFO] Archivo " + ficheroCSV + " creado correctamente", 0);
                        break;
                
                    case 5:
                        salir = true;
                        break;
                
                    default:
                        break;
                }  
            }
            catch (IOException e) {
                vista.mostrarError("[ERROR : " + e.getMessage(), 3);
            }
            catch (NumberFormatException e) {
                vista.mostrarError("[ERROR : vista.leerTeclado] No se ha introducido un número válido: " + e.getMessage(), 3);
            }


        } while (!salir);
                //FIN DEL PROGRAMA
        vista.mostrarMensaje("[INFO] FIN DEL PROGRAMA. ¡Hasta otra, que tenga un buen día!", 1);
    }
}
