#pragma once
#include "objects.h"


/*Functie pentru a verifica daca un nou produs e valid
* return -1 daca nu e valid un produs si 0 in caz contrar
*/
int validation(produs* new_product);

/*Functe de test
*/
void test_validators();
