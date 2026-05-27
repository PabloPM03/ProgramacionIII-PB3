#include <stdio.h>
#include <stdlib.h>
#define LIMITE 4UL

/* Prototipos */
int perfecto (unsigned long);
void presentacion (void);

int main(void) {
 unsigned long numero, cuantos;

 presentacion();

 cuantos = 0;
 numero = 1;
 while (cuantos < LIMITE)
 {  if (perfecto(numero))
       { printf("%8lu", numero);
	 cuantos++;
       }
    numero++;
  }
  
  printf("\n\n");
  system ("pause");
  return 0;
}

int perfecto(unsigned long n)
{
   unsigned long acu = 0;
   unsigned long j;

   for (j=1; j<n; j++)
      if (n % j == 0)
	  acu += j;
   return (acu==n);
}

void presentacion (void) {
  system("cls");  /* Borrado de la pantalla */
  
  puts("Números perfectos");
  puts("=================");
  puts("");
  printf("El programa le presentará los %lu primeros números perfectos.",
	  LIMITE);
  puts("");
  puts("");
}
