#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>

using namespace std;


Colectie::Colectie() {
	// 1) vom initializa capacitatea celor 2 vectori dinamici cu 2, iar redimensionarea se va face dubland capacitatile lor
	// 2) numarul de elemente al celor doi vectori va fi initial 0
	// 3) alocarea spatiului de memorie sa va face in coformitate cu capacitatile initiale
	
	/*1*/ this->capacitate_el_distincte = 1;
	/*2*/ this->nr_el_distincte = 0;
	/*3*/ this->distincte = new Distincte[this->capacitate_el_distincte];

	/*1*/ this->capacitate_pozitii = 1;
	/*2*/ this->nr_el_pozitii = 0;
	/*3*/ this->pozitii = new Pozitii[this->capacitate_pozitii];
}


void Colectie::adauga(TElem elem) //Complexitate: O(n), n - lungimea vectorului de elemente distincte
{
	int poz = this->cauta_pozitie(elem);
	if (poz == this->nr_el_distincte) // elementul nu apare in vectorul ce contine elemente distincte
	{
		// vom adauga elementul dat ca parametru in vectorul de elemente distincte si vom adauga pozitia pe care acesta se afla in vectorul de pozitii
		this->distincte[poz].element = elem;
		this->pozitii[this->nr_el_pozitii].pozitie = poz;

		// vom creste lungimile celor doi vectori
		this->nr_el_distincte++;
		this->nr_el_pozitii++;
		
		// verificam daca este necesar sa dublam capacitatea celor doi vectori (adica daca lungimea vectorului e egala cu capacitatea sa, ii vom dubla 
		// capacitatea) 
		if (this->nr_el_distincte == this->capacitate_el_distincte)
		{
			this->capacitate_el_distincte *= 2;
			Distincte* elemente_distincte_nou = new Distincte[this->capacitate_el_distincte];
			for (int i = 0; i < this->nr_el_distincte; i++) // copiem elementele intr-un nou vector 
				elemente_distincte_nou[i] = this->distincte[i];
			delete[]this->distincte; // stergem elementele din vectorul initial
			this->distincte = elemente_distincte_nou; // vectorul initial primeste elementele din vectorul nou construit
		}

		if (this->nr_el_pozitii == this->capacitate_pozitii)
		{
			this->capacitate_pozitii *= 2;
			Pozitii* elemente_pozitii_nou = new Pozitii[this->capacitate_pozitii];
			for (int i = 0; i < this->nr_el_pozitii; i++) // copiem elementele intr-un nou vector
				elemente_pozitii_nou[i] = this->pozitii[i];
			delete[]this->pozitii; // stergem elementele din vectorul initial
			this->pozitii = elemente_pozitii_nou; // vectorul initial primeste elementele din vectorul nou construit
		}
	}
	else // poz != lungimea vectorului de elemente distincte
	{
		// elementul se afla deja in vectorul de distincte deci trebuie doar sa adaugam pozitia acestuia in vectorul de pozitii
		this->pozitii[this->nr_el_pozitii++].pozitie = poz; // adaugam pozitia in vector
		// verificam daca este necesar sa dublam capacitatea vectorului de pozitii (adica daca lungimea vectorului e egala cu capacitatea sa, ii vom dubla 
		// capacitatea) 
		if (this->nr_el_pozitii == this->capacitate_pozitii)
		{
			this->capacitate_pozitii *= 2;
			Pozitii* elemente_pozitii_nou = new Pozitii[this->capacitate_pozitii];
			for (int i = 0; i < this->nr_el_pozitii; i++) // copiem elementele intr-un nou vector
				elemente_pozitii_nou[i] = this->pozitii[i];
			delete[]pozitii; // stergem elementele din vectorul initial
			this->pozitii = elemente_pozitii_nou; // vectorul initial primeste elementele din vectorul nou construit
		}
	}
}


bool Colectie::sterge(TElem elem) // Complexitate: O(m + n), m - lungimea vectorului de pozitii, n - lungimea vectorului de elemente distincte
{
	bool gasit = false;
	int poz = this->cauta_pozitie(elem);
	if(poz == this->nr_el_distincte) // elementul pe care dorim sa il stergem nu exista in vectorul de elemente dsitincte
		return false;

	// cautam in vectorul de pozitii, poz (pozitia elemtului pe care dorim sa il stergem)
	for (int i = 0; i < this->nr_el_pozitii; i++)
	{
		if (poz == this->pozitii[i].pozitie)
		{
			gasit = true;
			for (int j = i; j < this->nr_el_pozitii - 1; j++) // se sterge pozitia elementului care trebuie sters
				this->pozitii[j] = this->pozitii[j + 1];
			this->nr_el_pozitii--;
			break;
		}
	}
	return gasit;
}


bool Colectie::cauta(TElem elem) const // Complexitate: O(m + n), m - lungimea vectorului de pozitii, n - lungimea vectorului de elemente distincte
{
	int poz = cauta_pozitie(elem);
	for (int i = 0; i < this->nr_el_pozitii; i++)
		if (poz == this->pozitii[i].pozitie)
			return true;
	return false;
}

int Colectie::nrAparitii(TElem elem) const // Complexitate: O(m + n), m - lungimea vectorului de pozitii, n - lungimea vectorului de elemente distincte
{
	int i, frecv_el = 0;
	int poz = cauta_pozitie(elem);
	for (int i = 0; i < this->nr_el_pozitii; i++)
		if (this->pozitii[i].pozitie == poz)
			frecv_el++;
	return frecv_el;
}


int Colectie::dim() const // Complexitate: O(1)
{
	return this->nr_el_pozitii;
}


bool Colectie::vida() const // Complexitate: O(1)
{
	if (this->nr_el_distincte == 0)
		return true;
	return false;
}

IteratorColectie Colectie::iterator() const {
	return  IteratorColectie(*this);
}


Colectie::~Colectie() // Complexitate: O(1)
{
	delete[]this->distincte;
	delete[]this->pozitii;
}

int Colectie::cauta_pozitie(TElem elem) const // Complexitate: O(n), n - lungimea vectorului ce contine elementele distincte
{
	//Functia returneaza pozitia lui elem in vectorul de distincte, sau lungimea vectorului de elemente distincte daca nu exista
	if (this->nr_el_distincte == 0)
		return 0;
	for (int i = 0; i < this->nr_el_distincte; i++)
	{
		if (this->distincte[i].element == elem)
			return i;
	}
	return this->nr_el_distincte;
}


