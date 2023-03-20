#include <iostream>
#include <fstream>
#include <limits.h>
#include <queue>
#include <string.h>
#define INFINIT 100000007
using namespace std;

int V, E, x, y, w;
int G[101][101];
queue<int> Q;

bool BFS(int G[][101], int s, int t, int T[])
{
    bool Viz[101];
    memset(Viz, 0, sizeof(Viz));
    Q.push(s);
    Viz[s] = true;
    T[s] = -1;
    while (!Q.empty())
    {
        int u = Q.front();
        Q.pop();
        for (int vf = 0; vf < V; vf++)
        {
            if (Viz[vf] == false && G[u][vf] > 0)
            {
                if (vf == t)
                {
                    T[vf] = u;
                    return true;
                }
                Q.push(vf);
                T[vf] = u;
                Viz[vf] = true;
            }
        }
    }
    return false;
}

int FordFulkerson(int G[][101], int s, int t)
{
    int i, vf, T[101], flux_maxim = 0;
    while (BFS(G, s, t, T))
    {
        int dr_flux = INFINIT;
        for (vf = t; vf != s; vf = T[vf])
        {
            i = T[vf];
         //   cout << vf << " ";
            dr_flux = min(dr_flux, G[i][vf]);
        }
        cout << endl;
        for (vf = t; vf != s; vf = T[vf])
        {
            i = T[vf];
            G[i][vf] -= dr_flux;
            G[vf][i] += dr_flux;
        }
        flux_maxim += dr_flux;
    }
    return flux_maxim;
}


int main(int argc, char** argv)
{
    ifstream fin("1.in");
    ofstream fout("1.out");
    fin >> V >> E;
    for (int i = 1; i <= E; i++)
    {
        fin >> x >> y >> w;
        G[x][y] = w;
    }
    fout << FordFulkerson(G, 0, V - 1);
    return 0;
}
