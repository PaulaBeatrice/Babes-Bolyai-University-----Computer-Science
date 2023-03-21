#include "Repo.h"
#include "Errors.h"
#include <fstream>

void OfertaRepo::store(const Oferta& o)
{
	if (exists(o)) {
		throw Exception("Oferta exista deja in lista.\n");
	}
	this->allOferte.push_back(o);
}

void OfertaRepo::delete_of(string denumire)
{
	int i = 0;
	while (i < this->allOferte.size() && this->allOferte.at(i).getDenumire() != denumire) {
		i++;
	}
	if (i < this->allOferte.size()) this->allOferte.erase(this->allOferte.begin() + i);
	else throw Exception("Oferta nu exista!\n");
}

void OfertaRepo::modify_of(const Oferta& oferta) {
	int i = 0;
	while (i < this->allOferte.size() && !(this->allOferte.at(i).getDenumire() == oferta.getDenumire()))
		i++;
	if (i < this->allOferte.size()) {
		this->allOferte.at(i).setDestinatie(oferta.getDestinatie());
		this->allOferte.at(i).setTip(oferta.getTip());
		this->allOferte.at(i).setPret(oferta.getPret());
	}
	else throw Exception("Oferta nu exista!\n");
}


vector<Oferta>& OfertaRepo::getAllOferte() 
{
	return allOferte;
}

const Oferta& OfertaRepo::find(string denumire)
{
	vector<Oferta>::iterator f = std::find_if(this->allOferte.begin(), this->allOferte.end(),[=](const Oferta& o) {
			return o.getDenumire() == denumire;});

	if (f != this->allOferte.end())
		return(*f);
	else
		throw Exception("Oferta nu exista in lista!\n");}

bool OfertaRepo::exists(const Oferta& o)
{
	try {
		find(o.getDenumire());
		return true;
	}
	catch (Exception) {
		return false;
	}}