package model.furbo;

public class Partido {

    private Equipo equipo1, equipo2;
    private char resultado;

    public Partido(Equipo equipo1, Equipo equipo2, char resultado) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resultado = resultado;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
    public char getResultado() {
        return resultado;
    }
    
    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }
    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }
    public void setResultado(char resultado) {
        this.resultado = resultado;
    }

}
