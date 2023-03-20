#include "console.h"

#include "oferta.h"
#include <iostream>
#include <string>

using std::cout;
using std::cin;
using std::vector;


void ConsoleUI::tipareste(const vector<Oferta>& oferte) {
	cout << "Oferte:\n";
	for (const auto& oferta : oferte) {
		cout << "Denumire: " << oferta.getDenumire() << ", Destinatie: " << oferta.getDestinatie() << ", Tip: " << oferta.getTip() << ", Pret: " << oferta.getPret() << '\n';
	}
	cout << "Sfarsit lista oferte\n";
}

void ConsoleUI::adaugaUI() {
	string den, dest, tip;
	int pret;
	cout << "Denumire: ";
	cin >> den;
	cout << "Destinatie:";
	cin >> dest;
	cout << "Tip:";
	cin >> tip;
	cout << "Pret:";
	cin >> pret;
	ctr.addOferta(den, dest, tip, pret);
	cout << "Adaugata cu succes\n";
}

void ConsoleUI::start() {
	while (true) {
		cout << "        Meniu:\n";
		cout << "1. Adauga oferta\n";
		cout << "2. Afiseaza ofertele\n";
		cout << "3. Sorteaza dupa denumire\n";
		cout << "4. Sorteaza dupa destinatie\n";
		cout << "5. Sorteaza dupa tip si pret\n";
		cout << "6. Filtru destinatie\n";
		cout << "7. Filtru pret\n";
		cout << "0. Exit\n";
		cout << "*******************************\n";
		cout << "Introduceti comanda: ";
		
		int cmd;
		string dest;
		int pr;
		cin >> cmd;
		try {
			switch (cmd) {
			case 1:
				adaugaUI();
				break;
			case 2:
				tipareste(ctr.getAll());
				break;
			case 3:
				tipareste(ctr.sortByDenumire());
				break;
			case 4:
				tipareste(ctr.sortByDestinatie());
				break;
			case 5: 
				tipareste(ctr.sortByTipPret());
				break;
			case 6:
				cout << "Introduceti destinatia: ";
				cin >> dest;
				tipareste(ctr.filtrareDestiantieEgala(dest));
				break;
			case 7:
				cout << "Introduceti pretul: ";
				char pret[101];
				cin >> pret;
				pret[strlen(pret)] = '\0';
				pr = atoi(pret);
				if (pr != 0)
					tipareste(ctr.filtrarePretEgal(pr));
				//else 
				//	tipareste(ctr.filtrarePretEgal(-1));
				break;
			case 0:
				return;
			default:
				cout << "Comanda invalida!\n";
			}
		}
		catch (const OfertaRepoException& ex) {
			cout << ex << '\n';
		}
		catch (const ValidateException& ex) {
			cout << ex << '\n';
		}
	}
}