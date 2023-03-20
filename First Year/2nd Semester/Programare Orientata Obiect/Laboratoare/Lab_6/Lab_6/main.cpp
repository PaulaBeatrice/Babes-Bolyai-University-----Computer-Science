#include "console.h"
#include "store.h"
#include "repo.h"
#include "validator.h"

void testAll() {
	testeRepo();
	testCtr();
	testValidator();
}

/*
void adaugaOferte(OfertaStore& ctr) {
	ctr.addOferta("a", "Paris", "all-inclusive", 100);
	ctr.addOferta("b", "Berlin", "all-inclusive", 550);
	ctr.addOferta("c", "Paris", "sejur", 630);
	ctr.addOferta("d", "Londra", "city-break", 800);
}
*/

int main() {
	testAll();
	OfertaRepo rep;
	OfertaValidator val;
	OfertaStore ctr{ rep, val };
//	adaugaOferte(ctr);
	ConsoleUI ui{ ctr };
	ui.start();
}