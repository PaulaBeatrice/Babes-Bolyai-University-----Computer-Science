from operator import attrgetter

from domain.dtos import ClientBooks, List_rent_books, BookRents
from domain.entities import RentItem
from domain.validators import RentItemValidator
from exceptions.exceptions import BookNotFoundException, ClientNotFoundException, RentAlreadyAssignedException
from repository.book_repo import BookRepoFile
from repository.repo_rent_item import RentItemRepoMemory
from repository.client_repo import ClientRepoFile
#from operator import attrgetter



class RentItemService:
    def __init__(self, rent_repo, rent_val, book_repo, client_repo):
        self.__rent_repo = rent_repo
        self.__rent_val = rent_val
        self.__book_repo = book_repo
        self.__client_repo = client_repo

    def add_rent_item(self, book_id, client_id):
        """
        Adauga un RentItem
        :param book_id: id-ul cartii
        :type book_id: str
        :param client_id: id-ul clientului
        :type client_id:str
        :return: RentItem adaugat
        :rype: RentItem
        ::raises:BookNotFoundException daca nu exista carte cu id dat
                 ClientNotFoundException daca nu exista client cu id dat
                 ValidationException daca item-ului nu e valid
                 RentItemAlreadyAssigned daca item-ul deja exista pentru cartea si clientul dat
        """
        book = self.__book_repo.find(book_id)
        if book is None:
            raise BookNotFoundException()
        else:
            client = self.__client_repo.find(client_id)
            if client is None:
                raise ClientNotFoundException()
            else:
                rent_item = RentItem(book, client)
                self.__rent_val.validate(rent_item)
                self.__rent_repo.store(rent_item)
                return rent_item

    def get_all(self):
        return self.__rent_repo.get_all()

    def return_rent_item(self, book_id, client_id):
        """
        Clientul cu id ul dat returneaza o carte cu id-ul dat
        :param book_id: id-ul cartii returnate
        :type book_id:str
        :param client_id: id-ul clientului dat
        :type client_id: str
        :return: RentItem modificat
        :rtype: RentItem
        :raises: ValueError daca nu exista inchirierea cu id urile date
        """
        book = self.__book_repo.find(book_id)
        if book is None:
            raise BookNotFoundException()

        client = self.__client_repo.find(client_id)
        if client is None:
            raise ClientNotFoundException()

        rent_item = RentItem(book, client)
        self.__rent_val.validate(rent_item)
        return self.__rent_repo.return_book(rent_item)

    def get_number_of_rent_books_sorted_by_books(self):
        """
        Calculeaza numarul de carti inchiriate de fiecare client
        :return: lista de ClientBooks DTO
        :rtype: list of ClientBooks objects
        """
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            client = self.__client_repo.find(id_client)
            if client.getId() not in situatie_clienti:
                situatie_clienti[id_client] = 1
            else:
                situatie_clienti[id_client] += 1
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            nr_carti_citite = situatie_clienti[cheie_situatie_clienti]
            client_books_dtos = ClientBooks(client_name, nr_carti_citite)
            res.append(client_books_dtos)

        res.sort(key = lambda x: x.getNumarCartiInchiriate(), reverse=False)
        return res

    def top_clients(self):
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            client = self.__client_repo.find(id_client)
            if client.getId() not in situatie_clienti:
                situatie_clienti[id_client] = 1
            else:
                situatie_clienti[id_client] += 1
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            nr_carti_citite = situatie_clienti[cheie_situatie_clienti]
            client_books_dtos = ClientBooks(client_name, nr_carti_citite)
            res.append(client_books_dtos)

        res.sort(key=lambda x: x.getNumarCartiInchiriate(), reverse=True)
        return res[:len(res)//5]

    def get_number_of_rent_books_sorted_by_name(self):
        """
        Calculeaza numarul de carti inchiriate de fiecare client
        :return: lista de ClientBooks DTO
        :rtype: list of ClientBooks objects
        """
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            client = self.__client_repo.find(id_client)
            if client.getId() not in situatie_clienti:
                situatie_clienti[id_client] = 1
            else:
                situatie_clienti[id_client] += 1
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            nr_carti_citite = situatie_clienti[cheie_situatie_clienti]
            client_books_dtos = ClientBooks(client_name, nr_carti_citite)
            res.append(client_books_dtos)

        res.sort(key = lambda x: x.getNumeClient(), reverse=False)
        return res

    def get_number_of_rents(self):
        """
        Calculeaza numarul de inchirieri pentru fiecare carte
        :return: lista de ClientBooks DTO
        :rtype: list of ClientBooks objects
        """
        all_rent_items = self.__rent_repo.get_all()
        books_rents_dtos = {}
        for rent_item in all_rent_items:
            if rent_item.getBook().getId() not in books_rents_dtos:
                books_rents_dtos[rent_item.getBook().getId()] = BookRents(rent_item.getBook().getTitlu())
            else:
                books_rents_dtos[rent_item.getBook().getId()].IncreaseNumberRents()
        return list(books_rents_dtos.values())

    def report_by_clients(self, client_id):
        """Afiseaza lista de carti inchiriate de un client cu un id dat"""
        all_rent_items = self.__rent_repo.get_all()
        list_books = []
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            if id_client == client_id:
                list_books.append(self.__book_repo.find(id_book).getTitlu())
        return list_books

    def get_raport(self):
        """lista cu clientii care inchiriaza cea mai inchiriata carte, si cartile pe care le inchiriaza"""
        lista = self.get_number_of_rents()
        the_best_book_title = lista[0].getTitluCarte()
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            book = self.__book_repo.find(id_book)
            if book.getTitlu() == the_best_book_title:
                if book.getTitlu() not in situatie_clienti:
                    situatie_clienti[id_client] = book.getId()
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_id = self.__client_repo.find(cheie_situatie_clienti).getId()
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            carti_citite = self.report_by_clients(client_id)
            read_books_dtos = List_rent_books(client_id, carti_citite, client_name)
            res.append(read_books_dtos)

        return res



def test_add_rent_item():
    rent_repo = RentItemRepoMemory()
    rent_val = RentItemValidator()
    book_repo = BookRepoFile('test_book.txt')
    client_repo = ClientRepoFile('test_client.txt')

    test_srv = RentItemService(rent_repo, rent_val, book_repo, client_repo)

    test_srv.add_rent_item('1', '1')
    assert (len(test_srv.get_all()) == 1)

    try:
        test_srv.add_rent_item('1', '1')
        assert False
    except RentAlreadyAssignedException:
        assert True

    try:
        test_srv.add_rent_item('100', '1')
        assert False
    except BookNotFoundException:
        assert True

    try:
        test_srv.add_rent_item('1', '100')
        assert False
    except ClientNotFoundException:
        assert True

#test_add_rent_item()

