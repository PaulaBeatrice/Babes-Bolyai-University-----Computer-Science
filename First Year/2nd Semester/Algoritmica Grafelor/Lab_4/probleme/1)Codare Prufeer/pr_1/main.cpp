#include <iostream>
#include <fstream>
using namespace std;
ifstream fin("1.in");
ofstream fout("1.out");

int P[100], n, len, C[100], nr, v[100];

int frunza_min()
{
    bool gasit;
    for(int i = 0 ; i < len ; i++)
    {
        if(P[i] != -1)
        {  //daca nodul mai exista in arbore
            gasit = false;
            for(int j = 0 ; j < len ; j++)
                if(i == P[j])
                    gasit = true;
            if(gasit == false)
                return i;
        }
    }
}

void Prufer()
{
    while(n != 1)
    {
        int x = frunza_min();
        C[nr++] = P[x];
        P[x] = -1;
        n--;
    }
}
int main()
{
    fin >> n;
    len = n;
    for(int i = 0 ; i < n ; i++)
        fin >> P[i];
    Prufer();
    fout << nr << '\n';
    for(int i = 0 ; i < nr ; i++)
        fout << C[i] << " ";
    return 0;
}
