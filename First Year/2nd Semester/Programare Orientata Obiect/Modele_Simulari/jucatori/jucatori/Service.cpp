#include "Service.h"
#include<algorithm>
#include<assert.h>

void Service::add(string nume, string tara, int pct, int rnk)
{
	Jucator j(nume, tara, pct, rnk);
	this->repo.store(j);
}

bool cmpPct(Jucator a, Jucator b) {
	return a.getPct() > b.getPct();
}

vector<Jucator> Service::sortByPct()
{
	vector<Jucator> sortedCopy{ repo.getAllJucatori() };
	sort(sortedCopy.begin(), sortedCopy.end(), cmpPct);
	for (int i = 0; i < sortedCopy.size(); i++)
		sortedCopy.at(i).setRank(i);
	return sortedCopy;
}

void Service::stergere(string nume)
{
	this->repo.delete_pl(nume);
}

vector<Jucator> Service::getAllJucatori()
{
	return this->repo.getAllJucatori();
}

void Service::ModificaPunctaj(int pct, string nume)
{
	Jucator j = this->repo.find(nume);
	j.setPct(j.getPct() + pct);
	this->repo.find(nume).setPct(j.getPct());
}

void test_service()
{
	Repo repo;
	Service srv(repo);
	srv.add("Ana", "Romania", 300, 6);
	srv.add("Flavia", "Romania", 450, 6);
	srv.add("Bianca", "Romania", 200, 6);
	assert(srv.getAllJucatori().size() == 3);
	vector<Jucator> v = srv.sortByPct();
	assert(v.size() == 3);
	assert(v.at(0).getNume() == "Flavia");
	srv.stergere("Bianca");
	assert(srv.getAllJucatori().size() == 2);
	srv.ModificaPunctaj(100, "Ana");
	assert(srv.sortByPct().at(1).getPct() == 400);


}
