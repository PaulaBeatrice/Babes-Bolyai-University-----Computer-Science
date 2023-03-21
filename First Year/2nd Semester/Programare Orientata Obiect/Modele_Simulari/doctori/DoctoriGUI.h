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

using std::vector;
using std::string;

class DoctoriGUI :public QWidget
{
private:
	Service& service;
	QLabel* labelCNP = new QLabel{"CNP: "};
	QLineEdit* editCNP;
	QGroupBox*  groupBox = new QGroupBox(tr("Tip filtrare"));

	QLabel* labelCriteriu1 = new QLabel{ "Sectia dupa care se filtreaza:" };
	QLineEdit* editSectie1;
	QPushButton* butonFiltrareSectie;

	QLabel* labelCriteriu2 = new QLabel{ "Numele dupa care se filtreaza:" };
	QLineEdit* editNume;
	QPushButton* butonFiltrareNume;

	QLabel* labelCriteriu3 = new QLabel{ "Sectia dupa care se filtreaza:" };
	QLineEdit* editSectie2;
	QLabel* labelCriteriu4 = new QLabel{ "Numele dupa care se filtreaza:" };
	QLineEdit* editNume2;
	QPushButton* butonFiltrareAmbele;

	QPushButton* reloadData;

	QListWidget* listaDoctori;


	void initializeGUIComponents();

	void connectSignalsSlots();
	void reloadOfertaList(vector<Doctor> doctori);
public:
	DoctoriGUI(Service& service) :service{ service } {
		initializeGUIComponents();
		connectSignalsSlots();
		reloadOfertaList(service.getAllDoctori());
	};
	int getSelectedIndex() {
		auto currentIndex = this->listaDoctori->selectionModel()->currentIndex();
		return currentIndex.row();
	}
};

