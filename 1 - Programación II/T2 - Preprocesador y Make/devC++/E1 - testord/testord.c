/* Fuente: TESTORD.C
   Programa ORDENACION DE VECTORES
   Realiza ordenaciones de vectores de enteros, de longitudes cada vez 
   mayores, por distintos algoritmos, y compara tiempos.
*/

#include <stdio.h>
#include <stdlib.h>
#include "metodos.h"
#include "auxi.h"
#include "memoria.h"

void pedir_datos (long *, long *, char *);
void presentacion(void);

int main(void)
{
   int *vector;
   int *vector_original;

   long tope, puntos_muestreo, incremento;
   char tipo_inicio_vector;
   long veces;

   long t_burbuja;
   long t_insercion;
   long t_seleccion;

   presentacion();
   pedir_datos (&tope, &puntos_muestreo, &tipo_inicio_vector);

   incremento = tope / puntos_muestreo;

   system("clear");
   printf("\n");
   printf("METODOS DE ORDENACION\n");
   printf("dim vector ord ini   burbuja inserci�n selecci�n\n",
	    "---------- ------- --------- --------- ---------\n");
   for (veces=incremento; veces <= tope; veces+=incremento)
   {
      /* Reservamos memoria para el vector                  */
      if ( (vector = asignar_memoria(veces)) == NULL)
	   return 1;

      /* Reservamos un segundo bloque del mismo tama�o para */
      /* guardar el vector primitivo desordenado y que todos*/
      /* algoritmos ordenen el mismo vector.                */
      if ( (vector_original = asignar_memoria(veces)) == NULL)
	   return 1;
      
 
      /* Iniciamos con valores el vector a ordenar.         */
      /* El tercer par�metro indica que tipo de iniciaci�n  */
      /* deseamos: ordenada de forma ascendente, de forma   */
      /* descendente o cargada con valores aleatorios.      */
       iniciar (veces, vector, tipo_inicio_vector);

      /* Guardamos el vector reci�n cargado en el           */
      /* vector_original. La funci�n duplicar copia desde   */
      /* vector origen a vector destino un n�mero dado de   */
      /* elementos: duplicar (n.elem., lorigen, ldestino)   */
      duplicar (veces, vector, vector_original);
      t_burbuja = ordena_burbuja (veces, vector);

      duplicar (veces, vector_original, vector);
      t_insercion = ordena_insercion (veces, vector);

      duplicar (veces, vector_original, vector);
      t_seleccion = ordena_seleccion (veces, vector);


      liberar_memoria(vector);
      liberar_memoria(vector_original);


      /* Salida de datos */
      printf("%9ld %7c %9ld %9ld %9ld\n",
	     veces, tipo_inicio_vector,
	     t_burbuja, t_insercion, t_seleccion);
   }

   printf("\n");
   printf("\nPULSE ENTER PARA CONTINUAR");
   scanf("");
   return 0;
}


void presentacion (void)
{
  system ("clear");
  printf("COMPARATIVA DE LOS METODOS BASICOS DE ORDENACION\n");
  printf("------------------------------------------------\n");
  printf("Este programa es capaz de medir los tiempos de ");
  printf("ejecuci�n de los algoritmos\n");
  printf("b�sicos de ordenaci�n para vectores de distintos ");
  printf("tama�os.\n");
  printf("\n");
  printf("El programa pedir� varios datos de entrada:\n");
  printf("(1) (long) Longitud m�xima del vector a ordenar\n");
  printf("(2) (long) N�mero de puntos de muestreo a presentar\n");
  printf("(3) (char) Tipo de iniciaci�n del vector\n");
  printf("	     a)    ascendente\n");
  printf("	     d)    descendente\n");
  printf("	     otro) aleatorio\n");
  printf("\n");
  printf("Ver enunciado del problema para m�s informaci�n\n");

  printf("\n");
  printf("\nPULSE ENTER PARA CONTINUAR");
  scanf("");
}


void pedir_datos (long *tp, long *pm, char *ord)
{
   system ("clear");
   printf ("\n");

   printf ("Longitud m�xima vector a ordenar: ");
   scanf ("%ld", tp);

   printf ("\n");
   do
   { printf ("N�mero de puntos de muestreo a presentar (max %ld): ",
              *tp/10);
     scanf ("%ld", pm);
   } while (*pm <= 0 || *pm > *tp);

   printf ("\n");
   printf ("Tipo de iniciaci�n del vector\n");
   printf (" a)    ascendente\n");
   printf (" d)    descendente\n");
   printf (" otro) aleatorio\n");
   fflush(stdin);
   scanf ("%c", ord);
}

