#include "Repo.h"
#include <fstream>
#include <sstream>

void Repo::store(const Apartament& a)
{
	this->allApartamente.push_back(a);
}

void Repo::delete_ap(const Apartament& a)
{
	int i = 0;
	while (i < this->allApartamente.size() && this->allApartamente.at(i).getSupr() != a.getSupr() && this->allApartamente.at(i).getStr() != a.getStr() && this->allApartamente.at(i).getPr() != a.getPr())
		i++;
	if (i < this->allApartamente.size())
		this->allApartamente.erase(this->allApartamente.begin() + i);
}

const vector<Apartament>& Repo::getAllAp()
{
	return this->allApartamente;
}

void RepoFile::loadFromFile()
{
	ifstream ApFile(this->filename);
	string line;
	while (getline(ApFile, line))
	{
		string strada;
		int pret=0, suprafata=0;

		stringstream linestream(line);
		string current_item;
		int item_no = 0;
		while (getline(linestream, current_item, ','))
		{
			if (item_no == 0) suprafata = stoi(current_item);
			if (item_no == 1) strada = current_item;
			if (item_no == 2) pret = stoi(current_item);
			item_no++;
		}
		Apartament a{ suprafata, strada, pret };
		Repo::store(a);
	}
	ApFile.close();
}

void RepoFile::saveToFile()
{
	ofstream ApOutput(this->filename);
	for (auto& ap : getAllAp()) {
		ApOutput << ap.getSupr() << "," << ap.getStr() << "," << ap.getPr() << endl;
	}
	ApOutput.close();
}

void RepoFile::store(const Apartament& a)
{
	Repo::store(a);
	saveToFile();
}

void RepoFile::delete_ap(const Apartament& a)
{
	Repo::delete_ap(a);
	saveToFile();
}
