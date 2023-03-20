#include "objects.h"
#include "repository.h"
#include "service.h"
#include <assert.h>
#include <string.h>

int validation(produs* new_product) {
	if (new_product->id < 0)
		return -1;
	if (strlen(new_product->model) == 0)
		return -1;
	if (strlen(new_product->producator) == 0)
		return -1;
	if (strlen(new_product->type) == 0)
		return -1;
	return 0;
}
/*
void test_validators() {
	produs* p1 = createProdus(1, 56, 7, "abc", "def", "ghi");
	memorie* test_memory = generate_products();
	int error = validation(p1, &test_memory);
	assert(error == -1);
	destroyProdus(&p1);

	produs* p2 = createProdus(5, 56, 7, "", "def", "ghi");
	error = validation(p2, &test_memory);
	assert(error == -1);
	destroyProdus(&p2);

	produs* p3 = createProdus(5, 56, 7, "abc", "", "ghi");
	error = validation(p3, &test_memory);
	assert(error == -1);
	destroyProdus(&p3);

	produs* p4 = createProdus(5, 56, 7, "abc", "def", "");
	error = validation(p4, &test_memory);
	assert(error == -1);
	destroyProdus(&p4);

	produs* p5 = createProdus(5, 56, 7, "abc", "def", "ghi");
	error = validation(p5, &test_memory);
	assert(error == 0);
	destroyProdus(&p5);
	destroy(&test_memory);
}*/
