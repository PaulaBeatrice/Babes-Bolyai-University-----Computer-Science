#pragma once

#include "domain.h"
#include "list.h"
#include "sort.h"

typedef struct {
	List* all;
	List* undo_list;
}Store;

Store createStore();

void destroyStore(Store* store);

int addProdus(Store* store, int id, int pret, int cantitate, char* type, char* producator, char* model);

produs* get_elem(Store* l, int poz);

int deleteProdus(Store* store, int id);

int findProdus(Store* store, int id);

TElement find(Store* store, int id);

int modifyProdus_pret(Store* store, int id, int pret);

int modifyProdus_cantitate(Store* store, int id, int cantitate);

List* filterByProducator(Store* store, char* producator);

List* filterByPret(Store* store, int pret);

List* filterByCantitate(Store* store, int cantitate);

List* filterByModel(Store* store, char* model);

int functie_comparare_pret_cresc(produs* p1, produs* p2);

int functie_comparare_pret_descresc(produs* p1, produs* p2);

int functie_comparare_cantitate_cresc(produs* p1, produs* p2);

int functie_comparare_cantitate_descresc(produs* p1, produs* p2);

List* sortByPretCresc(Store* store);

List* sortByPretDescresc(Store* store);

List* sortByCantitateCresc(Store* store);

List* sortByCantitateDescresc(Store* store);

List* sortBy3(Store* store);

int undo(Store* store);