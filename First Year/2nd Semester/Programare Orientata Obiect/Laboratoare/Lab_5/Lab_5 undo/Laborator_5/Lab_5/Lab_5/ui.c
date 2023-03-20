#include "ui.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include <stdio.h>
#include <string.h>


void printAllProducts(List* v) {
	if (v->len == 0)
		printf("Nu exista produse.\n");
	else
	{
		printf("Produsele curente sunt:\n");
		for (int i = 0; i < size(v); i++) {
			produs* m = get(v, i);
			printf("Id: %d | Pret: %d | Cantitate: %d | Tip: %s | Producator: %s | Model: %s \n", m->id, m->pret, m->cantitate, m->type, m->producator, m->model);
		}
	}
}

void printMenu() {
	printf("1.Adauga produs nou\n");
	printf("2.Afiseaza produsele\n");
	printf("3.Modifica pretul unui produs \n");
	printf("4.Modifica cantitatea unui produs \n");
	printf("5.Sterge produs \n");
	printf("6.Sortare pret crescator \n");
	printf("7.Sortare pret descrescator \n");
	printf("8.Sortare cantitate crescator \n");
	printf("9.Sortare cantitate descrescator \n");
	printf("10.Filtrare pret\n");
	printf("11.Filtrare cantitate\n");
	printf("12.Filtrare producator\n");
	printf("13.Filtrare model\n");
	printf("14.Sortare 3 criterii\n");
	printf("15.Undo\n");
	printf("0.Iesi\n");
}

void adauga_produs_ui(Store* l)
{
	char id_s[5], pret_s[5], cantitate_s[5], model[20], producator[20], type[20];
	int id, pret, cantitate;
	printf("Id produs: ");
	scanf_s("%s", id_s, 5);
	id_s[strlen(id_s)] = '\0';
	id = atoi(id_s);
	if (id <= 0)
		printf("Nu ati introdus un id valid!\n");
	else
	{
		printf("Pret produs: ");
		scanf_s("%s", pret_s, 5);
		pret_s[strlen(pret_s)] = '\0';
		pret = atoi(pret_s);
		if (pret <= 0)
			printf("Nu ati introdus un pret valid!\n");
		else
		{
			printf("Cantitate: ");
			scanf_s("%s", cantitate_s, 5);
			cantitate_s[strlen(cantitate_s)] = '\0';
			cantitate = atoi(cantitate_s);
			if (cantitate <= 0)
				printf("Nu ati introdus o cantitate valida!\n");
			else
			{
				printf("Tip: ");
				scanf_s("%s", type, 20);
				printf("Producator: ");
				scanf_s("%s", producator, 20);
				printf("Model: ");
				scanf_s("%s", model, 20);
				int ok = addProdus(l, id, pret, cantitate, type, producator, model);
				if (ok == 1)
					printf("Produsul a fost adaugat!\n");
				else
					printf("Produs invalid!\n");
			}
		}
	}
}

void modifica_pret_ui(Store* l)
{
	char id_s[5], pret_s[5];
	int id, pret;
	printf("Id-ul produsului pe care doriti sa il modificati: ");
	scanf_s("%s", id_s, 5);
	id_s[strlen(id_s)] = '\0';
	id = atoi(id_s);
	printf("Noul pret: ");
	scanf_s("%s", pret_s, 5);
	pret_s[strlen(pret_s)] = '\0';
	pret = atoi(pret_s);
	if (pret <= 0)
		printf("Ati introdus un pret invalid!\n");
	else
	{
		int successful = modifyProdus_pret(l, id, pret);
		if(successful)
			printf("Produs modificat cu succes.\n");
		else
			printf("Nu exista produs cu datele date.\n");
	}
}

void modifica_cantitate_ui(Store* l)
{
	char id_s[5], cantitate_s[5];
	int id, cantitate;
	printf("Id-ul produsului pe care doriti sa il modificati: ");
	scanf_s("%s", id_s, 5);
	id_s[strlen(id_s)] = '\0';
	id = atoi(id_s);
	printf("id ul este: %d\n", id);
	printf("Noua cantitate: ");
	scanf_s("%s", cantitate_s, 5);
	cantitate_s[strlen(cantitate_s)] = '\0';
	cantitate = atoi(cantitate_s);
	if (cantitate <= 0)
		printf("Ati introdus o cantitate invalida!\n");
	else
	{
		int successful = modifyProdus_cantitate(l, id, cantitate);
		if (successful)
			printf("Produs modificat cu succes.\n");
		else
			printf("Nu exista produs cu datele date.\n");
	}
}

void sterge_produs_ui(Store* l)
{
	char id_s[5];
	int id;
	printf("Id-ul produsului pe care doriti sa-l stergeti este: ");
	scanf_s("%s", id_s, 5);
	id_s[strlen(id_s)] = '\0';
	id = atoi(id_s);
	int successful = deleteProdus(l, id);
	if (successful)
		printf("Produs sters cu succes.\n");
	else
		printf("Nu exista produs cu datele date.\n");
}

void filtrare_pret_ui(Store* l)
{
	char pret_s[5];
	int pret;
	printf("Pret: ");
	scanf_s("%s", pret_s, 5);
	pret_s[strlen(pret_s)] = '\0';
	pret = atoi(pret_s);
	if (pret <= 0)
		printf("Ati introdus un pret invalid!\n");
	else
	{
		List* filtred = filterByPret(l, pret);
		printAllProducts(filtred);
		destroyVector(filtred);
	}
}

void filtrare_cantitate_ui(Store* l)
{
	char cantitate_s[5];
	int cantitate;
	printf("Cantitate: ");
	scanf_s("%s", cantitate_s, 5);
	cantitate_s[strlen(cantitate_s)] = '\0';
	cantitate = atoi(cantitate_s);
	if (cantitate <= 0)
		printf("Ati introdus o cantitate invalida!\n");
	else
	{
		List* filtred = filterByCantitate(l, cantitate);
		printAllProducts(filtred);
		destroyVector(filtred);
	}
}

void filtrare_producator_ui(Store* l)
{
	char producator[20];
	printf("Producator: ");
	scanf_s("%s", producator, 20);
	producator[strlen(producator)] = '\0';
	List* filtred = filterByProducator(l, producator);
	printAllProducts(filtred);
	destroyVector(filtred);
}

void filtrare_model_ui(Store* l)
{
	char model[20];
	printf("Model: ");
	scanf_s("%s", model, 20);
	model[strlen(model)] = '\0';
	List* filtred = filterByModel(l, model);
	printAllProducts(filtred);
	destroyVector(filtred);
}

void ordonare_pret_cresc_ui(Store* l)
{
	List* sorted = sortByPretCresc(l);
	printAllProducts(sorted);
	destroyVector(sorted);
}

void ordonare_pret_descresc_ui(Store* l)
{
	List* sorted = sortByPretDescresc(l);
	printAllProducts(sorted);
	destroyVector(sorted);
}

void ordonare_cantitate_cresc_ui(Store* l)
{
	List* sorted = sortByCantitateCresc(l);
	printAllProducts(sorted);
	destroyVector(sorted);
}

void ordonare_cantitate_descresc_ui(Store* l)
{
	List* sorted = sortByCantitateDescresc(l);
	printAllProducts(sorted);
	destroyVector(sorted);
}

void sortare_3_ui(Store* l) {
	List* sorted = sortBy3(l);
	printAllProducts(sorted);
	destroyVector(sorted);
}

void uiUndo(Store* s) {
	int successful = undo(s);
	if (successful)
		printf("Undo realizat cu succes.\n");
	else
		printf("Nu se mai poate face undo.\n");
}

extern void run() {
	Store l = createStore();
	while (1) {
		printMenu();
		printf("Optiunea dumneavoastra este: ");
		int option;
		scanf_s("%d", &option);
		if (option == 0)
			break;
		else if (option == 1)
			adauga_produs_ui(&l);
		else if (option == 2)
		{
			List* lista = l.all;
			printAllProducts(lista);
		}
		else if (option == 3)
			modifica_pret_ui(&l);
		else if (option == 4)
			modifica_cantitate_ui(&l);
		else if (option == 5)
			sterge_produs_ui(&l);
		else if (option == 6)
			ordonare_pret_cresc_ui(&l);
		else if (option == 7)
			ordonare_pret_descresc_ui(&l);
		else if (option == 8)
			ordonare_cantitate_cresc_ui(&l);
		else if (option == 9)
			ordonare_cantitate_descresc_ui(&l);
		else if (option == 10)
			filtrare_pret_ui(&l);
		else if (option == 11)
			filtrare_cantitate_ui(&l);
		else if (option == 12)
			filtrare_producator_ui(&l);
		else if (option == 13)
			filtrare_model_ui(&l);
		else if (option == 14)
			sortare_3_ui(&l);
		else if (option == 15)
			uiUndo(&l);
		else
			printf("Optiunea nu este valida!\n");
	}
	destroyStore(&l);
}
