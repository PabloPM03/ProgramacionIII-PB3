#include <stdio.h>
#include <math.h>

struct puntosPlano
{
    float x,y;
};

void main() {

    struct puntosPlano p1, p2, pMedio;
    float distancia;

    printf("\nIndroduce la coordenada X de P1 -> ");
    scanf("%f", &p1.x);
    printf("Indroduce la coordenada Y de P1 -> ");
    scanf("%f", &p1.y);
    printf("Indroduce la coordenada X de P2 -> ");
    scanf("%f", &p2.x);
    printf("Indroduce la coordenada Y de P2 -> ");
    scanf("%f", &p2.y);

    // Cálculo de la distancia entre P1 y P2
    distancia = sqrt(pow(p1.x-p2.x, 2)+pow(p1.y-p2.y, 2));

    // Cálculo del punto medio entre P1 y P2
    pMedio.x = (p1.x + p2.x) / 2;
    pMedio.y = (p1.y + p2.y) / 2;

    printf("\nLos puntos introducidos son los siguientes:");
    printf("\nPUNTO 1 -> (%.2f - %.2f)", p1.x, p1.y);
    printf("\nPUNTO 2 -> (%.2f - %.2f)\n", p2.x, p2.y);

    printf("\nLa distancia entre P1 y P2 es de %f u.", distancia);
    printf("\nPUNTO MEDIO -> (%.2f - %.2f)\n\n", pMedio.x, pMedio.y);
}
