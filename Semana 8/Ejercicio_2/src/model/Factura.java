package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Factura {
    private String concepto, nif, nombreCliente, direccion;
    private Float descuento, iva;
    private LocalDate fecha;
    private Double importe;

    public Factura(String concepto, Float descuento, String fecha, Double importe,
        String nif, String nombreCliente, String direccion, Float iva) {
        this.concepto = concepto;
        this.descuento = descuento;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.parse(fecha, formatter);

        this.importe =importe;
        this.nif = nif;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.iva = iva;
    }



    public String getFilaCSV(String delimiter) {

        String fechaString = this.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format(Locale.ENGLISH, "%s" + delimiter + "%.2f" + delimiter + "%s" + delimiter + "%.2f" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%.2f", 
            this.concepto, this.descuento, fechaString, this.importe, this.nif, this.nombreCliente, this.direccion, this.iva);
    }



    public String getFilaHTML() {

        String fechaString = this.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format(Locale.ENGLISH,
            "<br>" + "\n\t" + 
                "<td>%s </td>" + 
                "<td>%.2f </td>" + 
                "<td>%s </td>" + 
                "<td>%.2f </td>" + 
                "<td>%s </td>" + 
                "<td>%s </td>" + 
                "<td>%s </td>" + 
                "<td>%.2f</td>" + "\n" + 
            "</br>", 
            this.concepto, this.descuento, fechaString, this.importe, this.nif, this.nombreCliente, this.direccion, this.iva);
    }









    public String getConcepto() {return concepto;}
    public Float getDescuento() {return descuento;}
    public String getDireccion() {return direccion;}

    public LocalDate getFecha() {return fecha;}                                             //AQUI NO HAGO PARSE YA QUE SERÁ SIEMPRE MEJOR SIEMPRE OBTENER EL VALOR 
                                                                                            //TIPO LOCALEDATE QUE UN SIMPLE STRING
    public Double getImporte() {return importe;}
    public Float getIva() {return iva;}
    public String getNif() {return nif;}
    public String getNombreCliente() {return nombreCliente;}

    public void setConcepto(String concepto) {this.concepto = concepto;}
    public void setDescuento(Float descuento) {this.descuento = descuento;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public void setFecha(String fecha) {                                                    //INCLUYO 2 POSIBILIDADES EN FUNCIÓN DE LA NECESIDAD PARA FACILITAR FUTURO CÓDIGO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.parse(fecha, formatter);
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setImporte(Double importe) {this.importe = importe;}
    public void setIva(Float iva) {this.iva = iva;}
    public void setNif(String nif) {this.nif = nif;}
    public void setNombreCliente(String nombreCliente) {this.nombreCliente = nombreCliente;}
}
