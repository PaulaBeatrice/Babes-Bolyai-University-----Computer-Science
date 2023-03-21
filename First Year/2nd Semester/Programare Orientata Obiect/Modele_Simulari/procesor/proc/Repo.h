#pragma once
#include <vector>
#include "Domain.h"

using namespace std;

class ProcRepo {
private:
	vector<Procesor> allProcesor;
public:
	ProcRepo() = default;
	ProcRepo(const ProcRepo& ot) = delete;

	virtual void store(const Procesor& p);

	const vector<Procesor>& getAllProcesor();
};

class ProcRepoFile : public ProcRepo
{
private:
	string filename;
	void loadFromFile();
	void saveToFile();
public:
	ProcRepoFile(string filename) : ProcRepo(), filename{ filename }{
		loadFromFile();
	}

	void store(const Procesor& p) override;
};

class PlRepo {
private:
	vector<PlacaDeBaza> allPlaca;
public:
	PlRepo() = default;
	PlRepo(const PlRepo& ot) = delete;

	virtual void store(const PlacaDeBaza& p);

	const vector<PlacaDeBaza>& getAllPlaci();
};

class PlRepoFile : public PlRepo
{
private:
	string filename;
	void loadFromFile();
	void saveToFile();
public:
	PlRepoFile(string filename) : PlRepo(), filename{ filename }{
		loadFromFile();
	}

	void store(const PlacaDeBaza& p) override;
};