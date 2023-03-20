#include "Teste.h"
#include "oferta.h"
#include "Repo.h"
#include "Errors.h"
#include "Validator.h"
#include "Service.h"
#include "vectordinamic.h"
#include <iostream>
#include <assert.h>
#include <string>

void test_domain()
{
	Oferta o{ "A", "B", "C", 100 };
	assert(o.getDenumire() == "A");
	assert(o.getDestinatie() == "B");
	assert(o.getTip() == "C");
	assert(o.getPret() == 100);

	o.setDenumire("X");
	o.setDestinatie("Y");
	o.setTip("Z");
	o.setPret(5);

	assert(o.getDenumire() == "X");
	assert(o.getDestinatie() == "Y");
	assert(o.getTip() == "Z");
	assert(o.getPret() == 5);
}

void test_operator() {
	Oferta o1{ "A", "s", "d", 10 };
	Oferta o2{ "A", "d", "c", 10 };
	assert(o1 == o2);
}

void test_add_repo() {
	OfertaRepo repo;
	Oferta o1{ "A", "d", "c", 10 };
	repo.addOferta(o1);
	auto list = repo.getAll();
	assert(list.size() == 1);
	try {
		repo.addOferta(o1);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta deja exista!\n");
	}
}

void test_delete_repo() {
	Oferta o1{ "A", "d", "c", 10 };
	Oferta o2{ "F", "dAS", "c", 410 };
	Oferta o3{ "PO", "SVS", "cVVV", 63 };
	OfertaRepo repo;
	repo.addOferta(o1);
	repo.addOferta(o2);
	repo.addOferta(o3);
	try {
		repo.deleteOferta("Fvsd");assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta nu exista!\n");
	}
	repo.deleteOferta("A");
	auto list = repo.getAll();
	assert(list.size() == 2);
	assert(list.get(0).getDenumire() == "F");
	assert(list.get(1).getDenumire() == "PO");
}

void test_modify_repo() {
	OfertaRepo repo;
	Oferta o1{ "A", "d", "c", 10 };
	Oferta o2{ "A", "dAS", "c", 410 };
	Oferta o3{ "PO", "SVS", "cVVV", 63 };
	repo.addOferta(o1);
	repo.modifyOferta(o2);
	auto list = repo.getAll();
	assert(list.size() == 1);
	assert(list.get(0).getPret() == 410);
	assert(list.get(0).getDestinatie() == "dAS");
	try {
		repo.modifyOferta(o3);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta nu exista!\n");
	}
}

void test_find_repo() {
	Oferta o1{ "A", "d", "c", 10 };
	Oferta o2{ "F", "dAS", "c", 410 };
	Oferta o3{ "PO", "SVS", "cVVV", 63 };
	OfertaRepo repo;
	repo.addOferta(o1);
	repo.addOferta(o2);
	repo.addOferta(o3);
	assert(repo.findOferta("F") == o2);
	try {
		repo.findOferta("szs");assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta nu exista!\n");
	}

}

void test_validator() {
	Validator validator;
	validator.validateOferta("a", "b", "c", 10);
	try {
		validator.validateOferta("", "", "", -10);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Denumirea este invalida!\nDestinatia este invalida!\nTipul este invalid!\nPretul este invalid!\n");
	}
}

void test_add_service() {
	OfertaRepo repo;
	Validator valid;
	OfertaService service{ repo, valid };
	assert(repo.length() == 0);
	service.addOfera("a", "b", "c", 10);
	service.addOfera("f", "b", "c", 10);
	try {
		service.addOfera("a", "svb", "c", 10);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta deja exista!\n");
	}
	try {
		service.addOfera("", "", "", 10);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Denumirea este invalida!\nDestinatia este invalida!\nTipul este invalid!\n");
	}
}

void test_modify_service() {
	OfertaRepo repo;
	Validator validator;
	OfertaService service{ repo, validator };
	service.addOfera("f", "b", "c", 10);
	service.addOfera("gd", "bsv", "fvc", 30);
	service.modifyOferta("f", "abc", "ABC", 65);
	assert(service.getAll().get(0).getPret() == 65);
	try {
		service.modifyOferta("1", "2", "ssc4", -10);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Denumirea este invalida!\nDestinatia este invalida!\nTipul este invalid!\nPretul este invalid!\n");
	}
	try {
		service.modifyOferta("w", "abc", "ABC", 65);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta nu exista!\n");
	}
}

void test_find_service() {
	OfertaRepo repo;
	Validator validator;
	OfertaService service{ repo, validator };
	Oferta o{ "p", "bedb", "vc", 100 };
	service.addOfera("f", "b", "c", 10);
	service.addOfera("p", "bedb", "vc", 100);
	assert(service.findOferta("p") == o);
	try {
		service.findOferta("1");assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Denumirea este invalida!\n");
	}
	try {
		service.findOferta("WRONG");assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Oferta nu exista!\n");
	}
}

void test_delete_service() {
	OfertaRepo repo;
	Validator validator;
	OfertaService service{ repo, validator };
	service.addOfera("f", "b", "c", 10);
	service.addOfera("p", "bedb", "vc", 100);
	service.addOfera("i", "bedgmb", "ui", 80);
	service.deleteOferta("p");
	assert(service.getAll().size() == 2);
	try {
		service.deleteOferta("111");assert(false);
	}
	catch (Exception ex) {
		assert("Denumirea este invalida!\n");
	}
	try {
		service.deleteOferta("A");assert(false);
	}
	catch (Exception ex) {
		assert("Oferta nu exista!\n");
	}
}

bool DestinatieEgala(const Oferta& oferta, const string& destinatie) {
	return oferta.getDestinatie() == destinatie;
}

bool PreturiEgale(const Oferta& oferta, const string& pret) {
	return std::to_string(oferta.getPret()) == pret;
}

void test_filter_service() {
	OfertaRepo repo;
	Validator valid;
	OfertaService service{ repo, valid };
	service.addOfera("a", "b", "c", 10);
	service.addOfera("aac", "ccb", "ccc", 50);
	service.addOfera("t", "b", "bfdc", 66);
	service.addOfera("ty", "gr", "brc", 50);
	auto list = service.filterOferta("b", DestinatieEgala);
	assert(list.size() == 2);
	assert(list.get(0).getDenumire() == "a");
	auto list2 = service.filterOferta("50", PreturiEgale);
	assert(list2.size() == 2);
	assert(list2.get(0).getDenumire() == "aac");
}

int sortByDenumireTest(const Oferta& o1, const Oferta& o2) {
	return o1.getDenumire().compare(o2.getDenumire());
}

int sortByDestinatieTest(const Oferta& o1, const Oferta& o2) {
	return o1.getDestinatie().compare(o2.getDestinatie());
}

int sortByTipSiPretTest(const Oferta& o1, const Oferta& o2) {
	if (o1.getTip().compare(o2.getTip()) == 0) {
		return (o1.getTip().compare(o2.getTip()));}
	else {
		return o1.getPret()>o2.getPret();
	}
}

void test_sort_service() {
	OfertaRepo repo;
	Validator valid;
	OfertaService service{ repo, valid };
	service.addOfera("a", "b", "csc", 100);
	service.addOfera("sa", "a", "c", 50);
	service.addOfera("t", "y", "c", 63);
	service.addOfera("o", "xc", "m", 100);

	auto list = service.sortOferte(sortByDenumireTest);
	auto list2 = service.sortOferte(sortByDestinatieTest);
	auto list3 = service.sortOferte(sortByTipSiPretTest);

	assert(list.size() == 4);
	assert(list.get(0).getDenumire() == "a");
	assert(list.get(3).getDenumire() == "t");

	assert(list2.size() == 4);
	assert(list2.get(1).getDenumire() == "a");
	assert(list2.get(2).getDenumire() == "o");

	assert(list3.size() == 4);
	assert(list3.get(0).getDenumire() == "sa");
	assert(list3.get(1).getDenumire() == "t");
}

void test_dynamic_vector() {
	VectorDinamic<int> intregi;
	assert(intregi.size() == 0);
	for (int i = 0; i < 20; i++) {
		intregi.push_back(i);
	}
	assert(intregi.size() == 20);
	assert(intregi.get(3) == 3);
}

void test_to_string() {
	Oferta o{ "a", "a", "a", 50 };
	assert(o.toString() == "Denumire: a | Destinatie: a | Tip: a | Pret: 50");
}




template <typename MyVector>
MyVector testCopyIterate(MyVector v) {
	int totalPrice = 0;
	for (auto el : v) {
		totalPrice += el.getPret();
	}
	Oferta p{ "total","tt", "sdd", totalPrice};
	v.push_back(p);
	return v;
}

template <typename MyVector>
void addOferte(MyVector& v, int cate) {
	for (int i = 0; i < cate; i++) {
		Oferta p{ std::to_string(i) + "_denumire",std::to_string(i) + "_destinatie",std::to_string(i) + "_tip", i + 10 };
		v.push_back(p);
	}
}

/*
Testare constructori / assignment
E template pentru a refolosi la diferite variante de VectorDinamic din proiect
*/
template <typename MyVector>
void testCreateCopyAssign() {
	MyVector v;//default constructor
	addOferte(v, 100);
	assert(v.size() == 100);
	assert(v.get(50).getTip() == "50_tip");

	MyVector v2{ v };//constructor de copiere
	assert(v2.size() == 100);
	assert(v2.get(50).getTip() == "50_tip");

	MyVector v3;//default constructor
	v3 = v;//operator=   assignment
	assert(v3.size() == 100);
	assert(v3.get(50).getTip() == "50_tip");

	auto v4 = testCopyIterate(v3);
	assert(v4.size() == 101);
	assert(v4.get(50).getTip() == "50_tip");
}

/*
  Test pentru move constructor si move assgnment
*/
template <typename MyVector>
void testMoveConstrAssgnment() {
	std::vector<MyVector> v;
	//adaugam un vector care este o variabila temporara
	//se v-a apela move constructor
	v.push_back(MyVector{});

	//inseram, la fel se apeleaza move costructor de la vectorul nostru
	v.insert(v.begin(), MyVector{});

	assert(v.size() == 2);

	MyVector v2;
	addOferte(v2, 50);

	v2 = MyVector{};//move assignment

	assert(v2.size() == 0);

}

//template <typename VectorDinamic>

void test_all() {
	test_domain();
	test_operator();
	test_add_repo();
	test_delete_repo();
	test_modify_repo();
	test_find_repo();
	test_validator();
	test_add_service();
	test_modify_service();
	test_find_service();
	test_delete_service();
	test_filter_service();
	test_sort_service();
	test_dynamic_vector();
	test_to_string();
	testCreateCopyAssign<VectorDinamic<Oferta>>();
	testMoveConstrAssgnment<VectorDinamic<Oferta>>();
}
