#include "IteratorColectie.h"
#include "Colectie.h"



IteratorColectie::IteratorColectie(const Colectie& c) : col(c) /* Complexitate: O(1) */
{
	curent = 0;
	deplasare();
}

void IteratorColectie::deplasare() /* Complexitate: O(1) */
{
	while ((curent < col.n) && col.e[curent] == NULL_TELEM)
		curent++;
}

void IteratorColectie::prim() /* Complexitate: O(1) */
{
	//se determina prima locatie ocupata
	curent = 0;
	deplasare();
}


void IteratorColectie::urmator() /* Complexitate: O(1) */
{
	curent++;
	deplasare();
}


bool IteratorColectie::valid() const /* Complexitate: O(1) */
{
	return (curent < col.n);
}


TElem IteratorColectie::element() const /* Complexitate: O(1) */
{
	return col.e[curent];
}
