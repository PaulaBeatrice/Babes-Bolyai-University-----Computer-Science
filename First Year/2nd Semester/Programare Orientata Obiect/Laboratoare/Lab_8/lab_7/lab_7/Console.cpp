#include "Console.h"
#include "Errors.h"
#include <iostream>
#include <cstring>
#include<fstream>
void UI::UI_printMeniu() const {
	std::cout << "\n";
	std::cout << "***********MENIU**************\n";
	std::cout << "1. Adauga oferta\n";
	std::cout << "2. Modifica oferta\n";
	std::cout << "3. Sterge oferta\n";
	std::cout << "4. Filtru oferte\n";
	std::cout << "5. Sortare oferte\n";
	std::cout << "6. Afiseaza ofertele\n";
	std::cout << "7. Meniu Cos\n";
	std::cout << "8. Comenzi string\n";
	std::cout << "0. Exit\n";
	std::cout << "\n";
}

void UI::UI_printMeniuCos() const
{
	std::cout << "***********MENIU COS**************\n";
	std::cout << "1. Adauga oferta in cos\n";
	std::cout << "2. Adauga oferte random in cos\n";
	std::cout << "3. Goleste cos\n";
	std::cout << "4. Afiseaza ofertele din cos\n";
	std::cout << "5. Export\n";
	std::cout << "6. Inapoi la meniul principal\n";
}

void UI::UI_printOferte(const vector<Oferta>& allOferte) {

	if (allOferte.size() == 0)
		cout << "Nu exista oferte." << endl;
	else {
		for (const auto& oferta : allOferte) {
			cout << "Denumire: " << oferta.getDenumire() << " | Destinatie: " << oferta.getDestinatie() << " | Tip: " << oferta.getTip() << " | Pret: " << oferta.getPret() << endl;
		}
	}
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
			srv.addOferta(denumire, destinatie, tip, pret);
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
			srv.modifyOferta(denumire, destinatie, tip, pret);
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
		srv.deleteOferta(denumire);
		std::cout << "^^^^Oferta a fost stearsa!^^^^\n";
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
		auto list = srv.filtrareDest(destinatie);
		UI_printOferte(list);
	}
	else if (command == "2") {
		char pret[1001];
		cout << "Pret: ";
		cin >> pret;
		int pr = atoi(pret);
		auto list = srv.filtrarePret(pr);
		UI_printOferte(list);
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
		auto list = srv.sortByDenumire();
		UI_printOferte(list);
	}
	else if (command == "2") {
		auto list = srv.sortByDestinatie();
		UI_printOferte(list);
	}
	else if (command == "3") {
		auto list = srv.sortByTipSiPret();
		UI_printOferte(list);
	}
	else {
		std::cout << "^^^^Invalid command!^^^^\n";
	}
}

void UI::run() {
	bool runProgram = true;
	int command;
	while (runProgram) {
		UI_printMeniu();
		std::cout << ">>>";
		std::cin >> command;
		switch (command)
		{
		case 1:
			UI_addOferta();
			break;
		case 2:
			UI_modifyOferta();
			break;
		case 3:
			UI_deleteOferta();
			break;
		case 4:
			UI_filterOferta();
			break;
		case 5:
			UI_sortOferta();
			break;
		case 6:
			UI_printOferte(srv.getAllOferte());
			break;
		case 7: 
			UI_Cos();
			break;
		case 8:
			UI_comenzi("lista.txt");
			break;
		case 0:
			runProgram = false;
			break;
		default:
			std::cout << "^^^^Invalid command!^^^^\n";
			break;
		}
	}
}

void UI::UI_Cos() {
	bool runProgram = true;
	int command;
	while (runProgram) {
		UI_printMeniuCos();
		std::cout << ">>>";
		std::cin >> command;
		switch (command)
		{
		case 1:
			UI_addOfertaCos();
			break;
		case 2:
			UI_addRandomOferteCos();
			break;
		case 3:
			UI_EmptyCos();
			break;
		case 4:
			UI_printOferte(srv.getAllOferteCos());
			break;
		case 5:
			std::cout << "EXPORT";
			break;
		case 6:
			runProgram = false;
			break;
		default:
			std::cout << "^^^^Invalid command!^^^^\n";
			break;
		}
	}
}

void UI::UI_addOfertaCos() {
	string denumire;
	std::cout << "Denumirea ofertei este: ";
	getline(cin >> ws, denumire);
	try {
		srv.addToCos(denumire);
		std::cout << "^^^^Oferta s-a adaugat in cos^^^^";
	}
	catch (Exception ex) {
		std::cout << ex.getMessage() << endl;
	}
}

void UI::UI_addRandomOferteCos() {
	int howManyToAdd;
	char str[1001];
	std::cout << "Cate oferte sa se adauge in cos?";
	cin >> str;
	howManyToAdd = stoi(str);
	if (!(howManyToAdd < 0 || strlen(str) > 10 || strlen(str) == 10 && str[0] > '2'))
	{
		try {
			int howManyAdded = srv.addRandomToCos(howManyToAdd);
			std::cout << "^^^^S-au adaugat " << howManyAdded << "oferte in cos^^^^";
		}
		catch (Exception ex) {
			std::cout << ex.getMessage() << endl;
		}
	}
	else
		std::cout << "^^^^Trebuie sa introduceti un numar mai mic de 2.000.000.000 si sa nu introduceti alte caractere inafara de cifre!^^^^";
}

void UI::UI_EmptyCos() {
	srv.emptyCos();
	cout << "^^^^S-au sters toate ofertele din cosul curent.^^^^" << endl;
}

void UI::UI_comenzi(string filename)
{
	string line, opt;
	std::ifstream fin(filename);
	const string delimitator = ",";
	while (fin >> opt) {
		if (opt == "print")
			UI_printOferte(srv.getAllOferte());
		else
		{
			std::getline(fin, line);
			line.erase(0, 1);
			std::vector<string> v;
			string token = line.substr(0, line.find(delimitator));
			size_t pos = 0;
			while ((pos = line.find(delimitator)) != string::npos) {
				token = line.substr(0, pos);
				v.push_back(token);
				line.erase(0, pos + delimitator.length());
			}
			if (line != "")
				v.push_back(line);
			if (opt == "add")
			{
				if (v.size() != 4) {
					std::cout << "Comanda invalida!\n";
					continue;
				}
				try {
					int pret = stoi(v[3]);
					srv.addOferta(v[0], v[1], v[2], pret);
					std::cout << "^^^^Oferta a fost adaugata!^^^^\n";
				}
				catch (Exception ex) {
					std::cout << ex.getMessage() << endl;
				}
			}
			if (opt == "delete")
			{
				if(v.size() != 1) {
					std::cout << "Comanda invalida!\n";
					continue;
				}
				try {
					srv.deleteOferta(v[0]);
					std::cout << "^^^^Oferta a fost stersa!^^^^\n";
				}
				catch (Exception ex) {
					std::cout << ex.getMessage() << endl;
				}
			}
			if (opt == "update")
			{
				if (v.size() != 4) {
					std::cout << "Comanda invalida!\n";
					continue;
				}
				try {
					int pret = stoi(v[3]);
					srv.modifyOferta(v[0], v[1], v[2], pret);
					std::cout << "^^^^Oferta a fost modificata!^^^^\n";
				}
				catch (Exception ex) {
					std::cout << ex.getMessage() << endl;
				}
			}
		}
	}
}
