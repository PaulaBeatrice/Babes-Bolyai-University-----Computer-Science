#pragma once

#include "Domain.h"
#include <iostream>
#include <vector>

using namespace std;

class Repo {
private:
	vector<Apartament> allApartamente;
public:
	Repo() = default;
	Repo(const Repo& ot) = delete;

	/*
	Adauga un apartament in lista
	@param a: apartamentul ce se va adauga
	*/
	virtual void store(const Apartament& a);

	/*
	Sterge un apartament din lista
	@param a: apartamentul ce se va sterge
	*/
	virtual void delete_ap(const Apartament& a);

	/*
	Returneaza lista apartamentelor
	*/
	const vector<Apartament>& getAllAp();
};

class RepoFile : public Repo {
private:
	string filename;
	/*
	Incarca datele din fisier
	*/
	void loadFromFile();

	/*
	Salveaza informatii in fisier
	*/
	void saveToFile();
public:
	RepoFile(string f_name) : Repo(), filename{ f_name }{loadFromFile(); };

	/*
	Adauga un apartament in lista
	@param a: apartamentul ce se va adauga
	*/
	void store(const Apartament& a) override;

	/*
	Sterge un apartament din lista
	@param a: apartamentul ce se va sterge
	*/
	void delete_ap(const Apartament& a)override;
};