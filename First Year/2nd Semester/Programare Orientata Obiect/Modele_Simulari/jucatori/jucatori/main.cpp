#include "jucatori.h"
#include <QtWidgets/QApplication>
#include "Domain.h"
#include"Repo.h"
#include "Service.h"
#include "GUI.h"

int main(int argc, char *argv[])
{
    //teste
    teste_domain();
    teste_repo();
    test_service();

    QApplication a(argc, argv);
    RepoFile repoFile{ "jucatori.txt" };
    Service srv{ repoFile };
    GUI gui{ srv };
    gui.show();
    return a.exec();
}
