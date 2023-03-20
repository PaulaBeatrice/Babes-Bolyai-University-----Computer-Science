#pragma once

#include "repository.h"


typedef int (*CompareFct)(void* el1, void* el2);
void sort(List* v, CompareFct cmpF);