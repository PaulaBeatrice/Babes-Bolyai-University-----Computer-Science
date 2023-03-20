#pragma once

/*
	8.TAD Mulțime cu elemente de tip comparabil (TElement=TComparabil)–reprezentare folosind o LDI cu
	elementele ordonate în raport cu relația de ordine R=<=.
*/

typedef int TComparabil;
typedef TComparabil TElem;

typedef bool(*Relatie)(TElem, TElem);

bool rel(TElem, TElem);

class IteratorMultime;

class Nod;
typedef Nod* PNod;

class Nod
{
	public:
		friend class Multime;
		//Constructor
		Nod(TElem, PNod urm, PNod prec);
		TElem element();
		PNod urmator();
		PNod precedent();
private:
	TElem e;
	//cele doua legaturi - lista  dublu inlantuita
	PNod urm, prec;
};

class Multime {

	friend class IteratorMultime;

private:
	/* aici e reprezentarea */
	//reprezentarea listei ordonate
	// prim / ultim referas primul / ultimul nod din LDI
	PNod prim, ultim;
	//relatia de ordine intre elemente
	//Relatie rel;
	

public:
		//constructorul implicit
		Multime();

		Multime(Relatie rel);

		//adauga un element in multime
		//returneaza adevarat daca elementul a fost adaugat (nu exista deja in multime)
		bool adauga(TElem e);

		//sterge un element din multime
		//returneaza adevarat daca elementul a existat si a fost sters
		bool sterge(TElem e);

		//verifica daca un element se afla in multime
		bool cauta(TElem elem) const;


		//intoarce numarul de elemente din multime;
		int dim() const;

		//verifica daca multimea e vida;
		bool vida() const;

		//returneaza un iterator pe multime
		IteratorMultime iterator() const;

		// destructorul multimii
		~Multime();

		int diferentaMaxMin()const;

};

