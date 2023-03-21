#pragma once
#include <qwidget.h>
#include <qtimer.h>
#include <QtWidgets/QHBoxLayout>
#include <qpushbutton.h>
#include <qlistwidget.h>
#include <qtablewidget.h>
#include <qstring.h>
#include <qlabel.h>
#include <vector>
#include "Cos_Oferte.h"
#include "observer.h"

class CosGUICuLista : public QWidget, public Observer {
private:
	CosOferte& cos;
	QListWidget* lst;
	QPushButton* btn;
	QPushButton* btnRandom;
	void loadList(const std::vector<Oferta>& of) {
		lst->clear();
		for (auto& p : of) {
			auto item = new QListWidgetItem(
				QString::fromStdString(
					p.getDenumire() + " "
					+ p.getDestinatie() + " "
					+ p.getTip() + " "
					+ std::to_string(p.getPret()
					)));
			lst->addItem(item);
		}
	}
	void initGUI() {
		QHBoxLayout* ly = new QHBoxLayout;
		lst = new QListWidget;
		ly->addWidget(lst);
		btn = new QPushButton("Clear cos");
		ly->addWidget(btn);

		btnRandom = new QPushButton("Add random 3");
		ly->addWidget(btnRandom);
		setLayout(ly);
	}
	void connectSignals() {
		cos.addObserver(this);
		QObject::connect(btn, &QPushButton::clicked, [&]() {
			cos.emptyCos();
			loadList(cos.getAllOferteCos());
			});
		QObject::connect(btnRandom, &QPushButton::clicked, [&]() {
			cos.umple(3);
			loadList(cos.getAllOferteCos());
			});

	}
public:
	CosGUICuLista(CosOferte& cos) :cos{ cos } {
		initGUI();
		connectSignals();
		loadList(cos.getAllOferteCos());
	}

	void update() override {
		loadList(cos.getAllOferteCos());
	}
	~CosGUICuLista() {
		cos.removeObserver(this);
	}

};

class CosGUILabel :public QLabel, public Observer {
	CosOferte& cos;
public:
	CosGUILabel(CosOferte& cos) :cos{ cos } {
		setAttribute(Qt::WA_DeleteOnClose); //daca vreau sa se distruga fereastra imediat dupa inchidere
		cos.addObserver(this);
		update();
	}

	void update() /*override */ {
		setText("Cosul contine:" + QString::number(cos.getAllOferteCos().size()));
	}
	~CosGUILabel() {
		cos.removeObserver(this);
	}
};

class CosGUICuTabel : public QWidget, public Observer {
private:
	CosOferte& cos;
	QTableWidget* lst;
	QPushButton* btn;
	QPushButton* btnRefresh = new QPushButton{ "refresh" };
	QPushButton* btnRandom;

	void loadTable(const std::vector<Oferta>& of) {
		lst->clear();
		lst->setRowCount(of.size());
		lst->setColumnCount(3);
		for (int i = 0; i < of.size(); i++) {
			lst->setItem(i, 0, new QTableWidgetItem(of[i].getDenumire().c_str()));
			lst->setItem(i, 1, new QTableWidgetItem(of[i].getDestinatie().c_str()));
			lst->setItem(i, 2, new QTableWidgetItem(of[i].getTip().c_str()));
			lst->setItem(i, 3, new QTableWidgetItem(QString::number(of[i].getPret())));
		}
	}

	void initGUI() {
		QHBoxLayout* ly = new QHBoxLayout;
		lst = new QTableWidget;
		ly->addWidget(lst);
		btn = new QPushButton("Clear cos");
		ly->addWidget(btn);

		btnRandom = new QPushButton("Add random 4");
		ly->addWidget(btnRandom);
		ly->addWidget(btnRefresh);
		setLayout(ly);
	}
	void connectSignals() {
		cos.addObserver(this);
		QObject::connect(btn, &QPushButton::clicked, [&]() {
			cos.emptyCos();
			loadTable(cos.getAllOferteCos());
			});
		QObject::connect(btnRandom, &QPushButton::clicked, [&]() {
			cos.umple(4);
			loadTable(cos.getAllOferteCos());
			});
		QObject::connect(btnRefresh, &QPushButton::clicked, [this]() {
			loadTable(cos.getAllOferteCos());
			});
	}
public:
	CosGUICuTabel(CosOferte& cos) :cos{ cos } {
		initGUI();
		connectSignals();
		loadTable(cos.getAllOferteCos());
	}

	void update() override {
		loadTable(cos.getAllOferteCos());
	}

	~CosGUICuTabel() {
		cos.removeObserver(this);
	}

};
