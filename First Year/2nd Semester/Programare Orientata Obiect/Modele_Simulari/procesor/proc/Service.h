#pragma once
#include <functional>
#include "Repo.h"

using namespace std;

class Store {
private:
	ProcRepo& prRepo;
	PlRepo& plRepo;
public:
	Store(ProcRepo& prRepo, PlRepo& plRepo) :prRepo{ prRepo }, plRepo{ plRepo }{};
	Store(const Store& ot) = delete;

	void addPl(string nume, string soclu, int pret);

	vector<PlacaDeBaza> filtruSocluPl(string soclu);
	vector<PlacaDeBaza> getAllPl();

	vector<Procesor> getAllPr();
};