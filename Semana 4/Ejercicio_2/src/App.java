
public class App {
    public static void main(String[] args) throws Exception {
        
        if (args.length == 3) {
            try {
                Person p = new Person();

                p.setName(args[0]);
                p.setHeight(Float.parseFloat(args[1]));
                p.setWeight(Float.parseFloat(args[2]));

                imprimirDatosPersona(p);
            }
            catch (NumberFormatException exception) {
                System.err.println("ERROR: Uno de los argumentos introducidos NO es válido: Fomrato: (Nombre Altura Peso)");
            }
        }
        else {
            System.err.println("ERROR: Número de argumentos introducidos no válido. Debes introducir 3 argumentos con el formato (Nombre Altura Peso)");
        }
        

    }

    private static void imprimirDatosPersona(Person p) {
        System.out.println("|---------------------------------|");
        System.out.println("| Nombre: " + p.getName() + "                   |");
        System.out.println("|---------------------------------|");
        System.out.println("| Altura: " + p.getHeight() + "cm  | Peso: " + p.getWeight() + "kg  |");
        System.out.println("|---------------------------------|");
        System.out.println("| IMC: " + p.getIMC() + "                  |");
        System.out.println("|---------------------------------|");
        System.out.println();

    }
}
