public class Alumno {

    private String nombre;
    private Float examenParcial1, examenParcial2, examenFinal;

    private Float notaFinal;

    public Alumno(String nombre, float examenParcial1, float examenParcial2, float notaFinal, float examenFinal){

        this.nombre = nombre;
        this.examenParcial1 = examenParcial1;
        this.examenParcial2 = examenParcial2;
        this.examenFinal = examenFinal;
        this.notaFinal = notaFinal;

    }

    public String getNombre() {
        return nombre;
    }
    public Float getExamenParcial1() {
        return examenParcial1;
    }
    public Float getExamenParcial2() {
        return examenParcial2;
    }
    public Float getExamenFinal() {
        return examenFinal;
    }
    public Float getnotaFinal() {
        return notaFinal;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setExamenParcial1(Float examenParcial1) {
        this.examenParcial1 = examenParcial1;
    }
    public void setExamenParcial2(Float examenParcial2) {
        this.examenParcial2 = examenParcial2;
    }
    public void setExamenFinal(Float examenFinal) {
        this.examenFinal = examenFinal;
    }
    public void setnNotaFinal(Float notaFinal) {
        this.notaFinal = notaFinal;
    }

}
