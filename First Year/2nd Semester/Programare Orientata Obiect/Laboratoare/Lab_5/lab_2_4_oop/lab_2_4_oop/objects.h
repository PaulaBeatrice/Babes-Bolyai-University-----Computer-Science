#pragma once
#include <stdlib.h>

/*
Creez struct pentru produse
*/
typedef struct {
	int id, pret, cantitate;
	char* type;
	char* producator;
	char* model;
}produs;

void destroyProdus(produs* p);

produs* createCopy(produs* p);

produs* createProdus(int id, int pret, int cantitate, char* type, char* producator, char* model);

