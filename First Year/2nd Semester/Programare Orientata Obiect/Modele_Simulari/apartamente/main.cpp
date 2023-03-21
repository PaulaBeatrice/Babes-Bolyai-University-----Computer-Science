#include "ap.h"
#include <QtWidgets/QApplication>
#include"StoreGUI.h"
#include"Teste.h"
int main(int argc, char *argv[])
{
    test_all();
    QApplication a(argc, argv);
    RepoFile repoFile{ "apartamente.txt" };
    Store srv{ repoFile };
    StoreGUI gui{ srv };
    gui.show();
    return a.exec();
}
