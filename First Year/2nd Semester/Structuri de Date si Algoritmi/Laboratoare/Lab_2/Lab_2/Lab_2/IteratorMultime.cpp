#include "IteratorMultime.h"
#include "Multime.h"


IteratorMultime::IteratorMultime(const Multime& m): mult(m) {
	curent = mult.prim;
}

TElem IteratorMultime::element() const {
	return curent->element();
}

bool IteratorMultime::valid() const {
	return curent != nullptr;
}

void IteratorMultime::urmator() {
	curent = curent->urmator();
}

void IteratorMultime::prim() {
	curent = mult.prim;
}

