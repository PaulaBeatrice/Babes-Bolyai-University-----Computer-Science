#include "Repo.h"
#include <fstream>
#include <iostream>
#include <sstream>

void ProcRepo::store(const Procesor& p)
{
	this->allProcesor.push_back(p);
}

const vector<Procesor>& ProcRepo::getAllProcesor()
{
	return this->allProcesor;
}

void PlRepoFile::loadFromFile()
{
	ifstream plFile(this->filename);
	string line;
	while (getline(plFile, line))
	{
		string nume, soclu;
		int pret = 0;
		stringstream linestream(line);
		string current_item;
		int item_no = 0;
		while (getline(linestream, current_item, ','))
		{
			if (item_no == 0) nume = current_item;
			if (item_no == 1) soclu = current_item;
			if (item_no == 2) pret = stoi(current_item);
			item_no++;
		}
		PlacaDeBaza p{ nume, soclu, pret };
		PlRepo::store(p);
	}
	plFile.close();
}

void PlRepoFile::saveToFile()
{
	ofstream PrOutput(this->filename);
	for (auto& proc : getAllPlaci()) {
		PrOutput << proc.getNumePl() << "," << proc.getsocluPl() << "," << proc.getpretPl() << endl;
	}
	PrOutput.close();
}

void PlRepoFile::store(const PlacaDeBaza& p)
{
	PlRepo::store(p);
	saveToFile();
}

void ProcRepoFile::loadFromFile()
{
	ifstream prFile(this->filename);
	string line;
	while (getline(prFile, line))
	{
		string nume, soclu;
		int nrThr = 0, pret = 0;
		stringstream linestream(line);
		string current_item;
		int item_no = 0;
		while (getline(linestream, current_item, ','))
		{
			if (item_no == 0) nume = current_item;
			if (item_no == 1) nrThr = stoi(current_item);
			if (item_no == 2) soclu = current_item;
			if (item_no == 3) pret = stoi(current_item);
			item_no++;
		}
		Procesor p{ nume, nrThr, soclu, pret };
		ProcRepo::store(p);
	}
	prFile.close();
}

void ProcRepoFile::saveToFile()
{
	ofstream PrOutput(this->filename);
	for (auto& proc : getAllProcesor()) {
		PrOutput << proc.getNumePr() << "," << proc.getNrThr() << "," << proc.getsocluPr() << "," << proc.getpretPr() << endl;
	}
	PrOutput.close();
}

void ProcRepoFile::store(const Procesor& p)
{
	ProcRepo::store(p);
	saveToFile();
}

void PlRepo::store(const PlacaDeBaza& p)
{
	this->allPlaca.push_back(p);
}


const vector<PlacaDeBaza>& PlRepo::getAllPlaci()
{
	return this->allPlaca;
}
