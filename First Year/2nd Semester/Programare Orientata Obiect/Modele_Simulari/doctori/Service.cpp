#include "Service.h"
#include <functional>
#include <algorithm>
#include <fstream>

const vector<Doctor> Service::filreazaSectie(string sectie)
{
	const vector<Doctor>& doctori = getAllDoctori();
	vector<Doctor> filtredList;
	std::copy_if(doctori.begin(), doctori.end(), back_inserter(filtredList),
		[sectie](const Doctor& doctor) {
			return doctor.getSectie() == sectie;
		});
	return filtredList;
}

const vector<Doctor> Service::filtreazaNume(string nume)
{
	const vector<Doctor>& doctori = getAllDoctori();
	vector<Doctor> filtredList;
	std::copy_if(doctori.begin(), doctori.end(), back_inserter(filtredList),
		[nume](const Doctor& doctor) {
			return doctor.getNume() == nume;
		});
	return filtredList;
}

const vector<Doctor> Service::filtreazaSectie_Nume(string sectie, string nume)
{
	const vector<Doctor>& doctori = getAllDoctori();
	vector<Doctor> filtredList;
	std::copy_if(doctori.begin(), doctori.end(), back_inserter(filtredList),
		[nume,sectie](const Doctor& doctor) {
			return doctor.getNume() == nume && doctor.getSectie() == sectie;
		});
	return filtredList;
}

vector<Doctor> Service::getAllDoctori()
{
	return repo.getAll();
}
