#ifndef SERVICE_H
#define SERVICE_H
#include "repo.h"

// Adauga in lista de cheltuieli un obiect de tip cheltuiala
int adaugaInListaDeCheltuieliService(Lista_DE_Cheltuieli* lista_de_cheltuieli, int zi_cheltuiala, int suma_cheltuiala, char* tip_cheltuiala);

// Modifica un obiect de tip cheltuiala
int modificaCheltuialaData(Lista_DE_Cheltuieli* lista_de_cheltuieli, int index, char* campul_de_modificat, char* valoarea_noua, int valoare_noua);

//Vizualizarea listei dupa un anumit criteriu
Lista_DE_Cheltuieli vizionare_lista_dupa_zi(Lista_DE_Cheltuieli* lista_de_cheltuieli, int ziua_data);
Lista_DE_Cheltuieli vizionare_lista_dupa_suma(Lista_DE_Cheltuieli* lista_de_cheltuieli, int suma_data);
Lista_DE_Cheltuieli vizionare_lista_dupa_tip(Lista_DE_Cheltuieli* lista_de_cheltuieli, char* tipul_dat);

//Sortarea listei dupa anumite criterii
void sorteaza_cheltuielile_dupa_suma_crescator(Lista_DE_Cheltuieli* lista_de_cheltuieli);
void sorteaza_cheltuielile_dupa_tip_crescator(Lista_DE_Cheltuieli* lista_de_cheltuieli);
void sorteaza_cheltuielile_dupa_suma_descrescator(Lista_DE_Cheltuieli* lista_de_cheltuieli);
void sorteaza_cheltuielile_dupa_tip_descrescator(Lista_DE_Cheltuieli* lista_de_cheltuieli);

#endif // !SERVICE_H

