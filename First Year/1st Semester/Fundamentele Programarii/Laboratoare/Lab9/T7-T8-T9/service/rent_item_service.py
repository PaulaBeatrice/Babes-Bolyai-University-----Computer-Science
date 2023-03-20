from operator import attrgetter

from domain.entities import RentItem, Book, Client
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
                cnt1 = book.getNr_Inchirieri() + 1
                cnt2 = client.getNrCarti() + 1
                rent_item = RentItem(book, client)
                self.__rent_val.validate(rent_item)
                self.__rent_repo.store(rent_item)
                new_book = Book(book_id, book.getTitlu(), book.getDescriere(), book.getAutor(), cnt1)
                new_client = Client(client_id, client.getNume(), client.getCnp(), cnt2)
                self.__book_repo.update_book(book_id, new_book)
                self.__client_repo.update(client_id, new_client)
                return rent_item

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


    def get_all(self):
        return self.__rent_repo.get_all()

    def get_top_book(self):
        lst = self.__book_repo.get_all()
        l = []
        for b in lst:
            if b.getNr_Inchirieri() != 0:
                l.append(b)
        l.sort(key= lambda x: x.nr_inchirieri, reverse=True )
        return l

    def get_top_client(self):
        lst = self.__client_repo.get_all()
        l = []
        for c in lst:
            if c.getNrCarti() != 0:
                l.append(c)
        l.sort(key=lambda x: x.nr_carti, reverse=True)
        return l

    def rent_report_nume(self):
        """
        Afisare clienti cu carti inchiriate ordonat dupa: nume
        """
        lst = self.__client_repo.get_all()
        l = []
        for c in lst:
            if c.getNrCarti() != 0:
                l.append(c)
        l.sort(key=attrgetter('nume'), reverse=True)
        return l

    def rent_report_carti(self):
        """
        Afisare clienti cu carti inchiriate ordonat dupa numarul de carti inchiriate
        """
        lst = self.__client_repo.get_all()
        l = []
        for c in lst:
            if c.getNrCarti() != 0:
                l.append(c)
        l.sort(key=lambda x: x.nr_carti, reverse=True)
        return l

    def cea_mai_citita_carte(self):
        lst = self.__book_repo.get_all()
        lst.sort(key=lambda x: x.nr_inchirieri, reverse=True)
        return lst

    def clienti_care_au_citit_cea_mai_citita_carte(self):
        books = self.cea_mai_citita_carte()
        book_id = books[0].getId()
        lst = self.get_all()
        l = []
        for c in lst:
            if c.__book.getId() == book_id:
                print(c)
                l.append(c.__client)
        l.sort(key=lambda x: x.nr_carti, reverse=True)
        return l

    def show_books(self, client_id):
        lst = []
        rent_list = self.get_all()
        for el in rent_list:
            if el.client.getId() == client_id:
                lst.append(el.__book.getTitlu())
        return lst


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

