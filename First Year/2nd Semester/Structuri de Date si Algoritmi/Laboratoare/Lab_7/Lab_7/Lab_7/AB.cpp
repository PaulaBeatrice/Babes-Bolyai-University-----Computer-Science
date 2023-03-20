#include "AB.h"
#include "IteratorAB.h"
#include <exception>
#include <string>
#define NULL_TVALOARE -1

void AB::distruge(Nod* nod) /* Complexitate: theta(n) , n - nr de noduri ale arborelui */
{
    if (nod != NULL) {
        distruge(nod->stanga);
        distruge(nod->dreapta);
        delete nod;
    }
}


void AB::distrugeSubarbori(Nod* nod) /* Complexitate: theta(n) , n - nr de noduri ale arborelui */
{
    if (nod != NULL) {
        distruge(nod->stanga);
        distruge(nod->dreapta);
    }
}


Nod* AB::copiere(Nod* nod) const /* Complexitate: theta(n) , n - nr de noduri ale arborelui */
{
    if (nod != NULL) {
        //creez radacina
        Nod* nodNew = new Nod(nod->e, NULL, NULL);
        nodNew->stanga = copiere(nod->stanga);
        nodNew->dreapta = copiere(nod->dreapta);
        return nodNew;
    }
    return NULL;
}


AB::AB() /* Complexitate: theta(1) */
{
    this->radacina = NULL;
}


AB::AB(const AB& ab) /* Complexitate: theta(n) , n - nr de noduri ale arborelui */
{
    this->radacina = copiere(ab.radacina);
}


AB::AB(TElem e) /* Complexitate: theta(1) */
{
    this->radacina = new Nod(e, NULL, NULL);
}


AB::AB(const AB& st, TElem e, const AB& dr) /* Complexitate: theta(n) , n - nr de noduri ale arborelui */
{
    distruge(this->radacina);
    this->radacina = new Nod(e, NULL, NULL);
    this->radacina->stanga = copiere(st.radacina);
    this->radacina->dreapta = copiere(dr.radacina);
}


void AB::adaugaSubSt(const AB& st) /* Complexitate: theta(n) , n - nr de noduri ale subarborelui stang */
{
    if (this->radacina == NULL) {
        throw exception();
    }
    //distrug vechii subarbori ai subarborelui stang
    distruge(this->radacina->stanga);
    //copiez noul subarbore
    this->radacina->stanga = copiere(st.radacina);
}


void AB::adaugaSubDr(const AB& dr) /* Complexitate: theta(n) , n - nr de noduri ale subarborelui drept */
{
    if (this->radacina == NULL) {
        throw exception();
    }
    //distrug vechii subarbori ai subarborelui drept
    distruge(this->radacina->dreapta);
    //copiez noul arbore
    this->radacina->dreapta = copiere(dr.radacina);
}

TElem AB::element() const /* Complexitate: theta(1) */
{
    if (this->radacina == NULL) {
        throw exception();
    }
    return this->radacina->e;
}


AB AB::stang() const /* Complexitate: theta(n) , n - nr de noduri ale subarborelui stang */
{
    if (this->radacina == NULL) {
        throw exception();
    }

    AB ab;
    ab.radacina = copiere(this->radacina->stanga);
    return ab;
}


AB AB::drept() const /* Complexitate: theta(n) , n - nr de noduri ale subarborelui drept */
{
    if (this->radacina == NULL) {
        throw exception();
    }

    AB ab;
    ab.radacina = copiere(this->radacina->dreapta);
    return ab;
}


AB::~AB() /* Complexitate: theta(n) , n - nr de noduri ale arborelui */
{
    distruge(this->radacina);
}

int AB::valoareMaxima() const /* Complexitate: theta(n), n - nr de noduri ale arborelui */
{
    if (this->vid())
        return NULL_TVALOARE;
    TElem max = radacina->e;
    string ordine = "postordine";
    IteratorAB* it = this->iterator(ordine);
    it->prim();
    while (it->valid())
    {
        TElem x = it->element();
        if (x > max)
            max = x;
        it->urmator();
    }
    return max;
}

bool AB::vid() const /* Complexitate: theta(1) */
{
    return this->radacina == NULL;
}


IteratorAB* AB::iterator(string s) const /* Complexitate: theta(1)*/
{
    if (s == "preordine")
        return new IteratorPreordine(*this);
    if (s == "inordine")
        return new IteratorInordine(*this);
    if (s == "postordine")
        return new IteratorPostordine(*this);
    if (s == "latime")
        return new IteratorLatime(*this);
    return nullptr;
};

