#include "Domain.h"

int Apartament::getSupr() const noexcept
{
	return this->suprafata;
}

string Apartament::getStr() const
{
	return this->strada;
}

int Apartament::getPr() const noexcept
{
	return this->pret;
}
