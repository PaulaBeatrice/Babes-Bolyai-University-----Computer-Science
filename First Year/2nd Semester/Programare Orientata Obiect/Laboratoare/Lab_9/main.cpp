#include <iostream>
#include "Teste.h"
#include "Console.h"
#include <crtdbg.h>
#include <stdlib.h>
#define _CRTDBG_MAP_ALLOC

using namespace std;

void start() {
	//OfertaRepo repo;
	ProbRepo repo;
	repo.setProb(1);
	Validator valid;
	CosOferte cos;
	OfertaStore service{ repo, valid , cos};
	UI console{ service };
	console.run();
}

int main() {

	test_all();
	start();
	_CrtDumpMemoryLeaks() ? printf("Leaks") : printf("No leaks!");
}

