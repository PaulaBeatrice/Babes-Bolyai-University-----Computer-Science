#pragma once

#include <qwidget.h>
#include <qpainter.h>
#include "Cos_Oferte.h"

class HistogramGUI : public QWidget, public Observer
{
private:
	CosOferte& cos;
public:
	HistogramGUI(CosOferte& cos) : cos{ cos }{
		cos.addObserver(this);
	};

	void update() override {
		repaint();
	}

	void paintEvent(QPaintEvent* ev) override {
		QPainter p{ this };
		srand(time(NULL));
		for (const auto& u : cos.getAllOferteCos()) {
			int x = rand() % 300;
			int y = rand() % 300;
			p.drawRect(x, y, 20, u.getPret() / 10);
		}
	}
};