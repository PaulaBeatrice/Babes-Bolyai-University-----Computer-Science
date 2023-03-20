#include "repo.h"
#include <assert.h>
#include <iostream>
#include <sstream>

using std::ostream;
using std::stringstream;

void OfertaRepo::store(const Oferta& o)
{
	if (exist(o)) {
		throw OfertaRepoException("Exista deja oferta cu destinatia: " + o.getDestinatie() + " si de tipul: " + o.getTip());
	}
	all.push_back(o);
}

bool OfertaRepo::exist(const Oferta& o)const {
	try {
		find(o.getDestinatie(), o.getTip());
		return true;
	}
	catch (OfertaRepoException&) {
		return false;}
}

const Oferta& OfertaRepo::find(string destinatie, string tip) const {
	for (const auto& o : all) {
		if (o.getDestinatie() == destinatie && o.getTip() == tip) {
			return o;
		}
	}
	// daca nu exista arunc o exceptie
	throw OfertaRepoException("Nu exista oferta cu destinatia: " + destinatie + " si de tipul: " + tip);}

const vector<Oferta>& OfertaRepo::getAll() const noexcept {
	return all;
}

ostream& operator<<(ostream& out, const OfertaRepoException& ex) {
	out << ex.msg;
	return out;
}

void testAdauga() {
	OfertaRepo rep;
	rep.store(Oferta{ "a", "a", "a", 100 });
	assert(rep.getAll().size() == 1);
	assert(rep.find("a", "a").getDenumire() == "a");

	rep.store(Oferta{ "b", "b", "b", 20 });
	assert(rep.getAll().size() == 2);

	//nu pot adauga 2 cu aceeasi destinatie si acelasi tip
	try {
		rep.store(Oferta{ "a", "a", "a", 50 });
		assert(0 != 1);
	}
	catch (const OfertaRepoException&) {
		assert(true);
	}

	//cauta inexistent
	try {
		rep.find("c", "c");
		assert(0 != 1);
	}
	catch (const OfertaRepoException& e) {
		stringstream os;
		os << e;
		assert(os.str().find("exista") >= 0);
	}
}

void testCauta() {
	OfertaRepo rep;
	rep.store(Oferta{ "a", "a", "a", 100 });
	rep.store(Oferta{ "b", "b", "b", 20 });

	auto o = rep.find("a", "a");
	assert(o.getDenumire() == "a");
	assert(o.getPret() == 100);

	//arunca exceptiie daca nu gaseste
	try {
		rep.find("a", "b");
		assert(0 != 1);
	}
	catch (OfertaRepoException&) {
		assert(true);
	}
}

void testeRepo() {
	testAdauga();
	testCauta();
}
