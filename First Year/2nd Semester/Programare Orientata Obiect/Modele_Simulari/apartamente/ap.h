#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_ap.h"

class ap : public QMainWindow
{
    Q_OBJECT

public:
    ap(QWidget *parent = Q_NULLPTR);

private:
    Ui::apClass ui;
};
