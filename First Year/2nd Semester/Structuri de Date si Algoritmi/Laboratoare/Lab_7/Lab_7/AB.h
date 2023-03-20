#pragma once
#include <string>
using namespace std;

typedef int TElem;

class AB;

class IteratorAB;

class Nod {
public:
    Nod(TElem e, Nod* stanga, Nod* dreapta) : e{ e }, stanga{ stanga }, dreapta{ dreapta }{};
    friend class AB;
    friend class IteratorAB;
    friend class IteratorPreordine;
    friend class IteratorInordine;
    friend class IteratorPostordine;
    friend class IteratorLatime;

private:
    TElem e;
    Nod* stanga;
    Nod* dreapta;
};

class AB {

private:
    Nod* radacina;

    //functie care distruge arborele
    void distruge(Nod* nod);

    //functie care distruge subarborii unui arbore
    void distrugeSubarbori(Nod* nod);

    //copiere subarbore
    Nod* copiere(Nod* nod) const;
public:
    friend class IteratorAB;
    friend class IteratorPreordine;
    friend class IteratorInordine;
    friend class IteratorPostordine;
    friend class IteratorLatime;

    //constructorul implicit
    AB();

    //contructor de copiere
    AB(const AB& ab);

    //creeaza un arbore binar care se reduce la o frunza
    AB(TElem elem);

    //constructor arbore binar
    AB(const AB& st, TElem elem, const AB& dr);

    //adauga unui arbore binar subarborele stang
    //arunca exceptie daca arborele este vid
    void adaugaSubSt(const AB& st);

    //adauga unui arbore binar subarborele drept
    //arunca exceptie daca arborele este vid
    void adaugaSubDr(const AB& dr);

    //verifica daca arborele este vid
    bool vid() const;

    //accesare element din radacina
    //arunca exceptie daca arborele este vid
    TElem element() const;

    //accesare subarbore stang
    //arunca exceptie daca arborele este vid
    AB stang() const;

    //accesare subarbore drept
    //arunca exceptie daca arborele este vid
    AB drept() const;

    //iteratori pe arborele binar (ordine="preordine", "postordine", "inordine", "latime")
    IteratorAB* iterator(string ordine) const;

    // destructorul arborelui binar
    ~AB();

    int valoareMaxima()const;
    
};