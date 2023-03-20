#include <iostream>
#include "Teste.h"
#include "Console.h"
#include <crtdbg.h>
#include <stdlib.h>
#define _CRTDBG_MAP_ALLOC

using namespace std;

void start() {
	OfertaRepo repo;
	Validator valid;
	OfertaStore service{ repo, valid };
	UI console{ service };
	console.run();
}

int main() {

	test_all();
	start();
	_CrtDumpMemoryLeaks() ? printf("Leaks") : printf("No leaks!");
}

