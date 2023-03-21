#include "Lab_10.h"
#include <QtWidgets/QApplication>
#include "OfertaStoreGUI.h"
#include "Cos_GUI.h"
#include "Teste.h"
#include "HistrogramGUI.h"

#include <stdlib.h>
#include <crtdbg.h>
#include <iostream>
using std::cout;
using std::endl;


#include <QtWidgets/QApplication>
#include <QtWidgets/QLabel>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets/qtablewidget.h>
#include <QtWidgets/qmessagebox.h>
#include <qlist.h>
#include "StoreGUIModele.h"
#include <string>
#include <list>

#include <QTreeView>
//#include <QDirModel>
#include <QListView>
#include <QStringListModel>
#include <QVBoxLayout>
#include <QComboBox>
#include <qdebug.h>

int ROWS = 10000;
int COLS = 100;

class TestTableModel :public QAbstractTableModel {
public:
	int rowCount(const QModelIndex& parent = QModelIndex()) const override {
		return ROWS;
	}
	int columnCount(const QModelIndex& parent = QModelIndex()) const override {
		return COLS;
	}
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
		if (role == Qt::DisplayRole) {
			qDebug() << "row:" << index.row() << " col:" << index.column();
			return QString("Row%1, Column%2").arg(index.row()).arg(index.column());
		}
		return QVariant{};
	}
};

void createTableModelV() {
	QTableView* tV = new QTableView();
	TestTableModel* model = new TestTableModel();
	tV->setModel(model);
	tV->show();
}

void create2Views() {
	QTableView* tV = new QTableView();
	TestTableModel* model = new TestTableModel();
	tV->setModel(model);
	tV->show();

	QListView* tVT = new QListView();
	tVT->setUniformItemSizes(true);
	tVT->setModel(model);
	tVT->show();
}


void createLargeTable() {
	QTableWidget* tV = new QTableWidget(ROWS, COLS);
	qDebug() << "Start adding elements";
	for (int i = 0; i < ROWS; i++) {
		for (int j = 0; j < COLS; j++) {
			QTableWidgetItem* newItem = new QTableWidgetItem(
				QString("Row%1, Column%2").arg(i).arg(j));
			tV->setItem(i, j, newItem);
		}
	}
	qDebug() << "Finished adding elements";
	tV->show();
}

void createTree() {
	QTreeView* tV = new QTreeView();
	tV->show();
}

void createListComboSharedModel() {
	QWidget* w = new QWidget;
	QVBoxLayout* wl = new QVBoxLayout;
	w->setLayout(wl);
	QListView* lv = new QListView(w);
	wl->addWidget(lv);
	QComboBox* qC = new QComboBox;
	wl->addWidget(qC);
	QStringListModel* model = new QStringListModel(w);
	QStringList sl;
	sl << "item1" << "item2" << "item4" << "item5";
	model->setStringList(sl);
	lv->setModel(model);
	qC->setModel(model);
	w->show();
}



int main(int argc, char* argv[])
{
	OfertaRepoFile rep{ "oferte.txt" };
	Validator val;
	OfertaStore ctr{ rep , val};
	QApplication a(argc, argv);
	StoreGUIModele gui{ ctr };
	gui.show();
	return a.exec();

}

