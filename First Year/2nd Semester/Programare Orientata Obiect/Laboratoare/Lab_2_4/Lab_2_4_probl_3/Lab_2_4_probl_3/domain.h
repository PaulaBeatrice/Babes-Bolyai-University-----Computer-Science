#ifndef DOMAIN_H
#define DOMAIN_H

typedef struct 
{
	int zi;
	int suma;
	char tip[20];
}Cheltuiala;

typedef struct
{
	Cheltuiala cheltuieli[30];
	int nr_cheltuieli;
}Lista_DE_Cheltuieli;

Cheltuiala CreeazaCheltuiala(int, int, char*);
int getZiEfectuareCheltuaiala(Cheltuiala*);
int getSumaCheltuiala(Cheltuiala*);
char* getTipCheltuiala(Cheltuiala*);
void seteazaZiEfectuareaCheltuiala(Cheltuiala*, int);
void seteazaSumaCheltuiala(Cheltuiala*, int);
void seteazaTipulCheltuieli(Cheltuiala*, char*);

#endif /* DOMAIN_H */