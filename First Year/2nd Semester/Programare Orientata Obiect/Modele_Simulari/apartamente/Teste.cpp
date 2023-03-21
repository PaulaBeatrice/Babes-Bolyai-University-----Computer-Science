#include "Teste.h"
#include "Domain.h"
#include "Service.h"
#include <iostream>
#include <assert.h>
#include <string>
#include <fstream>
#include <sstream>

void test_domain()
{
	Apartament a{ 1000, "ABC", 67};
	assert(a.getSupr() == 1000);
	assert(a.getStr() == "ABC");
	assert(a.getPr() == 67);
}

void test_add_repo() {
	Repo repo;
	Apartament a{ 1000, "ABC", 67 };
	repo.store(a);
}

void test_delete_repo() {
	Apartament a{ 1000, "ABC", 67 };
	Apartament a2{ 234, "B", 45 };
	Repo repo;
	repo.store(a);
	repo.store(a2);
	repo.delete_ap(a2);
	auto list = repo.getAllAp();
	assert(list.size() == 1);
	assert(list.at(0).getStr() == "ABC");
	assert(list.at(0).getPr() == 67);
}

void test_delete_service() {
	Repo repo;
	Store service{ repo };
	Apartament a{ 1000, "ABC", 67 };
	Apartament a2{ 234, "B", 45 };
	repo.store(a);
	repo.store(a2);
	
	service.sterge(234,"B", 45);
	assert(service.getAllAp().size() == 1);
}

void test_filter_service() {
	Repo repo;
	Store service{ repo };
	Apartament a{ 1000, "ABC", 67 };
	Apartament a2{ 234, "B", 45 };
	repo.store(a);
	repo.store(a2);

	vector<Apartament> f1 = service.filtruPr(40,50);
	assert(f1.size() == 1);
	vector<Apartament> f2 = service.filtruPr(0,1);
	assert(f2.size() == 0);

	vector<Apartament> f3 = service.filtruSupr(400, 5000);
	assert(f3.size() == 1);
	vector<Apartament> f4 = service.filtruSupr(0, 1);
	assert(f4.size() == 0);
}

void test_repo_file() {
	std::ofstream ofs;
	ofs.open("test.txt", std::ofstream::out | std::ofstream::trunc);
	ofs << "123,df,100\n";
	ofs << "345,sdfg,700\n";
	ofs << "12345,fff,800\n";
	ofs.close();

	RepoFile testRepoF{ "test.txt" };
	assert(testRepoF.getAllAp().size() == 3);

	Apartament a{ 234,"abcd",455};

	testRepoF.store(a);
	assert(testRepoF.getAllAp().size() == 4);

}

void test_all() {
	test_domain();
	test_add_repo();
	//test_delete_repo();
	test_delete_service();
	test_filter_service();
	test_repo_file();
}
