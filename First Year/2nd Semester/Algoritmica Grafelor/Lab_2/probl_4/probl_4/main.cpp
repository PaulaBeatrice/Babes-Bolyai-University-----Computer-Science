/*
5. Pentru un graf dat să se afișeze pe ecran vârfurile descoperite de apelul recursiv al procedurii DFS_VISIT(G, u) (apadurea descoperită de DFS).
*/
#include <iostream>
#include <vector>
#include <queue>
#include <fstream>

using namespace std;

ifstream fin("1.in");
ofstream fout("1.out");

int n, m, timp;
vector <int> G[101];

struct varf {
    char color;
    int p, d, f;
}u[101];

void DFS_VISIT(int nod) {
    timp++;
    u[nod].d = timp;
    u[nod].color = 'g';
    for (size_t i = 0; i < G[nod].size(); i++) {
        int vecin = G[nod][i];
        if (u[vecin].color == 'a') {
            u[vecin].p = nod;
            DFS_VISIT(vecin);
        }
    }
    u[nod].color = 'n';
    timp++;
    u[nod].f = timp;
}

void DFS() {
    for (int i = 1; i <= n; i++)
    {
        u[i].color = 'a';
        u[i].p = -1;
    }
    timp = 0;
    for (int i = 1; i <= n; i++) {
        int tmp = timp + 1;
        if (u[i].color == 'a')
            DFS_VISIT(i);
        if (tmp != timp + 1) {
            for (int j = 1; j <= n; j++)
                if (u[j].d >= tmp && u[j].f <= timp)
                    fout << j << ", ";
            fout << '\n';
        }
    }
}

int main() {
    fin >> n >> m;
    int a, b;
    for (int i = 1; i <= m; i++)
    {
        fin >> a >> b;
        G[a].push_back(b);
    }
    fout << "Nodurile sunt descoperite in urmatoarea ordine: ";
    DFS();
    return 0;
}