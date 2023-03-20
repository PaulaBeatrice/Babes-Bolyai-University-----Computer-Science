#include "Multime.h"
#include "IteratorMultime.h"
#include <iostream>

using namespace std;

Nod::Nod(TElem e, PNod urm, PNod prec) {
	this->e = e;
	this->urm = urm;
	this->prec = prec;
}

TElem Nod::element() {
	return e;
}

PNod Nod::urmator() {
	return urm;
}

PNod Nod::precedent() {
	return prec;
}

bool rel(TElem e1, TElem e2) {
	if (e1 <= e2) {
		return true;
	}
	else {
		return false;
	}
}

Multime::Multime()
{
	this->prim = nullptr;
	this->ultim = nullptr;
}

Multime::Multime(Relatie r) {
	//rel = r;
	this->prim = nullptr;
	this->ultim = nullptr;
}


bool Multime::adauga(TElem elem) { // Complexitate: O(n)
	PNod nou = new Nod(elem, nullptr, nullptr);
	//daca multimea e vida se adauga primul element
	if (prim == nullptr) {
		prim = nou;
		ultim = nou;
		nou->prec = ultim;
		nou->urm = prim;
		return true;
	}
	else
	{
		//elementul de adaugat e in relatie cu informatia utila a primului nod 
		//se adauga inainte de prim
		auto new_nod = prim;
		while (new_nod->urm != nullptr && rel(new_nod->urm->e, elem) == 1) {
			new_nod = new_nod->urm;
			if (new_nod->e == elem)
				return false;
		}
		if (new_nod == prim && rel(new_nod->e, elem) == 0) {
			nou->urm = new_nod;
			prim = nou;
			new_nod->prec = nou;
		}
		else
		{
			if (new_nod->urm == nullptr) {
				ultim = nou;
				new_nod->urm = nou;
				nou->prec = new_nod;
			}
			else 
			{
				nou->urm = new_nod->urm;
				new_nod->urm = nou;
				nou->prec = new_nod;
			}
		}
		return true;
	}
}


bool Multime::sterge(TElem elem) { // Complexitate: O(n)
	if (elem == NULL)
		return false;
	else {
		auto new_nod = prim;
		while (new_nod->urm != nullptr && new_nod->e != elem) {
			new_nod = new_nod->urm;
		}
		if (new_nod->urm == nullptr && new_nod->e != elem)
			return false;
		if (new_nod != prim)
		{
			new_nod->prec->urm = new_nod->urm;
			if (new_nod->urm != nullptr)
				new_nod->urm->prec = new_nod->prec->prec;
		}
		else
		{
			prim = new_nod->urm;
		}
	}
	return true;
	
}


bool Multime::cauta(TElem elem) const { // Complexitate: O(n)
	Nod* head = prim;
	int poz = 0;
	while (head != NULL) {
		if (head->e == elem)
			return true;
		head = head->urm;
		poz++;
	}
	return false;
}


int Multime::dim() const { // Complexitate: On(n)
	int size = 0;
	Nod* head = prim;
	while (head != NULL) {
		head = head->urm;
		++size;
	}
	return size;
}



bool Multime::vida() const { // Complexitate: O(1)
	if (prim == NULL)
		return true;
	return false;
}

IteratorMultime Multime::iterator() const {
	return IteratorMultime(*this);
}


Multime::~Multime() {
	//se elibereaza memoria alocata de nodurile listei
	while (prim != nullptr) {
		PNod p = prim;
		prim = prim->urm;
		delete p;
	}
}

int Multime::diferentaMaxMin()const { // Complexitate: O(1)
	if (vida() == NULL)
		return -1;
	return ultim - prim;
}





