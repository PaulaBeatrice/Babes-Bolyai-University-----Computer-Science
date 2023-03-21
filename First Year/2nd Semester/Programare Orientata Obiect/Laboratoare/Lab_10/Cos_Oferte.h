#pragma once
#include "oferta.h"
#include <vector>
#include <algorithm>
#include <random>
#include <chrono>

using std::vector;

class CosOferte {
private:
	vector<Oferta> cosOferte;
public:
	CosOferte() = default;

	/*
	* Adauga o oferta in cos
	* @param o: oferta care se adauga (Oferta)
	* post: oferta va fi adaugata in cos
	*/
	void addOfertaToCos(const Oferta& o);

	/*
	* Elimina toate ofertele din cos
	* post: cosul este gol
	*/
	void emptyCos();

	/*
	* Adauga un numar random de oferte in cos
	* @param originalOferte: ofertele din care se alege (vector<Oferta>)
	* @param howMany: cate oferte se adauga (int)
	* post: ofertele sunt adaugate in cosul curent
	*/
	void addRandomOferte(vector<Oferta> originalOferte, int howMany);

	// Returneaza un vector care contine toate ofertele din cos
	const vector<Oferta>& getAllOferteCos();

	void exportaCos(string numeFisier);
};