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

// creez un produs
produs* createProdus(int id, int pret, int cantitate, char* type, char* producator, char* model);

// distrug un produs
void destroyProdus(produs* p);

// copiaza un produs
produs* copyProdus(produs* p);

//validez un produs
int validate(produs* p);

