#pragma once
#include<QAbstractListModel>
#include "oferta.h"
#include <vector>
#include <qdebug.h>

class MyListModel :public QAbstractListModel {
	std::vector<Oferta> oferte;
public:
	MyListModel(const std::vector<Oferta>& oferte) :oferte{ oferte } {
	}

	int rowCount(const QModelIndex& parent = QModelIndex()) const override {
		return oferte.size();
	}

	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
		if (role == Qt::DisplayRole) {
			qDebug() << "get row:" << index.row();
			auto sp = oferte[index.row()].getDenumire();
			return QString::fromStdString(sp);
		}
		if (role == Qt::UserRole) {
			auto tp = oferte[index.row()].getTip();
			return QString::fromStdString(tp);
		}
		if (role == Qt::BackgroundRole) {
			if (oferte[index.row()].getTip()[0] == 'A') {
				return QColor(Qt::green);
			}
		}
		return QVariant{};
	}
};