class Book:
    def __init__(self, id, titlu, descriere, autor, nr_inchirieri):
        """
        Initializeaza un obiect de tip Book cu vallorile date
        :param id: id-ul cartii
        :type id:
        :param titlu: titlul cartii
        :type titlu: str
        :param descriere: descrierea cartii
        :type descriere: str
        :param autor: autorul cartii
        :type autor: str
        """
        self.__id = id
        self.__titlu = titlu
        self.__descriere = descriere
        self.__autor = autor
        self.nr_inchirieri = nr_inchirieri

    def getId(self):
        return self.__id

    def getTitlu(self):
        return self.__titlu

    def getDescriere(self):
        return self.__descriere

    def getAutor(self):
        return self.__autor

    def getNr_Inchirieri(self):
        return self.nr_inchirieri

    def setId(self, value):
        self.__id = value

    def setTitlu(self, value):
        self.__titlu = value

    def setDescriere(self, value):
        self.__descriere = value

    def setAutor(self, value):
        self.__autor = value

    def setNr_Inchirieri(self, value):
        self.nr_inchirieri = value

    def __eq__(self, other):
        """
        Verifica egalitatea
        :param other: cartea cu care se compara produsul curent
        :type other: Book
        :return: True daca cartile sunt aceleasi(au acelasi id), False altfel
        :rtype: bool
        """
        if self.__id == other.getId():
            return True
        return False

    def __str__(self):
        return "Id: " + str(self.__id) + '; Titlu: ' + str(self.__titlu) + '; Descriere: ' + str(self.__descriere)\
               + '; Autor: ' + str(self.__autor) + '; Nr Inchirieri: ' + str(self.nr_inchirieri)


def test_create_book():
    b = Book('1', "Cum sa opresti timpul", "Tom Hazard arata ca oricare alt barbat obisnuit de 41 ani, profesor de istorie, cu o viata normala. Insa, viata lui Tom a devenit demult o corvoada, timpul scurgandu-se in cazul lui, extrem de lent. In fapt, a trecut prin nenumarate epoci, nume, meserii si orase, pentru a se reinventa mereu, ca sa poate duce o viata linistita. Dar ce se va intampla atunci cand cunoaste iubire adevarata, timpul va incepe sa curga mai repede sau se va opri definitiv, pentru Tom?",
             "Matt Haig", 2)
    assert (b.getId() == '1')
    assert(b.getTitlu() == 'Cum sa opresti timpul')
    assert(b.getDescriere() == 'Tom Hazard arata ca oricare alt barbat obisnuit de 41 ani, profesor de istorie, cu o viata normala. Insa, viata lui Tom a devenit demult o corvoada, timpul scurgandu-se in cazul lui, extrem de lent. In fapt, a trecut prin nenumarate epoci, nume, meserii si orase, pentru a se reinventa mereu, ca sa poate duce o viata linistita. Dar ce se va intampla atunci cand cunoaste iubire adevarata, timpul va incepe sa curga mai repede sau se va opri definitiv, pentru Tom?')
    assert (b.getAutor() == 'Matt Haig')
    assert (b.getNr_Inchirieri() == 2)

    b.setId('3')
    b.setTitlu('ABC')
    assert (b.getId() == '3')
    assert (b.getTitlu() == 'ABC')
    b.setDescriere('Tom Hazard')
    b.setAutor('Matt')

    assert (b.getDescriere() == 'Tom Hazard')
    assert (b.getAutor() == 'Matt')

    b.setNr_Inchirieri(4)
    assert (b.getNr_Inchirieri() == 4)


def test_equal_books():
    b1 = Book('1', 'ABC', 'abcdef', 'Ana', 3)
    b2 = Book('1', 'ABC', 'abcdef', 'Ana', 3)
    assert(b1 == b2)

    b3 = Book('2', 'ABC', 'abcdef', 'Ana', 3)
    assert (b1 != b3)


test_create_book()
test_equal_books()


class Client:
    def __init__(self, id, nume, cnp, nr_carti):
        """
        Initializeaza un obiect de tip Client cu valorile date
        :param id: id-ul clientului
        :type id:
        :param nume: numele clientului
        :type nume: str
        :param cnp: cnp-ul clientului
        :type cnp: str
        """
        self.__id = id
        self.nume = nume
        self.__cnp = cnp
        self.nr_carti = nr_carti

    def getId(self):
        return self.__id

    def getNume(self):
        return self.nume

    def getCnp(self):
        return self.__cnp

    def getNrCarti(self):
        return self.nr_carti

    def setId(self, value):
        self.__id = value

    def setNume(self, value):
        self.nume = value

    def setCnp(self, value):
        self.__cnp = value

    def setNrCarti(self, value):
        self.nr_carti = value

    def __eq__(self, other):
        """
        Verifica egalitatea
        :param other: clientul cu care se compara clientul curent
        :type other: Client
        :return: True daca clientii sunt aceeasi(au acelasi id), False altfel
        :rtype: bool
        """
        if self.__id == other.getId():
            return True
        return False

    def __str__(self):
        return "Id: " + str(self.__id) + '; Nume: ' + str(self.nume) + '; Cnp: ' + str(self.__cnp) + '; Nr Carti Inchiriate: ' + str(self.nr_carti)


def test_create_client():
    c = Client('1', 'Bianca', '15290648945773', 2)
    assert(c.getId() == '1')
    assert(c.getCnp() == '15290648945773')
    assert (c.getNume() == 'Bianca')
    assert (c.getNrCarti() == 2)

    c.setId('2')
    assert (c.getId() == '2')
    c.setNume('Tom')
    c.setCnp('15292888940073')

    assert (c.getNume() == 'Tom')
    assert (c.getCnp() == '15292888940073')

    c.setNrCarti(8)
    assert (c.getNrCarti() == 8)


def test_equal_clients():
    c1 = Client('1', 'Andrei', '15292888940073', 7)
    c2 = Client('1', 'Andrei', '15292888940073', 7)
    assert(c1 == c2)

    c3 = Client('2', 'Andreea', '15292888940073', 7)
    assert (c1 != c3)


test_create_client()
test_equal_clients()


class RentItem:
    def __init__(self, book, client):
        self.__book = book
        self.__client = client

    def getBook(self):
        return self.__book

    def getClient(self):
        return self.__client

    def setBook(self, value):
        self.__book = value

    def setClient(self, value):
        self.__client = value

    def setReturnat(self, value):
        self.__returnat = value

    def __eq__(self, other):
        if self.__book.getId() == other.__book.getId() and self.__client.getId() == other.__client.getId():
            return True
        return False

    def __str__(self):
        return 'Book: [' + str(self.__book.getTitlu() + '; ' + str(self.__book.getAutor()) + '] Client: [' + str(self.__client.getNume()) + str(self.__client.getCnp())) + ']'


def test_create_rent():
    b1 = Book('1', 'A', 'dfsd', 'ffsd', 0)
    c1 = Client('1', 'dfd', '1479789851254', 1)
    rent_item = RentItem(b1, c1)

    assert (rent_item.getBook() == b1)
    assert (rent_item.getClient() == c1)


def test_equal_rent():
    b1 = Book('1', 'A', 'dfsd', 'ffsd', 3)
    c1 = Client('1', 'dfd', '1479789851254', 5)

    rent_item = RentItem(b1, c1)
    rent_item2 = RentItem(b1, c1)

    assert(rent_item == rent_item2)

    b2 = Book('2', 'B', 'fsvdf', 'wedf', 8)
    rent_item3 = RentItem(b2, c1)
    assert (rent_item != rent_item3)


test_create_rent()
test_equal_rent()
