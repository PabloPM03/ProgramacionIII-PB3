package model;

import java.util.Locale;

public class Persona {

    private String nombre;
    private String telefono;
    private double peso;

    
    
    public Persona(String nombre, String telefono, double peso) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.peso = peso;
    }


    public String getNombre() {return nombre;}public double getPeso() {return peso;}
    public String getTelefono() {return telefono;}

    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPeso(double peso) {this.peso = peso;}
    public void setTelefono(String telefono) {this.telefono = telefono;}


    public static Persona cadenaTabulada(String linea) {String[] partes = linea.split("\t");
        try {
            if (partes.length < 3) return null;
            
            String nombre = partes[0].trim();
            String telefono = partes[1].trim();
            double peso = Double.parseDouble(partes[2].trim().replace(",", "."));
            
            return new Persona(nombre, telefono, peso);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String encabezadoTabla() {
        return String.format("| %-20s | %-15s | %-10s |", "NOMBRE", "TELÉFONO", "PESO (kg)");
    }

    public String filaTabla() {
        return String.format(Locale.ENGLISH, "| %-20s | %-15s | %10.2f |", this.nombre, this.telefono, this.peso);
    }
}
