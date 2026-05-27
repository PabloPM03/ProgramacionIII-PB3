#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define DIM 11

/* Fuente: EQUIPO.C
   Programa ESTADÍSTICA BASICA EQUIPO DEPORTIVO
   Realiza una estadística básica de un equipo deportivo
   Almacenado en un vector de tipo struct
*/

struct jugador {
    char  nombre [81];
    int   edad;
    float talla;
};

void presentacion (void);
void pedir_nombre_equipo (char *);
void pedir_datos_jugadores (struct jugador *, int);
void calculos_edad  (struct jugador *, int, int *, int *, float *);
void calculos_talla (struct jugador *, int, float *, int *, float *);
void escribir_nombre_equipo (char *);

int main(void) {
/* Definiciones de variables y constantes
   ---------------------------------------
*/

  struct jugador equipo[DIM]; /* Vector de elementos estructuras.
                                 Cada estructura recoge los tres
                                 datos de un jugador   */
  
  char  nombre_equipo[51];   /* Nombre del equipo que se está tratando  */

  float edad_media;          /* Edad media del equipo a calcular        */
  int   edad_min;            /* Edad mínima del equipo a calcular       */
  int   numjug_edad_min;     /* Número de jugadores con edad mínima     */

  float talla_media;         /* Estatura media del equipo a calcular    */
  float talla_max;           /* Estatura máxima del equipo a calcular   */
  int   numjug_talla_max;    /* Número de jugadores con estatura máxima */

  int i;                /* Variable auxiliar para recorrido del vector  */

  presentacion();
  pedir_nombre_equipo(nombre_equipo);
  pedir_datos_jugadores(equipo, DIM);
  calculos_edad (equipo, DIM, &edad_min, &numjug_edad_min, &edad_media);
  calculos_talla(equipo, DIM, &talla_max, &numjug_talla_max, &talla_media);

 
/* Resultados --------------------------------------- */
  printf("\n\n");
  escribir_nombre_equipo(nombre_equipo);

  printf("\nEdad media del equipo..........: %.2f\n", edad_media);
  printf("Estatura media del equipo......: %.2f\n", talla_media);

  printf("\nEdad mínima del equipo.........: %d\n", edad_min);
  printf("Jugadores con edad mínima......: %d\n", numjug_edad_min);
  printf("            Nombre             Estatura\n");
  printf("------------------------------ --------\n");

  for (i=0; i < DIM; i++)
       if (equipo[i].edad == edad_min)
            printf ("%-30s %7.2f\n", equipo[i].nombre, equipo[i].talla);

  printf("\nEstatura máxima del equipo.....: %.2f\n", talla_max);
  printf("Jugadores con estatura máxima..: %d\n", numjug_talla_max);
  printf("            Nombre               Edad  \n");
  printf("------------------------------ --------\n");
  for (i=0; i < DIM; i++)
       if (equipo[i].talla == talla_max)
            printf ("%-30s %7d\n", equipo[i].nombre, equipo[i].edad);

  printf("\n\n");
  system ("pause");
  return 0;
}

void presentacion(void) {
  printf("ESTADISTICA DEPORTIVA\n");
  printf("=====================\n\n");
  printf("Se pedirán por pantalla los nombres, edades y estaturas "
	 "de los %d jugadores \n"
	 "de un equipo, y posteriormente se presentará:\n", DIM);
  printf("   - La estatura y edad media del equipo.\n"
	   "   - La edad mínima del equipo y el número, nombre y estatura de "
	   "los jugadores \n     que la poseen.\n"
	   "   - La estatura máxima del equipo y el número, nombre y edad de "
	   "los jugadores \n     que la poseen.\n\n");
}

void pedir_nombre_equipo(char *nom_equ) {
     fflush(stdin); /* Vaciado del buffer de teclado */
     printf("Introduzca el nombre del equipo: ");
     gets(nom_equ);
}

void pedir_datos_jugadores(struct jugador *lista, int dim) {
   int i;  

   puts("\nIntroduzca los datos de los jugadores:");
   for (i=0; i < dim; i++)
    {
      fflush(stdin); /* Vaciado del buffer de teclado */
	printf("Nombre jugador   %2d?: ", i+1);
	gets(lista[i].nombre);
	printf("Edad jugador     %2d?: ", i+1);
	scanf("%d", &lista[i].edad);
	printf("Estatura jugador %2d?: ", i+1);
	scanf("%f", &lista[i].talla);
	printf("\n");
   }
}

void calculos_edad(struct jugador *lista, int dim,
                   int *min, int *num_min, float *media) {
/* Cálculo edad mínima y media, y número de jugadores con edad mínima */

   int i;  

   *media = *min = lista[0].edad;
   *num_min = 1;
   for (i=1; i < dim; i++)
     { *media += lista[i].edad;
       if (lista[i].edad == *min)
		  (*num_min)++;
	 else
	   if (lista[i].edad < *min)
	     { *min = lista[i].edad;
	       *num_min = 1;
	     }
     }
   *media /= (float) dim;
}

void calculos_talla(struct jugador *lista, int dim,
                    float *max, int *num_max, float *media) {    
    /* Cálculo estatura máxima y media, y 
     * número de jugadores con estatura máxima
     */
   int i;

   *media = *max = lista[0].talla;
   *num_max = 1;
   for (i=1; i < dim; i++)
	    { *media += lista[i].talla;
	      if (lista[i].talla == *max)
		      (*num_max)++;
	      else
		 if (lista[i].talla > *max)
		   { *max = lista[i].talla;
		     *num_max = 1;
		   }
	    }

   *media /= (float) dim;
}


 
void escribir_nombre_equipo(char *nom_equ) {
   int i;

   puts(nom_equ);
   for (i=1; i<=strlen(nom_equ); i++) /* incluir string.h */
       printf("=");
}
