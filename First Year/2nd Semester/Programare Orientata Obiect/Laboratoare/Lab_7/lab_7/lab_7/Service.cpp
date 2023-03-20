#include "Service.h"

VectorDinamic<Oferta> OfertaService::getAll() {
	return repo.getAll();
}

void OfertaService::addOfera(const string& denumire, const string& destinatie, const string& tip, const int& pret) {
	valid.validateOferta(denumire, destinatie, tip, pret);
	Oferta newOferta{ denumire, destinatie, tip, pret };
	repo.addOferta(newOferta);
}

void OfertaService::modifyOferta(const string& denumire, const string& destinatie, const string& tip, const int& pret) {
	valid.validateOferta(denumire, destinatie, tip, pret);
	Oferta modifiedOferta{ denumire, destinatie, tip, pret };
	repo.modifyOferta(modifiedOferta);
}

Oferta OfertaService::findOferta(const string& denumire) {
	valid.validateOferta(denumire, "a", "a", 10);
	return repo.findOferta(denumire);
}

void OfertaService::deleteOferta(const string& denumire) {
	valid.validateOferta(denumire, "a", "a", 1);
	repo.deleteOferta(denumire);
}

VectorDinamic<Oferta> OfertaService::filterOferta(const string& filterParameter, Compare function) {
	VectorDinamic <Oferta> result;
	VectorDinamic <Oferta> list = repo.getAll();
	for (int i = 0; i < list.size(); i++) {
		Oferta currentD;
		currentD.setDenumire(list.get(i).getDenumire());
		currentD.setDestinatie(list.get(i).getDestinatie());
		currentD.setTip(list.get(i).getTip());
		currentD.setPret(list.get(i).getPret());
		if (function(currentD, filterParameter)) result.push_back(currentD);
	}
	return result;
}

VectorDinamic <Oferta> OfertaService::sortOferte(Sort sortFunction) {
	VectorDinamic <Oferta> result;
	VectorDinamic<Oferta> list = repo.getAll();
	for (int i = 0; i < list.size(); i++) {
		result.push_back(Oferta(list.get(i)));
	}
	bool sorted = false;
	while (!sorted) {
		sorted = true;
		for (int i = 0; i < result.size() - 1; i++) {
			if (sortFunction(result.get(i), result.get(i + 1)) > 0) {
				Oferta aux = result.get(i);
				result.get(i) = result.get(i + 1);
				result.get(i + 1) = aux;
				sorted = false;
			}
		}
	}
	return result;
}

