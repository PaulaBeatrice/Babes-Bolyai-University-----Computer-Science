#include <iostream>
#include "Teste.h"
#include "Console.h"
#include "vectordinamic.h"
#include <crtdbg.h>
#define _CRTDBG_MAP_ALLOC

using namespace std;

void start() {
	OfertaRepo repo;
	Validator valid;
	OfertaService service{ repo, valid };
	UI console{ service };
	console.runApplication();
}

int main() {

	test_all();
	start();
	_CrtDumpMemoryLeaks() ? printf("Leaks") : printf("No leaks!");
}

