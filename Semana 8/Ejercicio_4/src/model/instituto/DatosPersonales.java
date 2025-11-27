package model.instituto;

public class DatosPersonales {

    private String nombre;
    private String apellidos;
    private int edad;
    private String NIF;

    public DatosPersonales(String nombre, String apellidos, int edad, String nIF) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        NIF = nIF;
    }



    public String getApellidos() {return apellidos;}
    public int getEdad() {return edad;}
    public String getNIF() {return NIF;}
    public String getNombre() {return nombre;}

    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public void setEdad(int edad) {this.edad = edad;}
    public void setNIF(String nIF) {NIF = nIF;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    
}
