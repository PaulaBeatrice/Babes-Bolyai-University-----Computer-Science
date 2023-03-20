#pragma once
#include<vector>
#include<utility>
// 17.TAD MultiDicționar –reprezentare  sub  forma  unei  LSI  în  care  apar  toate  perechile  de  forma (cheie, valoare). O cheie poate apărea în vector 
// de mai multe ori.
using namespace std;

typedef int TCheie;
typedef int TValoare;

typedef std::pair<TCheie, TValoare> TElem;

class IteratorMD;

class MD
{
	friend class IteratorMD;

private:
	/* aici e reprezentarea */
	TElem* e;//tablu de elem
	int* urm;//tablou de nod urmator
	int size, capacity, prim_el, ultim_el, prim_ind_liber;//dimensiune tablou, capacitate, primul el din TElem, primul elem din urm  
	int aloca(); //se returneaza pozitia unui spatiu liber din lista	
	void dealoca(int i);//dealoca spatiul de indice i7
	void resize();//functie de redimensionare a tablourilor dinamice
public:
	// constructorul implicit al MultiDictionarului
	MD();

	// adauga o pereche (cheie, valoare) in MD	
	void adauga(TCheie c, TValoare v);

	//cauta o cheie si returneaza vectorul de valori asociate
	vector<TValoare> cauta(TCheie c) const;

	//sterge o cheie si o valoare 
	//returneaza adevarat daca s-a gasit cheia si valoarea de sters
	bool sterge(TCheie c, TValoare v);

	//returneaza numarul de perechi (cheie, valoare) din MD 
	int dim() const;

	//verifica daca MultiDictionarul e vid 
	bool vid() const;

	// se returneaza iterator pe MD
	IteratorMD iterator() const;

	// destructorul MultiDictionarului	
	~MD();


	vector<TValoare> colectiaValorilor()const;
};

