#pragma once

// 5.TAD Colecție – reprezentare memorând toate elementele, folosind o TD cu rezolvare coliziuni prin adresare deschisă, dispersia dublă.

#include <climits>
#define NULL_TELEM INT_MAX
#define STERS INT_MAX-1
#define MAX 20
typedef int TElem;

class IteratorColectie;

class Colectie
{
	friend class IteratorColectie;
	

private:
	int n; //nr de locatii din tabela de dispersie
	int dimensiune;
	TElem* e;
	int d1(TElem e) const;
	int d2(TElem e) const;
	int d(TElem e, int i) const;
	void redimensionare();
public:
	//constructorul implicit
	Colectie();

	//adauga un element in colectie
	void adauga(TElem e);

	//sterge o aparitie a unui element din colectie
	//returneaza adevarat daca s-a putut sterge
	bool sterge(TElem e);

	//verifica daca un element se afla in colectie
	bool cauta(TElem elem) ;

	//returneaza numar de aparitii ale unui element in colectie
	int nrAparitii(TElem elem) const;


	//intoarce numarul de elemente din colectie;
	int dim() const;

	//verifica daca colectia e vida;
	bool vida() const;

	//returneaza un iterator pe colectie
	IteratorColectie iterator() const;

	// destructorul colectiei
	~Colectie();

	void adaugaToateElementele(const Colectie& b);
};

