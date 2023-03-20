#include "repository.h"
#include "sort.h"
#include "validators.h"
#include "service.h"
#include <stdio.h>
#include <string.h>
#include <assert.h>

ProductStore createProductStore()
{
	ProductStore rez;
	rez.allProducts = createEmpty(destroyProdus);
	rez.undoList = createEmpty(destroyList);
	return rez;
}

ProductStore destroyStore(ProductStore* store)
{
	destroyList(store->allProducts);
	destroyList(store->undoList);
}

void add_produs(ProductStore* store, int id, int pret, int cantitate, char* type, char* producator, char* model) {
	produs* p = createProdus(id, pret, cantitate, type, producator, model);
	memorie* toUndo = copyMem(store->allProducts, createCopy);
	add(store->allProducts, p);
	add(store->undoList, toUndo);
	return 0;
}

int undo(ProductStore* store) {
	if (size(store->undoList) == 0) {
		return 1;
	}
	memorie* l = removeLast(store->undoList);
	destroyList(store->allProducts);
	store->allProducts = l;
	return 0;
}

int modify_product_price(int id, int new_price, ProductStore* store) {
	memorie* copie = copyMem(store->allProducts, createCopy);
	int ok = find_product(id, copie);
	if (ok == -1)
	{
		printf("Nu exista produs cu id ul tau");
		return -1;
	}
	else
	{
		produs* copie_p = get_p(copie, ok);
		copie_p->pret = new_price;
		ElemType set(memorie * copie, int poz, ElemType copie_p);
		memorie* toUndo = copyMem(store->allProducts, createCopy);
		add(store->undoList, toUndo);
	}
}

/*
void test_modify_product_price() {
	memorie* test_memory = generate_products();
	produs* p1;
	int id = 2;
	p1 = createCopy(test_memory->products[id]);
	int new_price = p1->pret + 10;
	int ok = modify_product_price(id, new_price, &test_memory);
	//assert(test_memory->products[id - 1]->pret == new_price);
	assert(modify_product_price(500, 100, &test_memory) == -1);
	destroy(&test_memory);
	destroyProdus(&p1);

}*/

int modify_product_quantity(int id, int new_quantity, ProductStore* store) {
	memorie* copie = copyMem(store->allProducts, createCopy);
	int ok = find_product(id, copie);
	if (ok == -1)
	{
		printf("Nu exista produs cu id ul tau");
		return -1;
	}
	else
	{
		produs* copie_p = get_p(copie, ok);
		copie_p->cantitate = new_quantity;
		ElemType set(memorie * copie, int poz, ElemType copie_p);
		memorie* toUndo = copyMem(store->allProducts, createCopy);
		add(store->undoList, toUndo);
	}
}

/*
void test_modify_product_quantity() {
	memorie* test_memory = generate_products();
	produs* p1;
	int id = 2;
	p1 = createCopy(test_memory->products[id]);
	int new_price = p1->cantitate + 10;
	modify_product_quantity(id, new_price, &test_memory);
	//assert(test_memory.products[id - 1]->cantitate == new_price);
	assert(modify_product_quantity(234, 234, &test_memory) == -1);
	destroyProdus(&p1);
	destroy(&test_memory);
}*/

void delete_product(int id, ProductStore* store) {
	memorie* copie = copyMem(store->allProducts, createCopy);
	int ok = find_product(id, copie);
	if (ok == -1)
	{
		printf("Nu exista produs cu id ul tau");
		return -1;
	}
	else
	{
		produs* copie_p = get_p(copie, ok);
		delete_product(ok, copie);
		memorie* toUndo = copyMem(store->allProducts, createCopy);
		add(store->undoList, toUndo);
	}
}
/*
void test_delete_product() {
	memorie* test_memory = generate_products();
	int position = 2;
	produs* copie = get(position, test_memory);
	int id = copie->id;
	delete_product(id, &test_memory);
	assert(test_memory->lenght == 3);
//	assert(test_memory->products[position]->id != id);
	id = 8;
	delete_product(id, &test_memory);
	assert(test_memory->lenght == 3);
	destroy(&test_memory);
}*/

int cmp_price_cresc(produs* p1, produs* p2) {
	if (p1->pret >= p2->pret)
		return 1;
	else
		return -1;
}

int cmp_price_descresc(produs* p1, produs* p2) {
	if (p1->pret <= p2->pret)
		return 1;
	return -1;
}

memorie* filter_by_price(ProductStore* store, int direction) {
	int i, j;
	memorie* l = copyMem(store->allProducts, createCopy);
	if (direction == 0) {
		sort(l, cmp_price_cresc);
	}
	else {
		sort(l, cmp_price_descresc);
	}
}
/*
void test_filter_price() {
	memorie* test_memory = generate_products();
	filter_by_price(&test_memory, 0);
	produs* p1, *p2;
	p1 = get_p(&test_memory, 0);
	p2 = get_p(&test_memory, 1);
	assert(p1->pret <= p2->pret);
	p1 = get_p(&test_memory, 1);
	p2 = get_p(&test_memory, 2);
	assert(p1->pret <= p2->pret);
	p1 = get_p(&test_memory, 2);
	p2 = get_p(&test_memory, 3);
	assert(p1->pret <= p2->pret);
	filter_by_price(&test_memory, 1);
	p1 = get_p(&test_memory, 0);
	p2 = get_p(&test_memory, 1);
	assert(p1->pret >= p2->pret);
	p1 = get_p(&test_memory, 1);
	p2 = get_p(&test_memory, 2);
	assert(p1->pret >= p2->pret);
	p1 = get_p(&test_memory, 2);
	p2 = get_p(&test_memory, 3);
	assert(p1->pret >= p2->pret);
	destroy(&test_memory);
}
*/
int cmp_quantity_cresc(produs* p1, produs* p2) {
	if (p1->cantitate >= p2->cantitate)
		return 1;
	return -1;
}

int cmp_quantity_descresc(produs* p1, produs* p2) {
	if (p1->cantitate <= p2->cantitate)
		return 1;
	return -1;
}

memorie* filter_by_quantity(ProductStore* store, int direction) {
	int i, j;
	memorie* l = copyMem(store->allProducts, createCopy);
	if (direction == 0) {
		sort(l, cmp_quantity_cresc);
	}
	else {
		sort(l, cmp_quantity_descresc);
	}
}
/*
void test_filter_quantity() {
	memorie *test_memory = generate_products();
	filter_by_quantity(&test_memory, 0);
	produs *p1, *p2;
	p1 = get_p(&test_memory, 0);
	p2 = get_p(&test_memory, 1);
	assert(p1->cantitate <= p2->cantitate);
	p1 = get_p(&test_memory, 1);
	p2 = get_p(&test_memory, 2);
	assert(p1->cantitate <= p2->cantitate);
	p1 = get_p(&test_memory, 2);
	p2 = get_p(&test_memory, 3);
	assert(p1->cantitate <= p2->cantitate);

	filter_by_quantity(&test_memory, 1);
	p1 = get_p(&test_memory, 0);
	p2 = get_p(&test_memory, 1);
	assert(p1->cantitate >= p2->cantitate);
	p1 = get_p(&test_memory, 1);
	p2 = get_p(&test_memory, 2);
	assert(p1->cantitate >= p2->cantitate);
	p1 = get_p(&test_memory, 2);
	p2 = get_p(&test_memory, 3);
	assert(p1->cantitate >= p2->cantitate);
	destroy(&test_memory);

}
*/
memorie* get_products_by_price(ProductStore* store, int price) {
	memorie* new_list = createEmpty(destroyProdus);
	for (int i = 0;i < size(store->allProducts); i++) {
		produs* p = get_p(store->allProducts, i);
		if (p->pret == price)
			add(new_list, createCopy(p));
	}
	return new_list;
}
/*
void test_get_by_price() {
	memorie* test_memory = generate_products();
	int price = 10;
	memorie* new_memory = get_products_by_price(&test_memory, price);
	assert(new_memory->lenght == 2);
//	assert(new_memory->products[0]->pret == price);
//	assert(new_memory->products[1]->pret == price);
	destroy(&test_memory);
	destroy(&new_memory);
}*/

memorie* get_products_by_quantity(ProductStore* store, int cantitate) {
	memorie* new_list = createEmpty(destroyProdus);
	for (int i = 0; i < size(store->allProducts); i++) {
		produs* p = get_p(store->allProducts, i);
		if (p->cantitate == cantitate)
			add(new_list, createCopy(p));
	}
	return new_list;
}
/*
void test_get_by_quantity() {
	memorie* test_memory = generate_products();
	int quantity = 90;
	memorie* new_memory = get_products_by_quantity(&test_memory, quantity);
	assert(new_memory->lenght == 2);
	//assert(new_memory->products[0]->cantitate == quantity);
	//assert(new_memory->products[1]->cantitate == quantity);
	destroy(&test_memory);
	destroy(&new_memory);
}*/

memorie* get_products_by_producer(ProductStore* store, char* producator) {
	memorie* new_list = createEmpty(destroyProdus);
	for (int i = 0; i < size(store->allProducts); i++) {
		produs* p = get_p(store->allProducts, i);
		if (strstr(p->producator, producator) != NULL)
			add(new_list, createCopy(p));
	}
	return new_list;
}
/*
void test_get_by_producer() {
	memorie* test_memory = generate_product();
	memorie* new_memory = get_products_by_producer(&test_memory, "samsung");
	assert(new_memory->lenght == 1);
//	assert(strcmp(new_memory->products[0]->producator, "samsung") == 0);
	destroy(&new_memory);
	destroy(&test_memory);
}

void test_add_products_service() {;
//	ProductStore* store;
	//store->allProducts = generate_product();
	//add_produs(store,5, 11, 90, "televizor", "sony", "bun");
//	assert(test_memory.products[4]->id == p1->id);
//	assert(test_memory.products[4]->pret == p1->pret);
//	assert(test_memory.products[4]->cantitate == p1->cantitate);
//	assert(strcmp(test_memory.products[4]->type, p1->type) == 0);
//	assert(strcmp(test_memory.products[4]->model, p1->model) == 0);
//	assert(strcmp(test_memory.products[4]->producator, p1->producator) == 0);
//	destroyStore(store);
}

memorie* generate_product() {
	memorie* test_mem = createEmpty();
	produs* p1, *p2, *p3, *p4;
	p1 = createProdus(1, 11, 90, "televizor", "sony", "bun");
	add_product(p1, &test_mem);
	p2 = createProdus(2, 10, 75, "monitor", "lg", "rau");
	add_product(p2, &test_mem);
	p3 = createProdus(3, 90, 99, "frigider", "asus", "nou");
	add_product(p3, &test_mem);
	p4 = createProdus(4, 25, 30, "pc", "samsung", "vechi");
	add_product(p4, &test_mem);
	return test_mem;
}
*/