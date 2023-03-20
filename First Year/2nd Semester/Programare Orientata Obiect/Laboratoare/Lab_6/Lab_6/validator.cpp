#include "validator.h"
#include <assert.h>
#include <sstream>

void OfertaValidator::validate(const Oferta& o) {
	vector<string> msgs;
	if (o.getDenumire().size() == 0)
		msgs.push_back("Denumire vida!!!");
	if (o.getDestinatie().size() == 0)
		msgs.push_back("Destinatie vida!!!");
	if (o.getTip().size() == 0)
		msgs.push_back("Tip vid!!!");
	if (o.getPret() < 0)
		msgs.push_back("Pret negativ!!!");
	if (msgs.size() > 0) {
		throw ValidateException(msgs);
	}
}

ostream& operator<<(ostream& out, const ValidateException& ex){
	for (const auto& msg : ex.msgs) {
		out << msg << " ";
	}
	return out;
}

void testValidator() {
	OfertaValidator v;
	Oferta o{ "","","",-10 };
	try {
		v.validate(o);
	}
	catch (const ValidateException& ex) {
		std::stringstream sout;
		sout << ex;
		auto mesaj = sout.str();
		assert(mesaj.find("negativ") >= 0);
		assert(mesaj.find("vid") >= 0);
	}
}