#include "Cos_Oferte.h"
using std::shuffle;

void CosOferte::addOfertaToCos(const Oferta& o)
{
	this->cosOferte.push_back(o);
}

void CosOferte::emptyCos()
{
	this->cosOferte.clear();
}

void CosOferte::addRandomOferte(vector<Oferta> originalOferte, int howMany)
{
	shuffle(originalOferte.begin(), originalOferte.end(), std::default_random_engine(std::random_device{}())); //amesteca vectorul v
	while (cosOferte.size() < howMany && originalOferte.size() > 0) {
		cosOferte.push_back(originalOferte.back());
		originalOferte.pop_back();
	}
}

const vector<Oferta>& CosOferte::getAllOferteCos()
{
	return this->cosOferte;
}
