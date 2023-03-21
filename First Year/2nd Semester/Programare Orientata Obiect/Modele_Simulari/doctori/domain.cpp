#include "domain.h"


string Doctor::getCNP()const {
	return this->cnp;
}
string Doctor::getNume()const {
	return this->nume;
}
string Doctor::getPrenume()const {
	return this->prenume;
}
string Doctor::getSectie()const {
	return this->sectie;
}
int Doctor::getConcediu()const {
	return this->concediu;
}

void Doctor::setCNP(string cnp_nou) {
	this->cnp = cnp_nou;
}
void Doctor::setNume(string nume_nou) {
	this->nume = nume_nou;
}
void Doctor::setPrenume(string prenume_nou) {
	this->prenume = prenume_nou;
}
void Doctor::setConcediu(int concediu) {
	this->concediu = concediu;
}
void Doctor::setSectie(string sectie_noua) {
	this->sectie = sectie_noua;
}