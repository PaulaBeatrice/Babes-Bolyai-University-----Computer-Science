#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_jucatori.h"

class jucatori : public QMainWindow
{
    Q_OBJECT

public:
    jucatori(QWidget *parent = Q_NULLPTR);

private:
    Ui::jucatoriClass ui;
};
