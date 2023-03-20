//
// Created by Victor on 3/13/2022.
//
#include <string.h>
#include <stdlib.h>
#include "objects.h"

produs* createProdus(int id, int pret, int cantitate, char* type, char* producator, char* model) {
    produs* p = malloc(sizeof(produs));
    p->id = id;
    p->pret = pret;
    p->cantitate = cantitate;

    int nrC = (int)strlen(type) + 1;
    p->type = malloc(nrC * sizeof(char));
    strcpy_s(p->type, nrC, type);

    nrC = (int)strlen(producator) + 1;
    p->producator = malloc(nrC * sizeof(char));
    strcpy_s(p->producator, nrC, producator);

    nrC = (int)strlen(model) + 1;
    p->model = malloc(nrC * sizeof(char));
    strcpy_s(p->model, nrC, model);
    return p;
}

produs* createCopy(produs* p) {
    return createProdus(p->id, p->pret, p->cantitate, p->type, p->producator, p->model);
}

void destroyProdus(produs* p) {
    free(p->model);
    free(p->producator);
    free(p->type);
    free(p);
}