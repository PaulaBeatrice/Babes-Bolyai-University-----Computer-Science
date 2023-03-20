#pragma once
#include "repository.h"

typedef int (*CompareFct)(produs* el1, produs* el2);
void sort(memorie* v, CompareFct cmpF);
void swap(produs* xp, produs* yp);