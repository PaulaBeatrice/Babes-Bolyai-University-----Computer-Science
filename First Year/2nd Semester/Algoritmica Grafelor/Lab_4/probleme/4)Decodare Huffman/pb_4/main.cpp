#include <iostream>
#include <queue>
#include <map>
#include <fstream>
#include <string>
using namespace std;

ifstream fin("1.in");
ofstream fout("1.out");

struct Varf
{
    int caracter;
    int frecv;
    int minimCh;
    Varf* left, * right;
    Varf(int caracter, int frecv, int minimCh)
    {
        left = right = nullptr;
        this->caracter = caracter;
        this->frecv = frecv;
        this->minimCh = minimCh;
    }
};

struct compare
{
    bool operator()(Varf* left, Varf* right) {
        if (left->frecv == right->frecv) { return left->minimCh > right->minimCh;}
        return (left->frecv > right->frecv);
    }
};

priority_queue<Varf*, vector<Varf*>, compare> coada;
map<string, int> Dict;
size_t maxKey = 0;
string cod;
int n = 0;

void aflareCod(struct Varf* root, string str)
{
    if (!root) return;
    if (root->caracter != '||') {
        Dict.insert(pair<string, int>(str, root->caracter));
        if (str.size() > maxKey) {
            maxKey = str.size();
        }
    }
    aflareCod(root->right, str + "1");
    aflareCod(root->left, str + "0");
}

void codareHuffman()
{
    struct Varf* stang, * drept, * top;
    for (int i = 1; i <= n - 1; i++)
    {
        stang = coada.top();
        coada.pop();
        drept = coada.top();
        coada.pop();

        top = new Varf('||', stang->frecv + drept->frecv, min(stang->minimCh, drept->minimCh));
        top->left = stang;
        top->right = drept;
        coada.push(top);
    }
    aflareCod(coada.top(), "");
}

void decodareHuffman()
{
    auto begin = Dict.begin(), end = Dict.end();
    string key;
    char caracter;
    for (int i = 0; i < cod.size(); i++) {
        key = "";
        while (key.size() != maxKey && i < cod.size()) {
            key += cod[i];
            i++;
        }
        while (Dict.find(key) == end) {
            key.pop_back();
            i--;
        }
        caracter = Dict[key];
        fout << caracter;
        i--;
    }
}

int main()
{
    int caractere[128] = { 0 }, frecv = 0, caracter;
    char c;
    string linie;
    Varf* nou;
    getline(fin, linie);
    for (int i = 0; i < linie.size(); i++) {
        n = n * 10 + linie[i] - '0';
    }
    getline(fin, linie);
    c = linie[0];
    for (int i = 2; i < linie.size(); i++) {
        frecv = frecv * 10 + linie[i] - '0';
    }
    caractere[c] = frecv;
    caracter = c;
    nou = new Varf(caracter, frecv, caracter);
    coada.push(nou);
    for (int i = 0; i < n - 1; i++) {
        fin >> c >> frecv;
        caractere[c] = frecv;
        caracter = c;
        nou = new Varf(caracter, frecv, caracter);
        coada.push(nou);
    }
    fin >> cod;

    codareHuffman();
    decodareHuffman();
    return 0;
}
