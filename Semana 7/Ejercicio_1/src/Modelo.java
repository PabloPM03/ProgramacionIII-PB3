public class Modelo {

    private int num1, num2, suma;

    public void guardarNumero(int indice, int numeroIntroducido) {

        switch (indice) {
            case 1:
                num1 = numeroIntroducido;
            break;
            case 2:
                num2 = numeroIntroducido;
            break;
        }
    }

    public void calcularSuma() {
        suma = num1 + num2;
    }

    public int getSuma() {
        return suma;
    }
}
