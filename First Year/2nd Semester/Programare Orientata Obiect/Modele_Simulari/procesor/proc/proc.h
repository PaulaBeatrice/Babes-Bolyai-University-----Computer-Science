#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_proc.h"

class proc : public QMainWindow
{
    Q_OBJECT

public:
    proc(QWidget *parent = Q_NULLPTR);

private:
    Ui::procClass ui;
};
