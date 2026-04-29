// 3 Punteros con memoria dinámica
#include <stdlib.h>

void main() {

    //Definición
    double *punteros;

    //Asignación de Memoria
    punteros = (double *) malloc(sizeof(punteros) * 3);

    //Operaciones
    

    //Liberación de Memoria
    free(punteros);
}