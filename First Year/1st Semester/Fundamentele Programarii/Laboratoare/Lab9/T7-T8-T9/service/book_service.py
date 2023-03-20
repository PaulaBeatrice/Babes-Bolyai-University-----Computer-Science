import random
import string

from domain.entities import Book
from domain.validators import BookValidator
from repository.book_repo import InMemoryRepository
from exceptions.exceptions import ValidationException, BookNotFoundException


def random_string(len=None):
    """genereaza string random"""
    len = len if len is not None else random.randint(1, 20)
    lit = string.ascii_letters
    str_list = [random.choice(lit) for _ in range(len)]
    random_str = "".join(str_list)
    return random_str

class BookService:
    """
        GRASP Controller
        Responsabil de efectuarea operatiilor cerute de utilizator
        Coordoneaza operatiile necesare pentru a realiza actiunea declansata de utilizator
        (i.e. declansare actiune: utilizator -> ui-> obiect tip service in ui -> service -> service coordoneaza
         operatiile
        folosind alte obiecte (e.g. repo, validator) pentru a realiza efectiv operatia)
    """
    def __init__(self, repo, validator):
        """
        Initializeaza service
        :param repo: obiectul de tip repo care ne ajuta sa gestionam lista de carti
        :type repo: InMemoryRepository
        :param validator: validatorul pentru verificarea cartilor
        :type validator: BookValidator
        """
        self.__repo = repo
        self.__validator = validator

    def add_book(self, id, titlu, descriere, autor, nr_inchirieri):
        """
        Adauga carte
        :param id: id ul cartii
        :param id: str
        :param titlu: titlul cartii
        :type titlu: str
        :param descriere: descrierea cartii
        :type descriere: str
        :param autor: autorul cartii
        :type autor: str
        :return: cartea adaugata in lista
        :rtype: BookService
        :raises: ValueError daca cartea e invalida
        """
        book = Book(id, titlu, descriere, autor, nr_inchirieri)
        self.__validator.validate(book)
        self.__repo.library(book)
        return book

    def generate_book(self, number):
        for i in range(number):
            id = random_string(random.randint(1, 20))
            titlu = random_string(random.randint(1, 20))
            descriere = random_string(random.randint(1, 20))
            autor = random_string(random.randint(1, 20))
            nr_inchirieri = 0
            carte = Book(id, titlu, descriere, autor, nr_inchirieri)
            self.__validator.validate(carte)
            self.__repo.library(carte)

    def get_all_books(self):
        """
        Returneaza o lista cu toate cartile disponibile
        :return: lista de carti disponibile
        :rtype: list of Book objects
        """
        return self.__repo.get_all()

    def delete_by_id(self, id):
        """
        Stegre cartea cu id dat
        :param id: id-ul dat
        :type id: str
        :return: cartea stearsa
        :rtype: Book
        :raises: ValueError daca nu exista carte cu id-ul dat
        """
        return self.__repo.delete_book(id)

    def update_book(self, id, titlu, descriere, autor, nr_inchirieri):
        """
        Modifica cartea cu id cu datele date
        :param id: id-ul dat
        :type id: str
        :param titlu: noul titlu al cartii
        :type titlu: str
        :param descriere: noua descriere a cartii
        :type descriere: str
        :param autor: noul autor al cartii
        :type autor: str
        :return: cartea modificata
        :rtype: Book
        :raises: ValueError daca nu exista cartea cu id-ul dat
        """
        book = Book(id, titlu, descriere, autor, nr_inchirieri)
        self.__validator.validate(book)
        return self.__repo.update_book(id, book)

    def filter_by_title(self, str_to_search):
        """
        Selecteaza cartile care contin stringul str_to_search in titlu
        :param str_to_search: stringul de cautat in titlu
        :type str_to_search: str
        :return: lista de carti care au str_to_search in titlu
        :rtype: list of Books objects
        """
        all_books = self.get_all_books()
        filtered_list = [book for book in all_books if str_to_search in book.getTitlu()]
        return filtered_list



def test_add_books():
    test_repo = InMemoryRepository()
    test_val = BookValidator()

    test_srv = BookService(test_repo, test_val)

    book = test_srv.add_book('1', 'ABC', 'abcdef', 'Ana', 4)
    assert (book.getTitlu() == 'ABC')
    assert (book.getDescriere() == 'abcdef')
    assert (book.getNr_Inchirieri() == 4)

    assert (len(test_srv.get_all_books()) == 1)

    try:
        test_srv.add_book('2', '', 'abcdef', 'Ana', 1)
        assert False
    except ValidationException:
        assert True


def test_get_all_books():
    test_repo = InMemoryRepository()
    test_validator = BookValidator()

    test_srv = BookService(test_repo, test_validator)
    added_book = test_srv.add_book('2', 'Abecedar', 'abcdef', 'Mara', 0)
    #assert (type(test_srv.get_all_books()) == list)
    assert (len(test_srv.get_all_books()) == 1)
    crt_books = test_srv.get_all_books()
    assert (crt_books[0].getTitlu() == 'Abecedar')

    added_book2 = test_srv.add_book('1', 'Alba-ca-zapada', 'fwdasw', 'Doru', 0)
    assert (len(test_srv.get_all_books()) == 2)
    assert (added_book2.getDescriere() == 'fwdasw')
    assert (added_book2.getAutor() == 'Doru')

    try:
        test_srv.add_book('1', 'wfe', 'fs', 'sffds', 0)
    except:
        pass
    assert (len(test_srv.get_all_books()) == 2)

    test_srv.delete_by_id('1')
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 1)
    assert (crt_books[0].getTitlu() == 'Abecedar')

    test_srv.update_book('2', 'ABC', 'sfdfsef', 'Dana', 0)
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 1)
    assert (crt_books[0].getTitlu() == 'ABC')


def test_delete_book():
    test_repo = InMemoryRepository()
    test_val = BookValidator()

    test_srv = BookService(test_repo, test_val)
    test_srv.add_book('1', 'carte1', 'wfsv', 'fwes', 0)
    test_srv.add_book('2', 'carte2', 'fer', 'weri', 0)
    test_srv.add_book('3', 'roman', 'fgs', 'werf', 0)

    deleted_book1 = test_srv.delete_by_id('1')
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 2)
    assert (deleted_book1.getTitlu() == 'carte1')
    assert (deleted_book1.getDescriere() == 'wfsv')
    assert (deleted_book1.getAutor() == 'fwes')

    deleted_book2 = test_srv.delete_by_id('2')
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 1)
    assert (deleted_book2.getTitlu() == 'carte2')
    assert (deleted_book2.getDescriere() == 'fer')
    assert (deleted_book2.getAutor() == 'weri')

    try:
        test_srv.delete_by_id('dve')
        assert False
    except BookNotFoundException:
        assert True
    assert (len(crt_books) == 1)


def test_update_book():
    test_repo = InMemoryRepository()
    test_val = BookValidator()

    test_srv = BookService(test_repo, test_val)
    test_srv.add_book('1', 'carte1', 'wfsv', 'fwes', 0)
    test_srv.add_book('2', 'carte2', 'fer', 'weri', 0)
    test_srv.add_book('3', 'roman', 'fgs', 'werf', 0)

    updated_book = test_srv.update_book('2', 'CARTE', 'fer', 'weri', 0)

    assert (updated_book.getAutor() == 'weri')
    assert (updated_book.getDescriere() == 'fer')
    assert (updated_book.getTitlu() == 'CARTE')
    assert (updated_book.getNr_Inchirieri() == 0)

    try:
        test_srv.update_book('INVALID ID', 'CARTEEE', 'wfsv', 'fwes', 0)
        assert False
    except BookNotFoundException:
        assert True


def test_filter_by_title():
    test_repo = InMemoryRepository()
    test_val = BookValidator()

    test_srv = BookService(test_repo, test_val)
    test_srv.add_book('1', 'carte1', 'wfsv', 'fwes', 0)
    test_srv.add_book('2', 'carte2', 'fer', 'weri', 0)
    test_srv.add_book('3', 'roman', 'fgs', 'werf', 0)

    filtered_list = test_srv.filter_by_title('carte')
    assert (len(filtered_list) == 2)

    filtered_list = test_srv.filter_by_title('ded')
    assert (len(filtered_list) == 0)

    filtered_list = test_srv.filter_by_title('ro')
    assert (len(filtered_list) == 1)


test_add_books()
test_get_all_books()
test_filter_by_title()
test_delete_book()
test_update_book()
