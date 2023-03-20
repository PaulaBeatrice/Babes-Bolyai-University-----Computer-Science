#include "domain.h"
#include <string.h>

Cheltuiala CreeazaCheltuiala(int zi, int suma, char* tip)
{
	Cheltuiala cheltuiala;
	cheltuiala.zi = zi;
	cheltuiala.suma = suma;
	strcpy_s(cheltuiala.tip, sizeof(cheltuiala.tip), tip);
	return cheltuiala;
}

int getZiEfectuareCheltuaiala(Cheltuiala* cheltuiala)
{
	return cheltuiala->zi;
}

int getSumaCheltuiala(Cheltuiala* cheltuiala)
{
	return cheltuiala->suma;
}

char* getTipCheltuiala(Cheltuiala* cheltuiala)
{
	return cheltuiala->tip;
}

void seteazaZiEfectuareaCheltuiala(Cheltuiala* cheltuiala, int zi_noua)
{
	cheltuiala->zi = zi_noua;
}

void seteazaSumaCheltuiala(Cheltuiala* cheltuiala, int suma_noua)
{
	cheltuiala->suma = suma_noua;
}

void seteazaTipulCheltuieli(Cheltuiala* cheltuiala, char* tip_nou)
{
	strcpy_s(cheltuiala->tip, sizeof(cheltuiala->tip), tip_nou);
}