#pragma once
#include "domain.h"

typedef void* TElement;

typedef void (*TElementDestroyFn)(TElement);
typedef TElement(*CopyFct)(TElement);

typedef struct {
	TElement* elems;
	int cap;
	int len;
	TElementDestroyFn destroyElement;
} List;

/*
	Functie ce creeaza un vector
	destroyElem - functie ce distruge elementele create
	cap - capacitatea initiala(int)
 */
List* createEmpty(TElementDestroyFn destroyElem);

/*
	Returneaza un element de pe o pozitie data
*/
TElement get(List* l, int poz);

TElement set(List* v, int poz, TElement el);

// Lungimea unei liste
int size(List* l);

/*
	 Distruge un vector dinamic dat, impreuna cu elementele sale
 */
void destroyVector(List* vector);

/*
	Adauga un nou element in vectorul dinamic
 */
void add(List* l, TElement element);

/*
	Functia va sterge un element, de pe o pozitie data
 */
TElement deleteElementIndex(List* l, int poz);

/*
	Functia copiaza o lista data
*/
List* copyList(List* l, CopyFct cf);





