#pragma once
#include <vector>
#include <string>
#include <QtWidgets/QApplication>
#include <QLabel>
#include <QPushButton>
#include <QHBoxLayout>
#include <QFormLayout>
#include <QLineEdit>
#include <QTableWidget>
#include <QMessageBox>
#include <QHeaderView>
#include <QGroupBox>
#include <QRadioButton>
#include <qlistwidget.h>
#include "Service.h"

class StoreGUI : public QWidget {
private:
	Store& srv;

	QListWidget* lista_Proc;
	QListWidget* lista_Pl;

	QLabel* lblNume = new QLabel{ "Nume:" };
	QLabel* lblSoclu = new QLabel{ "Soclu:" };
	QLabel* lblPret = new QLabel{ "Pret:" };
	QLineEdit* editNume;
	QLineEdit* editSoclu;
	QLineEdit* editPret;
	QPushButton* btnAdauga;

	QPushButton* btnReload;
	QPushButton* btnCalcPret;

	void initializeGUIComponents();
	void connectsignals();
	void reloadPrList(vector<Procesor> proc);
	void reloadPlList(vector<PlacaDeBaza> pl);
public:
	StoreGUI(Store& srv) : srv{ srv } {
		initializeGUIComponents();
		connectsignals();
		reloadPrList(srv.getAllPr());
		reloadPlList(srv.getAllPl());
	}
	int getSelectedIndexPl() {
		auto current_index = this->lista_Pl->selectionModel()->currentIndex();
		return current_index.row();
	}
	int getSelectedIndexPr() {
		auto current_index = this->lista_Proc->selectionModel()->currentIndex();
		return current_index.row();
	}
	void guiAddPl();
};