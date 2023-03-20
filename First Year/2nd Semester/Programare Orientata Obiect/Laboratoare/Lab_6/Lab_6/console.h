#pragma once
#include "store.h"
#include "oferta.h"

class ConsoleUI {
	OfertaStore& ctr;

	/*
	Citeste datele de la tastatura si adauga Oferta
	arunca exceptie daca: nu se poate salva, nu e valida
	*/
	void adaugaUI();

	/*
	Tipareste o lista de oferte la consola
	*/
	void tipareste(const vector<Oferta>& oferte);

public:
	ConsoleUI(OfertaStore& ctr) noexcept :ctr{ ctr } {
	}
	//nu permitem copierea obiectelor
	ConsoleUI(const ConsoleUI& ot) = delete;

	void start();

};