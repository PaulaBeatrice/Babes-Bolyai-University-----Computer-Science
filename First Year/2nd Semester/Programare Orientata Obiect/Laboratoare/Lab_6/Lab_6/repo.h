#pragma once
#include "oferta.h"

#include <string>
#include <vector>
#include <ostream>
#include "gsl.h"

using std::vector;
using std::string;
using std::ostream;

class OfertaRepo {
	vector<Oferta> all;
	/*
		metoda privata verifica daca exista deja o in repository
	*/
	bool exist(const Oferta& o) const;
public:
	OfertaRepo() = default;

	//nu permitem copierea de obiecte
	OfertaRepo(const OfertaRepo& ot) = delete;

	/*
		Salvare oferta
		arunca exceptie daca mai exista o oferta cu aceeasi destinatie si acelasi tip
	*/
	void store(const Oferta& o);

	/*
		Cauta
		arunca exceptie daca nu exista oferta
	*/
	const Oferta& find(string destinatie, string tip) const;

	/*
		returneaza toate ofertele salvate
	*/
	const vector<Oferta>& getAll() const noexcept;
};

/*
Folosit pentru a semnala situatii exceptionale care pot aparea in repo
*/
class OfertaRepoException {
	string msg;
public:
	OfertaRepoException(string m) :msg{ m } {}
	//functie friend (vreau sa folosesc membru privat msg)
	friend ostream& operator<<(ostream& out, const OfertaRepoException& ex);
};

ostream& operator<<(ostream& out, const OfertaRepoException& ex);

void testeRepo();