#include "Cos_Oferte.h"
using std::shuffle;
#include <fstream>

void CosOferte::addOfertaToCos(const Oferta& o)
{
	this->cosOferte.push_back(o);
}

void CosOferte::emptyCos()
{
	this->cosOferte.clear();
}

bool gasit(vector<Oferta> originalOferte, vector<Oferta> cosof) {
	for (auto of : cosof)
		if (of.getDenumire() == originalOferte.back().getDenumire())
			return false;
	return true;
}

void CosOferte::addRandomOferte(vector<Oferta> originalOferte, int howMany)
{
	shuffle(originalOferte.begin(), originalOferte.end(), std::default_random_engine(std::random_device{}())); //amesteca vectorul v
	while (cosOferte.size() < howMany && originalOferte.size() > 0) {
		if (gasit(originalOferte, cosOferte) == true) {
			cosOferte.push_back(originalOferte.back());
		}
		originalOferte.pop_back();
	}
}

const vector<Oferta>& CosOferte::getAllOferteCos()
{
	return this->cosOferte;
}

void CosOferte::exportaCos(string numeFisier)
{
	std::ofstream fout(numeFisier);
	for (auto& oferta : cosOferte)
		fout << oferta.getDenumire() << ":" << oferta.getDestinatie() << ":" << oferta.getTip() << ":" << oferta.getPret() << std::endl;
	fout.close();
}
