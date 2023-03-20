class ClientBooks:
    def __init__(self, client_name, number_books):
        self.__client_name = client_name
        self.__number_books = number_books

    def getNumeClient(self):
        return self.__client_name

    def getNumarCartiInchiriate(self):
        return self.__number_books

    def setNumeClient(self, value):
        self.__client_name = value

    def setNumarCartiInchiriate(self, value):
        self.__number_books = value


class BookRents:
    def __init__(self, book_title):
        self.__book_title = book_title
        self.__number_rents = 1

    def getTitluCarte(self):
        return self.__book_title

    def getNumarInchirieri(self):
        return self.__number_rents

    def setTitluCarte(self, value):
        self.__book_title = value

    def setNumarInchirieri(self, value):
        self.__number_rents = value

    def IncreaseNumberRents(self):
        self.__number_rents += 1

class List_rent_books:
    def __init__(self, id_client, lista_carti, nume_client):
        self.__id_client = id_client
        self.__nume_client = nume_client
        self.__list_books = lista_carti

    def getIdClient(self):
        return self.__id_client

    def getListaCarti(self):
        return self.__list_books

    def getNumeClient(self):
        return  self.__nume_client