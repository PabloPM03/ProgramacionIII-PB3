public class App {
    public static void main(String[] args) throws Exception {

        if (args.length == 2) {
            try {
                int suma =  Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
                System.out.println("Los argumentos introducidos eran: " + Integer.parseInt(args[0]) + " " + Integer.parseInt(args[1]));
                System.out.println("La suma de ambos argumentos es: " + suma);
            }
            catch (NumberFormatException exception) {
                System.err.println("ERROR: Argumento introducido NO VÁLIDO, tiene que ser un NÚMERO");
            }
        }
        else {
            System.err.println("ERROR: Número de argumentos introducidos no válido. Debes introducir 2 argumentos numéricos");
        }
        
        
    }
}
