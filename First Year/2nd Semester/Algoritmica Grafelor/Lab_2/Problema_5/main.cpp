/*
3. Să se scrie un program care găsește o soluție pentru unul din următoarele labirinturi: labirint_1.txt,
labirint_2.txt, labirint_cuvinte.txt. Pentru labirintul 1 si 2 punctul de pornire este indicat de litera S și
punctul de oprire este indicat de litera F, spre deosebire de labirintul 1 și 2,labirintul 3 nu are o soluție unică.
*/

#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

ifstream fin("labirint1.txt");
ofstream fout("solutie.out");

struct punct {
	int x, y;
};

char L[101][101];
int n = 21, m = 41, k = 0, l = 0;
punct Q[101], viz[101], start, curent, urmator;

int vizitat(punct varf) {
	// 0 - a fost vizitat, 1 - nu a fost vizitat
	for (int i = 1; i <= l; i++)
		if (viz[i].x == varf.x && viz[i].y == varf.y)
			return 0;
	return 1;
}

int next()
{
	int i = curent.x;
	int j = curent.y;
	punct varf;
	varf.x = i + 1;
	varf.y = j;

	if (L[i + 1][j] != '1' && i + 1 < n && vizitat(varf) == 1)
	{
		urmator.x = i + 1;
		urmator.y = j;
		return 1;
	}
	varf.x = i;
	varf.y = j + 1;

	if (L[i][j + 1] != '1' && j + 1 < m && vizitat(varf) == 1)
	{
		urmator.x = i;
		urmator.y = j + 1;
		return 1;
	}
	varf.x = i - 1;
	varf.y = j;

	if (L[i - 1][j] != '1' && i - 1 >= 0 && vizitat(varf) == 1)
	{
		urmator.x = i - 1;
		urmator.y = j;
		return 1;
	}
	varf.x = i;
	varf.y = j - 1;

	if (L[i][j - 1] != '1' && j - 1 >= 0 && vizitat(varf) == 1)
	{
		urmator.x = i;
		urmator.y = j - 1;
		return 1;
	}
	return 0;
}

void DFS()
{
	curent = start;
	viz[++l] = start;
	while (L[curent.x][curent.y] != 'F')
	{
		if (next() == 1)
		{
			curent = urmator;
			viz[++l] = curent;
			Q[++k] = curent;
		}
		else
			curent = Q[--k];
	}
}

int main() {
	for (int i = 0; i < n; i++)
		fin.getline(L[i], m + 1);
	int gasit = 0;
	for (int i = 0; i < n && gasit == 0; i++)
		for (int j = 0; j < m && gasit == 0; j++)
			if (L[i][j] == 'S')
			{
				gasit = 1;
				start.x = i;
				start.y = j;
			}
	DFS();
	for (int i = 1; i < k; i++)
		L[Q[i].x][Q[i].y] = 'D';
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
			fout << L[i][j] << " ";
		fout << '\n';
	}
}
