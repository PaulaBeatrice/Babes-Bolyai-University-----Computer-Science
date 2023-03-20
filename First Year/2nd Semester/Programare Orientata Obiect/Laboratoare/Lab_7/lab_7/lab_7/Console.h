#pragma once
#include <string>
#include "Service.h"
using namespace std;

class UI {

public:
	void runApplication();
	UI(const OfertaService& service) : service{ service } {
	}
private:
	OfertaService service;
	void UI_printMeniu() const;
	void UI_printOferte();
	void UI_addOferta();
	void UI_deleteOferta();
	void UI_modifyOferta();
	void UI_searchOferta();
	void UI_filterOferta();
	void UI_sortOferta();
};