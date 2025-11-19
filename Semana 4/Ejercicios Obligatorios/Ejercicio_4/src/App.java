import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduce el numero de alumnos: ");
        int estudiantes = Integer.parseInt(scan.nextLine());        
        Alumno alumnos[] = new Alumno[5];

        for (int i = 0; i < estudiantes; i++){
  
            alumnos[i] = new Alumno(null,  0, 0, 0, 0);
            System.out.printf("\nIntroduzca el nombre del estudiante %d \n", i + 1);
            alumnos[i].setNombre(scan.nextLine());


            System.out.printf("Nota del primer examen parcial del estudiante %d\n", i + 1);
            alumnos[i].setExamenParcial1(Float.parseFloat(scan.nextLine()));

            System.out.printf("Nota del segundo examen parcial del estudiante %d\n", i + 1);
            alumnos[i].setExamenParcial2(Float.parseFloat(scan.nextLine()));

            System.out.printf("Nota del examen final del estudiante %d\n", i + 1);
            alumnos[i].setExamenFinal(Float.parseFloat(scan.nextLine()));


            float notaFinal = (float) (((alumnos[i].getExamenParcial1()) * 0.1) + ((alumnos[i].getExamenParcial2()) * 0.1) + ((alumnos[i].getExamenFinal()) * 0.8));
            alumnos[i].setnNotaFinal(notaFinal);

        }
        

        for (int i = 0; i < estudiantes; i++){

            System.out.println("---------------------------------------------------------");
            System.out.printf("|  Alumno: %s                                            |\n",alumnos[i].getNombre());
            System.out.println("|-------------------------------------------------------|");
            System.out.printf("| Primer Parcial   | Nota:  %2.3f  | Ponderada:  %2.3f  |\n", alumnos[i].getExamenParcial1(), (alumnos[i].getExamenParcial1()) * 0.1);
            System.out.printf("| Segundo Parcial  | Nota:  %2.3f  | Ponderada:  %2.3f  |\n", alumnos[i].getExamenParcial2(), (alumnos[i].getExamenParcial2()) * 0.1);
            System.out.printf("| Final            | Nota:  %2.3f  | Ponderada:  %2.3f  |\n", alumnos[i].getExamenFinal(), (alumnos[i].getExamenFinal()) * 0.8);
            System.out.println("|-------------------------------------------------------|");
            System.out.printf("| Resultado de las notas ponderadas:              %2.3f |\n", alumnos[i].getnotaFinal());
            System.out.println("---------------------------------------------------------");
        }

        scan.close();
    }
}