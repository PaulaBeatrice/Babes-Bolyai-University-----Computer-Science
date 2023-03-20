#include "IteratorMD.h"
#include "MD.h"

using namespace std;

IteratorMD::IteratorMD(const MD& _md): md(_md) {
	ind_curent = _md.prim_el;
}

TElem IteratorMD::element() const{
	return pair <TCheie, TValoare>  (md.e[ind_curent]);
}

bool IteratorMD::valid() const {
	return (ind_curent != -1);
}

void IteratorMD::urmator() {
	ind_curent = md.urm[ind_curent];
}

void IteratorMD::prim() {
	ind_curent = md.prim_el;
}

