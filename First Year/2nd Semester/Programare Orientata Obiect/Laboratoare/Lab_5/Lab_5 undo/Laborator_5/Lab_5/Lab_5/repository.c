#include "repository.h"
#include <string.h>

Store createStore(){
	Store store;
	store.all = createEmpty(destroyProdus);
	store.undo_list = createEmpty(destroyVector);
	return store;
}

void destroyStore(Store* store){
	destroyVector(store->all);
	destroyVector(store->undo_list);
}

int addProdus(Store* store, int id, int pret, int cantitate, char* type, char* producator, char* model){
	produs* p = createProdus(id, pret, cantitate, type, producator, model);
	int s = validate(p);
	if (s == 0) {
		destroyProdus(p);
		return 0;
	}
	if (store->all->len > 0)
	{
		int poz = findProdus(store, id);
		if (poz != -1)
		{
			destroyProdus(p);
			return 0;
		}
	}
	List* Undo = copyList(store->all, copyProdus);
	add(store->all, p);
	add(store->undo_list, Undo);
	return 1;
}


produs* get_elem(Store* l, int poz){
	return (l->all)->elems[poz];
}

int deleteProdus(Store* store, int id)
{
	int poz = findProdus(store, id);
	if (poz != -1) {
		List* Undo = copyList(store->all, copyProdus);
		add(store->undo_list, Undo);
		produs* p = deleteElementIndex(store->all, poz);
		destroyProdus(p);
		return 1;
	}
	return 0;
}

int findProdus(Store* store, int id)
{
	for (int i = 0; i < store->all->len; i++)
	{
		produs* p = get(store->all, i);
		if (p->id == id)
		{
			return i;
		}
	}
	return -1;
}

TElement find(Store* store, int id)
{
	for (int i = 0; i < store->all->len; i++)
	{
		produs* p = get(store->all, i);
		if (p->id == id)
			return p;
	}
	return NULL;
}

int modifyProdus_pret(Store* store, int id, int pret)
{
	int poz = findProdus(store, id);
	if (poz != -1) {
		List* undo = copyList(store->all, copyProdus);
		add(store->undo_list, undo);
		produs* p_vechi = find(store, id);
		produs* nou = createProdus(p_vechi->id, pret, p_vechi->cantitate, p_vechi->type, p_vechi->producator, p_vechi->model);
		produs* re = set(store->all, poz, nou);
		destroyProdus(re);
		return 1;
	}
	return 0;
}

int modifyProdus_cantitate(Store* store, int id, int cantitate)
{
	int poz = findProdus(store, id);
	if (poz != -1) {
		List* undo = copyList(store->all, copyProdus);
		add(store->undo_list, undo);
		produs* p_vechi = find(store, id);
		produs* nou = createProdus(p_vechi->id, p_vechi->pret, cantitate, p_vechi->type, p_vechi->producator, p_vechi->model);
		produs* re = set(store->all, poz, nou);
		destroyProdus(re);
		return 1;
	}
	return 0;
}

List* filterByProducator(Store* store, char* producator)
{
	if (strcmp(producator, "") != 0) {
		List* filteredList = createEmpty(destroyProdus);
		for (int i = 0; i < store->all->len; i++) {
			produs* m = get(store->all, i);
			if (strcmp(producator, m->producator) == 0)
				add(filteredList, createProdus(m->id, m->pret, m->cantitate, m->type, m->producator, m->model));
		}
		return filteredList;
	}
	return copyList(store->all, copyProdus);
}

List* filterByPret(Store* store, int pret)
{
	if (pret > 0) {
		List* filteredList = createEmpty(destroyProdus);
		for (int i = 0; i < store->all->len; i++) {
			produs* m = get(store->all, i);
			if (m->pret == pret)
				add(filteredList, createProdus(m->id, m->pret, m->cantitate, m->type, m->producator, m->model));
		}
		return filteredList;
	}
	return copyList(store->all, copyProdus);
}

List* filterByCantitate(Store* store, int cantitate)
{
	if (cantitate > 0) {
		List* filteredList = createEmpty(destroyProdus);
		for (int i = 0; i < store->all->len; i++) {
			produs* m = get(store->all, i);
			if (m->cantitate == cantitate)
				add(filteredList, createProdus(m->id,  m->pret, m->cantitate, m->type, m->producator, m->model));
		}
		return filteredList;
	}
	return copyList(store->all, copyProdus);
}

List* filterByModel(Store* store, char* model)
{
	if (strcmp(model, "") != 0) {
		List* filteredList = createEmpty(destroyProdus);
		for (int i = 0; i < store->all->len; i++) {
			produs* m = get(store->all, i);
			if (strcmp(model, m->model) == 0)
				add(filteredList, createProdus(m->id, m->pret, m->cantitate, m->type, m->producator, m->model));
		}
		return filteredList;
	}
	return copyList(store->all, copyProdus);
}

int functie_comparare_pret_cresc(produs* p1, produs* p2)
{
	return (p1->pret > p2->pret);
}

int functie_comparare_pret_descresc(produs* p1, produs* p2)
{
	return (p1->pret < p2->pret);
}

int functie_comparare_cantitate_cresc(produs* p1, produs* p2)
{
	return (p1->cantitate > p2->cantitate);
}

int functie_comparare_cantitate_descresc(produs* p1, produs* p2)
{
	return (p1->cantitate < p2->cantitate);
}

List* sortByPretCresc(Store* store)
{
	List* list = copyList(store->all, copyProdus);
	sort(list, functie_comparare_pret_cresc);
	return list;
}

List* sortByPretDescresc(Store* store)
{
	List* list = copyList(store->all, copyProdus);
	sort(list, functie_comparare_pret_descresc);
	return list;
}

List* sortByCantitateCresc(Store* store)
{
	List* list = copyList(store->all, copyProdus);
	sort(list, functie_comparare_cantitate_cresc);
	return list;
}

List* sortByCantitateDescresc(Store* store)
{
	List* list = copyList(store->all, copyProdus);
	sort(list, functie_comparare_cantitate_descresc);
	return list;
}

int undo(Store* store)
{
	if (size(store->undo_list) == 0)
		//nothing to undo
		return 0;
	List* v = deleteElementIndex(store->undo_list, store->undo_list->len - 1);
	destroyVector(store->all);
	store->all = v;
	return 1;
}

List* sortBy3(Store* store)
{
	List* list = copyList(store->all, copyProdus);
	for(int i = 0 ; i < list->len; i++)
		for (int j = i + 1; j < list->len; j++)
		{
			if (get_elem(store, i)->pret > get_elem(store, j)->pret)
			{
				void* p1 = get(list, i);
				void* p2 = get(list, j);
				set(list, i, p2);
				set(list, j, p1);
			}
			else if (get_elem(store, i)->pret == get_elem(store, j)->pret)
			{
				if (get_elem(store, i)->cantitate > get_elem(store, j)->cantitate)
				{
					void* p1 = get(list, i);
					void* p2 = get(list, j);
					set(list, i, p2);
					set(list, j, p1);
				}
				else if (get_elem(store, i)->cantitate == get_elem(store, j)->cantitate)
					{
						if (strcmp(get_elem(store, i)->model, get_elem(store, j)->model) > 0)
						{
							void* p1 = get(list, i);
							void* p2 = get(list, j);
							set(list, i, p2);
							set(list, j, p1);
						}
					}
			}
		}
	return list;
}
