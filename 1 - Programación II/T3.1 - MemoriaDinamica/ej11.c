#include <stdio.h>
#include <stdlib.h>

int *aislarColumna(int**, int, int, int);

void main() {

    int x, y, colSel; 
    int **matriz, *colAislada;

    printf("\nIntroduzca el tamaño del eje x -> ");
    scanf("%d", &x);
    printf("Introduzca el tamaño del eje y -> ");
    scanf("%d", &y);
    
    //matriz = (int **)malloc(x * sizeof(int*));
    if ((matriz = (int **)malloc(x * sizeof(int*))) == NULL)  {
        exit(0);
    }
    for (int i = 0; i < x; i++){
        if (((matriz[i] = (int*)malloc(y * sizeof(int))) == NULL)) {
            exit(0);
        }
    }

    printf("\n");
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
            printf("Introduzca el numero (%d - %d) -> ", i+1, j+1);
            scanf("%d", &matriz[i][j]);
        }
    }
    printf("\n");
    for (int i = 0; i < x; i++) {
        printf("| ");
        for (int j = 0; j < y; j++) {
            printf("%d ", matriz[i][j]);
        }
        printf("|\n");
    }
    
    printf("\nElija una columna para aislar -> ");
    scanf("%d", &colSel);
    colAislada = aislarColumna(matriz, x, y, colSel);

    for(int i = 0; i < y; i++) {
        printf("\n| %d |", colAislada[i]);
    }
    printf("\n\n");

}

int *aislarColumna(int **matriz, int x, int y, int columnaSeleccionada) {

    int *v;

    if (((v = (int*)malloc(sizeof(int) * y)) == NULL)) {
        exit(0);
    }

    for (int i = 0; i < y; i++) {
        v[i] = matriz[i][columnaSeleccionada-1];
    }

    return v;
}