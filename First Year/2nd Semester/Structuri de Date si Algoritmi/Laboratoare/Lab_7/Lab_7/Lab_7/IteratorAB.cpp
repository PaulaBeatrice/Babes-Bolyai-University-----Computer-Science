#include "IteratorAB.h"

//SRD
IteratorInordine::IteratorInordine(const AB& _ab) :ab{ _ab }, st(), curent{ NULL }
{
    prim();
}

void IteratorInordine::prim()
{
    while (!this->st.empty())
    {
        this->st.pop();
    }

    this->curent = this->ab.radacina;
    while (this->curent != NULL) {
        st.push(this->curent);
        this->curent = this->curent->stanga;
    }

    if (!this->st.empty()) {
        this->curent = this->st.top();
    }
}


//verifica daca iteratorul e valid (indica un element al containerului)
bool IteratorInordine::valid() {
    return this->curent != NULL;
}


//returneaza valoarea elementului din container referit de iterator
//arunca exceptie daca iteratorul nu e valid
TElem IteratorInordine::element() {
    if (!valid()) {
        throw exception();
    }
    return this->curent->e;
}


//muta iteratorul in container
// arunca exceptie daca iteratorul nu e valid
void IteratorInordine::urmator() {
    this->curent = this->st.top();
    this->st.pop();

    if (this->curent->dreapta != NULL) {
        this->curent = this->curent->dreapta;
        while (this->curent != NULL)
        {
            this->st.push(this->curent);
            this->curent = this->curent->stanga;
        }
    }

    if (!this->st.empty()) {
        this->curent = this->st.top();
    }
    else {
        this->curent = NULL;
    }
}

//RSD
IteratorPreordine::IteratorPreordine(const AB& _ab) :ab{ _ab }, st(), curent{ ab.radacina } {
    this->prim();
};


void IteratorPreordine::prim() {
    while (!this->st.empty()) {
        this->st.pop();
    }
    this->curent = this->ab.radacina;
}


bool IteratorPreordine::valid() {
    return this->curent != NULL;
}


TElem IteratorPreordine::element() {
    if (!valid()) {
        throw exception();
    }
    return this->curent->e;
}


void IteratorPreordine::urmator() {
    if (!valid()) {
        throw exception();
    }
    if (this->curent->dreapta != NULL) {
        this->st.push(this->curent->dreapta);
    }

    if (this->curent->stanga != NULL) {
        this->curent = this->curent->stanga;
    }
    else if (!this->st.empty()) {
        this->curent = this->st.top();
        this->st.pop();
    }
    else {
        this->curent = NULL;
    }

}


//SDR
IteratorPostordine::IteratorPostordine(const AB& _ab) :ab{ _ab }, curent{ NULL }, st() {
    prim();
}

void IteratorPostordine::prim() {
    while (!this->st.empty()) {
        this->st.pop();
    }

    this->curent = ab.radacina;
    while (this->curent != NULL) {
        this->st.push(make_pair(this->curent, false));
        while (this->curent->stanga != NULL) {
            this->curent = this->curent->stanga;
            this->st.push(make_pair(this->curent, false));
        }

        this->st.pop();
        this->st.push(make_pair(this->curent, true));

        this->curent = this->curent->dreapta;
        while (this->curent != NULL && this->curent->stanga == NULL) {
            this->st.push(make_pair(this->curent, true));
            this->curent = this->curent->dreapta;
        }
    }

    if (!this->st.empty()) {
        this->curent = this->st.top().first;
        urmator();
    }


}


bool IteratorPostordine::valid() {
    return this->curent != NULL;
}


TElem IteratorPostordine::element() {
    if (!valid()) {
        throw exception();
    }
    return this->curent->e;
}

void IteratorPostordine::urmator() {
    if (!valid()) {
        throw exception();
    }
    if (this->st.empty()) {
        this->curent = NULL;
        return;
    }

    while (true) {
        auto top = this->st.top();
        this->st.pop();

        if (top.second) {
            this->curent = top.first;
            break;
        }
        else {
            this->curent = top.first;
            this->st.push(make_pair(this->curent, true));
            this->curent = this->curent->dreapta;

            while (this->curent != NULL) {
                this->st.push(make_pair(this->curent, false));
                this->curent = this->curent->stanga;
            }
        }
    }
}


//PE NIVELURI
IteratorLatime::IteratorLatime(const AB& _ab) :ab{ _ab }, curent{ NULL }, q(){
    prim();
}

void IteratorLatime::prim() {
    while (!this->q.empty()) {
        q.pop();
    }
    this->curent = this->ab.radacina;
}


bool IteratorLatime::valid() {
    return this->curent != NULL;
}


TElem IteratorLatime::element() {
    if (!valid()) {
        throw exception();
    }
    return this->curent->e;
}


void IteratorLatime::urmator()
{
    if (!valid()) {
        throw exception();
    }

    if (this->curent->stanga != NULL) {
        this->q.push(this->curent->stanga);
    }
    if (this->curent->dreapta != NULL) {
        this->q.push(this->curent->dreapta);
    }

    if (!this->q.empty()) {
        this->curent = this->q.front();
        this->q.pop();
    }
    else {
        this->curent = NULL;
    }
}