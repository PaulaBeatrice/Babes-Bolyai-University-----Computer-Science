#pragma once
#include <qwidget.h>
#include <qlistwidget.h>
#include <qlistview.h>
#include <qpushbutton.h>
#include <qlabel.h>
#include <qlineedit.h>
#include <qtablewidget.h>
#include "Service.h"
#include "oferta.h"
#include <vector>
#include "MyTableModel.h"
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
#include "Cos_GUI.h"
#include "HistrogramGUI.h"
#include "MyListModel.h"

class StoreGUIModele : public QWidget {
private:
	OfertaStore& ctr;
	MyTableModel* model;
	QLabel* lblCate = new QLabel{ "Cate:" };
	QListView* lstV = new QListView;

	QTableView* tblV = new QTableView;

	QLabel* lblDenumire = new QLabel{ "Denumire oferta:" };
	QLabel* lblDestinatie = new QLabel{ "Destinatie oferta:" };
	QLabel* lblTip = new QLabel{ "Tip oferta:" };
	QLabel* lblPret = new QLabel{ "Pret oferta:" };

	QLineEdit* editDenumire;
	QLineEdit* editDestinatie;
	QLineEdit* editTip;
	QLineEdit* editPret;

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

	QPushButton* btnUndo;

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

	QPushButton* btnCosCRUGUI;
	QPushButton* btnCosReadOnlyGUI;

	CosGUICuLista* fereastraCos;
	HistogramGUI* fereastraFiguri;

	QPushButton* btnAddCos;
	QPushButton* btnDeleteCos;
	QPushButton* btnRandomCos;

	void initGUICmps();
	void connectSignalsSlots();
	void reloadList(std::vector<Oferta> oferte);
	void guiAddOferta();
	void guiModifyOferta();
	void guiDeleteOferta();
public:
	StoreGUIModele(OfertaStore& ctr) :ctr{ ctr } {
		initGUICmps();
		model = new MyTableModel{ ctr.getAllOferte() };
		lstV->setModel(model);
		tblV->setModel(model);
		connectSignalsSlots();
	}

};