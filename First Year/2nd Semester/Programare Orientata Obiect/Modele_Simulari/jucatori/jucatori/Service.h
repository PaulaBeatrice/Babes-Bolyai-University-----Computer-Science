#pragma once
#include <iostream>
#include "Repo.h"
using namespace std;

class Service {
private:
	Repo& repo;
public:
	Service(Repo& repo) :repo{ repo }{};
	Service(const Service& ot) = delete;

	/*
	Adauga un jucator
	*/
	void add(string nume, string tara, int pct, int rnk);

	/*
	Sorteaza jucatori dupa punctaj
	*/
	vector<Jucator> sortByPct();

	/*
	Sterge un jucator
	*/
	void stergere(string nume);

	/*
	Returneaza toti jucatori
	*/
	vector<Jucator> getAllJucatori();

	/*
	Modifica punctajului unui jucator
	*/
	void ModificaPunctaj(int pct, string nume);

};

void test_service();