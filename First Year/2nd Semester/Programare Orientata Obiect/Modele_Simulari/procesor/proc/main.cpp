#include "proc.h"
#include <QtWidgets/QApplication>
#include "StoreGUI.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    ProcRepoFile repo1{ "proc.txt" };
    PlRepoFile repo2{ "pl.txt" };
    Store srv{ repo1,repo2 };
    StoreGUI gui{ srv };
    gui.show();
    return a.exec();
}
