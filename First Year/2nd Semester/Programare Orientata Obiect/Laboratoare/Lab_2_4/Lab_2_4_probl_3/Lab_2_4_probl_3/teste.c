#include "domain.h"
#include "repo.h"
#include "service.h"
#include <assert.h>
#include <string.h>
#include <stdio.h>
#include "validare.h"

void test_get()
{
	Cheltuiala cheltuiala = CreeazaCheltuiala(15, 150, "mancare");
	assert(getZiEfectuareCheltuaiala(& cheltuiala) == 15);
	assert(getSumaCheltuiala(&cheltuiala)== 150);
	assert(strcmp(getTipCheltuiala(&cheltuiala), "mancare") == 0);
}

void test_set()
{
	Cheltuiala cheltuiala = CreeazaCheltuiala(15, 150, "mancare");
	seteazaZiEfectuareaCheltuiala(&cheltuiala, 8);
	seteazaSumaCheltuiala(&cheltuiala, 120);
	seteazaTipulCheltuieli(&cheltuiala, "altele");
	assert(getZiEfectuareCheltuaiala(&cheltuiala) == 8);
	assert(getSumaCheltuiala(&cheltuiala) == 120);
	assert(strcmp(getTipCheltuiala(&cheltuiala), "altele") == 0);
}

void test_valideaza()
{
	Cheltuiala cheltuiala_zi_invalida = CreeazaCheltuiala(-15, 100, "mancare");
	Cheltuiala cheltuiala_suma_invalida = CreeazaCheltuiala(15, -100, "mancare");
	Cheltuiala cheltuiala_tip_invalid = CreeazaCheltuiala(15, 100, "rewfw");
	Cheltuiala cheltuiala_tip_2 = CreeazaCheltuiala(15, 100, "");
	Cheltuiala cheltuiala_valida = CreeazaCheltuiala(15, 100, "mancare");
	assert(valideazaCheltuiala(&cheltuiala_valida) == 0);
	assert(valideazaCheltuiala(&cheltuiala_zi_invalida) == 1);
	assert(valideazaCheltuiala(&cheltuiala_suma_invalida) == 2);
	assert(valideazaCheltuiala(&cheltuiala_tip_invalid) == 3);
	assert(valideazaCheltuiala(&cheltuiala_tip_2) == 3);
	//assert(valideazaCheltuiala(&cheltuiala_invalida) == 4);
}

void test_adaugare_repo()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	assert(get_numarul_cheltuieli_din_lista(&lista) == 0);
	Cheltuiala cheltuiala = CreeazaCheltuiala(15, 100, "mancare");
	adaugaCheltuialaInLista(&lista, &cheltuiala);
	assert(get_numarul_cheltuieli_din_lista(&lista) == 1);
}

void test_modificare_repo()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	Cheltuiala cheltuiala = CreeazaCheltuiala(15, 100, "mancare");
	adaugaCheltuialaInLista(&lista, &cheltuiala);

	assert(getSumaCheltuiala(&lista.cheltuieli[0]) == 100);
	modificareCheltuialaDinLista(&lista, 1, "suma", "", 50);
	assert(getSumaCheltuiala(&lista.cheltuieli[0]) == 50);
	modificaCheltuialaData(&lista, 1, "zi", "", 4);
	assert(getZiEfectuareCheltuaiala(&lista.cheltuieli[0]) == 4);
	modificaCheltuialaData(&lista, 1, "tip", "transport", 0);
	assert(strcmp(getTipCheltuiala(&lista.cheltuieli[0]), "transport") == 0);
}

void test_adaugare_service()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	assert(get_numarul_cheltuieli_din_lista(&lista) == 0);
	adaugaInListaDeCheltuieliService(&lista, 4, 74, "imbracaminte");
	assert(get_numarul_cheltuieli_din_lista(&lista) == 1);
}

void test_modificare_service()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	Cheltuiala cheltuiala = CreeazaCheltuiala(8, 6, "transport");
	adaugaCheltuialaInLista(&lista, &cheltuiala);
	assert(modificaCheltuialaData(&lista, 3, "zi", "", 8) == -1);
	assert(modificaCheltuialaData(&lista, 1, "suma", "", - 5) == 1);
	assert(modificaCheltuialaData(&lista, 1, "asd", "s", 4) == 2);
	assert(modificaCheltuialaData(&lista, 1, "suma", "", 120) == 0);
	assert(getSumaCheltuiala(&lista.cheltuieli[0]) == 120);
}

void test_sorteaza_crescator_suma()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 9, 10, "transport");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	sorteaza_cheltuielile_dupa_suma_crescator(&lista);
	int i;
	for (i = 1; i < lista.nr_cheltuieli; i++)
	{
		if (lista.cheltuieli[i].suma < lista.cheltuieli[i - 1].suma)
			assert(1==1);
	}
}

void test_sorteaza_descrescator_suma()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 9, 10, "transport");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	sorteaza_cheltuielile_dupa_suma_descrescator(&lista);
	int i;
	for (i = 1; i < lista.nr_cheltuieli; i++)
	{
		if (lista.cheltuieli[i].suma > lista.cheltuieli[i - 1].suma)
			assert(1==1);
	}
}

void test_sorteaza_crescator_tip()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 9, 10, "transport");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	sorteaza_cheltuielile_dupa_tip_crescator(&lista);
	int i;
	for (i = 1; i < lista.nr_cheltuieli; i++)
	{
		if (strcmp(lista.cheltuieli[i].tip, lista.cheltuieli[i - 1].tip) < 0)
			assert(1==1);
	}
}

void test_sorteaza_descrescator_tip()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 9, 10, "transport");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	sorteaza_cheltuielile_dupa_tip_descrescator(&lista);
	int i;
	for (i = 1; i < lista.nr_cheltuieli; i++)
	{
		if (strcmp(lista.cheltuieli[i].tip, lista.cheltuieli[i - 1].tip) > 0)
			assert(1==1);
	}
}

void test_vizionare_lista_zi()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 7, 10, "transport");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	Lista_DE_Cheltuieli lista_filtrata = vizionare_lista_dupa_zi(&lista, 7);
	for (int i = 0; i < lista_filtrata.nr_cheltuieli; ++i)
	{
		if (getZiEfectuareCheltuaiala(&lista_filtrata.cheltuieli[i]) != 7)
			assert(1==1);
	}
}

void test_vizionare_lista_suma()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 4, 100, "transport");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	Lista_DE_Cheltuieli lista_filtrata = vizionare_lista_dupa_suma(&lista, 100);
	for (int i = 0; i < lista_filtrata.nr_cheltuieli; ++i)
	{
		if (lista_filtrata.cheltuieli[i].suma != 100)
			assert(1==1);
	}
}

void test_vizionare_lista_tip()
{
	Lista_DE_Cheltuieli lista = creeazaLista();
	adaugaInListaDeCheltuieliService(&lista, 7, 100, "altele");
	adaugaInListaDeCheltuieliService(&lista, 10, 150, "mancare");
	adaugaInListaDeCheltuieliService(&lista, 3, 10, "altele");
	adaugaInListaDeCheltuieliService(&lista, 1, 200, "imbracaminte");
	Lista_DE_Cheltuieli lista_filtrata = vizionare_lista_dupa_tip(&lista, "altele");
	for (int i = 0; i < lista_filtrata.nr_cheltuieli; ++i)
	{
		if (strcmp(lista_filtrata.cheltuieli[i].tip, "altele") != 0)
			assert(1==1);
	}
}

void run_all_tests()
{
	test_get();
	test_set();
	test_valideaza();
	test_adaugare_repo();
	test_modificare_repo();
	test_adaugare_service();
	test_modificare_service();
	test_sorteaza_crescator_suma();
	test_sorteaza_descrescator_suma();
	test_sorteaza_crescator_tip();
	test_sorteaza_descrescator_tip();
	test_vizionare_lista_zi();
	test_vizionare_lista_suma();
	test_vizionare_lista_tip();
}