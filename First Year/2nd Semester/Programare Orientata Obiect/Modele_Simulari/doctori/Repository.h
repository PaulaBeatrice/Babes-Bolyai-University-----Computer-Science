#pragma once
#include "domain.h"
#include <string>
#include <vector>

using std::vector;

class RepoExceptii {
private:
	string eroare;
public:
	RepoExceptii(string eroare) :eroare{ eroare } {};
	string getErrorMessage() {
		return this->eroare;
	}
};

class Repository
{
private:
	vector<Doctor> allDoctori;
public:
	Repository() = default;
	Repository(const Repository& repo) = delete;

	virtual void store(const Doctor& doctor);
	const vector<Doctor>& getAll();
	const Doctor& find(string cnp);
	bool exists(const Doctor& doctor);
};

class RepoFile : public Repository {
private:
	string nume_fisier;
	void load_from_file();
	void save_to_file();
public:
	RepoFile(string nume_fisier) :Repository(), nume_fisier{ nume_fisier }{load_from_file(); };

	void store(const Doctor& doctor)override;
};

