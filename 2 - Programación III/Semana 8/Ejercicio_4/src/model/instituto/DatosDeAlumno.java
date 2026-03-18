package model.instituto;

public class DatosDeAlumno {

    private DatosPersonales personales;
    private Direccion direccion;

    public DatosDeAlumno(DatosPersonales personales, Direccion direccion) {
        this.personales = personales;
        this.direccion = direccion;
    }


    public Direccion getDireccion() {return direccion;}
    public DatosPersonales getPersonales() {return personales;}

    public void setDireccion(Direccion direccion) {this.direccion = direccion;}
    public void setPersonales(DatosPersonales personales) {this.personales = personales;}

}
