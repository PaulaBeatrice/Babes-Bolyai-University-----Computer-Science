#pragma once

#include <QtWidgets/QWidget>
#include "ui_doctori.h"

class doctori : public QWidget
{
    Q_OBJECT

public:
    doctori(QWidget *parent = Q_NULLPTR);

private:
    Ui::doctoriClass ui;
};
