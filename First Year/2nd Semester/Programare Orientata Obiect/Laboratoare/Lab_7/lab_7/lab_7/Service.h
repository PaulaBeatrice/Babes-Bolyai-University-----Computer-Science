#pragma once
#include "oferta.h"
#include "Repo.h"
#include "Validator.h"

typedef bool(*Compare)(const Oferta&, const string&);
typedef int(*Sort)(const Oferta&, const Oferta&);

class OfertaService {
private:
	OfertaRepo repo;
	Validator valid;
public:
	OfertaService(const OfertaRepo& repo, const Validator& valid) : repo{ repo }, valid{ valid } {
	}
	VectorDinamic<Oferta> getAll();
	void addOfera(const string& denumire, const string& destinatie, const string& tip, const int& pret);
	void modifyOferta(const string& denumire, const string& destinatie, const string& tip, const int& pret);
	Oferta findOferta(const string& denumire);
	void deleteOferta(const string& denumire);
	VectorDinamic<Oferta> filterOferta(const string& filterParameter, Compare function);
	VectorDinamic<Oferta> sortOferte(Sort sortFunction);
};