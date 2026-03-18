package Paquete2;
import Paquete1.Clase1;
import Paquete1.Clase2;


public class App {

    public static void main(String[] args) {
        
        int prueba;
        Clase1 clase1 = new Clase1();          // La Clase 1 es pública : Sí se puede acceder desde App
        Clase2 clase2 = new Clase2();          // La Clase 2 es sin atributo (Protegida por defecto para el paquete) : No se puede acceder desde App


        prueba = clase1.atributoPublico;       // Atributo Público : Accesible desde cualquier paquete
        prueba = clase1.atributo;              // Atributo Sin Modificador de Visibilidad (Por defecto Package-private) : Solo accesible desde el mismo paquete (Paquete1) o subpaquetes
        prueba = clase1.atributoPrivado;       //Atributo Privado : Solo accesible desde la propia clase
        prueba = clase1.atributoProtegido;     // Atributo Protejido : Solo accesible desde el mismo paquete (Paquete1) o subpacketes

        prueba = clase2.atributoPublico;       // No se puede acceder ya que Clase2 es protected
        prueba = clase2.atributo;              // No se puede acceder ya que Clase2 es protected
        prueba = clase2.atributoPrivado;       // No se puede acceder ya que Clase2 es protected
        prueba = clase2.atributoProtegido;     // No se puede acceder ya que Clase2 es protected


        //El objetivo de las diferentes visibilidades es la de reducir los accesos desde cualquier ubicación del programa
    }

}
