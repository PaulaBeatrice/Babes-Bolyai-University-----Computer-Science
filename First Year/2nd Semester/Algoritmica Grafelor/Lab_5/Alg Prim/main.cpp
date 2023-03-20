#include<iostream>
#include<fstream>
#include<vector>
#include<queue>
#define INFINIT 1000000000
#define NIL 1000000000

using namespace std;
ifstream fin("1.in");
ofstream fout("1.out");

int n, m, vf;
vector < pair < int, int> > G[101];
int D[101];
int P[101];

struct comparare_distante
{
    bool operator()(int x, int y)
    {
        return D[x] > D[y];
    }
};

priority_queue < int, vector < int >, comparare_distante > Q;

bool exist(int x)
{
    priority_queue < int, vector < int >, comparare_distante > Q2= Q;
    bool ok = false;
    for(int i = 1; i < Q.size(); i++)
    {
        int y = Q2.top();
        if(y == x)
            ok = true;
    }
    return ok;
}

void Prim(int k)
{
    for(int i = 0; i < n; i++)
    {
        D[i] = INFINIT;
        P[i] = NIL;
    }
    D[k] = 0;
    P[k] = NIL;
    for(int i = 0; i < n; i++)
        Q.push(i);
    while(!Q.empty())
    {
        int nod = Q.top();
        Q.pop();
        for(int i = 0; i < G[nod].size(); i++)
        {
            int vecin = G[nod][i].first;
            int cost = G[nod][i].second;
           // cout << vecin << " " << cost << " " << D[vecin]<< endl;
            if(cost < D[vecin])
            {
                D[vecin] = cost;
                P[vecin] = nod;
              //  cout << vecin << " " << P[vecin] << " " << endl;
            }
            //cout << cost << " " << D[vecin] << endl;
            fout << nod << " " << Q.top();
        }

    }
}


int main ()
{
    fin >> n >> m;
    for(int i = 0; i < m; i++)
    {
        int a, b, c;
        fin >> a >> b >> c;
        G[a].push_back(make_pair(b,c));
    };
    Prim(0);
   /* for(int i = 0; i < n ; i++)
    {
        if(D[i] != INFINIT)
            fout << D[i] << " ";
        else
            fout << "INF ";
    }*/
    return 0;
}

