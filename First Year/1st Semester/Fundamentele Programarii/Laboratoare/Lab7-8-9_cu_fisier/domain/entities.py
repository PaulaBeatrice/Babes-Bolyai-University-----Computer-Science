class Book:
    def __init__(self, id, titlu, descriere, autor, inchiriata):
        """
        Initializeaza un obiect de tip Book cu valorile date
        :param id: id-ul cartii
        :type id:
        :param titlu: titlul cartii
        :type titlu: str
        :param descriere: descrierea cartii
        :type descriere: str
        :param autor: autorul cartii
        :type autor: str
        :param inchiriata: retine daca cartea este inchiriata sau nu(True daca este inchiriata, False altfel)
        :type inchiriata: bool
        """
        self.__id = id
        self.__titlu = titlu
        self.__descriere = descriere
        self.__autor = autor
        self.__inchiriata = inchiriata

    def getId(self):
        """
        Va returna atributul id al unui obiect de tip Book
        """
        return self.__id

    def getTitlu(self):
        """
        Va returna atributul titlu al unui obiect de tip Book
        """
        return self.__titlu

    def getDescriere(self):
        """
        Va returna atributul descriere al unui obiect de tip Book
        """
        return self.__descriere

    def getAutor(self):
        """
        Va returna atributul autor al unui obiect de tip Book
        """
        return self.__autor

    def getInchiriata(self):
        """
        Va returna atributul inchiriata al unui obiect de tip Book
        """
        return self.__inchiriata

    def setId(self, value):
        """
        Va seta atributul id al unui obiect de tip Book cu valoarea lui value
        """
        self.__id = value

    def setTitlu(self, value):
        """
        Va seta atributul titlu al unui obiect de tip Book cu valoarea lui value
        """
        self.__titlu = value

    def setDescriere(self, value):
        """
        Va seta atributul descriere al unui obiect de tip Book cu valoarea lui value
        """
        self.__descriere = value

    def setAutor(self, value):
        """
        Va seta atributul autor al unui obiect de tip Book cu valoarea lui value
        """
        self.__autor = value

    def setInchiriata(self, value):
        """
        Va seta atributul inchiriata al unui obiect de tip Book cu valoarea lui value
        """
        self.__inchiriata = value

    def __eq__(self, other):
        """
        Verifica egalitatea a doua obiecte de tip Book
        :param other: cartea cu care se compara produsul curent
        :type other: Book
        :return: True daca cartile sunt aceleasi(au acelasi id), False altfel
        :rtype: bool
        """
        if self.__id == other.getId():
            return True
        return False

    def __str__(self):
        """
        Returneaza un string ce contine atributele unui obiect de tip Book
        """
        return "Id: " + str(self.__id) + "; Titlu: " + str(self.__titlu) + "; Descriere: " + str(self.__descriere)\
        + "; Autor: " + str(self.__autor) + " Inchiriata: " + str(self.__inchiriata)


class Client:
    def __init__(self, id, nume, cnp):
        """
        Initializam un obiect de tip Client cu valorile date
        :param id: id-ul clientului
        :type id: str
        :param nume: numele clientului
        :type id: str
        :param cnp: cnp ul clientului
        :type cnp: str
        """
        self.__id = id
        self.__nume = nume
        self.__cnp = cnp

    def getId(self):
        """
        Va returna atributul id al unui obiect de tipul Client
        """
        return self.__id

    def getNume(self):
        """
        Va returna atributul nume al unui obiect de tipul Client
        """
        return self.__nume

    def getCnp(self):
        """
        Va returna atributul cnp al unui obiect de tipul Client
        """
        return self.__cnp

    def setId(self, value):
        """
        Va seta atributul id al unui obiect de tip Client cu valoarea lui value
        """
        self.__id = value

    def setNume(self, value):
        """
        Va seta atributul nume al unui obiect de tip Client cu valoarea lui value
        """
        self.__nume = value

    def setCnp(self, value):
        """
        Va seta atributul cnp al unui obiect de tip Client cu valoarea lui value
        """
        self.__cnp = value

    def __eq__(self, other):
        """
        Verificam egalitatea a doua obiecte de tip Client
        :param other: clientul cu care se compara clientul curent
        :type other: Client
        :return: True daca clienti sunt aceeasi(au acelasi id), False altfel
        :rtype: Bool
        """
        if self.__id == other.getId():
            return True
        return False

    def __str__(self):
        """
        Returneaza un string ce contine atributele unui obiect de tip Client
        """
        return 'Id: ' + str(self.__id) + ';Nume: ' + str(self.__nume) + ';Cnp: ' + str(self.__cnp)


class RentItem:
    def __init__(self, book, client):
        """
        Initializeaza un obiect de tip RentItem
        :param book: cartea inchiriata
        :type book: Book
        :param client: clientul care inchiriaza cartea
        :type client: Client
        """
        self.__book = book
        self.__client = client

    def getBook(self):
        """
        Returneaza atributul book al unui obiect de tip RentItem
        """
        return self.__book

    def getClient(self):
        """
        Returneaza atributul book al unui obiect de tip RentItem
        """
        return self.__client

    def setBook(self, value):
        """
        Va seta atributul book al unui obiect de tip RentItem cu valoarea lui value
        """
        self.__book = value

    def setClient(self, value):
        """
        Va seta atributul client al unui obiect de tip RentItem cu valoarea lui value
        """
        self.__client = value

    def getIdBook(self):
        return self.__book.getId()

    def getIdClient(self):
        return self.__client.getId()

    def __eq__(self, other):
        """
        Verificam egalitatea dintre doua obiecte de tip RentItem
        :param other: RentItem ul cu care se compara RentItem ul curent
        :type other: RentItem
        :return: True, daca cele doua RentItem uri sunt aceleasi, False altfel
        :rtype: bool
        """
        if self.__book.getId() == other.getBook().getId() and self.__client.getId() == other.getClient().getId():
            return True
        return False

    def __str__(self):
        """
        Returneaza un string ce contine toate atributele unui obiect de tip RentItem
        """
        return 'Book[Id: ' + str(self.__book.getId()) + '; Titlu: ' + str(self.__book.getTitlu()) + ';Descriere: '\
        + str(self.__book.getDescriere()) + '; Autor: ' + str(self.__book.getAutor()) + '; Inchiriata: ' \
        + str(self.__book.getInchiriata()) + '] Client[Id: ' + str(self.__client.getId()) + '; Nume: ' \
        + str(self.__client.getNume()) + '; Cnp: ' + str(self.__client.getCnp())
