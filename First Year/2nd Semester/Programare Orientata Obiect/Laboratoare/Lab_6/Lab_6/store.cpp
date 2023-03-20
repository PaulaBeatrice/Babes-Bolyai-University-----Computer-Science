#include "store.h"
#include <functional>
#include <algorithm>
#include <assert.h>
#include "gsl.h"
//gsl::not_null<const connection*> connection

vector<Oferta> OfertaStore::generalSort(bool(*maiMicF)(const Oferta&, const Oferta&)) {
	vector<Oferta> v{ rep.getAll() };//fac o copie	
	for (size_t i = 0; i < v.size(); i++) {
		for (size_t j = i + 1; j < v.size(); j++) {
				if (!maiMicF(gsl::at(v, i), gsl::at(v, j))) {
					//interschimbam
					Oferta aux = gsl::at(v, i);
					gsl::at(v, i) = gsl::at(v, j);
					//v[i] = v[j];
					gsl::at(v, j) = aux;
				}
		}
	}
	return v;
}

/*
Adauga un nou pet
arunca exceptie daca: nu se poate salva, nu este valid
*/
void OfertaStore::addOferta(const string& denumire, const string& destinatie, const string& tip, int pret) {
	Oferta o{ denumire, destinatie, tip, pret };
	val.validate(o);
	rep.store(o);
}

/*
Sorteaza dupa denumire
*/
vector<Oferta> OfertaStore::sortByDenumire() {
	auto copyAll = rep.getAll();
	std::sort(copyAll.begin(), copyAll.end(), cmpDenumire);
	return copyAll;
	//return generalSort(cmpType);
}

/*
Sorteaza dupa destinatie
*/
vector<Oferta> OfertaStore::sortByDestinatie() {
	//auto copyAll = rep.getAll();
	//std::sort(copyAll.begin(), copyAll.end(), cmpDestinatie);
	//return copyAll;
	return generalSort(cmpDestinatie);
}

/*
Sorteaza dupa tip+pret
*/
vector<Oferta> OfertaStore::sortByTipPret() {
	return generalSort([](const Oferta& o1, const Oferta& o2) {
		if (o1.getTip() == o2.getTip()) {
			return o1.getPret() < o2.getPret();
		}
		return o1.getTip() < o2.getTip();
	});
}

vector<Oferta> OfertaStore::filtreaza(function<bool(const Oferta&)> fct) {
	vector<Oferta> rez;
	for (const auto& oferta : rep.getAll()) {
		if (fct(oferta)) {
			rez.push_back(oferta);
		}
	}
	return rez;
}

vector<Oferta> OfertaStore::filtrareDestiantieEgala(string destinatie) {
	return filtreaza([destinatie](const Oferta& o) {
		return o.getDestinatie() == destinatie;
	});
}

vector<Oferta> OfertaStore::filtrarePretEgal(int pret)  noexcept{
	return filtreaza([pret](const Oferta& o) {
		return o.getPret() == pret;
		});
}

void testAdaugatr() {
	OfertaRepo rep;
	OfertaValidator val;
	OfertaStore ctr{ rep, val };
	ctr.addOferta("a", "a", "a", 10);
	assert(ctr.getAll().size() == 1);

	//adaug ceva invalid
	try {
		ctr.addOferta("", "", "", -10);
		assert(0 != 1);
	}
	catch (ValidateException&) {
		assert(true);
	}

	//adaug ceva ce exista deja
	try {
		ctr.addOferta("a", "a", "a", -5);
		assert(0 != 1);
	}
	catch (ValidateException&) {
		assert(true);
	}
}

void testFiltrare() {
	OfertaRepo rep;
	OfertaValidator val;
	OfertaStore ctr{ rep, val };
	ctr.addOferta("a", "a", "a", 10);
	ctr.addOferta("b", "a", "l", 25);
	ctr.addOferta("c", "a", "k", 10);
	assert(ctr.filtrareDestiantieEgala("a").size() == 3);
	assert(ctr.filtrareDestiantieEgala("c").size() == 0);
	assert(ctr.filtrarePretEgal(10).size() == 2);
	assert(ctr.filtrarePretEgal(25).size() == 1);
	assert(ctr.filtrarePretEgal(40).size() == 0);
}

void testSortare() {
	OfertaRepo rep;
	OfertaValidator val;
	OfertaStore ctr{ rep, val };
	ctr.addOferta("a", "a", "t", 10);
	ctr.addOferta("b", "r", "l", 25);
	ctr.addOferta("c", "o", "l", 80);

	vector<Oferta> COPIE = ctr.sortByDenumire();
	auto firstO = gsl::at(COPIE, 0);
	assert(firstO.getTip() == "t");
	COPIE = ctr.sortByDestinatie();
	firstO = gsl::at(COPIE, 0);
	assert(firstO.getTip() == "t");
	COPIE = ctr.sortByTipPret();
	firstO =gsl::at(COPIE, 0);
	assert(firstO.getDestinatie() == "r");
}

void testCtr() {
	testAdaugatr();
	testFiltrare();
	testSortare();
}
