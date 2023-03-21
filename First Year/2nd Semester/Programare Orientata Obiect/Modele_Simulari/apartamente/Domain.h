#pragma once

#include<iostream>

using namespace std;

class Apartament {
private:
	int suprafata, pret;
	string strada;

public:
	Apartament() = delete;
	Apartament(int supr, string strada, int pr) :suprafata{ supr }, strada{ strada }, pret{ pr }{};
	Apartament(const Apartament& ot) : suprafata{ ot.getSupr() }, strada{ ot.getStr() }, pret{ ot.getPr() }{};

	/*
	Returneaza suprafata unui apartament
	*/
	int getSupr()const noexcept;

	/*
	Returneaza strada unui apartament
	*/
	string getStr()const;

	/*
	Returneaza pretul unui apartament
	*/
	int getPr()const noexcept;
};