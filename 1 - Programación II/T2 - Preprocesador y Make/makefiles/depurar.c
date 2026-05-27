/* Fuente: DEPURAR.C
   Programa: FUENTE PRUEBAS PARA DEPURACION
   Descripción: Mediante este fuente se podrán comprobar distintos aspectos y 
   comportamiento de la herramienta de Depuración del EDI Dev-C++
*/

#include <stdio.h>
#include <stdlib.h>
#define DIM 10

/* Prototipos de funciones
   -----------------------
*/

void  presentacion (void);
void  despedida (void);
void  inicia_vector_indice (int *, int);
float suma_vector (int *, int);

int main(void) {
/* Definiciones de variables y constantes
   ---------------------------------------
*/

  int vector [DIM] = {1,2,3,4,5,6,7,8,9,10};
  float suma=0;
  int   cuantos = 120;
  
  presentacion();
  inicia_vector_indice (vector, DIM);
  suma = suma_vector (vector, DIM);
  printf ("La suma de los elementos del vector es: %g\n", suma);
  
  despedida();
  
  printf("\n\n");
  system ("pause");
  return 0;
}

void presentacion(void) {
  printf("PROGRAMA DE PRUEBA PARA HERRAMIENTA DEBUG\n");
  printf("==========================================\n\n");
  
  puts("Este programa permite comprobar distintos aspectos y comportamiento");
  puts("del Depurador de Dev-C++.");
  puts("");
}

void despedida(void) {
  puts("");
  printf("Finalización de programa de prueba\n");
  printf("Adios\n\n");
}

void inicia_vector_indice (int * la, int dm) {
    int j;
    for (j = 0; j < dm; j++)
        la[j] = j+10;
}

float suma_vector (int * la, int dm) {
    int j;
    float resul = 0;
    for (j = 0; j < dm; j++)
        resul = la[j];
    
    return resul;
}


