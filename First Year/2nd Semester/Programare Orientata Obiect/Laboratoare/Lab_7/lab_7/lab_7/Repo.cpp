#include "Repo.h"
#include "Errors.h"

Oferta OfertaRepo::findOferta(const string& denumire) {
	for (int i = 0; i < OferteList.size(); i++) {
		if (OferteList.get(i).getDenumire() == denumire) 
			return OferteList.get(i);
	}
	throw Exception("Oferta nu exista!\n");
}

void OfertaRepo::addOferta(const Oferta& oferta) {
	for (int i = 0; i < OferteList.size(); i++) {
		if (OferteList.get(i).getDenumire() == oferta.getDenumire()) {
			throw Exception("Oferta deja exista!\n");
		}
	}
	OferteList.push_back(Oferta(oferta));
}

void OfertaRepo::deleteOferta(const string& denumire) {
	int i = 0;
	while (i < OferteList.size() && OferteList.get(i).getDenumire() != denumire) {
		i++;
	}
	if (i < OferteList.size()) OferteList.erase(i);
	else throw Exception("Oferta nu exista!\n");
}

void OfertaRepo::modifyOferta(const Oferta& oferta) {
	int i = 0;
	while (i < OferteList.size() && !(OferteList.get(i) == oferta)) i++;
	if (i < OferteList.size()) {
		OferteList.get(i).setDestinatie(oferta.getDestinatie());
		OferteList.get(i).setTip(oferta.getTip());
		OferteList.get(i).setPret(oferta.getPret());
	}
	else throw Exception("Oferta nu exista!\n");
}