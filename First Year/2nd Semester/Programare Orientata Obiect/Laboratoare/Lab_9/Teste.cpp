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
#include <fstream>

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

void test_add_repo() {
	OfertaRepo repo;
	Oferta o1{ "A", "d", "c", 10 };
	repo.store(o1);
	assert(repo.getAllOferte().size() == 1);
	try {
		repo.store(o1);assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
}

void test_delete_repo() {
	Oferta o1{ "A", "d", "c", 10 };
	Oferta o2{ "F", "dAS", "c", 410 };
	Oferta o3{ "PO", "SVS", "cVVV", 63 };
	OfertaRepo repo;
	repo.store(o1);
	repo.store(o2);
	repo.store(o3);
	try {
		repo.delete_of("Fvsd");assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
	repo.delete_of("A");
	auto list = repo.getAllOferte();
	assert(list.size() == 2);
	assert(list.at(0).getDenumire() == "F");
	assert(list.at(1).getDenumire() == "PO");
}

void test_modify_repo() {
	OfertaRepo repo;
	Oferta o1{ "A", "d", "c", 10 };
	Oferta o2{ "A", "dAS", "c", 410 };
	Oferta o3{ "PO", "SVS", "cVVV", 63 };
	repo.store(o1);
	repo.modify_of(o2);
	auto list = repo.getAllOferte();
	assert(list.size() == 1);
	assert(list.at(0).getPret() == 410);
	assert(list.at(0).getDestinatie() == "dAS");
	try {
		repo.modify_of(o3);assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
}

void test_find_repo() {
	Oferta o1{ "A", "d", "c", 10 };
	Oferta o2{ "F", "dAS", "c", 410 };
	Oferta o3{ "PO", "SVS", "cVVV", 63 };
	OfertaRepo repo;
	repo.store(o1);
	assert(repo.exists(o1));
	assert(!repo.exists(o2));
	repo.store(o2);
	repo.store(o3);
	assert(repo.find("F").getDenumire() == o2.getDenumire());
	try {
		repo.find("szs");assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
}

void test_validator() {
	Validator validator;
	Oferta o{ "a", "b", "c", 10 };
	Oferta o2{ "", "", "", -8 };
	validator.validateOferta(o);
	try {
		validator.validateOferta(o2);assert(false);
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Denumirea este invalida!\nDestinatia este invalida!\nTipul este invalid!\nPretul este invalid!\n");
	}
}

void test_add_service() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos};

	service.addOferta("a", "b", "c", 10);
	service.addOferta("f", "b", "c", 10);
	assert(service.getAllOferte().size() == 2);
	try {
		service.addOferta("a", "svb", "c", 10);assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
	try {
		service.addOferta("", "", "", 10);assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
}

void test_modify_service() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };

	service.addOferta("f", "b", "c", 10);
	service.addOferta("gd", "bsv", "fvc", 30);
	service.modifyOferta("f", "abc", "ABC", 65);
	assert(service.getAllOferte().at(0).getPret() == 65);
	try {
		service.modifyOferta("1", "2", "ssc4", -10);assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
	try {
		service.modifyOferta("w", "abc", "ABC", 65);assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
}

void test_find_service() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	Oferta o{ "p", "bedb", "vc", 100 };
	service.addOferta("f", "b", "c", 10);
	service.addOferta("p", "bedb", "vc", 100);
	assert(service.findOferta("p").getDenumire() == o.getDenumire());
	try {
		service.findOferta("1");assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
	try {
		service.findOferta("WRONG");assert(false);
	}
	catch (Exception ex) {
		assert(true);
	}
}

void test_delete_service() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	service.addOferta("f", "b", "c", 10);
	service.addOferta("p", "bedb", "vc", 100);
	service.addOferta("i", "bedgmb", "ui", 80);
	service.deleteOferta("p");
	assert(service.getAllOferte().size() == 2);
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

void test_filter_service() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	service.addOferta("a", "b", "c", 10);
	service.addOferta("aac", "ccb", "ccc", 50);
	service.addOferta("t", "b", "bfdc", 66);
	service.addOferta("ty", "gr", "brc", 50);
	
	vector<Oferta> oferte_cu_dest_b = service.filtrareDest("b");
	assert(oferte_cu_dest_b.size() == 2);
	vector<Oferta> oferte_cu_dest_null = service.filtrareDest("gdd");
	assert(oferte_cu_dest_null.size() == 0);

	vector<Oferta> oferte_cu_pret_50 = service.filtrarePret(50);
	assert(oferte_cu_pret_50.size() == 2);
	vector<Oferta> oferte_cu_pret_null = service.filtrarePret(80);
	assert(oferte_cu_pret_null.size() == 0);
}

void test_sort_service() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	service.addOferta("a", "b", "csc", 100);
	service.addOferta("sa", "a", "c", 50);
	service.addOferta("t", "y", "c", 63);
	service.addOferta("o", "xc", "m", 100);

	vector<Oferta>sortedByDenumire = service.sortByDenumire();
	vector<Oferta>sortedByDest = service.sortByDestinatie();
	vector<Oferta>sortedByTipSiPret = service.sortByTipSiPret();

	assert(sortedByDenumire.size() == 4);
	assert(sortedByDenumire.at(0).getDenumire() == "a");
	assert(sortedByDenumire.at(3).getDenumire() == "t");

	assert(sortedByDest.size() == 4);
	assert(sortedByDest.at(1).getDenumire() == "a");
	assert(sortedByDest.at(2).getDenumire() == "o");

	assert(sortedByTipSiPret.size() == 4);
	assert(sortedByTipSiPret.at(0).getDenumire() == "sa");
	assert(sortedByTipSiPret.at(1).getDenumire() == "t");
}

void test_cos()
{
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	service.addOferta("a", "b", "csc", 100);
	service.addOferta("sa", "a", "c", 50);
	service.addOferta("t", "y", "c", 63);
	service.addOferta("o", "xc", "m", 100);

	service.addToCos("a");
	assert(service.getAllOferteCos().size() == 1);

	service.addRandomToCos(2);
	assert(service.getAllOferteCos().size() == 2);

	service.emptyCos();
	assert(service.getAllOferteCos().size() == 0);
}

void test_count() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	service.addOferta("a", "b", "csc", 100);
	service.addOferta("sa", "a", "c", 50);
	service.addOferta("t", "a", "c", 63);
	service.addOferta("o", "A", "m", 100);
	assert(service.count_oferte("a") == 2);
	assert(service.count_oferte("A") == 1);
	assert(service.count_oferte("x") == 0);
}

void test_undo() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	try {
		service.undo();
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Nu mai exista operatii");
	}
	service.addOferta("a", "b", "csc", 100);
	service.addOferta("sa", "a", "c", 50);
	service.addOferta("t", "a", "c", 63);
	service.addOferta("o", "A", "m", 100);
	service.undo();
	assert(service.getAllOferte().size() == 3);
	service.undo();
	service.undo();
	service.undo();
	try {
		service.undo();
	}
	catch (Exception ex) {
		assert(ex.getMessage() == "Nu mai exista operatii");
	}
	service.addOferta("a", "b", "csc", 100);
	service.addOferta("sa", "a", "c", 50);
	assert(service.getAllOferte().size() == 2);
	service.modifyOferta("a", "a", "a", 20);
	assert(service.getAllOferte().at(0).getDestinatie() == "a");
	service.undo();
	assert(service.getAllOferte().at(1).getDestinatie() == "b");
	service.deleteOferta("sa");
	assert(service.getAllOferte().size() == 1);
	service.undo();
	assert(service.getAllOferte().size() == 2);
}

void test_export() {
	OfertaRepo repo;
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos };
	service.addOferta("a", "b", "csc", 100);
	service.addOferta("sa", "a", "c", 50);
	service.addOferta("t", "y", "c", 63);
	service.addOferta("o", "xc", "m", 100);

	service.addToCos("a");
	service.addToCos("sa");
	service.addToCos("t");
	assert(service.getAllOferteCos().size() == 3);

	service.export_fisier("test");

	string den, dest, tip, pr, line;
	int cnt = 0;
	std::ifstream fin("test");
	while (std::getline(fin, line)) {
	//	std::vector < string > v;
	//	string delimitator = " | ";
	//	string token = line.substr(0, line.find(delimitator));
	//	size_t pos = 0;
	//	int atr = 0;
	//	while ((pos = line.find(" | ")) != string::npos) {
	//		token = line.substr(0, pos);
	//		if (atr == 0)
	//			assert(token == service.getAllOferteCos().at(cnt).getDenumire());
	//		if (atr == 1)
	//			assert(token == service.getAllOferteCos().at(cnt).getDestinatie());
	//		if (atr == 2)
	//			assert(token == service.getAllOferteCos().at(cnt).getTip());
	//		if (atr == 3) assert(stoi(token) == service.getAllOferteCos().at(cnt).getPret());
	//		v.push_back(token);
	//		line.erase(0, pos + delimitator.length());
	//		atr++;
	//	}
		cnt++;
	}
	assert(cnt == 3);
}

void test_repo_prob() {
	ProbRepo repoTest;
	repoTest.setProb(0);
	try {
		repoTest.store(Oferta("a", "a", "a",10)); assert(false);
	}
	catch (Exception r) {
		assert(true);
	}
	/*
	try {
		repoTest.getAllOferte();
		assert(false);
	}
	catch (Exception r) {
		assert(true);
	}
	*/
	repoTest.setProb(1);

	repoTest.store(Oferta("a", "a", "a", 10));
	assert(repoTest.exists(Oferta("a", "a", "a", 15)) == true);
	repoTest.modify_of(Oferta("a", "b", "b", 10));
	try {
		repoTest.store(Oferta{ "a", "a", "a", 10 }); assert(false);
	}
	catch (Exception r) {
		assert(true);
	}
	repoTest.delete_of("a");
	Oferta o1{ "a", "a", "a", 10 };
	Oferta o2{ "b", "b", "b", 15 };
	Oferta o3{ "c", "c", "c", 20 };
	repoTest.store(o1);
	repoTest.store(o2);
	repoTest.store(o3);
	repoTest.delete_of("b");
	assert(repoTest.find("a").getDenumire() == o1.getDenumire());
	repoTest.delete_of("a");
	repoTest.delete_of("c");
	repoTest.store(o1);
	try {
		repoTest.store(o1); assert(false);
	}
	catch (Exception r) {
		assert(true);
	}
	try {
		repoTest.find("w"); assert(false);
	}
	catch (Exception&) {}
		repoTest.delete_of("w");
		assert(true);
		repoTest.modify_of(Oferta{"aw", "a","b",100});
		assert(true);
	vector<Oferta> lista = repoTest.getAllOferte();
}

void test_all() {
	test_domain();
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
	test_cos();
	test_count();
	test_undo();
	test_export();
	test_repo_prob();
}
