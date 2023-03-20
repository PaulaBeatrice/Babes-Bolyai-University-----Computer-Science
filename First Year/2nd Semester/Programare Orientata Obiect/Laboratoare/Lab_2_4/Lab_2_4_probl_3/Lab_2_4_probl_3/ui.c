#include <stdio.h>
#include "repo.h"
#include "service.h"

/* 3 Buget de familie

Creati o aplicatie care permite gestiunea bugetului pentru o familie. Aplicatia trebuie sa stocheze cheltuielile pe o luna. Fiecare
cheltuiala are : zi(ziua in care s - a efectuat), suma, tip(mancare, transport, telefon& internet, inbracaminte, altele).

Aplicatia permite :
	a) Adaugarea de cheltuieli noi(cand familia cumpara ceva sau plateste o factura)
	b) Modificarea unei cheltuieli existente(ziua, tipul, suma)
	c) Stergere cheltuiala
	d) Vizualizare lista de cheltuieli filtrat dupa o proprietate(suma, ziua, tipul)
	e) Vizualizare lista de cheltuieli ordonat dupa suma sau tip(crescator / descrescator)
*/

void afisare_meniu()
{
	printf("1 -> Adaugare de cheltuiala in lista\n");
	printf("2 -> Modificare cheltuiala existenta\n");
	printf("3 -> Afisare Lista\n");
	printf("4 -> Vizualizare cheltuieli dupa un criteriu anume\n");
	printf("5 -> Sorteaza lista crescator/descrescator dupa suma/tip\n");
	printf("6 -> Stergere cheltuiala\n");
	printf("7 -> Exit\n");
}

void afiseaza_lista(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	//printf("Numarul de cheltuieli este: %d\n", lista_de_cheltuieli->nr_cheltuieli);
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli; i++)
		printf("%d. Zi efectuare: %d, Suma: %d, Tip: %s\n", i + 1, getZiEfectuareCheltuaiala(&lista_de_cheltuieli->cheltuieli[i]), getSumaCheltuiala(&lista_de_cheltuieli->cheltuieli[i]), getTipCheltuiala(&lista_de_cheltuieli->cheltuieli[i]));
}

void adaugaInLista(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	int zi;
	int suma;
	char tip[20];
	printf("Introduceti ziua efectuarii cheltuielii: ");
	scanf_s("%d", &zi);
	Cheltuiala ch = CreeazaCheltuiala(zi, 100, "altele");
	if (valideazaCheltuiala(ch) == 1)
		printf("Ati introdus o zi invalida!\n");
	else
	{
		printf("Introduceti suma: ");
		scanf_s("%d", &suma);
		Cheltuiala ch2 = CreeazaCheltuiala(zi, suma, "altele");
		if (valideazaCheltuiala(ch2) == 2)
			printf("Ati introdus o suma invalida!\n");
		else
		{
			printf("Introduceti tipul: ");
			scanf_s("%s", &tip, sizeof(tip));
			Cheltuiala ch3 = CreeazaCheltuiala(zi, suma, tip);
			if (valideazaCheltuiala(ch3) == 3)
				printf("Ati introdus un tip invalid!\n");
			else
			{
				adaugaInListaDeCheltuieliService(lista_de_cheltuieli, zi, suma, tip);
				printf("Cheltuiala adaugata cu succes!\n");
			}		
		}
	}	
}

void modificareCheltuiala(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	int index;
	char camp_de_modificat[31];
	char valoarea_noua[51];
	int valoare_noua;
	printf("Alegeti nr de ordine al cheltuielii pe care doriti sa o modificati: ");
	scanf_s("%d", &index);
	printf("Alegeti  din lista [zi, suma, tip] campul pe care doriti sa-l modificati ");
	scanf_s("%s", &camp_de_modificat, sizeof(camp_de_modificat));
	printf("Introduceti noua valoare: ");
	if (strcmp(camp_de_modificat, "zi") == 0 || strcmp(camp_de_modificat, "suma") == 0)
	{
		scanf_s("%d", &valoare_noua, sizeof(valoare_noua));
		strcpy_s(valoarea_noua, sizeof(valoarea_noua), "a");
	}
	if (strcmp(camp_de_modificat, "tip") == 0)
	{
		scanf_s("%s", &valoarea_noua, sizeof(valoarea_noua));
		valoare_noua = 0;
	}
		
	int verif = modificaCheltuialaData(lista_de_cheltuieli, index, camp_de_modificat, valoarea_noua, valoare_noua);
	if (verif == -1)
		printf("Indexul nu este corect!\n");
	else if (verif == 1)
		printf("Valoarea noua este invalida!\n");
	else if (verif == 2)
		printf("Campul introdus nu face parte din lista data!\n");
	else
		printf("Actualizare realizata cu succes!\n");

}



void vizualizare(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	Lista_DE_Cheltuieli lista_filtrata = creeazaLista();
	printf("1 -> Vizionare dupa zi\n");
	printf("2 -> Vizionare dupa suma\n");
	printf("3 -> Vizionare dupa tip\n");
	int comanda;
	scanf_s("%d", &comanda);
	if (comanda == 1)
	{
		int zi;
		printf("Introduceti ziua dupa care doriti sa cautati: ");
		scanf_s("%d", &zi);
		lista_filtrata = vizionare_lista_dupa_zi(lista_de_cheltuieli, zi);
		afiseaza_lista(&lista_filtrata);
	}
	else if (comanda == 2)
	{
		int suma;
		printf("Introduceti suma dupa care doriti sa cautati: ");
		scanf_s("%d", &suma);
		lista_filtrata = vizionare_lista_dupa_suma(lista_de_cheltuieli, suma);
		afiseaza_lista(&lista_filtrata);
	}
	else if (comanda == 3)
	{
		char tip[20];
		printf("Introduceti suma dupa care doriti sa cautati: ");
		scanf_s("%s", &tip, sizeof(tip));
		lista_filtrata = vizionare_lista_dupa_tip(lista_de_cheltuieli, tip);
		afiseaza_lista(&lista_filtrata);
	}
}

void sorteaza(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	printf("1 -> Sortare dupa suma\n");
	printf("2 -> Sortare dupa tip\n");
	int cmd;
	scanf_s("%d", &cmd);
	if (cmd == 1)
	{
		printf("1 -> Sortare dupa suma crescatoare\n");
		printf("2 -> Sortare dupa suma descrescatoare\n");
		scanf_s("%d", &cmd);
		if (cmd == 1)
		{
			sorteaza_cheltuielile_dupa_suma_crescator(lista_de_cheltuieli);
			afiseaza_lista(lista_de_cheltuieli);
		}
		else
		{
			sorteaza_cheltuielile_dupa_suma_descrescator(lista_de_cheltuieli);
			afiseaza_lista(lista_de_cheltuieli);
		}
	}
	else if (cmd == 2)
	{
		printf("1 -> Sortare dupa tip crescatoare\n");
		printf("2 -> Sortare dupa tip descrescatoare\n");
		scanf_s("%d", &cmd);
		if (cmd == 1)
		{
			sorteaza_cheltuielile_dupa_tip_crescator(lista_de_cheltuieli);
			afiseaza_lista(lista_de_cheltuieli);
		}
		else
		{
			sorteaza_cheltuielile_dupa_tip_descrescator(lista_de_cheltuieli);
			afiseaza_lista(lista_de_cheltuieli);
		}		
	}
}

int run()
{
	Lista_DE_Cheltuieli lista_de_cheltuieli = creeazaLista();

	while (1)
	{
		afisare_meniu();
		int optiune_meniu;
		scanf_s("%d", &optiune_meniu);
		if (optiune_meniu == 1)
			adaugaInLista(&lista_de_cheltuieli);
		else if (optiune_meniu == 2)
			modificareCheltuiala(&lista_de_cheltuieli);
		else if (optiune_meniu == 3)
			afiseaza_lista(&lista_de_cheltuieli);
		else if (optiune_meniu == 4)
			vizualizare(&lista_de_cheltuieli);
		else if (optiune_meniu == 5)
			sorteaza(&lista_de_cheltuieli);
		else if (optiune_meniu == 7)
			return;
		else
			printf("Comanda invalida!");
	}
}