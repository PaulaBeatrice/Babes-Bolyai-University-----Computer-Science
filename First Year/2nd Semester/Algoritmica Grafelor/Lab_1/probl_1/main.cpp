#include <iostream>
#include <fstream>
#define INFINIT 1000000000

using namespace std;

ifstream fin("1.in");
ofstream fout("1.out");

ifstream fin11("11.in");
ofstream fout11("11.out");

ifstream fin21("21.in");
ofstream fout21("21.out");

ifstream fin22("22.in");
ofstream fout22("22.out");

ifstream fin23("23.in");
ofstream fout23("23.out");

ifstream fin24("24.in");
ofstream fout24("24.out");

int M[101][101], n_varfuri, viz[101];

void DFS(int x)
{
    viz[x] = 1;
    for(int i = 1; i <= n_varfuri ; i++)
        if(M[x][i] == 1 && viz[i] == 0)
            DFS(i);
}

int A[101][101], n;

int main()
{
    int comanda;
    cout << "1. Lista muchii -> matrice adiacenta\n";
    cout << "11. matrice adiacenta -> matrice incidenta\n";
    cout << "21. nr varfuri izolate\n";
    cout << "22. verificare graf regular\n";
    cout << "23. verificare grad conex\n";
    cout << "24. matricea distantelor\n";

    cout << "Introduceti comanda: ";
    cin >> comanda;
    if(comanda == 1)
    {
        int nr_muchii, Matrice_Adiacenta[101][101] = {0} , a, b, nr_varfuri;
        fin >> nr_varfuri;
        fin >> nr_muchii;
        for(int i = 1; i <= nr_muchii; i++)
        {
            fin >> a >> b;
            Matrice_Adiacenta[a][b] = 1;
            Matrice_Adiacenta[b][a] = 1;
        }
        for(int i = 1; i <= nr_varfuri; i++)
        {
            for(int j = 1; j <= nr_varfuri; j++)
                fout << Matrice_Adiacenta[i][j] << " ";
            fout << endl;
        }
    }
    if(comanda == 11)
    {
        int matr_adiacenta[101][101], matr_incidenta[101][101] = {0}, nrmuchii = 0, nr_varfuri;
        fin11 >> nr_varfuri;
        for(int i = 1; i <= nr_varfuri; i++)
            for(int j = 1; j <= nr_varfuri; j++)
                {
                    fin11 >> matr_adiacenta[i][j];
                    if(matr_adiacenta[i][j] == 1)
                        nrmuchii++;
                }
        nrmuchii = nrmuchii / 2;
        int contor = 0;
        for(int i = 1; i <= nr_varfuri; i++)
            for(int j = 1;j <= nr_varfuri; j++)
                if(matr_adiacenta[i][j] == 1)
                {
                    contor++;
                    matr_incidenta[i][contor] = 1;
                    matr_incidenta[j][contor] = 1;
                }
        for(int i = 1; i <= nr_varfuri; i++)
        {
            for(int j = 1; j <= nrmuchii; j++)
                fout11 << matr_incidenta[i][j] << " ";
            fout11 << endl;
        }
    }

    if(comanda == 21)
    {
        int matr_adiac[101][101], nr_vf, nr_vf_izolate = 0;
        fin21 >> nr_vf;
        for(int i = 1; i <= nr_vf; i++)
            for(int j = 1; j <= nr_vf; j++)
                fin21 >> matr_adiac[i][j];
        for(int i = 1; i <= nr_vf; i++)
        {
            int izolat = 1;
            for(int j = 1; j <= nr_vf; j++)
                if(matr_adiac[i][j] == 1)
                    izolat = 0;
            if(izolat == 1)
                nr_vf_izolate++;
        }
        fout21 << "Nr de varfuri izolate: " << nr_vf_izolate;
    }

    if(comanda == 22)
    {
        int Matr_adiac[101][101], nr_vf, regular = 1;
        fin22 >> nr_vf;
        for(int i = 1; i <= nr_vf; i++)
            for(int j = 1; j <= nr_vf; j++)
                fin22 >> Matr_adiac[i][j];
        int grad_anterior = 0, grad_curent = 0;
        for(int i = 1; i <= nr_vf; i++)
            if (Matr_adiac[1][i] == 1)
                grad_anterior++;
        for(int i = 2; i <= nr_vf; i++)
        {
            grad_curent = 0;
            for(int j = 1; j <= nr_vf; j++)
                if(Matr_adiac[i][j] == 1)
                    grad_curent++;
            if(grad_anterior != grad_curent)
                regular = 0;
            grad_anterior = grad_curent;
        }
        if(regular == 1)
            fout22 << "Graful este regular";
        else
            fout22 << "Graful nu este regular";
    }

    if(comanda == 23)
    {
        int conex = 1;
        fin23 >> n_varfuri;
        for(int i = 1; i <= n_varfuri; i++)
            for(int j = 1; j <= n_varfuri; j++)
                fin23 >> M[i][j];
        DFS(1);
        for(int i = 1; i <= n_varfuri; i++)
            if(viz[i] == 0)
                conex = 0;
        if(conex == 0)
            fout23 << "Graful nu este conex";
        else
            fout23 << "Graful este conex";
    }

    if(comanda == 24)
    {
        ///citire lista de muchii
        int numar_muchii, a1, a2;
        fin24 >> n;
        for(int i = 1 ; i <= n ; ++i)
        {
            for(int j = 1 ; j <= n ; ++j)
                A[i][j] = INFINIT;
            A[i][i] = 0;
        }
        fin24 >> numar_muchii;
        for(int i = 1; i <= numar_muchii; i++)
        {
            fin24 >> a1 >> a2;
            A[a1][a2] = 1;
            A[a2][a1] = 1;
        }
        for(int k = 1; k <= n; k++)
            for(int i = 1; i <= n; i++)
                for(int j = 1; j <= n; j++)
                    if(A[i][k] != INFINIT && A[k][j] != INFINIT)
                        if(A[i][j] > A[i][k] + A[k][j])
                            A[i][j] = A[i][k] + A[k][j];
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(A[i][j] == INFINIT)
                    fout24 << -1 << " ";
                else
                    fout24 << A[i][j] << " ";
            }
            fout24 << endl;
        }
    }
    return 0;
}
