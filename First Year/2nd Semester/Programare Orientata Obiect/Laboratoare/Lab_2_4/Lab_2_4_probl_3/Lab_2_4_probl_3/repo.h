#ifndef REPO_H
#define REPO_H
#include "domain.h"

Lista_DE_Cheltuieli creeazaLista();

void adaugaCheltuialaInLista(Lista_DE_Cheltuieli*, Cheltuiala*);
void modificareCheltuialaDinLista(Lista_DE_Cheltuieli*, int, char*, char*);
int get_numarul_cheltuieli_din_lista(Lista_DE_Cheltuieli*);

#endif /* REPO_H */