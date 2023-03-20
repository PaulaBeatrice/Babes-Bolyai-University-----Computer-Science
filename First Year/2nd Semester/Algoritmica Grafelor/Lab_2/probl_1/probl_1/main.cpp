#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("1.in");
ofstream fout("1.out");

int main() {
	int n, m, M[101][101] = {0}, T[101][101] = {0}, i, j, k, a, b;
	fin >> n >> m;
	for (int i = 1; i <= m; i++)
	{
		fin >> a >> b;
		M[a][b] = 1;
	}
	for (k = 1; k <= n; k++)
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++)
				if (M[i][j] == 0)
					M[i][j] = (M[i][k] && M[k][j]);
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			if (M[i][j] != 0)
				T[i][j] = 1;
	
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
			fout << T[i][j] << " ";
		fout << endl;
	}
}