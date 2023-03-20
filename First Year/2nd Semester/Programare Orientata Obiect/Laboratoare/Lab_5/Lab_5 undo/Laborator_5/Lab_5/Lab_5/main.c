#define _CRTDBG_MAP_ALLOC
#include <stdio.h>
#include "ui.h"
#include "teste.h"
#include <crtdbg.h>


int main() {

	testAll();
	run();
	_CrtDumpMemoryLeaks() ? printf("Leaks") : printf("No leaks!");
}