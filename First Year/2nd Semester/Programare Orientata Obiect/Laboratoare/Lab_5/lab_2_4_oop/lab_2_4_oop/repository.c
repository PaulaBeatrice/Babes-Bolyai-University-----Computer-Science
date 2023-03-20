#include "repository.h"
#include "objects.h"
#include "service.h"
#include <stdio.h>
#include <assert.h>

memorie* createEmpty(DestroyFct f) {
	memorie* list = malloc(sizeof(memorie));
	list->capacitate = 2;
	list->lenght = 0;
	list->products = malloc(list->capacitate * sizeof(ElemType));
	return list;
}

void destroyList(memorie* mem) {
	for (int i = 0;i < mem->lenght; i++) {
		mem->dfnc(mem->products[i]);
	}
	free(mem->products);
	free(mem);
}

ElemType get_p(memorie* mem, int poz) {
	return mem->products[poz];
}

ElemType set(memorie* l, int poz, ElemType p) {
	ElemType rez = l->products[poz];
	l->products[poz] = p;
	return rez;
}

int size(memorie* l) {
	return l->lenght;
}

void ensureCapacity(memorie* l) {
	if (l->lenght < l->capacitate) {
		return; 
	}
	ElemType* nElems = malloc(sizeof(ElemType) * (l->capacitate + 3));
	for (int i = 0; i < l->lenght; i++) {
		nElems[i] = l->products[i];
	}
	free(l->products);
	l->products = nElems;
	l->capacitate += 3;
}

void add(memorie* mem, ElemType product) {
	ensureCapacity(mem);
	mem->products[mem->lenght] = product;
	mem->lenght++;
}

ElemType removeLast(memorie* l) {
	ElemType rez = l->products[l->lenght - 1];
	l->lenght -= 1;
	return rez;
}

memorie* copyMem(memorie* l, CopyFct cf) {
	memorie* rez = createEmpty(l->dfnc);
	for (int i = 0; i < size(l); i++) {
		ElemType p = get_p(l, i);
		add(rez, cf(p));
	}
	return rez;
}

int find_product(int id, memorie* mem) {
	produs* new_product;
	for (int i = 0;i < mem->lenght; i++) {
		new_product = get_p(mem, i);
		if (new_product->id == id) {
			return i;
		}
	}
	return -1;
}

void delete_produs(int position, memorie* mem) {
	produs* p = mem->products[position];
	for (int i = position;i < mem->lenght; i++) {
		mem->products[i] = mem->products[i + 1];
	}
	destroyProdus(&p);
	mem->lenght -= 1;
}

/*
void test_delete_produs() {
	memorie* test_memory = generate_products();
	int position = 2;
//	int id = test_memory->products[position]->id;
	delete_produs(position, &test_memory);
	assert(test_memory->lenght == 3);
//	assert(test_memory->products[position]->id != id);
	destroy(&test_memory);
}

void test_find_product() {
	memorie* test_memory = generate_products();
	int id = 1;
	int found = find_product(id, &test_memory);
	assert(found == 0);
	id = 6;
	found = find_product(id, &test_memory);
	assert(found == -1);
	destroy(&test_memory);
}


void test_add_product() {
	memorie* test_memory;
	produs* p1;
	p1 = createProdus(5, 11, 90, "televizor", "sony", "bun");

	test_memory = generate_products();
	add_produs(p1, &test_memory);
	assert(test_memory->lenght == 1);
	//assert(test_memory->products[4]->id == p1->id);
	//assert(test_memory->products[4]->pret == p1->pret);
	//assert(test_memory->products[4]->cantitate == p1->cantitate);
	//assert(strcmp(test_memory->products[4]->type, p1->type) == 0);
	//assert(strcmp(test_memory->products[4]->model, p1->model) == 0);
	//assert(strcmp(test_memory->products[4]->producator, p1->producator) == 0);
	destroy(&test_memory);
}
*/