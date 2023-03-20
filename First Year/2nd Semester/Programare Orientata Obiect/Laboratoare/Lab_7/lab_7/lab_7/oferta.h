/*
6 Agenție de turism
Creați o aplicație care permite:
· gestiunea unei liste de oferte turistice. Oferta: denumire, destinație, tip, preț
· adăugare, ștergere, modificare și afișare oferte turistice
· căutare oferta
· filtrare oferte turistice după: destinație, preț
· sortare oferte după: denumire, destinație, tip + preț
*/

#pragma once
#include <string>
#include <iostream>

using std::cout;
using std::string;

class Oferta {
public:
	Oferta() = default;
	Oferta(const string& denumire, const string& destinatie, const string& tip, const int& pret);
	Oferta(const Oferta& ot) :denumire{ ot.getDenumire()}, destinatie{ot.getDestinatie()}, tip{ot.getTip()}, pret{ot.getPret()}{
	//	cout << "Copy constructor called\n";
	}
	string getDenumire() const;
	string getDestinatie() const;
	string getTip() const;
	int getPret() const noexcept;

	void setDenumire(string denumire_noua);
	void setDestinatie(string destinatie_noua);
	void setTip(string tip_nou);
	void setPret(int pret_nou);

	string toString()const;
	bool operator==(const Oferta& o1);
private:
	string denumire;
	string destinatie;
	string tip;
	int pret=-1;
};
