package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Modelo {

    private List<Factura> listaFacturas = new ArrayList<>();
    private int numFacturas;


    public void leerTSV(String rutaFichero) throws IOException {

        File archivoTSV = new File(rutaFichero);
        Scanner scanTSV = new Scanner(archivoTSV, StandardCharsets.UTF_8);
        
        if (scanTSV.hasNextLine()) scanTSV.nextLine();      //SALTAMOS LA CABECERA

        while (scanTSV.hasNextLine()) {                     //LEEMOS EL CONTENIDO
            String linea = scanTSV.nextLine();
            if (!linea.trim().isEmpty()) {
                String[] datos = linea.split("\t");
                if (datos.length >= 0) {
                    Factura factura = new Factura(datos[0], Float.parseFloat(datos[1]), datos[2], Double.parseDouble(datos[3]),
                        datos[4], datos[5], datos[6], Float.parseFloat(datos[7]));
                    this.listaFacturas.add(factura);
                }
            }
            numFacturas = listaFacturas.size();
        }
        scanTSV.close();
    }


    public List<Factura> getFacturasFiltradasImporte(double importeMinimo) {

        List<Factura> facturasFiltradas = new ArrayList<>();

        for (Factura f : this.listaFacturas) {
            if (f.getImporte() > (importeMinimo * (1 - f.getDescuento()) * (1 + f.getIva()))) facturasFiltradas.add(f);
        }
        return facturasFiltradas;
    }
    public List<Factura> getFacturas() {return this.listaFacturas;}


    public void generarCSV(String ruta) throws IOException {

        if (this.listaFacturas == null || this.listaFacturas.isEmpty()) {
            throw new IOException("generarCSV] Lista de facturas vacía");
        }
        
        ArrayList<String> lineas = new ArrayList<>();

            //CABECERA
        lineas.add("Concepto;Descuento;Fecha;Importe;NIF;Cliente;Direccion;IVA");

            //AQUI SE AÑADEN LAS DISTINTAS FACTURAS LINEA POR LINEA
        for (Factura factura : listaFacturas) {
            lineas.add(factura.getFilaCSV(";"));
        }
        try {
            Path path = Paths.get(ruta);
            Files.write(path, lineas, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IOException("generarCSV] Fallo al generar archivo CSV");
        }
    }


    public void generarHTML(String ruta) throws IOException {

        if (this.listaFacturas == null || this.listaFacturas.isEmpty()) {
            throw new IOException("generarHTML] Lista de facturas vacía");
        }
        
        ArrayList<String> lineas = new ArrayList<>();

            //CABECERA
        lineas.add("<br>Concepto Descuento Fecha Importe NIF Cliente Direccion IVA</br>");

            //AQUI SE AÑADEN LAS DISTINTAS FACTURAS LINEA POR LINEA
        for (Factura factura : listaFacturas) {
            lineas.add(factura.getFilaHTML());
        }
        try {
            Path path = Paths.get(ruta);
            Files.write(path, lineas, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IOException("generarHTML] Fallo al generar archivo CSV");
        }

    }






    //GETTERS Y SETTERS
    public int getNumFacturas() {return numFacturas;}
}
