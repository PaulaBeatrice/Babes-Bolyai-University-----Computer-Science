#pragma once

#include"Domain.h"
#include <iostream>
#include<vector>

using namespace std;

class Repo {
private:
	vector<Jucator> allJucatori;
public:
	Repo() = default;
	Repo(const Repo& ot) = delete;

	/*
	Adauga un jucator in lista
	*/
	virtual void store(const Jucator & j);

	/*
	Sterge un jucator din lista
	*/
	virtual void delete_pl(string nume);

	/*
	Returneaza o lista cu toti jucatori
	*/
	const vector<Jucator>& getAllJucatori();

	/*
	Cauta un jucator cu nume dat
	*/
	Jucator& find(string nume);

};

class RepoFile : public Repo {
private:
	string filename;
	void loadFromFile();
	void saveToFile();
public:
	RepoFile(string fname) : Repo(), filename{ fname } {
		loadFromFile();
	};

	void store(const Jucator& j) override;
	void delete_pl(string nume) override;
};

void teste_repo();