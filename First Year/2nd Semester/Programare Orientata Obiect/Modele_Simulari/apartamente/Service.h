#pragma once

#include<iostream>
#include "Repo.h"

using namespace std;

class Store {
private:
	Repo& repo;
public:
	Store(Repo& repo) :repo{ repo }{};
	Store(const Store& ot) = delete;
	
	/*
	Sterge un apartament din lista
	@param supr: suprafata apartamentului ce se va sterge
	@param str: strada apartamentului ce se va sterge
	@param pr: pretul apartamentului ce se va sterge
	*/
	void sterge(int supr, string str, int pr);

	/*
	Returneaza o lista cu apartamentele care au suprafata cuprinsa intre doua valori date
	@param supr1: prima suprafata data
	@param supr2: a doua suprafata data
	*/
	vector<Apartament> filtruSupr(int supr1, int supr2);

	/*
	Returneaza o lista cu apartamentele care au pretul cuprins intre doua valori date
	@param pr1: primul pret dat
	@param pr2: al doilea pret dat
	*/
	vector<Apartament> filtruPr(int pr1, int pr2);

	/*
	Returneaza o lista cu apartamentele
	*/
	const vector<Apartament>& getAllAp() {
		return this->repo.getAllAp();
	}
};