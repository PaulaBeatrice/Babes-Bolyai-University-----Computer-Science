#include "oferta.h"

bool cmpDenumire(const Oferta& o1, const Oferta& o2)
{
	return o1.getDenumire() < o2.getDenumire();
}

bool cmpDestinatie(const Oferta& o1, const Oferta& o2)
{
	return o1.getDestinatie() < o2.getDestinatie();
}
