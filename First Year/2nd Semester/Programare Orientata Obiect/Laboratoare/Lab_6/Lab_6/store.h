#pragma once

#include "oferta.h"
#include "repo.h"
#include <string>
#include <vector>

#include <functional>
#include "validator.h"

using std::vector;
using std::function;

class OfertaStore {
	OfertaRepo& rep;
	OfertaValidator& val;

	/*
	 Functie de sortare generala
	 maiMareF - functie care compara 2 Oferte, verifica daca are loc relatia mai mare
			  - poate fi orice functe (in afara clasei) care respecta signatura (returneaza bool are 2 parametrii Oferta)
			  - poate fi functie lambda (care nu capteaza nimic in capture list)
	 returneaza o lista sortata dupa criteriu dat ca paramatru
	*/
	vector<Oferta> generalSort(bool(*maimicF)(const Oferta&, const Oferta&));

	/*
	Functie generala de filtrare
	fct - poate fi o functie
	fct - poate fi lambda, am folosit function<> pentru a permite si functii lambda care au ceva in capture list
	returneaza doar ofertele care trec de filtru (fct(oferta)==true)
	*/
	vector<Oferta> filtreaza(function<bool(const Oferta&)> fct);

public:
	OfertaStore(OfertaRepo& rep, OfertaValidator& val) noexcept :rep{ rep }, val{ val } {}

	//nu permitem copierea de obiecte OfertaStore
	OfertaStore(const OfertaStore& ot) = delete;

	//returneaza toate ofertele in ordinea in care au fost adaugate
	const vector<Oferta>& getAll() noexcept {
		return rep.getAll();
	}

	/*
		Adauga o noua oferta
		arunca exceptie daca: nu se poate salva, nu este valid
	*/
	void addOferta(const string& denumire, const string& destinatie, const string& tip, int pret);

	// sorteaza dupa denumire
	vector<Oferta> sortByDenumire();

	// sorteaza dupa destinatie
	vector<Oferta> sortByDestinatie();

	//sorteaza dupa tip + pret
	vector<Oferta> sortByTipPret();

	//filtru destinatie
	vector<Oferta> filtrareDestiantieEgala(string destinatie);

	//filtru pret
	vector<Oferta> filtrarePretEgal(int pret) noexcept;
};

void testCtr();