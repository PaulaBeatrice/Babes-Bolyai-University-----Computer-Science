#include "repo.h"
#include <string.h>
#include <stdio.h>


Lista_DE_Cheltuieli creeazaLista()
{
	Lista_DE_Cheltuieli lista_cheltuielilor;
	lista_cheltuielilor.nr_cheltuieli = 0;
	return lista_cheltuielilor;
}

void adaugaCheltuialaInLista(Lista_DE_Cheltuieli* lista_cheltuielilor, Cheltuiala* cheltuiala_de_adaugat)
{
	lista_cheltuielilor->cheltuieli[lista_cheltuielilor->nr_cheltuieli] = *cheltuiala_de_adaugat;
	lista_cheltuielilor->nr_cheltuieli++;
}

void modificareCheltuialaDinLista(Lista_DE_Cheltuieli* lista_cheltuielilor, int indicele_cheltuielii_de_modificat, char* campul_care_se_modifica, char* valoarea_noua, int valoare_noua)
{
	if (strcmp(campul_care_se_modifica, "zi") == 0)
		seteazaZiEfectuareaCheltuiala(&lista_cheltuielilor->cheltuieli[indicele_cheltuielii_de_modificat - 1], valoare_noua);
	else if (strcmp(campul_care_se_modifica, "suma") == 0)
		seteazaSumaCheltuiala(&lista_cheltuielilor->cheltuieli[indicele_cheltuielii_de_modificat - 1], valoare_noua);
	else if (strcmp(campul_care_se_modifica, "tip") == 0)
		seteazaTipulCheltuieli(&lista_cheltuielilor->cheltuieli[indicele_cheltuielii_de_modificat - 1], valoarea_noua);
}

int get_numarul_cheltuieli_din_lista(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	return lista_de_cheltuieli->nr_cheltuieli;
}