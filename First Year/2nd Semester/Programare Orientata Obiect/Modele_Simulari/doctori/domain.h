#pragma once
#include <string>
#include <iostream>

using namespace std;
using std::string;

class Doctor
{
private:
	string cnp;
	string nume;
	string prenume;
	string sectie;
	int concediu;
public:
	//Doctor() = default;
	Doctor(string cnp,string nume, string prenume,string sectie,int concediu) :cnp{ cnp }, nume{ nume }, prenume{ prenume }, sectie{ sectie }, concediu{ concediu }{};
	Doctor(const Doctor& doctor) :cnp{ doctor.getCNP() }, nume{ doctor.getNume() }, prenume{ doctor.getPrenume() }, sectie{ doctor.getSectie() }, concediu{ doctor.getConcediu() }{};
	string getCNP()const;
	string getNume()const;
	string getPrenume()const;
	string getSectie()const;
	int getConcediu()const;

	void setCNP(string cnp_nou);
	void setNume(string nume_nou);
	void setPrenume(string prenume_nou);
	void setConcediu(int concediu);
	void setSectie(string sectie_noua);

	Doctor& operator=(const Doctor& doctor1)
	{
		this->cnp = doctor1.cnp;
		this->nume = doctor1.nume;
		this->prenume = doctor1.prenume;
		this->sectie = doctor1.sectie;
		this->concediu = doctor1.concediu;
		return (*this);
	}
};

