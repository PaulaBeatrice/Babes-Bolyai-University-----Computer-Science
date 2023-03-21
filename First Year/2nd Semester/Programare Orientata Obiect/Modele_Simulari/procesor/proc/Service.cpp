#include "Service.h"
#include<algorithm>

void Store::addPl(string nume, string soclu, int pret)
{
	PlacaDeBaza p{ nume, soclu, pret };
	plRepo.store(p);
}

vector<PlacaDeBaza> Store::filtruSocluPl(string soclu)
{
	const vector<PlacaDeBaza>& all = getAllPl();
	vector<PlacaDeBaza> filtred;
	std::copy_if(all.begin(), all.end(), back_inserter(filtred), [soclu](const PlacaDeBaza& p) {
		return p.getsocluPl() == soclu;
		});
	return filtred;
}

vector<PlacaDeBaza> Store::getAllPl()
{
	return this->plRepo.getAllPlaci();
}

vector<Procesor> Store::getAllPr()
{
	return this->prRepo.getAllProcesor();
}