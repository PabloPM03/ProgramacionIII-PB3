//Carga de un array de tamaño dinámico con números
#include <stdio.h>
#include <stdlib.h>

void main() {

    int *vector, tam;

    printf("\nIntroduzca el tamaño del vector: ");
    scanf("%d", &tam);

    vector = (int *) malloc(sizeof(int) * tam);

    printf("\nSe ha creado un vector de tamaño %d.\n", tam);

    for(int i = 0; i < tam; i++) {
        printf("Introduzca el valor numero %d: ", 1+i);
        scanf("%d", vector + i);
    }

    printf("\n\nVector cargado safisfactoriamente.");
    printf("Mostrando vector:");
    printf("\n(");

    for(int i = 0; i < tam; i++) {
        printf(" %d", vector[i]);
    }
    printf(")\n\n");
}