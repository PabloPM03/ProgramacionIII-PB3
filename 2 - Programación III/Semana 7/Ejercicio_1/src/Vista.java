public class Vista {

    public void mostrarMensaje(String mensaje, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }
        System.out.println(mensaje);
    }


    public String mostrarMenuPrincipal() {

        System.out.println("----------------SUMA DE DOS NÚMEROS ENTEROS----------------");
        System.out.println("1. Leer los numeros");
        System.out.println("2. Calcular suma");
        System.out.println("3. Mostrar el resultado");
        System.out.println("q. Salir");
        
        String opcion = com.coti.tools.Esdia.readString("Introduzca una opción: ");

        return opcion;

    }


    public int leerNumero(int indice) {

        int numeeoIntroducido = com.coti.tools.Esdia.readInt("Introduzca el numero " + indice + ": ");
        return numeeoIntroducido;

    }


    public void mostrarResultado(int suma, int saltosAdicionales) {
        for (int i = 0; i < saltosAdicionales; i++) {
            System.out.printf("\n");
        }
        System.out.println("El resultado de la suma es " + suma);
    }
}
