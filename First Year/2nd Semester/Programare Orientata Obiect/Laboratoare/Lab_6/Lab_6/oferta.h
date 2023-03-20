/*
6 Agenție de turism
Creați o aplicație care permite:
· gestiunea unei liste de oferte turistice. Oferta: denumire, destinație, tip, preț
· adăugare, ștergere, modificare și afișare oferte turistice
· căutare oferta
· filtrare oferte turistice după: destinație, preț
· sortare oferte după: denumire, destinație, tip + preț
*/

#pragma once
#include <string>
#include <iostream>

using std::string;
using std::cout;

class Oferta {
	std::string denumire;
	std::string destinatie;
	std::string tip;
	int pret;

public: 
	Oferta(const string n, const string d, const string t, int p) : denumire{ n }, destinatie{ d }, tip{ t }, pret{ p }{}

	Oferta(const Oferta& ot) :denumire{ ot.denumire }, destinatie{ ot.destinatie }, tip{ ot.tip }, pret{ ot.pret }{
		cout << "!!!!!!!!!!!!!!!!!!!!!!!\n";
	}

	string getDenumire() const {
		return denumire;
	}

	string getDestinatie() const {
		return destinatie;
	}

	string getTip() const {
		return tip;
	}

	int getPret() const noexcept {
		return pret;
	}

};

/*
	Compara dupa denumire 
	returneaza o1.denumire e mai mic decat o2.denumire
*/
bool cmpDenumire(const Oferta& o1, const Oferta& o2);

/*
	Compara dupa destinatie
	returneaza o1.destinatie e mai mic decat o2.destinatie
*/
bool cmpDestinatie(const Oferta& o1, const Oferta& o2);

