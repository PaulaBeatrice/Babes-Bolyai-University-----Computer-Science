import random
import string

from domain.entities import Book


def random_string(len=None):
    """genereaza string random"""
    len = len if len is not None else random.randint(1, 20)
    lit = string.ascii_letters
    str_list = [random.choice(lit) for _ in range(len)]
    random_str = "".join(str_list)
    return random_str


class BookService:
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

    def add_book(self, id, titlu, descriere, autor):
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
        inchiriata = False
        book = Book(id, titlu, descriere, autor, inchiriata)
        self.__validator.validate(book)
        self.__repo.store(book)
        return book

    def generate_book(self, number):
        for i in range(number):
            id = random_string(random.randint(1, 20))
            titlu = random_string(random.randint(1, 20))
            descriere = random_string(random.randint(1, 20))
            autor = random_string(random.randint(1, 20))
            inchiriata = False
            carte = Book(id, titlu, descriere, autor, inchiriata)
            self.__validator.validate(carte)
            self.__repo.store(carte)
            return carte

    def get_all_books(self):
        """
        Returneaza o lista cu toate cartile
        :return: lista de carti
        :rtype: list of Book objects
        """
        return self.__repo.get_all()

    def delete_by_id(self, id):
        """
        Sterge cartea cu id dat
        :param id: id-ul dat
        :type id: str
        :return: cartea stearsa
        :rtype: Book
        :raises: ValueError daca nu exista carte cu id-ul dat
        """
        return self.__repo.delete(id)

    def update_book(self, id, titlu, descriere, autor, inchiriata):
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
        :param inchiriata: stadiul cartii(inchiriata sau nu)
        :type inchiriata: bool
        :return: cartea modificata
        :rtype: Book
        :raises: ValueError daca nu exista cartea cu id-ul dat
        """
        book = Book(id, titlu, descriere, autor, inchiriata)
        self.__validator.validate(book)
        return self.__repo.update(id, book)

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
