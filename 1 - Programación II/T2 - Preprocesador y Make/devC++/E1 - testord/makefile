testord: testord.o auxi.o memoria.o metodos.o
	gcc testord.o auxi.o memoria.o metodos.o -o testord
testord.o: testord.c auxi.h memoria.h metodos.h
	gcc -c testord.c
auxi.o: auxi.h auxi.c
	gcc -c auxi.c
memoria.o: memoria.h memoria.c
	gcc -c memoria.c
metodos.o: metodos.h metodos.c
	gcc -c metodos.c