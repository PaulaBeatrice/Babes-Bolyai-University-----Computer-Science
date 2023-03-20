#include "validare.h"
#include <string.h>

int valideazaCheltuiala(Cheltuiala* cheltuiala_de_validat) 
{
	int valid_zi = 1, valid_suma = 1, valid_tip = 1, tipuri = 0, valid_cheltuiala = 1;
	if (getZiEfectuareCheltuaiala(cheltuiala_de_validat) < 1 || getZiEfectuareCheltuaiala(cheltuiala_de_validat) > 31 /* || isaplha(getZiEfectuareCheltuaiala(cheltuiala_de_validat)) != 0*/)
		return 1;
	if (getSumaCheltuiala(cheltuiala_de_validat) < 0)
		return 2;
	if (strcmp(getTipCheltuiala(cheltuiala_de_validat), "") == 0)
		return 3;
	if (strcmp(getTipCheltuiala(cheltuiala_de_validat), "mancare") == 0 || strcmp(getTipCheltuiala(cheltuiala_de_validat), "transport") == 0 || strcmp(getTipCheltuiala(cheltuiala_de_validat), "telefon& internet") == 0 || strcmp(getTipCheltuiala(cheltuiala_de_validat), "imbracaminte") == 0 || strcmp(getTipCheltuiala(cheltuiala_de_validat), "altele") == 0)
		tipuri = 1;
	if (tipuri == 0)
		return 3;
	return 0;
}