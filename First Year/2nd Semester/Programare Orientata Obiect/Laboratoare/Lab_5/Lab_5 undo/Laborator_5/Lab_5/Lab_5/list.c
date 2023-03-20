#include "list.h"

List* createEmpty(TElementDestroyFn destroyElem)
{
	List* lista = malloc(sizeof(List));
	if (lista) {
		lista->len = 0;
		lista->cap = 3;
		lista->elems = malloc(lista->cap * sizeof(produs));
		lista->destroyElement = destroyElem;
	}
	return lista;
}

TElement get(List* l, int poz)
{
	return l->elems[poz];
}

TElement set(List* v, int poz, TElement el)
{
	TElement r = v->elems[poz];
	v->elems[poz] = el;
	return r;
}

int size(List* l)
{
	return l->len;
}

void destroyVector(List* vector)
{
	for (int i = 0; i < vector->len; i++) {
		vector->destroyElement(vector->elems[i]);
	}
	vector->len = 0;
	free(vector->elems);
	free(vector);
}

void add(List* l, TElement element)
{
	if (l->len >= l->cap) {

		int newCapacity = l->cap * 2;
		TElement* aux = malloc(sizeof(TElement) * newCapacity);
		for (int i = 0; i < l->len; i++) {
				aux[i] = l->elems[i];
		}
		free(l->elems);
		l->elems = aux;
		l->cap = newCapacity;
	}
	l ->elems[l->len] = element;
	l->len++;
}

TElement deleteElementIndex(List* l, int poz)
{
	TElement rez = l->elems[poz];
	for (int i = poz; i < l->len - 1; i++)
		l->elems[i] = l->elems[i + 1];
	l->len--;
	return rez;
}

List* copyList(List* l, CopyFct cf)
{
	List* rez = createEmpty(l->destroyElement);
	for (int i = 0; i < size(l); i++) {
		TElement p = get(l, i);
		add(rez, cf(p));
	}
	return rez;
}
