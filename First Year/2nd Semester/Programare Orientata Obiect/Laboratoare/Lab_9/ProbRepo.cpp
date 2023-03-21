#include "Repo.h"
#include "Errors.h"

void ProbRepo::pass() const
{
	double r = ((double)rand() / (RAND_MAX));
	if (r > prob)
		throw(Exception("Nu se poate executa functia. Mai incearca o data!"));
}

void ProbRepo::setProb(double f)
{
	prob = f;
}

void ProbRepo::store(const Oferta& oferta)
{
	pass();
	if (exists(oferta)) {
		throw Exception("Oferta exista deja in lista.\n");
	}
	int index = int(oferte.size());
//	oferte[index] = oferta;
	Oferta of = oferta;
	oferte.insert(std::make_pair(index,of));
}

void ProbRepo::modify_of(const Oferta& o)
{
	pass();
	int i = 0;
	while (i < this->oferte.size() && this->oferte.at(i).getDenumire() != o.getDenumire()) {i++;}
	if (i > this->oferte.size()) throw Exception("Oferta nu exista!\n");
	else
		oferte[i] = o;
}

const Oferta& ProbRepo::find(string denumire)
{
	pass();
	auto it = find_if(oferte.begin(), oferte.end(), [=](const std::pair<int, Oferta> oferta) {return oferta.second.getDenumire() == denumire; });

	if (it == oferte.end())
		throw Exception("Oferta cautata nu exista.");

	return  (*it).second;
}

bool ProbRepo::exists(const Oferta& o)
{
	pass();
	for (int i = 0; i < oferte.size(); ++i) {
		if (oferte[i].getDenumire() == o.getDenumire())
			return true;
	}
	return false;
}

void ProbRepo::delete_of(string denumire)
{
	pass();
	int i = 0;
	while (i < this->oferte.size() && this->oferte.at(i).getDenumire() != denumire) {
		i++;
	}
	if (i > this->oferte.size()) throw Exception("Oferta nu exista!\n");
	else
	{
		for (int j = i; j < oferte.size() - 1; ++j) {
			oferte[j] = oferte[j + 1];
		}
		oferte.erase((int)oferte.size() - 1);
	}
}

vector<Oferta> OF;
vector<Oferta>& ProbRepo::getAllOferte()
{
	pass();
	OF.clear();
	for (auto& o : oferte) { OF.push_back(o.second); }
	return OF;
}

