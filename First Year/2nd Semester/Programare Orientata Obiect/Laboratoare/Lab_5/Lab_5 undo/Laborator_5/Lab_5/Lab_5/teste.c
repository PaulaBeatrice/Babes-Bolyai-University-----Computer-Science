#include "teste.h"
#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>

void test_domain()
{
	//destroy
	produs* p;
	p = createProdus(1, 10, 10, "laptop", "asus", "ok");
	assert(strcmp(p->type, "laptop") == 0);
	assert(strcmp(p->producator, "asus") == 0);
	assert(strcmp(p->model, "ok") == 0);
	assert(p->id == 1);
	assert(p->cantitate == 10);
	assert(p->pret == 10);
	destroyProdus(p);
	//validare
	produs* m1 = createProdus(-5, 10, 23, "asa", "asa", "asa");
	assert(validate(m1) == 0);
	produs* m2 = createProdus(10, -5, 10, "laptop", "asus", "zephyrius");
	assert(validate(m2) == 0);
	produs* m3 = createProdus(1234, 1, 10, "", "", "");
	assert(validate(m3) == 0);
	produs* m4 = createProdus(1234, 1, 20, "vdvd", "", "zephyrius");
	assert(validate(m4) == 0);
	produs* m5 = createProdus(1234, 1, 100, "fdfh", "asus", "");
	assert(validate(m5) == 0);
	produs* m6 = createProdus(1234, 1, -20, " cxbf", "asus", "zephyrius");
	assert(validate(m6) == 0);
	produs* m7 = createProdus(4, 1, 20, "", "hf", "zephyrius");
	assert(validate(m7) == 0);
	destroyProdus(m1);
	destroyProdus(m2);
	destroyProdus(m3);
	destroyProdus(m4);
	destroyProdus(m5);
	destroyProdus(m6);
	destroyProdus(m7);
}

void test_empty_list() {
	List* v = createEmpty(destroyProdus);
	assert(size(v) == 0);
	destroyVector(v);
}

void test_iterate()
{
	List* v = createEmpty(destroyProdus);
	produs* m1 = createProdus(1, 1, 10, "a", "d", "g");
	produs* m2 = createProdus(2, 1, 10, "s", "d", "g");
	produs* m3 = createProdus(3, 1, 10, "a", "b", "g");
	add(v, m1);
	add(v, m2);
	add(v, m3);
	assert(size(v) == 3);
	produs* m = get(v, 0);
	assert(strcmp(m->type, "a") == 0);
	assert(strcmp(m->producator, "d") == 0);
	assert(strcmp(m->model, "g") == 0);
	assert(m->id == 1);
	assert(m->cantitate == 10);
	destroyVector(v);
}

void testCopy() {
	List* v1 = createEmpty(destroyProdus);
	add(v1, createProdus(1, 1, 50, "a", "d", "g"));
	add(v1, createProdus(2, 1, 41, "b", "b", "b"));
	assert(size(v1) == 2);
	List* v2 = copyList(v1, copyProdus);
	assert(size(v2) == 2);
	produs* p = get(v2, 0);
	assert(strcmp(p->type, "a") == 0);
	destroyVector(v1);
	destroyVector(v2);
}

void testDelete() {
	List* v1 = createEmpty(destroyProdus);
	add(v1, createProdus(1, 1, 20, "a", "d", "g"));
	add(v1, createProdus(2, 1, 20, "a", "d", "g"));
	add(v1, createProdus(3, 1, 20, "a", "d", "g"));
	add(v1, createProdus(4, 1, 20, "a", "d", "g"));
	add(v1, createProdus(5, 1, 20, "a", "d", "g"));
	add(v1, createProdus(6, 1, 20, "a", "d", "g"));
	assert(size(v1) == 6);
	produs* m = deleteElementIndex(v1, 0);
	assert(m->id == 1);
	assert(strcmp(m->type, "a") == 0);
	assert(m->cantitate == 20);
	destroyProdus(m);
	assert(size(v1) == 5);
	destroyVector(v1);
}

void testSet() {
	List* v1 = createEmpty(destroyProdus);
	add(v1, createProdus(1, 1, 10, "a", "d", "g"));
	assert(size(v1) == 1);
	produs* replaced = set(v1, 0, createProdus(2, 12, 40, "b", "e", "g"));
	produs* m = get(v1, 0);
	assert(m->id == 2);
	assert(strcmp(m->type, "b") == 0);
	assert(m->cantitate == 40);
	destroyProdus(replaced);
	destroyVector(v1);
}

void testListVoid() {
	List* list = createEmpty(destroyProdus);
	add(list, createProdus(1, 1, 20, "a", "d", "g"));
	add(list, createProdus(2, 1, 20, "a", "d", "g"));
	List* list2 = createEmpty(destroyProdus);
	add(list2, createProdus(1, 1, 20, "a", "d", "g"));
	add(list2, createProdus(2, 1, 20, "a", "d", "g"));
	add(list2, createProdus(3, 1, 20, "a", "d", "g"));
	List* undoList = createEmpty(destroyVector);
	add(undoList, list);
	assert(size(undoList) == 1);
	add(undoList, list2);
	assert(size(undoList) == 2);
	destroyVector(undoList);
}

void test_adauga_produs()
{
	Store l1 = createStore();
	int ok = addProdus(&l1, 1, 100, 50, "televizor", "Sony", "bun");
	assert(get_elem(&l1, 0)->id == 1);
	assert(get_elem(&l1, 0)->cantitate == 50);
	assert(strcmp(get_elem(&l1, 0)->model, "bun") == 0);
	assert(ok == 1);
	ok = addProdus(&l1, 1, 100, 50, "televizor", "Sony", "bun");
	assert(ok == 0);
	destroyStore(&l1);
}

void test_find_service() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 23, 123, "laptop", "zephyrius", "asus");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 1, 23, 10, "f", "f", "asus");
	assert(successful2 == 0);
	int successful3 = addProdus(&store, -1234, 23, -1, "", "", "");
	assert(successful3 == 0);
	assert(size(store.all) == 1);
	int poz = findProdus(&store, 1);
	assert(poz == 0);
	poz = findProdus(&store, 2);
	assert(poz == -1);
	produs* e = find(&store, 1);
	produs* e1 = find(&store, 2);
	assert(e1 == NULL);
	assert(e->id == 1);
	assert(e->cantitate == 123);
	destroyStore(&store);
}

void test_modificare() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 23, 10, "laptop", "zephyrius", "asus");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 23, 1, "asa", "asa", "asus");
	assert(successful2 == 1);
	assert(size(store.all) == 2);
	int modify_success = modifyProdus_pret(&store, 1, 120);
	assert(modify_success == 1);
	int modify_success1 = modifyProdus_cantitate(&store, 1, 120);
	assert(modify_success1 == 1);
	int modify_success3 = modifyProdus_cantitate(&store, 3, 120);
	assert(modify_success3 == 0);
	int modify_success2 = modifyProdus_pret(&store, 4, 120);
	assert(modify_success2 == 0);
	destroyStore(&store);
}

void testDeleteService() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 23, 10, "laptop", "zephyrius", "asus");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 23, 1, "asa", "asa", "asus");
	assert(successful2 == 1);
	assert(size(store.all) == 2);
	int succesful_del = deleteProdus(&store, 1);
	assert(succesful_del == 1);
	int succesfully_del = deleteProdus(&store, 3);
	assert(succesfully_del == 0);
	destroyStore(&store);
}

void testFilterService() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 23, 10, "laptop", "zephyrius", "bun");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 100, 1, "asa", "asus", "rau");
	assert(successful2 == 1);
	assert(size(store.all) == 2);
	List* filteredList = filterByPret(&store, 23);
	assert(size(filteredList) == 1);
	destroyVector(filteredList);
	filteredList = filterByPret(&store, -1);
	assert(size(filteredList) == 2);
	destroyVector(filteredList);
	filteredList = filterByProducator(&store, "");
	assert(size(filteredList) == 2);
	destroyVector(filteredList);
	filteredList = filterByProducator(&store, "asus");
	assert(size(filteredList) == 1);
	destroyVector(filteredList);
	filteredList = filterByProducator(&store, "asa");
	assert(size(filteredList) == 0);
	destroyVector(filteredList);
	filteredList = filterByCantitate(&store, 10);
	assert(size(filteredList) == 1);
	destroyVector(filteredList);
	filteredList = filterByCantitate(&store, 12);
	assert(size(filteredList) == 0);
	destroyVector(filteredList);
	filteredList = filterByCantitate(&store, -12);
	assert(size(filteredList) == 2);
	destroyVector(filteredList);
	
	filteredList = filterByModel(&store, "");
	assert(size(filteredList) == 2);
	destroyVector(filteredList);
	filteredList = filterByModel(&store, "bun");
	assert(size(filteredList) == 1);
	destroyVector(filteredList);
	filteredList = filterByModel(&store, "bu");
	assert(size(filteredList) == 0);
	destroyVector(filteredList);
	destroyStore(&store);
}

void testSort1() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 12, 11, "aqa", "aaa", "vbfbg");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 29, 2, "aaa", "aaa", "vbfbg");
	assert(successful2 == 1);
	int successful3 = addProdus(&store, 3, 28, 34, "bbb", "bbb", "vbfbg");
	assert(successful3 == 1);
	int successful4 = addProdus(&store, 4, 45, 46, "aaa", "ccc", "bgv");
	assert(successful4 == 1);
	int successful5 = addProdus(&store, 5, 1, 5, "aaa", "aaa", "hfgh");
	assert(successful5 == 1);
	assert(size(store.all) == 5);
	List* sorted = sortByPretCresc(&store);
	produs* p1 = get(sorted, 0);
	produs* p2 = get(sorted, 1);
	produs* p3 = get(sorted, 2);
	produs* p4 = get(sorted, 3);
	produs* p5 = get(sorted, 4);
	assert(p1->id == 5);
	assert(p2->id == 1);
	assert(p3->id == 3);
	assert(p4->id == 2);
	assert(p5->id == 4);
	destroyVector(sorted);
	destroyStore(&store);
}

void testSort2() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 12, 11, "aqa", "aaa", "vbfbg");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 29, 2, "aaa", "aaa", "vbfbg");
	assert(successful2 == 1);
	int successful3 = addProdus(&store, 3, 28, 34, "bbb", "bbb", "vbfbg");
	assert(successful3 == 1);
	int successful4 = addProdus(&store, 4, 45, 46, "aaa", "ccc", "bgv");
	assert(successful4 == 1);
	int successful5 = addProdus(&store, 5, 1, 5, "aaa", "aaa", "hfgh");
	assert(successful5 == 1);
	assert(size(store.all) == 5);
	List* sorted = sortByPretDescresc(&store);
	produs* p1 = get(sorted, 0);
	produs* p2 = get(sorted, 1);
	produs* p3 = get(sorted, 2);
	produs* p4 = get(sorted, 3);
	produs* p5 = get(sorted, 4);
	assert(p1->id == 4);
	assert(p2->id == 2);
	assert(p3->id == 3);
	assert(p4->id == 1);
	assert(p5->id == 5);
	destroyVector(sorted);
	destroyStore(&store);
}

void testSort3() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 12, 11, "aqa", "aaa", "vbfbg");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 29, 2, "aaa", "aaa", "vbfbg");
	assert(successful2 == 1);
	int successful3 = addProdus(&store, 3, 28, 34, "bbb", "bbb", "vbfbg");
	assert(successful3 == 1);
	int successful4 = addProdus(&store, 4, 45, 46, "aaa", "ccc", "bgv");
	assert(successful4 == 1);
	int successful5 = addProdus(&store, 5, 1, 5, "aaa", "aaa", "hfgh");
	assert(successful5 == 1);
	assert(size(store.all) == 5);
	List* sorted = sortByCantitateCresc(&store);
	produs* p1 = get(sorted, 0);
	produs* p2 = get(sorted, 1);
	produs* p3 = get(sorted, 2);
	produs* p4 = get(sorted, 3);
	produs* p5 = get(sorted, 4);
	assert(p1->id == 2);
	assert(p2->id == 5);
	assert(p3->id == 1);
	assert(p4->id == 3);
	assert(p5->id == 4);
	destroyVector(sorted);
	destroyStore(&store);
}

void testSort4() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 12, 11, "aqa", "aaa", "vbfbg");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 29, 2, "aaa", "aaa", "vbfbg");
	assert(successful2 == 1);
	int successful3 = addProdus(&store, 3, 28, 34, "bbb", "bbb", "vbfbg");
	assert(successful3 == 1);
	int successful4 = addProdus(&store, 4, 45, 46, "aaa", "ccc", "bgv");
	assert(successful4 == 1);
	int successful5 = addProdus(&store, 5, 1, 5, "aaa", "aaa", "hfgh");
	assert(successful5 == 1);
	assert(size(store.all) == 5);
	List* sorted = sortByCantitateDescresc(&store);
	produs* p1 = get(sorted, 0);
	produs* p2 = get(sorted, 1);
	produs* p3 = get(sorted, 2);
	produs* p4 = get(sorted, 3);
	produs* p5 = get(sorted, 4);
	assert(p1->id == 4);
	assert(p2->id == 3);
	assert(p3->id == 1);
	assert(p4->id == 5);
	assert(p5->id == 2);
	destroyVector(sorted);
	destroyStore(&store);
}

void testSort5() {
	Store store = createStore();
	int successful1 = addProdus(&store, 1, 12, 11, "aqa", "aaa", "vbfbg");
	assert(successful1 == 1);
	int successful2 = addProdus(&store, 2, 12, 2, "aaa", "aaa", "vbfbg");
	assert(successful2 == 1);
	int successful3 = addProdus(&store, 3, 28, 34, "bbb", "bbb", "vbfbg");
	assert(successful3 == 1);
	int successful4 = addProdus(&store, 4, 12, 2, "aaa", "ccc", "bgv");
	assert(successful4 == 1);
	int successful5 = addProdus(&store, 5, 1, 5, "aaa", "aaa", "hfgh");
	assert(successful5 == 1);
	assert(size(store.all) == 5);
	List* sorted = sortBy3(&store);
	produs* p1 = get(sorted, 0);
	produs* p2 = get(sorted, 1);
	produs* p3 = get(sorted, 2);
	produs* p4 = get(sorted, 3);
	produs* p5 = get(sorted, 4);
	assert(p1->id == 5);
	assert(p2->id == 4);
	assert(p3->id == 2);
	assert(p4->id == 1);
	assert(p5->id == 3);
	destroyVector(sorted);
	destroyStore(&store);
}

void testUndo() {
	Store store = createStore();
	assert(addProdus(&store, 1, 10, 15, "A", "B", "C") == 1);
	assert(modifyProdus_cantitate(&store, 1, 5) == 1);
	assert(deleteProdus(&store, 1) == 1);
	undo(&store);
	List* crtList = filterByModel(&store, "");
	assert(size(crtList) == 1);
	produs* p = get(crtList, 0);
	assert(p->id == 1);
	destroyVector(crtList);
	undo(&store);
	crtList = filterByModel(&store, "");
	assert(size(crtList) == 1);
	produs* p1 = get(crtList, 0);
	assert(p1->id == 1);
	destroyVector(crtList);
	undo(&store);
	crtList = filterByModel(&store, "");
	assert(size(crtList) == 0);
	destroyVector(crtList);

	int moreUndo = undo(&store);
	assert(moreUndo == 0);
	destroyStore(&store);
}

void testAll()
{
	test_domain();
	test_empty_list();
	test_iterate();
	testCopy();
	testDelete();
	testSet();
	testListVoid();
	test_adauga_produs();
	test_find_service();
	test_modificare();
	testDeleteService();
	testFilterService();
	testSort1();
	testSort2();
	testSort3();
	testSort4();
	testSort5();
	testUndo();
}




