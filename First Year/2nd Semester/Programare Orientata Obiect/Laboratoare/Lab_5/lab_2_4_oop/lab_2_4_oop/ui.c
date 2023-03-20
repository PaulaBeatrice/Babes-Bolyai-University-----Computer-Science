#include <stdio.h>
#include "service.h"
#include <stdlib.h>
#include "validators.h"
#include "repository.h"
#include <crtdbg.h>
#define _CRTDBG_MAP_ALLOC


/*Citeste elementele pentru un produs nou
*/
produs read_product();

/*Afiseaza toate elmentele din memorie
*/
void print_all(memorie* mem);

/*Interfata penru moificare elmente
*/
void modify(memorie* mem);


/*Functie ui
*/
void options();

void delete_by_id(memorie* mem);

void add_product_ui(memorie* mem);

void modify_price(memorie* mem);

void modify_quantity(memorie* mem);

void filter_ui(memorie* mem);

void sorted_ui(memorie* mem);


/*
void test_all() {

	test_find_product();
	test_add_product();
	test_delete_produs();
	test_add_products_service();
	test_modify_product_price();
	test_modify_product_quantity();
	test_delete_product();
	test_filter_price();
	test_filter_quantity();
	test_get_by_price();
	test_get_by_quantity();
	test_get_by_producer();
	test_validators();
}
*/
int main() {
	ProductStore store = createProductStore();
	//test_all();
	int run = 1;
	while (run) {
		options();
		int option = 0;
		printf("Introduceti optiunea dumneavoastra: ");
		scanf_s("%d", &option);
		switch (option)
		{
		case 1:
			add_product_ui(&store);
			break;
		case 2:
			modify(&store);
			break;
		case 3:
			delete_by_id(&store);
			break;
		case 4:
			filter_ui(&store);
			break;
		case 5:
			sorted_ui(&store);
			break;
		case 6:
			if (undo(&store) != 0) {
				printf("No more undo!\n");
			}
			break;
		case 7: 
			run = 0;
			break;
		default:
			break;
		}
	}
	destroyStore(&store);
	_CrtDumpMemoryLeaks();
	return 0;
}


void options() {
	printf("1.Adauga produs nou\n");
	printf("2.Modifica produs \n");
	printf("3.Sterge produs \n");
	printf("4.Sortare \n");
	printf("5.Filtrare \n");
	printf("6.Undo\n");
	printf("7.Iesi\n");
}

void sorted_ui(ProductStore* mem) {
	int option;

	printf("Alegeti dupa ce sa filtrati\n");
	printf("1.Pret\n");
	printf("2.Cantitate\n");
	printf("3.Producator\n");
	scanf_s("%d", &option);
	if (option == 1)
	{
		int pret;
		printf("Introduceti pretul dorit:");
		scanf_s("%d", &pret);
		memorie* new_list = get_products_by_price(mem, pret);
		print_all(new_list);
		destroyList(new_list);
	}
	else if (option == 2)
	{
		int cantitate;
		printf("Introduceti cantitatea dorita:");
		scanf_s("%d", &cantitate);
		memorie* new_list1 = get_products_by_quantity(mem, cantitate);
		print_all(new_list1);
		destroyList(new_list1);
	}
	else if (option == 3)
	{
		char producator[30];
		printf("Introduceti producatorul dorit:");
		scanf_s("%s", &producator, (unsigned)_countof(producator));
		memorie* new_list2 = get_products_by_producer(mem, producator);
		print_all(new_list2);
		destroyList(new_list2);
	}
}

void filter_ui(ProductStore* store) {
	int direction, option;
	printf("Alegeti dupa ce doriti sa sortati:\n 1.Pret\n 2.Cantitate  ");
	scanf_s("%d", &option);
	if (option == 1) {
		printf("Alegeti sensul:\n 0.Crescator\n 1.Descrescator  ");
		scanf_s("%d", &direction);
		memorie* filtred = filter_by_price(store, direction);
		print_all(filtred);
		destroyList(filtred);
	}
	else {
		printf("Alegeti sensul:\n 0.Crescator\n 1.Descrescator  ");
		scanf_s("%d", &direction);
		memorie* filtred = filter_by_quantity(store, direction);
		print_all(filtred);
		destroyList(filtred);
	}
}

void delete_by_id(ProductStore* mem) {
	int id;
	printf("Introduceti id-ul cautat: ");
	scanf_s("%d", &id);
	delete_product(id, mem);
}

void modify(ProductStore* mem) {
	int option;
	printf("Alegeti ce doriti sa modificati:\n 1.Pretul\n 2.Cantitatea:  ");
	scanf_s("%d", &option);
	if (option == 1) {
		modify_price(mem);
	}
	else {
		modify_quantity(mem);
	}
}

void modify_quantity(ProductStore* mem) {
	int id, cantitate;
	printf("Introduceti id-ul dorit: ");
	scanf_s("%d", &id);
	printf("Introduceti noua cantitate: ");
	scanf_s("%d", &cantitate);
	modify_product_quantity(id, cantitate, mem);
	print_all(mem);
}

void modify_price(ProductStore* mem) {
	int id, pret;
	printf("Introduceti id-ul dorit: ");
	scanf_s("%d", &id);
	printf("Introduceti noul pret: ");
	scanf_s("%d", &pret);
	modify_product_price(id, pret, mem);
	print_all(mem);
}

void add_product_ui(ProductStore* store) {
	int id, pret, cantitate;
	char type[30], producator[30], model[30];

	printf("Introduceti id: ");
	scanf_s("%d", &id);
	printf("Introduceti pret: ");
	scanf_s("%d", &pret);
	printf("Introduceti cantitate: ");
	scanf_s("%d", &cantitate);
	printf("Introduceti tip: ");
	scanf_s("%s", &type, (unsigned)_countof(type));
	printf("Introduceti producator: ");
	scanf_s("%s", &producator, (unsigned)_countof(producator));
	printf("Introduceti model: ");
	scanf_s("%s", &model, (unsigned)_countof(model));
	produs* p = createProdus(id, pret, cantitate, type, producator, model);
	if (validation(p) == 0) {
		add_produs(store, id, pret, cantitate, type, producator, model);
	}
	else
		printf("Exista deja id-ul introdus\n");
	destroyProdus(&p);
}

void print_all(memorie* mem) {
	if (mem->lenght >= 1) {
		for (int i = 0;i < size(mem); i++) {
			produs* p = get_p(mem, i);
			printf("Id: %d Pret: %d  Cantitate: %d  Tip: %s Producator: %s Model: %s\n", p->id, p->pret, p->cantitate, p->type, p->producator, p->model);
		}
	}
	else {
		printf("Nu exista produse\n");
	}
}