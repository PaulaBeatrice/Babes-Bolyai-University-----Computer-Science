#pragma once
#include "oferta.h"
#include "vectordinamic.h"
#include <vector>
#include <string>
#include <ostream>

using std::vector;
using std::string;
using std::ostream;

class OfertaRepo {

private:
	VectorDinamic<Oferta> OferteList;

public:

	VectorDinamic<Oferta> getAll() {
		return OferteList;
	}

	Oferta findOferta(const string& denumire);

	void addOferta(const Oferta& oferta);

	void deleteOferta(const string& denumire);

	void modifyOferta(const Oferta& oferta);

	int length() {
		return int(OferteList.size());
	}
};