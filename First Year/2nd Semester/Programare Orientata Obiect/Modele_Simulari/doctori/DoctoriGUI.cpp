#include "DoctoriGUI.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void DoctoriGUI::initializeGUIComponents()
{
	QHBoxLayout* layoutmain = new QHBoxLayout;
	this->setLayout(layoutmain);

	QWidget* left = new QWidget;
	QVBoxLayout* layoutLeft = new QVBoxLayout;
	left->setLayout(layoutLeft);

	QWidget* form = new QWidget;
	QFormLayout* layoutForm = new QFormLayout;
	form->setLayout(layoutForm);
	editCNP = new QLineEdit;
	editCNP->setEnabled(false);
	layoutForm->addRow(labelCNP, editCNP);
	layoutLeft->addWidget(form);

	QWidget* formFiltrari = new QWidget;
	QFormLayout* layoutFiltrati = new QFormLayout;
	formFiltrari->setLayout(layoutFiltrati);

	editNume = new QLineEdit();
	layoutFiltrati->addRow(labelCriteriu2, editNume);
	butonFiltrareNume = new QPushButton("Filtreaza doctori dupa nume");
	layoutFiltrati->addWidget(butonFiltrareNume);

	editSectie1 = new QLineEdit();
	layoutFiltrati->addRow(labelCriteriu1, editSectie1);
	butonFiltrareSectie = new QPushButton("Filtreaza doctori dupa sectie");
	layoutFiltrati->addWidget(butonFiltrareSectie);

	butonFiltrareAmbele = new QPushButton("Filtreaza doctori dupa nume si sectie");
	layoutFiltrati->addWidget(butonFiltrareAmbele);

	layoutLeft->addWidget(formFiltrari);

	reloadData = new QPushButton("Reload data");
	layoutLeft->addWidget(reloadData);

	QWidget* right = new QWidget;
	QVBoxLayout* layoutRight = new QVBoxLayout;
	right->setLayout(layoutRight);

	this->listaDoctori = new QListWidget();
	layoutRight->addWidget(listaDoctori);



	layoutmain->addWidget(left);
	layoutmain->addWidget(right);
}


void DoctoriGUI::connectSignalsSlots()
{
	QObject::connect(butonFiltrareNume, &QPushButton::clicked, [&]() {
		string filterC = this->editNume->text().toStdString();
		this->reloadOfertaList(service.filtreazaNume(filterC));
		});

	QObject::connect(butonFiltrareSectie, &QPushButton::clicked, [&]() {
		string filterC = this->editSectie1->text().toStdString();
		this->reloadOfertaList(service.filreazaSectie(filterC));
		});
	QObject::connect(butonFiltrareAmbele, &QPushButton::clicked, [&]() {
		string filterC1 = this->editNume->text().toStdString();
		string filterC2 = this->editSectie1->text().toStdString();
		this->reloadOfertaList(service.filtreazaSectie_Nume(filterC2,filterC1));
		});

	QObject::connect(reloadData, &QPushButton::clicked, [&]() {
		this->reloadOfertaList(service.getAllDoctori());
		});

	QObject::connect(this->listaDoctori, &QListWidget::itemClicked, [&]() {
			int index = this->getSelectedIndex();
			auto elements = this->service.getAllDoctori();
			auto element = elements.at(index);
			this->editCNP->setText(QString::fromStdString(element.getCNP()));
	});
}

void DoctoriGUI::reloadOfertaList(vector<Doctor> doctori)
{
	this->listaDoctori->clear();
	for (const auto& doctor : doctori) {
		auto item = new QListWidgetItem(
			QString::fromStdString(
				 doctor.getNume() + " "
				+ doctor.getPrenume() + "-"
				+ doctor.getSectie() + " "
				));
		if (doctor.getConcediu() == 0)
			item->setBackground(Qt::green);
		else
			item->setBackground(Qt::red);
		this->listaDoctori->addItem(item);
	}
}