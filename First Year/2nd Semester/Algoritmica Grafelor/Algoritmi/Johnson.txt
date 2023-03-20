#include <iostream>
#include <fstream>
#include <queue>
#include <vector>
#include <stdlib.h>
#define INFINIT 100000007
using namespace std;
ifstream fin("1.in");
ofstream fout("1.out");
int n, m, d[101], p[101], dist[101][101], h[101];
bool V[101];

struct compara {
    bool operator()(int u, int v)
    {
        return d[u] > d[v];
    }
};
vector< pair< int, int > > G[101];
priority_queue <int, vector<int>, compara> Q;

void Dijkstra(int sursa);
bool Bellman_ford(int sursa);
void initializare();
bool relaxare(int u, int v, int w);
void reponderare();
void Johnson();
void afisare();

void Johnson()
{
    if(Bellman_ford(n) == false)
    {
        fout << "-1\n";
        exit(0);
    }
    for(int i = 0; i <= n; i++)
        h[i] = d[i];
    reponderare();
    for(int i = 0; i < n; i++)
    {
        Dijkstra(i);
        for(int j = 0; j < n; j++)
            if(d[j] - h[i] + h[j] < dist[i][j])
                dist[i][j] = d[j] - h[i] + h[j];
    }
}

void afisare()
{
    for(int u = 0; u < n; u++)
        for(int i = 0; i < G[u].size(); i++)
        {
            int v = G[u][i].first;
            int w = G[u][i].second;
            fout << u << " " << v << " " << w << '\n';
        }
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(dist[i][j] == INFINIT)
                fout << "INF ";
            else
                fout << dist[i][j]<<" ";
        }
        fout<<'\n';
    }
}

void reponderare()
{
    for (int u = 0; u <= n; u++)
        for (int i = 0; i < G[u].size(); i++)
        {
            int v = G[u][i].first;
            int w = G[u][i].second;
            G[u][i].second = w + h[u] - h[v];
        }
}

bool cmp(int u, int v)
{
    return d[u] > d[v];
}

void Dijkstra(int k)
{
    initializare();
    d[k] = 0;
    V[k] = true;
    Q.push(k);
    while(!Q.empty())
    {
        int u = Q.top();
        Q.pop();
        V[u] = false;
        for(int i = 0; i < G[u].size(); i++)
        {
            int vec = G[u][i].first;
            int cost = G[u][i].second;
            if(relaxare(u, vec, cost))
                if(V[vec] == false)
                {
                    Q.push(vec);
                    V[vec] = true;
                }
        }
    }
}

bool Bellman_ford(int s)
{
    initializare();
    d[s] = 0;
    for(int k = 1; k < n; k++)
        for(int i = 0; i < n; i++)
            for(int j = 0; j < G[i].size(); j++)
            {
                int v = G[i][j].first;
                int w = G[i][j].second;
                relaxare(i,v,w);
            }
    for(int i = 0; i < n; i++)
        for(int j = 0; j < G[i].size(); j++)
        {
            int v = G[i][j].first;
            if(d[v] > d[i] + G[i][j].second)
                return false;
        }
    return true;
}

void initializare()
{
    for(int i = 0; i < n; i++)
        d[i] = INFINIT, p[i] = 0;
}

bool relaxare(int u, int v, int w)
{
    if(d[v] > d[u] + w)
    {
        d[v] = d[u] + w;
        p[v] = u;
        return true;
    }
    return false;
}

int main()
{
    int a, b, w;
    fin >> n >> m;
    for(int i = 0; i < m; i++)
    {
        fin >> a >> b >> w;
        G[a].push_back(make_pair(b,w));
    }
    for (int i = 0; i < m; i++)
        G[n].push_back(make_pair(i, 0));

    ///initializare distante
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            dist[i][j] = INFINIT;

    Johnson();
    afisare();
    fin.close();
    fout.close();
    return 0;
}
