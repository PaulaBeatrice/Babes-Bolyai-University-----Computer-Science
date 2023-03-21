#include "Domain.h"
#include <assert.h>

string Jucator::getNume() const
{
	return this->nume;
}

string Jucator::getTara() const
{
	return this->tara;
}

int Jucator::getPct() const noexcept
{
	return this->puncte;
}

int Jucator::getRnk() const noexcept
{
	return this->rank;
}

void Jucator::setRank(int rnk)
{
	this->rank = rnk;
}

void Jucator::setPct(int pct)
{
	this->puncte = pct;
}

void teste_domain()
{
	Jucator a("Ana", "Romania", 300, 1);
	assert(a.getNume() == "Ana");
	assert(a.getTara() == "Romania");
	assert(a.getPct() == 300);
	assert(a.getRnk() == 1);
}
