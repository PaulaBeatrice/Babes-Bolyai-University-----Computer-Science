#pragma once
#include "Repository.h"

class Service
{
private:
	RepoFile& repo;
public:
	Service() = default;
	Service(RepoFile& repo) :repo{ repo } {};
	Service(const Service& service) = delete;

	const vector<Doctor> filreazaSectie(string sectie);
	const vector<Doctor> filtreazaNume(string nume);
	const vector<Doctor> filtreazaSectie_Nume(string sectie, string nume);
	vector<Doctor> getAllDoctori();
};
