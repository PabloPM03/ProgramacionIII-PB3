import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in); 
        
        System.out.println("Precio Manzanas: ");
        float precioManzana = Float.parseFloat(sc.nextLine());
        precioManzana= (float) (precioManzana + precioManzana * 0.21);

        System.out.println("Precio Peras: ");
        float precioPera = Float.parseFloat(sc.nextLine());
        precioPera = (float) (precioPera + precioPera * 0.21);

        System.out.println("Cuántos clientes tienes en la tienda? ");
        int numClientes = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numClientes; i++) {
            
            Cliente []cliente = new Cliente [3];

            cliente[i] = new Cliente();

            System.out.printf("Kg de Manzanas que ha cogido el clientete: \n", i);
            cliente[i].setManzanas(Float.parseFloat(sc.nextLine()));
            System.out.printf("Kg de Peras que ha cogido el clientete: \n", i);
            cliente[i].setPeras(Float.parseFloat(sc.nextLine()));

            
            float importeManzanas = (Integer.parseInt(cliente[i].getManzanas())) * precioManzana;
            float importePeras = (Integer.parseInt(cliente[i].getPeras())) * precioPera;
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|   Cliente Nº:                                                                    |");
            System.out.println("|-------------------------------------------------------------------------------|");
            System.out.println("| Manzanas  | " + cliente[i].getManzanas() + " kg  | Precio con iva: " + precioManzana + " €/kg | Precio final: " + importeManzanas + "€     |");
            System.out.println("| Manzanas  | " + cliente[i].getPeras() + " kg  | Precio con iva: " + precioPera + " €/kg | Precio final: " + importePeras + "€     |");
            System.out.println("---------------------------------------------------------------------------------");

        }
        sc.close();
    }
}
