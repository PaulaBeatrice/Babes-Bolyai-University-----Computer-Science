#include <fstream>
#include <queue>
#include <vector>

using namespace std;

ifstream fin("1.in");
ofstream fout("1.out");

typedef struct
{
    int h, e;
} nod;

queue <int> Q;
vector< vector< pair<int, int> > > Rezid;
vector < bool > Vf;
vector<nod> Det_Vf;
int n, x, y, c, m, s, t;

void INALTARE(int x)
{
    Det_Vf[x].h++;
}

void DESCARCARE(int x)
{
    while (Det_Vf[x].e > 0)
    {
        int it = 0;
        while (it < Rezid[x].size())
        {
            int sters = 0;
            if (Det_Vf[x].e == 0)  break;
            if (Det_Vf[x].h == Det_Vf[Rezid[x][it].first].h + 1)
            {
                //POMPARE
                int minim = std::min(Rezid[x][it].second, Det_Vf[x].e);//minimul dintre excesul lui x si cat poate trimite pana la acel nod
                Det_Vf[Rezid[x][it].first].e += minim;
                if (Rezid[x][it].first != s && Rezid[x][it].first != t && Vf[Rezid[x][it].first] == 0)
                {
                    Q.push(Rezid[x][it].first);
                    Vf[Rezid[x][it].first] = 1;
                }
                Det_Vf[x].e -= minim;
                Rezid[x][it].second = Rezid[x][it].second - minim;
                int nr = Rezid[x][it].first;
                if (Rezid[x][it].second == 0)
                {
                    sters = 1;
                    Rezid[x].erase(Rezid[x].begin() + it);//stergem un arc daca acesta este 0 (flux maxim pe acest arc)
                }
                int ok = 0;
                for (auto& it2 : Rezid[nr])
                {
                    if (it2.first == x)
                    {
                        ok = 1;
                        it2.second += minim;
                    }
                }
                if (ok == 0)
                {
                    Rezid[nr].push_back(std::make_pair(x, minim));//adaugam arcul invers in calea Reziduala
                }
            }
            if (sters == 0)
                it++;
        }
        if (Det_Vf[x].e > 0)
            INALTARE(x);
    }
}

int main()
{
    fin >> n >> m;
    Rezid.resize(n);
    Det_Vf.resize(n);
    Vf.resize(n);
    for (int i = 1; i <= m; i++)
    {
        fin >> x >> y >> c;
        Rezid[x].push_back(make_pair(y, c));
    }
    //INITIALIZARE
    for (int i = 1; i < n; i++)
    {
        Det_Vf[i].e = 0;
        Det_Vf[i].h = 0;
    }
    s = 0, t = n - 1;
    Det_Vf[s].h = n;
    for (auto& it : Rezid[s])
    {
        Det_Vf[it.first].e = it.second;
        Det_Vf[s].e -= it.second;
        Rezid[it.first].push_back(std::make_pair(s, it.second));
    }
    Rezid[s].clear();
    for (int i = 1; i < n - 1; i++)
    {
        Vf[i] = 1;
        Q.push(i);
    }
    while (!Q.empty())
    {
        int varf = Q.front();
        int old_h = Det_Vf[varf].h;
        DESCARCARE(varf);
        Q.pop();
        Vf[varf] = 0;
        if (Det_Vf[varf].h > old_h)
        {
            Q.push(varf);
            Vf[varf] = 1;
        }
    }
    fout << Det_Vf[t].e;
    return 0;
}
