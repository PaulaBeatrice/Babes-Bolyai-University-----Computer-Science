#pragma once
#include "Service.h"
#include <cstring>
using namespace std;
#pragma warning(disable : 4996)


class UI {

public:
	UI(OfertaStore& srv) : srv{ srv } {};
	UI(const UI& ot) = delete;
	void UI_printMeniu() const;
	void UI_printMeniuCos() const;
	void UI_printOferte(vector<Oferta>& allOferte);
	void UI_printOferteCos(const vector<Oferta>& allOferte);
	void UI_addOferta();
	void UI_deleteOferta();
	void UI_modifyOferta();
	void UI_filterOferta();
	void UI_sortOferta();
	void UI_Cos();
	void UI_addOfertaCos();
	void UI_addRandomOferteCos();
	void UI_EmptyCos();
	void UI_comenzi(string filename);
	void UI_export();
	void run();
private:
	OfertaStore& srv;
};