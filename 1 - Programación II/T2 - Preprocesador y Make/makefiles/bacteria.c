#include <stdio.h>
#include <stdlib.h>

/* Fuente: BACTERIA.C
   Programa CULTIVO DE BACTERIAS
   Presenta una tabla de incrementos diarios de la densidad de 
   un cultivo de bacterias
   Calcula tambi�n el n�mero de d�as que tarda la densidad de
   cultivo en duplicarse
*/


int main(void)
{
  float densidad_ini;  /* Entrada: Densidad de poblaci�n inicial    */
  float tasa_crec;     /* Entrada: Tasa de crecimiento diaria       */
  int   dias_cultivo;  /* Entrada: D�as que se mantendr� el cultivo */
  float dens_ini_dia;  /* C�lculo: Densidad al comienzo de cada d�a */
  float incremento;    /* C�lculo: Incremento diario de densidad    */ 
  int   dia;           /* C�lculo: Contador d�as transcurridos      */
  float dens_total;    /* Salida : Dens. total a fin de un periodo  */
  int   cont_dias;     /* Salida : Numero d�as a mantener cultivo                                               
                                   para duplicar su densidad        */

/* Presentaci�n del programa */
  system("cls");  /* Borrado de la pantalla */

  printf("Cultivo de bacterias\n");
  printf("====================\n");
  printf("Dada una densidad inicial de un cultivo de bacterias, una "
         "tasa de crecimiento \ndiaria y un n�mero de d�as a mantener "
         "el cultivo, se presenta la tabla de \ncrecimiento diaria.\n"
         "Se calcula tambi�n los d�as que el cultivo tarda en duplicar "
         "su densidad \ninicial.\n\n");

/* Petici�n y validaci�n de datos */
  do {   printf("Densidad inicial?: ");
         scanf("%f", &densidad_ini);
         if  (densidad_ini <= 0)
              printf("La densidad debe ser mayor que cero\n");
     } while (densidad_ini <= 0);

  do {   printf("Tasa de crecimiento diaria?: ");
         scanf("%f", &tasa_crec);
         if  (tasa_crec <= 0)
              printf("La tasa de crecimiento debe ser mayor que cero\n");
     } while (tasa_crec <= 0);

  do {   printf("D�as a mantener el cultivo?: ");
         scanf("%d", &dias_cultivo);
         if  (dias_cultivo <= 0)
             printf("El n�mero de d�as debe ser mayor que cero\n");
     } while (dias_cultivo <= 0);

/* C�lculo de la densidad total al final de un periodo */
/* --------------------------------------------------- */
  dens_total = densidad_ini;
 
  printf("\n");
  printf("D�a | Dens. comienzo d�a | Incr. d�a | Dens. fin d�a \n");
  printf("----|--------------------|-----------|---------------\n");

  for (dia = 1; dia <= dias_cultivo; dia++)
    { dens_ini_dia = dens_total;
      incremento = dens_total * (tasa_crec / 100);
      dens_total += incremento;
      printf("%3d |     %9.3f      | %9.3f |   %9.3f       \n",
             dia, dens_ini_dia, incremento, dens_total);
    }
  
  printf("\n");
  printf("La densidad total al cabo de %d d�as es de %.3f\n",
          dias_cultivo, dens_total);

/* C�lculo del n�mero de d�as en que el cultivo duplica su densidad */
/* ---------------------------------------------------------------- */
  cont_dias = 0;
  dens_total = densidad_ini;

  do
  {  cont_dias++;
     incremento = dens_total * (tasa_crec / 100);
     dens_total += incremento;
  } while (dens_total < densidad_ini * 2);

  printf("La densidad inicial se duplica al cabo de %d d�as\n", cont_dias);

  printf("\n\n");
  system ("pause");
  return 0;

}
