#pragma once
/*
7.TAD Colecție–memorată sub forma unui vector cu elementeledistincte (D) și a unui  vectorde poziții (P) în D ale elementelor
colecției. Spre  exemplu, colecția [5, 10, -1, 2, 3, 10, 5, 5, -5] va fi reprezentată sub forma vectorilor
	D = [5, 10, -1, 2, 3, -5]
	P = [0, 1, 2, 3, 4, 1, 0, 0, 5]
	*/

typedef int TElem;

class IteratorColectie;

typedef struct{
	TElem element;
}Distincte;

typedef struct{
	TElem pozitie;
}Pozitii;

class Colectie
{
	friend class IteratorColectie;
	
private:
	/* aici e reprezentarea */

	//reprezentarea capacitatii si lungimii vectorului ce contine elementele distincte
	int capacitate_el_distincte;
	int nr_el_distincte;
	//reprezentarea vectorului dinamic (succesiune de locatii de memorie)
	Distincte* distincte; // el retine elementele distincte

	//reprezentarea capacitatii si lungimii vectorului ce contine pozitiile elementelor
	int capacitate_pozitii;
	int nr_el_pozitii;
	//reprezentarea vectorului dinamic (succesiune de locatii de memorie)
	Pozitii* pozitii; // el retine pozitiile

	int cauta_pozitie(TElem el) const;
	/*
	Functie ce returneaza pozitia pe care se afla elementul el in vectorul de elemente distincte sau -1 in cazul in care elementul nu apare in vector
	*/

public:
		//constructorul implicit
		Colectie();

		//adauga un element in colectie
		void adauga(TElem e);

		//sterge o aparitie a unui element din colectie
		//returneaza adevarat daca s-a putut sterge
		bool sterge(TElem e);

		//verifica daca un element se afla in colectie
		bool cauta(TElem elem) const;

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

};

