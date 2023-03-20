#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>
#include <math.h>

using namespace std;


bool ePrim(int x) /* Complexitate: O(log n) */
{
	if (x < 2)
		return false;
	if (x % 2 == 0 && x != 2)
		return false;
	for (int d = 3; d * d < x; d += 2)
		if (x % d == 0)
			return false;
	return true;
}

int celMaiMicPrimMaiMareDecat(int n) /* Complexitate: O(1) */
{
	while (!ePrim(n))
		n++;
	return n;
}

// functia care da <hashcode>-ul unui element
int hashCode(TElem e) /* Complexitate: O(1) */
{
	return abs(e);
}

int Colectie::d1(TElem e) const /* Complexitate: O(1) */
{
	return hashCode(e) % n;
}

int Colectie::d2(TElem e) const /* Complexitate: O(1) */
{
	return 1 + (hashCode(e) % (n - 1));
}

// functia de dispersie 
int Colectie::d(TElem e, int i) const /* Complexitate: O(1) */
{
	long long x = d1(e) + 1LL * i * d2(e);
	return (int)(x % n);
}

Colectie::Colectie() { /* Complexitate: O(n) */
	dimensiune = 0;
	n = MAX;
	e = new TElem[20];
	for (int i = 0; i < n; i++)
		e[i] = NULL_TELEM;
}

void Colectie::redimensionare() /* Complexitate: O(n) */
{
	cout << "REDIM\n";
	int new_cap = celMaiMicPrimMaiMareDecat(n * 2);
	TElem* auxiliar = new TElem[new_cap];
	for (int i = 0; i < new_cap; i++)
		auxiliar[i] = NULL_TELEM;
	TElem* backup = e;
	e = auxiliar;
	int old_n = n;
	n = new_cap;
	dimensiune = 0;
	for (int i = 0; i < old_n; i++)
	{		
		TElem x = backup[i];
		if (x != NULL_TELEM && x != STERS)
		{
			adauga(x);
		}		
	}
	delete[] backup;	
}

void Colectie::adauga(TElem elem) { /* Complexitate: O(n) */
	int i = 0;
	bool gasit = false;
	do {
		int j = d(elem, i);		
		if (e[j] == NULL_TELEM || e[j] == STERS )
		{
			e[j] = elem;
			gasit = true;
			dimensiune++;
		}
		else
			i++;
	} while (i < n && !gasit);
	if (i == n)
	{
		redimensionare();
		adauga(elem); // incearca din nou
	}
}


bool Colectie::sterge(TElem elem) { /* Complexitate: O(n) */
	int i = 0;
	int j = 0;
	do
	{
		j = d(elem, i);
		if (e[j] == elem)
		{
			e[j] = STERS;
			dimensiune--;
			return true;
		}
		else
			i++;
	} while(i < n);
	if (dim() == 0)
		for (int k = 0; k < n; k++)
			e[k] = NULL_TELEM;
	return false;
}


bool Colectie::cauta(TElem elem) { /* Complexitate: O(n) */
	int i = 0;
	int j = 0;
	bool gasit = false;
	cout << elem << '\n';
	do
	{
		j = d(elem, i);
		if (e[j] == elem)
			gasit = true;
		else
			i++;
	} while (e[j] != NULL_TELEM && i < n && !gasit);	
	return gasit;
}

int Colectie::nrAparitii(TElem elem) const { /* Complexitate: O(n) */
	int i = 0;
	int j = 0;
	int cnt = 0;	
	do
	{
		j = d(elem, i);
		//cout << "A " << e[j] << '\n';
		if (e[j] == elem)
			cnt++;
		i++;
	} while (e[j] != NULL_TELEM && i < n);
	//cout << n << ' ' << cnt << '\n';
	return cnt;
}


int Colectie::dim() const { /* Complexitate: O(1) */
	return dimensiune;
}


bool Colectie::vida() const { /* Complexitate: O(1) */
	return dimensiune == 0;
}

IteratorColectie Colectie::iterator() const { /* Complexitate: O(1) */
	return  IteratorColectie(*this);
}


Colectie::~Colectie() { /* Complexitate: O(1) */
	delete[] e;
}

void Colectie::adaugaToateElementele(const Colectie& b) /* Complexitate: theta(n), n - lungimea colectiei b */
{
	int i = 0;
	while (b.e[i] != -1)
	{
		this->adauga(b.e[i]);
		i = i + 1;
	}
}

