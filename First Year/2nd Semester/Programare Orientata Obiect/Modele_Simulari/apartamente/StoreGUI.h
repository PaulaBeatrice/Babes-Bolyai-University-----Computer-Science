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
class StoreGUI : public QWidget {
private:

	Store& srv;

	QListWidget* listaAp;
	
	QLabel* lblP1 = new QLabel("Pret1: ");
	QLabel* lblP2 = new QLabel("Pret2: ");
	QLineEdit* pr1;
	QLineEdit* pr2;
	QPushButton* btnFiltruPr;

	QLabel* lblS1 = new QLabel("Suprafata1: ");
	QLabel* lblS2 = new QLabel("Suprafata2: ");
	QLineEdit* supr1;
	QLineEdit* supr2;
	QPushButton* btnFiltruSupr;


	void initializeGUIComponents();

	void connectSignalsSlots();
	void reloadList(vector<Apartament> ap);
public:
	StoreGUI(Store& apSrv) : srv{ apSrv } {
		initializeGUIComponents();
		connectSignalsSlots();
		reloadList(srv.getAllAp());
	}
	int getSelectedIndex() {
		auto currentIndex = this->listaAp->selectionModel()->currentIndex();
		return currentIndex.row();
	}
};