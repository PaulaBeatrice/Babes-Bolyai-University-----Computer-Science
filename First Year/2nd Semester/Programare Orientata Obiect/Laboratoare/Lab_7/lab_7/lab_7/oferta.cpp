#include "oferta.h"
#include <assert.h>

Oferta::Oferta(const string& denumire, const string& destinatie, const string& tip, const int& pret) {
	this->denumire = denumire;
	this->destinatie = destinatie;
	this->tip = tip;
	this->pret = pret;
}

string Oferta::getDenumire() const
{
	return this->denumire;
}

string Oferta::getDestinatie() const
{
	return this->destinatie;
}

string Oferta::getTip() const
{
	return this->tip;
}

int Oferta::getPret() const noexcept
{
	return this->pret;
}

void Oferta::setDenumire(string denumire_noua)
{
	this->denumire = denumire_noua;
}

void Oferta::setDestinatie(string destinatie_noua)
{
	this->destinatie = destinatie_noua;
}

void Oferta::setTip(string tip_nou)
{
	this->tip = tip_nou;
}

void Oferta::setPret(int pret_nou)
{
	this->pret = pret_nou;
}

string Oferta::toString()const {
	string text;
	text.append("Denumire: ");
	text.append(denumire);
	text.append(" | Destinatie: ");
	text.append(destinatie);
	text.append(" | Tip: ");
	text.append(tip);
	text.append(" | Pret: ");
	text.append(std::to_string(pret));
	return text;
}

bool Oferta::operator==(const Oferta& other) {
	return this->denumire == other.getDenumire();
}
