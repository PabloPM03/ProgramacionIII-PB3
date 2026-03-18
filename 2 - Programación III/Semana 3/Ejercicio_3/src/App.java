import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("----------DATOS DE LA PERSONA 1----------");
        Person p1 = obtenerPersona();
        System.out.println();
        System.out.println("----------DATOS DE LA PERSONA 2----------");
        Person p2 = obtenerPersona();
        System.out.println();
        System.out.println("----------DATOS DE LA PERSONA 3----------");
        Person p3 = obtenerPersona();
        System.out.println();

        System.out.println("----------DATOS DE LA PERSONA 1----------");
        imprimirDatosPersona(p1);
        System.out.println("----------DATOS DE LA PERSONA 2----------");
        imprimirDatosPersona(p2);
        System.out.println("----------DATOS DE LA PERSONA 3----------");
        imprimirDatosPersona(p3);

            //CÓDIO CÁLCULO MAYOR ALTURA
        //P1 ES MÁS ALTO O IGUAL QUE ALGUNO DE LOS OTROS 2
        if (p1.getHeight() >= p2.getHeight() && p1.getHeight() >= p3.getHeight()) {
            //LOS 3 PESAN LO MISMO
            if (p1.getHeight() == p2.getHeight() && p1.getHeight() == p3.getHeight()) {
                System.out.println("Las tres personas, " + p1.getName() + ", " +p2.getName() + " y " +p3.getName() + " tienen la misma altura (" + p1.getHeight() + "cm)");    
            }
            else if (p1.getHeight() == p2.getHeight()) {
                System.out.println("Las personas más altas son " + p1.getName() + " y " +p2.getName() + " con una altura de " + p1.getHeight() + "cm");
            }
            else if (p1.getHeight() == p3.getHeight()) {
                System.out.println("Las personas más altas son " + p1.getName() + " y " +p3.getName() + " con una altura de " + p1.getHeight() + "cm");
            }
            else {
                System.out.println("La persona más alta es " + p1.getName() + " con una altura de " + p1.getHeight() + "cm");
            }
        }

        //P2 ES MÁS ALTO O IGUAL QUE P3
        else if (p2.getHeight() >= p3.getHeight()) {
            if (p2.getHeight() == p3.getHeight()) {
                System.out.println("Las personas más altas son " + p2.getName() + " y " +p3.getName() + " con una altura de " + p2.getHeight() + "cm");
            }
            else {
                System.out.println("La persona más alta es " + p2.getName() + " con una altura de " + p2.getHeight() + "cm");
            }
        }

        //P3 ES EL MÁS ALTO
        else {
                System.out.println("La persona más alta es " + p3.getName() + " con una altura de " + p3.getHeight() + "cm");
        }
 

        
            //CÓDIGO CÁLCULO MAYOR PESO
        //P1 ES MÁS ALTO O IGUAL QUE ALGUNO DE LOS OTROS 2
        if (p1.getWeight() >= p2.getWeight() && p1.getWeight() >= p3.getWeight()) {
            //LOS 3 PESAN LO MISMO
            if (p1.getWeight() == p2.getWeight() && p1.getWeight() == p3.getWeight()) {
                System.out.println("Las tres personas, " + p1.getName() + ", " +p2.getName() + " y " +p3.getName() + " pesan lo mismo (" + p1.getWeight() + "kg)");    
            }
            else if (p1.getWeight() == p2.getWeight()) {
                System.out.println("Las personas que más pesan son " + p1.getName() + " y " +p2.getName() + " con un peso de " + p1.getWeight() + "kg");
            }
            else if (p1.getWeight() == p3.getWeight()) {
                System.out.println("Las personas que más pesan son " + p1.getName() + " y " +p3.getName() + " con un peso de " + p1.getWeight() + "kg");
            }
            else {
                System.out.println("La persona que más pesa es " + p1.getName() + " con un peso de " + p1.getWeight() + "kg");
            }
        }

        //P2 ES MÁS ALTO O IGUAL QUE P3
        else if (p2.getWeight() >= p3.getWeight()) {
            if (p2.getWeight() == p3.getWeight()) {
                System.out.println("Las personas que más pesan son " + p2.getName() + " y " +p3.getName() + " con un peso de " + p1.getWeight() + "kg");
            }
            else {
                System.out.println("La persona que más pesa es " + p2.getName() + " con un peso de " + p2.getWeight() + "kg");
            }
        }

        //P3 ES EL MÁS ALTO
        else {
                System.out.println("La persona que más pesa es " + p3.getName() + " con un peso de " + p3.getWeight() + "kg");
        }   
    }

    private static Person obtenerPersona() {
        
        Scanner scan = new Scanner(System.in);
        Person p = new Person();

        System.out.println("Introduzca el nombre de la persona: ");
        p.setName(scan.nextLine());

        System.out.println("Introduzca la altura de la persona: ");
        p.setHeight(Float.parseFloat(scan.next()));

        System.out.println("Introduzca el peso de la persona: ");
        p.setWeight(Float.parseFloat(scan.next()));

        return p;
    }

    private static void imprimirDatosPersona(Person p) {
        System.out.println("Nombre: " + p.getName());
        System.out.println("Altura: " + p.getHeight() + "cm");
        System.out.println("Peso: " + p.getWeight() + "kg");
        System.out.println("IMC: " + p.getIMC());
        System.out.println();

    }
}
