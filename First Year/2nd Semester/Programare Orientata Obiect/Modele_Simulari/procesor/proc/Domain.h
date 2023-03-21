#pragma once
#include <iostream>

using namespace std;

class Procesor {
private:
	string nume, soclu_pr;
	int nrThr, pret;
public:
	Procesor() = delete;
	Procesor(string nume, int nrThr, string soclu_pr, int pret) :nume{ nume }, nrThr{ nrThr }, soclu_pr{ soclu_pr }, pret{ pret }{};
	Procesor(const Procesor& ot) :nume{ ot.nume }, nrThr{ ot.nrThr }, soclu_pr{ ot.soclu_pr }, pret{ ot.pret }{};
	
	string getNumePr()const;
	int getNrThr()const;
	string getsocluPr()const;
	int getpretPr()const;
};

class PlacaDeBaza {
private:
	string nume, soclu_pr;
	int pret;
public:
	PlacaDeBaza() = delete;
	PlacaDeBaza(string nume, string soclu_pr, int pret) :nume{ nume }, soclu_pr{ soclu_pr }, pret{ pret }{};
	PlacaDeBaza(const PlacaDeBaza& ot) :nume{ ot.nume }, soclu_pr{ ot.soclu_pr }, pret{ ot.pret }{};

	string getNumePl()const;
	string getsocluPl()const;
	int getpretPl()const;
};