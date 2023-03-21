#include "StoreGUI.h"

void StoreGUI::initializeGUIComponents()
{
	QHBoxLayout* lyMain = new QHBoxLayout;
	this->setLayout(lyMain);


	QWidget* left = new QWidget;
	QVBoxLayout* lyLeft = new QVBoxLayout;
	left->setLayout(lyLeft);

	QWidget* form = new QWidget;
	QFormLayout* lyForm = new QFormLayout;
	form->setLayout(lyForm);
	pr1 = new QLineEdit;
	pr2 = new QLineEdit;
	lyForm->addRow(lblP1, pr1);
	lyForm->addRow(lblP2, pr2);
	btnFiltruPr = new QPushButton("Filtrare pret");
	lyForm->addWidget(btnFiltruPr);

	supr1 = new QLineEdit;
	supr2 = new QLineEdit;
	lyForm->addRow(lblS1, supr1);
	lyForm->addRow(lblS2, supr2);
	btnFiltruSupr = new QPushButton("Filtrare suprafata");
	lyForm->addWidget(btnFiltruSupr);

	lyLeft->addWidget(form);
	QWidget* right = new QWidget;
	QVBoxLayout* lyRight = new QVBoxLayout;
	right->setLayout(lyRight);

	
	this->listaAp = new QListWidget();
	lyRight->addWidget(listaAp);

	lyMain->addWidget(left);
	lyMain->addWidget(right);
}

void StoreGUI::connectSignalsSlots()
{
	QObject::connect(this->listaAp, &QListWidget::itemSelectionChanged, [&]() {
		int index = this->getSelectedIndex();
		if (index < this->srv.getAllAp().size())
		{
			auto elements = this->srv.getAllAp();
			auto element = elements.at(index);
			int supr = element.getSupr();
			string str = element.getStr();
			int pr = element.getPr();
			this->srv.sterge(supr, str, pr);
			this->reloadList(this->srv.getAllAp());
		}
		});
	
	QObject::connect(btnFiltruPr, &QPushButton::clicked, [&]() {
		int pr1 = this->pr1->text().toInt();
		int pr2 = this->pr2->text().toInt();
		this->reloadList(srv.filtruPr(pr1,pr2));
		});
	
	QObject::connect(btnFiltruSupr, &QPushButton::clicked, [&]() {
		int supr1 = this->supr1->text().toInt();
		int supr2 = this->supr2->text().toInt();
		this->reloadList(srv.filtruPr(supr1, supr2));
		});
}

void StoreGUI::reloadList(vector<Apartament> ap)
{
	this->listaAp->clear();
	for (const auto& elem : ap) {
		auto item = new QListWidgetItem(
			QString::fromStdString(
				std::to_string(elem.getSupr()) + " "
				+ elem.getStr() + " "
				+ std::to_string(elem.getPr()
				)));
		this->listaAp->addItem(item);
	}
}

