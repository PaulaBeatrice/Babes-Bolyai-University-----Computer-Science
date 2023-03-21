#include "Repo.h"
#include<sstream>
#include<fstream>
#include<assert.h>

void Repo::store(const Jucator& j)
{
	this->allJucatori.push_back(j);
}

void Repo::delete_pl(string nume)
{
	int i = 0;
	while (i < this->allJucatori.size() && this->allJucatori.at(i).getNume() != nume) {
		i++;
	}
	if (i < this->allJucatori.size())
		this->allJucatori.erase(this->allJucatori.begin() + i);
}

const vector<Jucator>& Repo::getAllJucatori()
{
	return this->allJucatori;
}

Jucator& Repo::find(string nume)
{
	vector<Jucator>::iterator f = std::find_if(this->allJucatori.begin(), this->allJucatori.end(),
		[=](const Jucator& s) {
			return s.getNume() == nume;
		});
	return (*f);
}


void RepoFile::loadFromFile()
{
	ifstream plFile(this->filename);
	string line;
	while (getline(plFile, line))
	{
		string nume, tara;
		int puncte, rank;

		stringstream linestream(line);
		string current_item;
		int item_no = 0;
		while (getline(linestream, current_item, ','))
		{
			if (item_no == 0) nume = current_item;
			if (item_no == 1) tara = current_item;
			if (item_no == 2) puncte = stoi(current_item);
			if (item_no == 3) rank = stoi(current_item);
			item_no++;
		}
		Jucator j{ nume, tara, puncte, rank};
		Repo::store(j);
	}
	plFile.close();
}

void RepoFile::saveToFile()
{
	ofstream plOutput(this->filename);
	for (auto& juc : getAllJucatori()) {
		plOutput << juc.getNume() << "," << juc.getTara() << "," << juc.getPct() << "," << juc.getRnk() << endl;
	}
	plOutput.close();
}

void RepoFile::store(const Jucator& j)
{
	Repo::store(j);
	saveToFile();
}

void RepoFile::delete_pl(string nume)
{
	Repo::delete_pl(nume);
	saveToFile();
}

void teste_repo()
{
	Repo repo;
	Jucator j("Ana", "Romania", 300, 6);
	repo.store(j);
	assert(repo.getAllJucatori().size() == 1);
	assert(repo.find("Ana").getTara() == "Romania");
	repo.delete_pl("Ana");
	assert(repo.getAllJucatori().size() == 0);
}
