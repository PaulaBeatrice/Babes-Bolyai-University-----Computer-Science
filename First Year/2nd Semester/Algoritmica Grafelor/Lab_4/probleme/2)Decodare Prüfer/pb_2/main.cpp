#include <iostream>
#include <vector>
#include <fstream>
#include <queue>

using namespace std;

ifstream fin("1.in");
ofstream fout("1.out")

int fr[100005], P[100005];
vector < int > T, K;
int n, m;

void Refresh_Freq() {
	for (int i = 0; i <= n; i++) {
		fr[i] = 0;
	}
}

void Prufer_Decoding() {
	int x, y;
	for (int k = 1; k <= n; k++) {
		cout << k << "\n";
		x = K[0];
		for (auto i : K) {
			fr[i] = 1;
		}
		for (int i = 0; i <= n; i++) {
			if (fr[i] == 0) {
				y = i;
				break;
			}
		}
		p[y] = x;
		K.push_back(y);
		K.erase(K.begin());
		Refresh_Freq();
	}
}

int main() {
    fin >> n;
	for (int i = 0; i < n; i++) {
		fin >> m;
		K.push_back(m);
	}
	for (int i = 0; i <= n; i++) {
		P[i] = 1000;
	}
	Prufer_Decoding();
	fout << n + 1 << '\n';
	for (int i = 0; i <= n; i++) {
		if (P[i] == 1000)
			fout << "-1 ";
		else fout << P[i] << " ";
	}
	return 0;
}
