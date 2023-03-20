#include "domain.h"
#include "repository.h"
#include <string.h>

produs* createProdus(int id, int pret, int cantitate, char* type, char* producator, char* model)
{
	produs* p = malloc(sizeof(produs));
	if (p)
	{
		p->id = id;
		p->pret = pret;
		p->cantitate = cantitate;

		int nrC = (int)strlen(type) + 1;
		p->type = malloc(nrC * sizeof(char));
		if (p->type)
			strcpy_s(p->type, nrC, type);

		nrC = (int)strlen(producator) + 1;
		p->producator = malloc(nrC * sizeof(char));
		if (p->producator)
			strcpy_s(p->producator, nrC, producator);

		nrC = (int)strlen(model) + 1;
		p->model = malloc(nrC * sizeof(char));
		if (p->model)
			strcpy_s(p->model, nrC, model);
	}
	return p;
}

void destroyProdus(produs* p)
{
	free(p->model);
	free(p->producator);
	free(p->type);
	free(p);
}

produs* copyProdus(produs* p) {
	return(createProdus(p->id, p->pret, p->cantitate, p->type, p->producator, p->model));
}

int validate(produs* p)
{
	if (p->id <= 0)
		return 0;
	if (p->pret <= 0)
		return 0;
	if (p->cantitate <= 0)
		return 0;
	if (strcmp(p->model, "") == 0)
		return 0;
	if (strcmp(p->producator, "") == 0)
		return 0;
	if (strcmp(p->type, "") == 0)
		return 0;
	return 1;
}
