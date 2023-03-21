#include "Repository.h"
#include <fstream>
#include <sstream>
#include <iostream>

using std::ifstream;
using std::ofstream;
using std::stringstream;
using std::endl;
using std::getline;
using std::stod;

void Repository::store(const Doctor& doctor)
{
	if (exists(doctor)) {
		throw RepoExceptii("Doctorul exista deja.");
	}
	this->allDoctori.push_back(doctor);
}

const vector<Doctor>& Repository::getAll()
{
	return this->allDoctori;
}

const Doctor& Repository::find(string cnp)
{
	vector<Doctor>::iterator doctor = std::find_if(this->allDoctori.begin(), this->allDoctori.end(),
		[=](const Doctor& doctor2) {
			return doctor2.getCNP() == cnp;
		});

	if (doctor != this->allDoctori.end())
		return (*doctor);
	else
		throw RepoExceptii("Doctorul nu exista in lista.\n");
}

bool Repository::exists(const Doctor& doctor)
{
	try {
		find(doctor.getCNP());
		return true;
	}
	catch(RepoExceptii)
	{
		return false;
	}
}

void RepoFile::load_from_file()
{
	ifstream file(this->nume_fisier);
	if (!file.is_open()) {
		throw RepoExceptii("Cannot read from file " + nume_fisier);
	}
	string line;
	while (getline(file, line))
	{
		string cnp, nume, prenume,sectie;
		int concediu;

		stringstream linestream(line);
		string current_item;
		int item_no = 0;
		while (getline(linestream, current_item, ','))
		{
			if (item_no == 0) cnp = current_item;
			if (item_no == 1) nume = current_item;
			if (item_no == 2) prenume = current_item;
			if (item_no == 3) sectie = current_item;
			if (item_no == 4) concediu = stoi(current_item);
			item_no++;
		}
		Doctor doctor{ cnp, nume, prenume, sectie,concediu };
		Repository::store(doctor);
	}
	file.close();
}

void RepoFile::save_to_file()
{
	ofstream outputFile(this->nume_fisier);
	if (!outputFile.is_open())
		throw RepoExceptii("Cannot write to file " + nume_fisier);
	for (auto& doctor : getAll()) {
		outputFile << doctor.getCNP() << "," << doctor.getNume() << "," << doctor.getPrenume() << "," << doctor.getSectie() <<"," <<doctor.getConcediu() << endl;
	}
	outputFile.close();
}

void RepoFile::store(const Doctor& doctor)
{
	Repository::store(doctor);
	save_to_file();
}
