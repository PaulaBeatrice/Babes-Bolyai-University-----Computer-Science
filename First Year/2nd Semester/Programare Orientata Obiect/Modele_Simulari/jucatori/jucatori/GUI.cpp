#include "GUI.h"

void GUI::initializeGUIComponents()
{

	QHBoxLayout* lyMain = new QHBoxLayout;
	this->setLayout(lyMain);
	QWidget* left = new QWidget;
	QVBoxLayout* lyLeft = new QVBoxLayout;
	left->setLayout(lyLeft);

	QWidget* form = new QWidget;
	QFormLayout* lyForm = new QFormLayout;
	form->setLayout(lyForm);
	editNume = new QLineEdit;
	editPct = new QLineEdit;
	lyForm->addRow(lblNume, editNume);
	lyForm->addRow(lblPct, editPct);
	lyForm->addWidget(radioF);
	lyForm->addWidget(radioSMF);
	lyForm->addWidget(radioSF);

	btnModificaPct = new QPushButton("Modifica pct");
	lyForm->addWidget(btnModificaPct);

	lyLeft->addWidget(form);

	btnRecalculare = new QPushButton("Recalculare rank");
	lyForm->addWidget(btnRecalculare);

	nrJucatori = new QSlider;
	nrJucatori->setOrientation(Qt::Horizontal);
	nrJucatori->setMinimum(1);
	nrJucatori->setMaximum(this->srv.getAllJucatori().size());
	nrJucatori->tickInterval();

	btnStergeJuc = new QPushButton("Sterge Jucatori");

	lyForm->addWidget(nrJucatori);
	lyForm->addWidget(btnStergeJuc);

	QWidget* right = new QWidget;
	QVBoxLayout* lyRight = new QVBoxLayout;
	right->setLayout(lyRight);
	this->listaJucatori = new QListWidget();
	lyRight->addWidget(listaJucatori);

	lyMain->addWidget(left);
	lyMain->addWidget(right);
	
}

void GUI::connectSignalsSlots()
{
	QObject::connect(btnModificaPct, &QPushButton::clicked, [&]() {
		string numee = editNume->text().toStdString();
		bool ok = true;
		vector<Jucator> all = this->srv.getAllJucatori();
		for (auto i : all)
			if (i.getNume() == numee)
					ok = true;
			
		if (ok == true) {
			if (this->radioF->isChecked())
			{
				int scor = editPct->text().toInt();
				string nume = editNume->text().toStdString();
				if(scor < 0)
					QMessageBox::information(this, "Info", QString::fromStdString("Scorul nu poate fi negativ."));
				else
				{
					this->srv.ModificaPunctaj(scor, nume);
				}
			}
			if (this->radioSMF->isChecked())
			{
				int scor = editPct->text().toInt();
				string nume = editNume->text().toStdString();
				if (scor < 0)
					QMessageBox::information(this, "Info", QString::fromStdString("Scorul nu poate fi negativ."));
				else
				{
					this->srv.ModificaPunctaj(int(scor *75 / 100), nume);
				}
			}
			if (this->radioSF->isChecked())
			{
				int scor = editPct->text().toInt();
				string nume = editNume->text().toStdString();
				if (scor < 0)
					QMessageBox::information(this, "Info", QString::fromStdString("Scorul nu poate fi negativ."));
				else
				{
					this->srv.ModificaPunctaj(int(scor/2), nume);
				}
			}
		}
		else
			QMessageBox::information(this, "Info", QString::fromStdString("Nu exista acest jucator."));

		});

	QObject::connect(btnRecalculare, &QPushButton::clicked, [&]() {
		this->reloadList(srv.sortByPct());
		});

	QObject::connect(btnStergeJuc, &QPushButton::clicked, [&]() {
		int nr = nrJucatori->value();
		if(nr > this->srv.getAllJucatori().size() / 2)
			QMessageBox::information(this, "Info", QString::fromStdString("Nr este prea mare."));
		else
		{
			int i = this->srv.sortByPct().size() - 1;
			vector<Jucator> juc = this->srv.sortByPct();
			while (nr > 0)
			{
				this->srv.stergere(juc.at(i).getNume());
				i--;
				nr--;
			}
		}
		});

}

void GUI::reloadList(vector<Jucator> juc)
{
	this->listaJucatori->clear();
	for (const auto& elem : juc) {
		auto item = new QListWidgetItem(
			QString::fromStdString(
				elem.getNume() + " "
				+ elem.getTara() + " "
				+ std::to_string(elem.getPct()) + " "
				+ std::to_string(elem.getRnk()
				)));
		this->listaJucatori->addItem(item);
	}
}
