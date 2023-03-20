#pragma once
#include "sort.h"

void swap(produs* xp, produs* yp)
{
	produs temp = *xp;
	*xp = *yp;
	*yp = temp;
}

void sort(memorie* v, CompareFct cmpF) {
	int i, j;
	for (i = 0; i < v->lenght; i++) {
		for (j = i + 1; j < v->lenght;j++) {
			//produs p1 = get_p(v, i);
			//produs p2 = get_p(v, j);
			if (cmpF(&v->products[i], &v->products[j]) > 0) {
				//interschimbam
				swap(&v->products[i], &v->products[j]);
			}
		}
	}
}