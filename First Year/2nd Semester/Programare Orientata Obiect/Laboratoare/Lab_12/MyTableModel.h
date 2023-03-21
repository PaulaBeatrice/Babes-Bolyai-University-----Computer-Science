#pragma once
#include <QAbstractTableModel>
#include "oferta.h"
#include <vector>
#include <qdebug.h>

using namespace std;

class MyTableModel :public QAbstractTableModel {
	std::vector<Oferta> oferte;
public:
	MyTableModel(const std::vector<Oferta>& oferte) :oferte{ oferte } {
	}

	int rowCount(const QModelIndex& parent = QModelIndex()) const override {
		return oferte.size();
	}
	int columnCount(const QModelIndex& parent = QModelIndex()) const override {
		return 4;
	}

	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
		qDebug() << "row:" << index.row() << " col:" << index.column() << " role" << role;
		if (role == Qt::ForegroundRole) {
			Oferta p = oferte[index.row()];
			if (p.getPret() > 500) {
				return QBrush{ Qt::red };
			}
			else {
				return QBrush{ Qt::green };
			}
		}
		if (role == Qt::DisplayRole) {

			Oferta p = oferte[index.row()];
			if (index.column() == 0) {
				return QString::fromStdString(p.getDenumire());
			}
			else if (index.column() == 1) {
				return QString::fromStdString(p.getDestinatie());
			}
			else if (index.column() == 2) {
				return QString::fromStdString(p.getTip());
			}
			else if (index.column() == 3) {
				return QString::number(p.getPret());
			}
		}

		return QVariant{};
	}

	void setOferte(const vector<Oferta>& oferte) {
		this->oferte = oferte;
		auto topLeft = createIndex(0, 0);
		auto bottomR = createIndex(rowCount(), columnCount());
		emit dataChanged(topLeft, bottomR);
	}
};