#include "MD.h"
#include "IteratorMD.h"
#include <exception>
#include <iostream>

using namespace std;


int MD::aloca() /* Complexitate: O(1) */
{
	int poz = prim_ind_liber;
	prim_ind_liber = urm[prim_ind_liber];
	return poz;
}

void MD::dealoca(int i) /* Complexitate: O(1) */
{
	urm[i] = prim_ind_liber;
	prim_ind_liber = i;
}

void MD::resize() /* Complexitate: O(2*capacity) */
{
	capacity *= 2;
	int* new_urm = new int[capacity];
	TElem* new_e = new TElem[capacity];
	for (int i = 0; i < size; i++)
	{
		new_urm[i] = urm[i];
		new_e[i] = e[i];
	}
	delete[] urm;
	delete[] e;
	for (int i = size; i < capacity - 1; i++)
		new_urm[i] = i + 1;
	new_urm[capacity - 1] = -1;
	prim_ind_liber = size;
	urm = new_urm;
	e = new_e;
}

MD::MD() { /* Complexitate: O(capacity) */
	size = 0;
	capacity = 100;
	urm = new int[capacity];
	e = new TElem[capacity];
	prim_el = ultim_el = -1;
	for (int i = 0; i < capacity - 1; i++)
		urm[i] = i + 1;
	urm[capacity - 1] = -1;
	prim_ind_liber = 0;
}


void MD::adauga(TCheie c, TValoare v) { /* Complexitate: O(1) amortizat, deoarece redimensionarea vectorilor are loc doar o data la 2^n elemente */
	TElem pereche{ c,v };
	if (prim_ind_liber == -1)
		resize();
	int poz = aloca();
	if (size == 0)// se adauga primul element
	{
		e[poz] = pereche;
		urm[poz] = -1;
		prim_el = poz;
		ultim_el = poz;
	}
	else
	{ //adaugam la final
		e[poz] = pereche;
		urm[poz] = -1;
		urm[ultim_el] = poz;
		ultim_el = poz;
	}
	size++;
}


bool MD::sterge(TCheie c, TValoare v) { /* Complexitate: O(n) */
	int precedent = -1;
	for (int i = prim_el; i != -1; i = urm[i])
	{
		if (e[i].first == c && e[i].second == v)
		{
			int urmator = urm[i];
			if (precedent != -1)
				urm[precedent] = urmator;
			else
				prim_el = urmator;
			if (urmator == -1)
				ultim_el = precedent;
			dealoca(i);
			size--;
			return true;
		}
		precedent = i;
	}
	return false;
}


vector<TValoare> MD::cauta(TCheie c) const { /* Complexitate: O(n) */
	vector<TValoare> valori;
	for(int i = prim_el; i != -1; i = urm[i])
		if (e[i].first == c)
			valori.push_back(e[i].second);
	return valori;
}


int MD::dim() const { /* Complexitate: O(1) */
	return size;
}


bool MD::vid() const { /* Complexitate: O(1) */
	return (prim_el == -1);
}

IteratorMD MD::iterator() const {
	return IteratorMD(*this);
}


MD::~MD() { /* Complexitate: O(2*n), lungime TElem si lungime urm */
	delete[]e;
	delete[] urm;
}


vector<TValoare> MD::colectiaValorilor() const /* Complexitate: theta(n) */
{ 
	vector<TValoare> colectieVal;
	int i = prim_el;
	while (i != -1)
	{
		colectieVal.push_back(e[i].second);
		i = urm[i];
	}

	return colectieVal;
}

