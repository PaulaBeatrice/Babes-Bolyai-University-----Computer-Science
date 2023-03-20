#include "Console.h"
#include "Errors.h"
#include <iostream>
#include <cstring>

//Lambda functions
int sortByDenumire(const Oferta& o1, const Oferta& o2) {
	return o1.getDenumire().compare(o2.getDenumire());
}

int sortByDestinatie(const Oferta& o1, const Oferta& o2) {
	return o1.getDestinatie().compare(o2.getDestinatie());
}

int sortByTipSiPret(const Oferta& o1, const Oferta& o2) {
	if (o1.getTip().compare(o2.getTip()) == 0) {
		return (o1.getTip().compare(o2.getTip()));
	}
	else {
		return o1.getPret() > o2.getPret();
	}
}

bool DestinatieEgala(const Oferta& oferta, const string& destinatie) {
	return oferta.getDestinatie() == destinatie;
}

bool PreturiEgale(const Oferta& oferta, const string& pret) {
	return std::to_string(oferta.getPret()) == pret;
}

void printList(VectorDinamic <Oferta> list) {
	if (list.size() == 0) std::cout << "Nu exista oferte!\n";
	else {
		for (int i = 0; i < list.size(); i++) {
			std::cout << list.get(i).toString() << endl;
		}
	}
	std::cout << endl;
}

void UI::UI_printMeniu() const {
	std::cout << "\n";
	std::cout << "***********MENIU**************\n";
	std::cout << "1. Adauga oferta\n";
	std::cout << "2. Modifica oferta\n";
	std::cout << "3. Sterge oferta\n";
	std::cout << "4. Cauta oferta\n";
	std::cout << "5. Filtru oferte\n";
	std::cout << "6. Sortare oferte\n";
	std::cout << "7. Afiseaza ofertele\n";
	std::cout << "0. Exit\n";
	std::cout << "\n";
}

void UI::UI_addOferta() {
	string denumire, destinatie, tip;
	char pr[10001];
	int pret;
	std::cout << "Denumire: ";
	std::cin >> denumire;
	std::cout << "Destinatie: ";
	std::cin >> destinatie;
	std::cout << "Tip: ";
	std::cin >> tip;
	std::cout << "Pret: ";
	std::cin >> pr;
	pret = atoi(pr);
	if (!(pret < 0 || strlen(pr) > 10 || strlen(pr) == 10 && pr[0] > '2'))
	{
		try {
			service.addOfera(denumire, destinatie, tip, pret);
			std::cout << "^^^^Oferta a fost adaugata!^^^^\n";
		}
		catch (Exception ex) {
			std::cout << ex.getMessage() << endl;
		}
	}
	else
		std::cout << "^^^^Pretul trebuie sa fie un numar mai mic de 2.000.000.000!^^^^";
}

void UI::UI_modifyOferta() {
	string denumire, destinatie, tip;
	char pr[10001];
	int pret;
	std::cout << "Denumire: ";
	std::cin >> denumire;
	std::cout << "Destinatie noua: ";
	std::cin >> destinatie;
	std::cout << "Tip nou: ";
	std::cin >> tip;
	std::cout << "Pret nou: ";
	std::cin >> pr;
	pret = atoi(pr);
	if (!(pret < 0 || strlen(pr) > 10 || strlen(pr) == 10 && pr[0] > '2'))
	{
		try {
			service.modifyOferta(denumire, destinatie, tip, pret);
			std::cout << "^^^^Oferta a fost modificata!^^^^\n";
		}
		catch (Exception ex) {
			std::cout << ex.getMessage() << endl;
		}
	}
	else
		std::cout << "^^^^Pretul trebuie sa fie un numar mai mic de 2.000.000.000!^^^^";
}

void UI::UI_deleteOferta() {
	string denumire;
	std::cout << "Denumire: ";
	std::cin >> denumire;
	try {
		service.deleteOferta(denumire);
		std::cout << "^^^^Oferta a fost stearsa!^^^^\n";
	}
	catch (Exception ex) {
		std::cout << ex.getMessage() << endl;
	}
}

void UI::UI_searchOferta() {
	string denumire;
	std::cout << "Denumire: ";
	std::cin >> denumire;
	try {
		Oferta found = service.findOferta(denumire);
		std::cout << found.toString() << endl;
	}
	catch (Exception ex) {
		std::cout << ex.getMessage() << endl;
	}
}

void UI::UI_filterOferta() {
	std::cout << "Filter by:\n1.Destinatie\n2.Pret\n>>> ";
	string command;
	cin >> command;
	if (command == "1") {
		string destinatie;
		cout << "Destinatie: ";
		cin >> destinatie;
		auto list = service.filterOferta(destinatie, DestinatieEgala);
		printList(list);
	}
	else if (command == "2") {
		string pret;
		cout << "Pret: ";
		cin >> pret;
		auto list = service.filterOferta(pret, PreturiEgale);
		printList(list);
	}
	else {
		std::cout << "^^^^Invalid command!^^^^\n";
	}
}

void UI::UI_sortOferta() {
	std::cout << "Sort by:\n1.Denumire\n2.Destinatie\n3.Tip+Pret\n>>> ";
	string command;
	std::cin >> command;
	if (command == "1") {
		auto list = service.sortOferte(sortByDenumire);
		printList(list);
	}
	else if (command == "2") {
		auto list = service.sortOferte(sortByDestinatie);
		printList(list);
	}
	else if (command == "3") {
		auto list = service.sortOferte(sortByTipSiPret);
		printList(list);
	}
	else {
		std::cout << "^^^^Invalid command!^^^^\n";
	}
}

void UI::UI_printOferte() {
	auto list = service.getAll();
	for (int i = 0; i < list.size(); i++) {
		std::cout << list.get(i).toString() << endl;
	}
}

void UI::runApplication() {
	bool runProgram = true;
	string command;
	while (runProgram) {
		UI_printMeniu();
		std::cout << ">>>";
		std::cin >> command;
		if (command == "0") {
			std::cout << "Program ended!\n";
			runProgram = false;
		}
		else if (command == "1") {
			UI_addOferta();
		}
		else if (command == "2") {
			UI_modifyOferta();
		}
		else if (command == "3") {
			UI_deleteOferta();
		}
		else if (command == "4") {
			UI_searchOferta();
		}
		else if (command == "5") {
			UI_filterOferta();
		}
		else if (command == "6") {
			UI_sortOferta();
		}
		else if (command == "7") {
			UI_printOferte();
		}
		else {
			std::cout << "^^^^Invalid command!^^^^\n";
		}

	}
}