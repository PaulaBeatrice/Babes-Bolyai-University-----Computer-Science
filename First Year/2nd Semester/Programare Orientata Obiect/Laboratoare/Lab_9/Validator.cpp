#include "Validator.h"
#include "Errors.h"

bool validString(string str) {
	if (str.size() == 0) return false;
	for (int i = 0; i < str.size(); i++) {
		if (!((str.at(i) >= 'a' && str.at(i) <= 'z') || (str.at(i) >= 'A' && str.at(i) <= 'Z'))) return false;
	}
	return true;
}

void Validator::validateOferta(Oferta o) {
	string errors;

	if (validString(o.getDenumire()) == false) errors.append("Denumirea este invalida!\n");
	if (validString(o.getDestinatie()) == false) errors.append("Destinatia este invalida!\n");
	if (validString(o.getTip()) == false) errors.append("Tipul este invalid!\n");
	if (o.getPret() <= 0) errors.append("Pretul este invalid!\n");
	if (errors.size() != 0) throw Exception(errors);
}