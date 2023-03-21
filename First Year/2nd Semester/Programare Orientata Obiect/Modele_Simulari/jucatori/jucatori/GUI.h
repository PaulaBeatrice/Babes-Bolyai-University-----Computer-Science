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
class GUI : public QWidget {
private:

	Service& srv;

	QLabel* lblNume = new QLabel{ "Nume:" };
	QLabel* lblPct = new QLabel{ "Punctaj:" };

	QLineEdit* editNume;
	QLineEdit* editPct;

	QRadioButton* radioF = new QRadioButton(QString::fromStdString("Finala"));
	QRadioButton* radioSMF = new QRadioButton(QString::fromStdString("Semifinala"));
	QRadioButton* radioSF = new QRadioButton(QString::fromStdString("Sfert de finala"));

	QPushButton* btnModificaPct;

	QPushButton* btnRecalculare;

	QListWidget* listaJucatori;

	QSlider* nrJucatori;
	QPushButton* btnStergeJuc;
	

	/*
	QPushButton* btnAddOferta;
	QPushButton* btnModifyOferta;
	QPushButton* btnDeleteOferta;

	QGroupBox* groupBox = new QGroupBox(tr("Tip sortare"));

	QRadioButton* radioSrtDenumire = new QRadioButton(QString::fromStdString("Denumire"));
	QRadioButton* radioSrtDestinatue = new QRadioButton(QString::fromStdString("Destinatie"));
	QRadioButton* radioSrtTipPret = new QRadioButton(QString::fromStdString("Tip+Pret"));
	QPushButton* btnSortOferte;

	QLabel* lblFilterCriteria = new QLabel{ "Pretul dupa care se filtreaza:" };
	QLineEdit* editFilterPret;
	QPushButton* btnFilterOferte1;

	QLabel* lblFilterCriteria2 = new QLabel{ "Destinatie dupa care se filtreaza:" };
	QLineEdit* editFilterDestinatie;
	QPushButton* btnFilterOferte2;

	QPushButton* btnReloadData;

	QPushButton* btnAllInclusive;
	QPushButton* btnCityBreak;
	QPushButton* btnCroaziere;
	QPushButton* btnMultiCountry;
	QPushButton* btnAltele;

	QPushButton* btnUndo;

	QTableWidget* tableOferta;
	QListWidget* listaOferte;
	QPushButton* btnCos;
	QWidget* fereastra_cos;
	QPushButton* addCos;
	QLineEdit* denumireOferta;
	QPushButton* addRandomCos;
	QLineEdit* editRandom;
	QPushButton* golesteCos;
	QListWidget* listaCos;
	QLineEdit* editExport;
	QPushButton* exportCos;
	QLabel* lblRandom = new QLabel{ "Nr oferte random: " };
	QLabel* lblDenumireCos = new QLabel{ "Denumire oferta: " };
	QLabel* lblExport = new QLabel{ "Nume fisier: " };
	*/

	void initializeGUIComponents();

	void connectSignalsSlots();
	void reloadList(vector<Jucator> juc);
public:
	GUI(Service& ofSrv) : srv{ ofSrv } {
		initializeGUIComponents();
		connectSignalsSlots();
		reloadList(srv.sortByPct());
	}
};