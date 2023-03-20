#ifndef REPOSITORY_H_
#define  REPOSITORY_H_
#include "objects.h"


typedef void* ElemType;
/*Struct pentru alocarea dinamica a obiectelor in memorie si pentru memorarea acestora
*/

typedef void(*DestroyFct) (ElemType);

typedef ElemType(*CopyFct)(ElemType);

typedef struct {
    ElemType* products;
    int lenght;
    int capacitate;
    DestroyFct dfnc;
}memorie;



/*Functie pentru initializarea unui obiect de tip memorie
*/
memorie* createEmpty();


/*Funcie pentru eliberarea memoriei
* mem- obiectul de tip memorie ce vrem sa il eliberam
*/
void destroyList(memorie* mem);


/*getter
*/
ElemType get_p(memorie* mem, int poz);

ElemType set(memorie* mem, int poz, ElemType p);

int size(memorie* l);


/*Functie ce se ocupa cu adaugarea de produde noi
* mem - elementel din memorie
* product-produsul ce vreau sa il adaug
*/
void add(memorie* mem, ElemType el);

ElemType removeLast(memorie* el);

memorie* copyMem(memorie* l, CopyFct cf);


/*Functie ce verifica daca id-ul unui produs exista in memorie
* id- id-ul cautat
* mem- lista cu produsele si cu contorul curent
* return 0 daca nu exista,altfel pozitia produsului
*/
int find_product(int id, memorie* mem);


/*Functie ce sterge un produs in functie de id
* position-pozitia produsului ce va fi sters
*  mem- lista cu produsele si cu contorul curent
*/
void delete_produs(int position, memorie* mem);


/*Teste
*/
void test_find_product();

void test_add_product();

void test_delete_produs();

/*Functie ce genereaza o serie de produse pentr teste
*/
memorie* generate_products();
#endif /* REPOSITORY_H_*/