#include <iostream>
#include <queue>
#include <map>
#include <fstream>
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
        if (left->frecv == right->frecv) { return left->minimCh > right->minimCh; }
        return (left->frecv > right->frecv);
    }
};

priority_queue<Varf*, vector<Varf*>, compare> coada;
map<int, string> Dict;
int n;

void aflareCod(struct Varf* root, string str)
{
    if (!root) return;
    if (root->caracter != '!')
        Dict.insert(pair<int, string>(root->caracter, str));
    aflareCod(root->right, str + "1");
    aflareCod(root->left, str + "0");
}

void codareHuffman()
{
    struct Varf* stang, *drept, *top;
    int newFrecv, newMinimCh;
    for (int i = 1; i <= n - 1; i++)
    {
        stang = coada.top();
        coada.pop();
        drept = coada.top();
        coada.pop();

        newFrecv = stang->frecv + drept->frecv;
        newMinimCh = min(stang->minimCh, drept->minimCh);
        top = new Varf('!', newFrecv, newMinimCh);
        top->left = stang;
        top->right = drept;
        coada.push(top);
    }
    aflareCod(coada.top(), "");
}

int main()
{
    int caractere[128] = { 0 }, nr_cuv = 0;
    char c;
	string input;
	while (fin >> input)
    {
        if (nr_cuv > 0)
            caractere[' ']++;
		for (size_t i = 0; i < input.size(); i++)
			caractere[input[i]]++;
        nr_cuv++;
	}
	for (int i = 0; i < 128; i++)
		if (caractere[i] > 0)
		{
            coada.push(new Varf(i, caractere[i], i));
            n++;
		}
    fout << n << endl;
    for (int i = 0; i < 128; i++)
        if (caractere[i] != 0)
            fout << char(i) << " " << caractere[i] << endl;

    codareHuffman();

    string output;
    int key;
    nr_cuv = 0;
    while (fin >> output)
    {
        if (nr_cuv > 0)
            fout << Dict[' '];
        for (int i = 0; i < output.size(); i++)
        {
            key = output[i];
            fout << Dict[key];
        }
        nr_cuv++;
    }
    return 0;
}
