#include <iostream>
#include <fstream>
using namespace std;

ifstream fin ("1.in");
ofstream fout ("1.out");

int V,E, Adj[1021][1021], Viz[1021];

void stergeMuchie(int x, int y)
{
    Adj[x][y] = Adj[y][x] = 0;
}

void addMuchie(int x, int y)
{
    Adj[x][y] = Adj[x][y] = 1;
}

int DFS(int start)
{
    Viz[start] = 1;
    int c = 1;
    for(int i = 0; i < V; i++)
    {
        if(Viz[i] == 0 && Adj[start][i] == 1)
            c += DFS(i);
    }
    return c;
}

bool estePunte(int x, int y)
{
    for(int i = 0 ; i < V ; i++)
        Viz[i] = 0;
    int nr = 0;
    for(int j = 0 ; j < V ; j++)
        if(Adj[x][j] == 1)
            nr++;
    if(nr==1)
        return false;  //daca x are un singur vecin muchia nu este punte
    int cnt1 = DFS(x);
    stergeMuchie(x,y);
    for(int i = 0; i < V; i++)
        Viz[i] = 0;
    int cnt2 = DFS(x);
    addMuchie(x , y);
    if(cnt1 == cnt2)
        return false;  //muchia x-y nu este muchie punte
    return true;  //muchia x-y este muchie punte (daca am elimina-o am obtine 2 componente conexe in graf)

}

void Fleury(int start)
{
    int gasit = 0;
    for(int x = 0; x < V; x++)
    {
        if(Adj[start][x] == 1)
        {
            gasit = 1;
            if(!estePunte(start, x))
            {
                fout << start << " ";
                stergeMuchie(start, x);
                Fleury(x);
            }
        }
    }
    if(gasit == 0)
        fout << start;
}
int main()
{
    int x, y;
    fin >> V >> E;
    for(int i = 1 ; i <= E ; i++)
    {
        fin >> x >> y;
        Adj[x][y] = Adj[y][x] = 1;
    }
    Fleury(0);
    return 0;
}
