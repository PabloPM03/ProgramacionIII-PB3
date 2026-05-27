#include <stdio.h>
#include <stdlib.h>


/* Muestra el alfabeto desde A a Z */
int main() {
    
    char a = 'A'; 
    int terminar = 0;

    while(!terminar) {
    
       putchar(a);   /* Muestra el car�cter actual */
       putchar(' ');   

       if (a = 'Z')  /* Si 'char a' es igual Z debemos terminar */
           terminar = 1;   
	   else  a++;   /* Aun quedan caracteres por mostrar,  
                       pasamos al siguiente ASCII con a=a+1 */
    }
    printf("\n\n");
    system ("pause");
    return 0;
}
