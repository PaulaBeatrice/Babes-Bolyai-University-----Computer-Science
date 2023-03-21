#include "Domain.h"

string Procesor::getNumePr() const
{
	return this->nume;
}

int Procesor::getNrThr() const
{
	return this->nrThr;
}

string Procesor::getsocluPr() const
{
	return this->soclu_pr;
}

int Procesor::getpretPr() const
{
	return this->pret;
}

string PlacaDeBaza::getNumePl() const
{
	return this->nume;
}

string PlacaDeBaza::getsocluPl() const
{
	return this->soclu_pr;
}

int PlacaDeBaza::getpretPl() const
{
	return this->pret;
}
