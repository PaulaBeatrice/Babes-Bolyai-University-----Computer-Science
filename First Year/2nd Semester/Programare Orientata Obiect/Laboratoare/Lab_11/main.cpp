#include "Lab_10.h"
#include <QtWidgets/QApplication>
#include "OfertaStoreGUI.h"
#include "Teste.h"

#include <stdlib.h>
#include <crtdbg.h>
#include <iostream>
using std::cout;
using std::endl;

int main(int argc, char *argv[])
{
    test_all();
    QApplication a(argc, argv);
    OfertaRepoFile repoFile{ "oferte.txt" };
    Validator val;
    OfertaStore srv{ repoFile, val };
    OfertaStoreGUI gui{ srv };

 //   Lab_10 w;
 //   w.show();
    gui.show();
    return a.exec();
}
