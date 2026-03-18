package model.instituto;

public class Direccion {
    private String calle;
    private int numero;
    private int piso;
    private String letra;

    public Direccion(String calle, int numero, int piso, String letra) {
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.letra = letra;
    }



    public String getCalle() {return calle;}
    public String getLetra() {return letra;}
    public int getNumero() {return numero;}
    public int getPiso() {return piso;}

    public void setCalle(String calle) {this.calle = calle;}
    public void setLetra(String letra) {this.letra = letra;}
    public void setNumero(int numero) {this.numero = numero;}
    public void setPiso(int piso) {this.piso = piso;}



}