#include <fstream>
#include <algorithm>
using namespace std;

ifstream fin("1.in");
ofstream fout("1.out");

int n , m , x , y , w , T[10001] , rez , cnt;

struct poz
{
    int i , j , c;
}P[10001];
poz A[10001];

int uneste(int a , int b)
{
    T[a] = T[b];
}

int radacina(int a)
{
    if(a == T[a])
        return a;
    else
        return T[a] = radacina(T[a]);
}

void Kruskal()
{
    int u , v;
    for(int i = 1 ; i <= m ; i++)
    {
        u = radacina(P[i].i);
        v = radacina(P[i].j);
        if(u != v)
        {
            rez += P[i].c;
            A[++cnt] = P[i];
            uneste(u , v);
        }
    }
}

int comp(poz a , poz b)
{
    return a.c < b.c;
}

int comp2(poz a , poz b)
{
    if (a.i < b.i)
        return 1;
    else
        if(a.i == b.i && a.j < b.j)
            return 1;
    else
        return 0;
}

int main()
{
    fin >> n >> m;
    for(int i = 1 ; i <= m ; i++)
    {
        fin >> x >> y >> w;
        P[i].i = x;
        P[i].j = y;
        P[i].c = w;
    }
    sort(P + 1 , P + m + 1 , comp);
    for(int i = 1 ; i <= n ; i++)
        T[i] = i;
    Kruskal();
    fout << rez << '\n';
    fout << n - 1  << '\n';
    sort(A + 1, A + n, comp2);
    for(int i = 1 ; i < n ; i++)
        fout << A[i].i << " " << A[i].j << '\n';
}

