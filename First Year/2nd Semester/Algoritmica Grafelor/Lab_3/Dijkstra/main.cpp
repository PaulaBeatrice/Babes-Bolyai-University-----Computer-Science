#include<iostream>
#include<fstream>
#include<vector>
#include<queue>

using namespace std;
ifstream fin("1.in");
ofstream fout("1.out");

#define INFINIT 1000000000

int n, m, vf;
vector < pair < int, int> > G[101];
int D[101];
bool V[101];

struct comparare_distante
{
    bool operator()(int x, int y)
    {
        return D[x] > D[y];
    }
};

priority_queue < int, vector < int >, comparare_distante > Q;

void Dijkstra(int k)
{
    for(int i = 0; i < n; i++)
        D[i] = INFINIT;
    Q.push(k);
    D[k] = 0;
    V[k] = false;
    while(!Q.empty())
    {
        int nod = Q.top();
        Q.pop();
        V[nod] = false;
        for(int i = 0; i < G[nod].size(); i++)
        {
            int vecin = G[nod][i].first;
            int cost = G[nod][i].second;
            if(D[nod] + cost < D[vecin])
            {
                D[vecin] = D[nod] + cost;
                if(V[vecin] == false)
                {
                    V[vecin] = true;
                    Q.push(vecin);
                }
            }
        }
    }
}



int main ()
{
    fin >> n >> m >> vf;
    for(int i = 0; i < m; i++)
    {
        int a , b , c;
        fin >> a >> b >> c;
        G[a].push_back(make_pair(b,c));
    };
    Dijkstra(vf);
    for(int i = 0; i < n ;i++)
    {
        if(D[i] != INFINIT)
            fout << D[i] << " ";
        else
            fout << "INF ";
    }
    //fin.close();
    //fout.close();
    return 0;
}
