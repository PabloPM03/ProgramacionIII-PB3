//Funcion1. Generar Vector
//Funcion2. Concatena 2 Vectores
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int *generarVectorEnteros(int tam);

int *concatenarVectoresEnteros(int *vector1, int longitud1, int *vector2, int longitud2);

void main() {

    int *vector_1, *vector_2, *vectorFinal;
    int longitud_1, longitud_2, longitudFinal;
    srand(time(NULL));

    printf("\nIntroduzca el tamano del primer vector: ");
    scanf("%d", &longitud_1);
    vector_1 = generarVectorEnteros(longitud_1);
    printf("Vector 1 generadocorrectamente.");

    printf("\nIntroduzca el tamano del primer vector: ");
    scanf("%d", &longitud_2);
    vector_2 = generarVectorEnteros(longitud_2);
    printf("Vector 2 generadocorrectamente.");



    printf("\n\nMostrando vector 1:");
    printf("\n(");
    for(int i = 0; i < longitud_1; i++) {
        printf(" %d", vector_1[i]);
    }
    printf(" )\nTamaño del vector = %d\n", longitud_1);

    printf("\nMostrando vector 2:");
    printf("\n(");
    for(int i = 0; i < longitud_2; i++) {
        printf(" %d", vector_2[i]);
    }
    printf(" )\nTamaño del vector = %d\n", longitud_2);



    vectorFinal = concatenarVectoresEnteros(vector_1, longitud_1, vector_2, longitud_2);
    longitudFinal = longitud_1 + longitud_2;
    printf("\n\nMostrando vector concatenado:");
    printf("\n(");
    for(int i = 0; i < longitudFinal; i++) {
        printf(" %d", vectorFinal[i]);
    }
    printf(" )\nTamaño del vector = %d\n\n", longitudFinal);

    free(vector_1);
    free(vector_2);
    free(vectorFinal);
}




int *generarVectorEnteros(int tam){

    int *v = (int *) malloc(sizeof(int *) * tam);
    for (int i = 0; i < tam; i++) {
        v[i] = (int) rand() %100 + 1;
    }

    return v;
}

int *concatenarVectoresEnteros(int *v_1, int l_1, int *v_2, int l_2){

    int l = l_1 + l_2;
    int *v = (int *) malloc(sizeof(int *) * l);

    for (int i = 0; i < l_1; i++) {
        v[i] = v_1[i];
    }
    for (int i = l_1; i < l; i++) {
        v[i] = v_2[l_2 - i];
    }

    return v;
}