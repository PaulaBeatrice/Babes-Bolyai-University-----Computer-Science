#include "StoreGUI.h"

void StoreGUI::initializeGUIComponents(){
	QHBoxLayout* lyMain = new QHBoxLayout;
	this->setLayout(lyMain);
	QWidget* left = new QWidget;
	QVBoxLayout* lyLeft = new QVBoxLayout;
	left->setLayout(lyLeft);

	QWidget* form = new QWidget;
	QFormLayout* lyForm = new QFormLayout;
	form->setLayout(lyForm);

	editNume = new QLineEdit;
	editSoclu = new QLineEdit;;
	editPret = new QLineEdit;
	lyForm->addRow(lblNume, editNume);
	lyForm->addRow(lblSoclu, editSoclu);
	lyForm->addRow(lblPret, editPret);
	btnAdauga = new QPushButton("Adauga placa de baza");
	lyForm->addWidget(btnAdauga);

	btnReload = new QPushButton("Reload");
	lyForm->addWidget(btnReload);

	btnCalcPret = new QPushButton("Calc pret");
	lyForm->addWidget(btnCalcPret);

	lyLeft->addWidget(form);


	QWidget* right = new QWidget;
	QVBoxLayout* lyRight = new QVBoxLayout;
	right->setLayout(lyRight);

	this->lista_Proc = new QListWidget;
	lyRight->addWidget(lista_Proc);

	this->lista_Pl = new QListWidget;
	lyRight->addWidget(lista_Pl);

	lyMain->addWidget(left);
	lyMain->addWidget(right);
}

void StoreGUI::connectsignals()
{
	QObject::connect(btnAdauga, &QPushButton::clicked, this, &StoreGUI::guiAddPl);

	QObject::connect(this->lista_Proc, &QListWidget::itemSelectionChanged, [&]() {
		int index = this->getSelectedIndexPr();
		auto elements = this->srv.getAllPr();
		if (index >= elements.size()) {

			return;
		}
		auto element = elements.at(index);
		string soclu = element.getsocluPr();
		reloadPlList(this->srv.filtruSocluPl(soclu));
		});

	QObject::connect(btnReload, &QPushButton::clicked, [&]() {
		this->reloadPrList(srv.getAllPr());
		this->reloadPlList(srv.getAllPl());
		});

	QObject::connect(btnCalcPret, &QPushButton::clicked, [&]() {
		int index = this->getSelectedIndexPr();
		auto elements = this->srv.getAllPr();
		if (index >= elements.size()) {
			return;
		}
		auto element = elements.at(index);
		int pret = element.getpretPr();
		int index2 = this->getSelectedIndexPl();
		auto elements2 = this->srv.getAllPl();
		if (index >= elements2.size()) {
			return;
		}
		auto elementt = elements2.at(index2);
		int pret2 = elementt.getpretPl();
		int pr_final = pret + pret2;

		QMessageBox::information(this, "Info", QString::fromStdString("Pret:%1").arg(pr_final));
		});
}

void StoreGUI::reloadPrList(vector<Procesor> proc)
{
	this->lista_Proc->clear();
	for (const auto& elem : proc) {
		auto item = new QListWidgetItem(
			QString::fromStdString(
				elem.getNumePr() + " " + std::to_string(elem.getNrThr())
			)
		);
		this->lista_Proc->addItem(item);
	}
}

void StoreGUI::reloadPlList(vector<PlacaDeBaza> pl)
{
	this->lista_Pl->clear();
	for (const auto& elem : pl) {
		auto item = new QListWidgetItem(
			QString::fromStdString(
				elem.getNumePl() + " " 
			)
		);
		this->lista_Pl->addItem(item);
	}
}

void StoreGUI::guiAddPl()
{
	string nume = editNume->text().toStdString();
	string soclu = editSoclu->text().toStdString();
	int pret = editPret->text().toInt();

	editNume->clear();
	editSoclu->clear();
	editPret->clear();

	this->srv.addPl(nume, soclu, pret);
	this->reloadPlList(this->srv.getAllPl());
}
