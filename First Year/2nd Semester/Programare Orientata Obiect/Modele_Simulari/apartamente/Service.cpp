#include "Service.h"
#include <functional>
#include <algorithm>

void Store::sterge(int supr, string str, int pr)
{
	Apartament a{ supr, str,pr };
	repo.delete_ap(a);
}

vector<Apartament> Store::filtruSupr(int supr1, int supr2)
{
	const vector<Apartament>& allAp = getAllAp();
	vector<Apartament> filtered;
	std::copy_if(allAp.begin(), allAp.end(), back_inserter(filtered),
		[supr1, supr2](const Apartament& o) {
			return o.getSupr() > supr1 && o.getSupr() < supr2;
		});
	return filtered;
}

vector<Apartament> Store::filtruPr(int pr1, int pr2)
{
	const vector<Apartament>& allAp = getAllAp();
	vector<Apartament> filtered;
	std::copy_if(allAp.begin(), allAp.end(), back_inserter(filtered),
		[pr1, pr2](const Apartament& o) {
			return o.getPr() > pr1 && o.getPr() < pr2;
		});
	return filtered;
}
