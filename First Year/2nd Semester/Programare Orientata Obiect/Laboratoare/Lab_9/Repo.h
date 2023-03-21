#pragma once
#include "oferta.h"
#include "Cos.h"
#include <vector>
#include <string>
#include <ostream>
#include <map>
#include <unordered_map>
#include <random>

using std::vector;

class Repo {
public:
	Repo() = default;
	//punem delete fiindca nu vrem sa se faca nicio copie la Repository
	Repo(const Repo& ot) = delete;

	/*
	Adauga o oferta in lista
	*/
	virtual void store(const Oferta& o) = 0;

	/*
	Sterge o oferta in lista
	*/
	virtual void delete_of(string denumire) = 0;

	/*
	Modifica o oferta din lista
	*/
	virtual void modify_of(const Oferta& o) = 0;

	/*
	Returneaza o lista cu toate ofertele
	*/
	//const vector<Oferta>& getAllOferte() noexcept = 0;

	/*
	Cauta o oferta cu o denumire data
	*/
	virtual const Oferta& find(string denumire) = 0;

	/*
	Verifica daca o oferta data exista in lista
	*/
	virtual bool exists(const Oferta& o) = 0;

	virtual vector<Oferta>& getAllOferte() = 0;
};

class OfertaRepo: public Repo {

private:
	vector<Oferta> allOferte;
	friend class Cos;
public:
	OfertaRepo() = default;
	//punem delete fiindca nu vrem sa se faca nicio copie la Repository
	OfertaRepo(const OfertaRepo& ot) = delete;

	/*
	Adauga o oferta in lista
	@param o: oferta care se adauga (Oferta)
	@return -
	post: Oferta va fi adaugata in lista
	@throws: Exception daca exista o oferta cu aceeasi denumirea
	*/
	void store(const Oferta& o) override;

	/*
	Sterge o oferta in lista
	@param denumire: denumirea ofertei care se va sterge
	@return -
	post: Oferta va fi stearsa din lista
	@throws: Exception daca o oferta nu exista
	*/
	void delete_of(string denumire) override;

	/*
	Modifica o oferta din lista
	@param o: oferta care se modifica (Oferta)
	@return -
	post: Oferta va fi modificata
	@throws: Exception daca nu exista oferta 
	*/
	void modify_of(const Oferta& o) override;

	/*
	Returneaza o lista cu toate ofertele
	@return: lista cu ofertele (vector of Oferta objects)
	*/
	vector<Oferta>& getAllOferte() override;

	/*
	Cauta o oferta cu o denumire data
	@param denumire: denumirea dupa care se cauta
	@returns: entitatea Oferta cu denumirea data (daca aceasta exista)
	@throws: Exception daca nu exista Oferta cu denumirea data*/
	const Oferta& find(string denumire) override;

	/*
	Verifica daca o oferta data exista in lista
	@param o: oferta care se cauta in lista
	@return: true daca oferta exista, false altfel
	*/
	bool exists(const Oferta& o) override;

};


class ProbRepo : public Repo {
	std::unordered_map<int, Oferta> oferte;
	double prob;
	friend class Cos;
	void pass() const;

public:
	ProbRepo() {
		std::mt19937 gen(123);
		std::uniform_real_distribution<double> dis(0.0, 1.0);
		prob = dis(gen);
	};

	void setProb(double f);

	//distrugem constructorul de copiere
	ProbRepo(const OfertaRepo& alt) = delete;

	/*
	* Adauga filmul dat in repo
	* Arunca exceptie daca exista deja un film cu acelasi titlu, gen si an al aparitiei
	*/
	void store(const Oferta& oferta) override;

	/*
	* Modifica filmul dat in noul film
	* Arunca exceptie daca nu exista filmul dat
	*/
	void modify_of(const Oferta& o) override;

	/*
	* Cauta filmul dupa titlu, gen si anul aparitiei
	* Arunca exceptie daca nu se gaseste
	*/
	const Oferta& find(string denumire) override;

	/*
	* Returneaza indicele filmului cautat in lista de filme
	* Returneaza -1 daca filmul nu se gaseste
	*/
	bool exists(const Oferta& o) override;

	/*
	* Sterge filmul cautat din lista
	* Returneaza filmul cautat daca in gaseste
	*/
	void delete_of(string denumire) override;

	//returneaza o lista cu toate filmele
	vector<Oferta>& getAllOferte() override;
};

