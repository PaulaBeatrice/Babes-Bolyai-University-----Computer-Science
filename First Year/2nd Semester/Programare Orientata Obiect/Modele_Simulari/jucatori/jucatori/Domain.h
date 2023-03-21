#pragma once
#include<iostream>
using namespace std;


class Jucator {
private:
	string nume, tara;
	int puncte, rank;
public:
	Jucator() = default;
	Jucator(string nume, string tara, int pct, int rnk) :nume{ nume }, tara{ tara }, puncte{ pct }, rank{ rnk }{};
	Jucator(const Jucator& ot) : nume{ ot.getNume() }, tara{ ot.getTara() }, puncte{ ot.getPct() }, rank{ ot.getRnk() }{};
	/*
	Returneaza numele unui jucator
	*/
	string getNume()const;
	/*
	Returneaza tara unui jucator
	*/
	string getTara()const;
	/*
	Returneaza punctajul unui jucator
	*/
	int getPct() const noexcept;

	/*
	Returneaza rank ul unui jucator
	*/
	int getRnk() const noexcept;

	/*
	Actualizeaza rank ul
	*/
	void setRank(int rnk);


	/*
	Actualizeaza punctajul ul
	*/
	void setPct(int pct);
};

void teste_domain();