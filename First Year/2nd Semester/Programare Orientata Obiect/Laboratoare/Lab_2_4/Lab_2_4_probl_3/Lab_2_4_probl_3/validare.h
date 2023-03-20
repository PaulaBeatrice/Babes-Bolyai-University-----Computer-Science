#ifndef VALIDARE_H
#define VALIDARE_H
#include "domain.h"

/*
	Valideaza o cheltuiala
	cheltuiala - cheltuiala care trebuie validata
	returneaza	0 daca cheltuiala este valida
				1 daca ziua efectuarii cheltuielii este invalida
				2 daca suma cheltuielii este invalida
				3 daca tipul cheltuielii este invalid
				4 daca intregul obiect de tip cheltuiala e invalid
*/
int valideazaCheltuiala(Cheltuiala*);


#endif // !VALIDARE_H

