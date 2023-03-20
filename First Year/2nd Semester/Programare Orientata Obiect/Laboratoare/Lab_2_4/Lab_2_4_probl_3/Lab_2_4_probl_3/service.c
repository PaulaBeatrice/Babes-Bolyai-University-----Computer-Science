#include "repo.h"
#include "service.h"
#include <string.h>

int adaugaInListaDeCheltuieliService(Lista_DE_Cheltuieli* lista_de_cheltuieli, int zi_cheltuiala, int suma_cheltuiala, char* tip_cheltuiala)
{
	Cheltuiala cheltuiala = CreeazaCheltuiala(zi_cheltuiala, suma_cheltuiala, tip_cheltuiala);
	int valid = valideazaCheltuiala(&cheltuiala);
	if (valid != 0)
		return valid;
	adaugaCheltuialaInLista(lista_de_cheltuieli, &cheltuiala);
	return 0;

}

int modificaCheltuialaData(Lista_DE_Cheltuieli* lista_de_cheltuieli, int index, char* campul_de_modificat, char* valoarea_noua, int valoare_noua)
{
	if (strcmp(campul_de_modificat, "zi") == 0 || strcmp(campul_de_modificat, "suma") == 0 || strcmp(campul_de_modificat, "tip") == 0)
	{
		if ((strcmp(valoarea_noua, "") != 0 && strcmp(campul_de_modificat, "tip") == 0) || (strcmp(campul_de_modificat, "suma") == 0 && valoare_noua >= 0) || ((strcmp(campul_de_modificat, "zi") == 0 && valoare_noua >= 0 && valoare_noua <= 31)) )
		{
			if (get_numarul_cheltuieli_din_lista(lista_de_cheltuieli) < index || index < 0)
				return -1;
			modificareCheltuialaDinLista(lista_de_cheltuieli, index, campul_de_modificat, valoarea_noua, valoare_noua);
		}
		else
			return 1;
	}
	else
		return 2;
	return 0;

}

Lista_DE_Cheltuieli vizionare_lista_dupa_zi(Lista_DE_Cheltuieli* lista_de_cheltuieli, int zi)
{
	Lista_DE_Cheltuieli lista_cheltuieli_dupa_zi = creeazaLista();
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli; i++)
		if (getZiEfectuareCheltuaiala(&lista_de_cheltuieli->cheltuieli[i]) == zi)
			adaugaCheltuialaInLista(&lista_cheltuieli_dupa_zi, &lista_de_cheltuieli->cheltuieli[i]);
	return lista_cheltuieli_dupa_zi;
}

Lista_DE_Cheltuieli vizionare_lista_dupa_suma(Lista_DE_Cheltuieli* lista_de_cheltuieli, int suma)
{
	Lista_DE_Cheltuieli lista_cheltuieli_dupa_suma = creeazaLista();
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli; i++)
		if (getSumaCheltuiala(&lista_de_cheltuieli->cheltuieli[i]) == suma)
			adaugaCheltuialaInLista(&lista_cheltuieli_dupa_suma, &lista_de_cheltuieli->cheltuieli[i]);
	return lista_cheltuieli_dupa_suma;
}


Lista_DE_Cheltuieli vizionare_lista_dupa_tip(Lista_DE_Cheltuieli* lista_de_cheltuieli, char* tip)
{
	Lista_DE_Cheltuieli lista_cheltuieli_dupa_tip = creeazaLista();
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli; i++)
		if(strcmp(getTipCheltuiala(&lista_de_cheltuieli->cheltuieli[i]), tip) == 0)
			adaugaCheltuialaInLista(&lista_cheltuieli_dupa_tip, &lista_de_cheltuieli->cheltuieli[i]);
	return lista_cheltuieli_dupa_tip;
}

void sorteaza_cheltuielile_dupa_suma_crescator(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	for(int i = 0; i < lista_de_cheltuieli->nr_cheltuieli - 1; i++)
		for(int j = i + 1; j < lista_de_cheltuieli->nr_cheltuieli; j++)
			if (lista_de_cheltuieli->cheltuieli[i].suma > lista_de_cheltuieli->cheltuieli[j].suma)
			{
				Cheltuiala ch = lista_de_cheltuieli->cheltuieli[i];
				lista_de_cheltuieli->cheltuieli[i] = lista_de_cheltuieli->cheltuieli[j];
				lista_de_cheltuieli->cheltuieli[j] = ch;
			}
}

void sorteaza_cheltuielile_dupa_suma_descrescator(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli - 1; i++)
		for (int j = i + 1; j < lista_de_cheltuieli->nr_cheltuieli; j++)
			if (getSumaCheltuiala(&lista_de_cheltuieli->cheltuieli[i]) < getSumaCheltuiala(&lista_de_cheltuieli->cheltuieli[j]))
			{
				Cheltuiala ch = lista_de_cheltuieli->cheltuieli[i];
				lista_de_cheltuieli->cheltuieli[i] = lista_de_cheltuieli->cheltuieli[j];
				lista_de_cheltuieli->cheltuieli[j] = ch;
			}
}

void sorteaza_cheltuielile_dupa_tip_crescator(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli - 1; i++)
		for (int j = i + 1; j < lista_de_cheltuieli->nr_cheltuieli; j++)
			if (strcmp(getTipCheltuiala(&lista_de_cheltuieli->cheltuieli[i]), getTipCheltuiala(&lista_de_cheltuieli->cheltuieli[j])) > 0)
			{
				Cheltuiala ch = lista_de_cheltuieli->cheltuieli[i];
				lista_de_cheltuieli->cheltuieli[i] = lista_de_cheltuieli->cheltuieli[j];
				lista_de_cheltuieli->cheltuieli[j] = ch;
			}
}

void sorteaza_cheltuielile_dupa_tip_descrescator(Lista_DE_Cheltuieli* lista_de_cheltuieli)
{
	for (int i = 0; i < lista_de_cheltuieli->nr_cheltuieli - 1; i++)
		for (int j = i + 1; j < lista_de_cheltuieli->nr_cheltuieli; j++)
			if (strcmp(getTipCheltuiala(&lista_de_cheltuieli->cheltuieli[i]), getTipCheltuiala(&lista_de_cheltuieli->cheltuieli[j])) < 0)
			{
				Cheltuiala ch = lista_de_cheltuieli->cheltuieli[i];
				lista_de_cheltuieli->cheltuieli[i] = lista_de_cheltuieli->cheltuieli[j];
				lista_de_cheltuieli->cheltuieli[j] = ch;
			}
}