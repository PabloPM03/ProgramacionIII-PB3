import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();

        p1.setName("Victor");
        p1.setHeight( 1.79f);
        p1.setWeight( 72.20f);

        p2.setName("David");
        p2.setHeight( 1.83f);
        p2.setWeight( 69.40f);

        p3.setName("Fernando");
        p3.setHeight( 1.60f);
        p3.setWeight( 75.37f);


            //CÓDIGO CÁLCULO DE LA ALTURA
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
}
