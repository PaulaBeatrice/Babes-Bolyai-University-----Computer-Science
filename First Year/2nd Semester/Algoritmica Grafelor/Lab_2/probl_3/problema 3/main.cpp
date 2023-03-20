/*
4. Pentru un graf dat să se afișeze pe ecran vârfurile descoperite de algoritmul BFS și distanța față de vârful sursă (arborele descoperit).
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;

#define INFINIT (1 << 30)
ifstream fin("1.in");
ofstream fout("1.out");
vector <int> G[101];
int n, m, n_sursa;
queue <int> Q;

struct varf {
    char color;
    int p, d;
}u[101];

void BFS(int s) {
    for (int i = 1; i <= n; i++) {
        u[i].color = 'a';
        u[i].d = INFINIT;
        u[i].p = -1;
    }
    fout << "Varfuri descoperite: " << s;
    u[s].color = 'g';
    u[s].d = 0;
    u[s].p = -1;
    Q.push(s);
    while (!Q.empty()) {
        int nod = Q.front();
        Q.pop();
        for (size_t i = 0; i < G[nod].size(); i++) {
            int vecin = G[nod][i];
            if (u[vecin].color == 'a') {
                fout << ", " << vecin;
                u[vecin].color = 'g';
                u[vecin].d = u[nod].d + 1;
                u[vecin].p = nod;
                Q.push(vecin);
            }
        }
        u[nod].color = 'n';
    }
}

int main() {

    fin >> n >> m;
    int a, b;
    for (int i = 1; i <= m; i++){
        fin >> a >> b;
        G[a].push_back(b);
    }
    cout << "Nodul sursa: ";
    cin >> n_sursa;
    BFS(n_sursa);
    fout << '\n' << "Distantele fata de varful " << n_sursa << '\n';
    for (int i = 1; i <= n; i++)
    {
        fout << i << ": ";
        if (u[i].d == INFINIT)
            fout << "-";
        else
            fout << u[i].d;
        fout << '\n';
    }
    return 0;
}