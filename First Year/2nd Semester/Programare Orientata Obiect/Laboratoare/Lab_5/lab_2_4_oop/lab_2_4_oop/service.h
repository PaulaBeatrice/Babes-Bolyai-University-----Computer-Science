#ifndef SERVICE_H_
#define SERVICE_H_
#include "objects.h"
#include "repository.h"

typedef struct {
	memorie* allProducts;
	memorie* undoList;
}ProductStore;

ProductStore createProductStore();

ProductStore destroyStore(ProductStore* store);

/*Functie pentru stocarea produselor in memorie
*/
void add_produs(ProductStore* store, int id, int pret, int cantitae, char* type, char* producator, char* model);

memorie* getAllProducts(ProductStore* store);

void test_modify_product_quantity();
/*Functie ce se ocupa cu modificarea pretului unui produs in functie de id
* id-id-ul produsului
* new_price-noul pret
* mem-produsele existente
*/
int modify_product_price(int id, int new_price,	ProductStore* mem);


/*Functie ce se ocupa cu modificarea cantitatii unui produs in functie de id
* id-id-ul produsului
* new_quantity-noua cantitate
* mem-produsele existente
*/
int modify_product_quantity(int id, int new_quantity, ProductStore* mem);


/*Functie ce sterge un produs in functie de id
* id- id-ul produsului ce va fi sters
*  mem- lista cu produsele si cu contorul curent
*/
void delete_product(int id, ProductStore* mem);


/*Functie ce creaza o lista sortata dupa pret din produsele existente
* daca direction=0- sortare crescatoare
*				=1 - sortare descrescatoare
*/
memorie* filter_by_price(ProductStore* mem, int direction);


/*Functie ce creaza o lista sortata dupa cantitate din produsele existente
* daca direction=0- sortare crescatoare
*				=1 - sortare descrescatoare
*/
memorie* filter_by_quantity(ProductStore* mem, int direction);


/*Functie ce genereaza o serie de produse pentru teste
*/
memorie* generate_product();


/*Functie pentru a gasi produsele ce au un anumit pret
* mem- structul ce tine toate produsele
* price- pretul cautat
* return un nou obiect memorie cu produse ce indeplinesc conditia
*/
memorie* get_products_by_price(ProductStore* mem, int price);


/*Functie ce se ocupa de a gasi produsele ce auo anumita cantitae
* mem- structul ce tine toate produsele
* cantitate- cantitatea cautatat
* return un nou obiect memorie cu produse ce indeplinesc conditia
*/
memorie* get_products_by_quantity(ProductStore* mem, int cantitate);


/*Functie ce se ocupa de a gasi produsele ce au un anumit producator
* mem- structul ce tine toate produsele
* producator- producatorul cautat
* return un nou obiect memorie cu produse ce indeplinesc conditia
*/
memorie* get_products_by_producer(ProductStore* mem, char producator[30]);

int undo(ProductStore* store);

/*Functii de test
*/
void test_add_products_service();

void test_modify_product_price();

void test_delete_product();

void test_filter_price();

void test_filter_quantity();

void test_get_by_price();

void test_get_by_quantity();

void test_get_by_producer();
#endif // !SERVICE_H_


