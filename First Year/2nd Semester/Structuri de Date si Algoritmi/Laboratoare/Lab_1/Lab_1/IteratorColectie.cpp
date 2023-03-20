#include "IteratorColectie.h"
#include "Colectie.h"
#include <iostream>
using std::exception;


IteratorColectie::IteratorColectie(const Colectie& c): col(c) {
	this->index_pozitii = 0; // initial ne aflam pe prima pozitie(la inceputul vectorului de pozitii)
}

void IteratorColectie::prim() {
	this->index_pozitii = 0; // indica primul element din vectorul de pozitii
}


void IteratorColectie::urmator() {
	if (this->valid() == false)
		throw exception();
	this->index_pozitii++;
}


bool IteratorColectie::valid() const {
	//indexul e valid daca e mai mic decat lungimea vectorului de pozitii
	if (this->index_pozitii < this->col.nr_el_pozitii)
		return true;
	return false;
}



TElem IteratorColectie::element() const {
	if (this->valid() == false)
		throw exception();
	return this->col.distincte[col.pozitii[this->index_pozitii].pozitie].element;
}
