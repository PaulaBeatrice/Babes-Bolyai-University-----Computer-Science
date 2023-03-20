from domain.entities import Book
from exceptions.exceptions import BookNotFoundException, DuplicateIDException, CorruptedFileException


class InMemoryRepository_list:
    """
        Clasa creata cu responsabilitatea de a gestiona multimea de carti(i.e. sa ofere un depozit persistent
        pentru obiecte de tip Book)
        """

    def __init__(self):
        #books - multimea de carti pe care o gestionam
        self.__books = []

    def find(self, id):
        """
        Cauta o carte cu id-ul dat in lista
        :param id: id-ul dat
        :type id: str
        :return: cartea cu id-ul dat, None daca nu exista carte cu id-ul dat
        :rtype: Book
        """
        for book in self.__books:
            if book.getId() == id:
                return book
        return None

    def library(self, book):
        """
        Adauga o carte in lista
        :param book: cartea de adaugat
        :type book: Book
        :return: -; lista de carti se modifica prin adaugarea cartii dat
        :rtype:
        :raises:
        """
        p = self.find(book.getId())
        if p is not None:
            raise DuplicateIDException
        self.__books.append(book)

    def get_all_books(self):
        """
        Returneaza o lista cu toate produsele disponibile
        rtype: list of Book objects
        """
        return self.__books

    def size(self):
        """
        Returneaza numarul de carti din lista
        :return: numarul de carti din lista
        :rtype:int
        """
        return len(self.__books)

    def delete_book(self, id):
        """
        Sterge cartea cu id-ul dat
        :param id: id dat
        :type id: str
        :return: cartea data
        :rtype: Book
        """
        book = self.find(id)
        if book is None:
            raise BookNotFoundException()
        self.__books.remove(book)
        return book

    def update_book(self, id, book):
        """
        Modifica o carte
        :param id: id-ul cartii de modificat
        :type id: str
        :return: cartea modificata
        :rtype: Book
        :raises: ValueError daca nu exista carte cu identificator id in lista
        """
        b = self.find(id)
        if b is None:
            raise BookNotFoundException()
        b.setTitlu(book.getTitlu())
        b.setDescriere(book.getDescriere())
        b.setAutor(book.getAutor())
        return b

    def delete_all(self):
        """
        Sterge toate cartile din lista
        """
        self.__books.clear()


class InMemoryRepository:
    def __init__(self):
        # books - multimea de carti pe care o gestionam
        self.__books = {}

    def find(self, id):
        """
        Cauta o carte cu id-ul dat in lista
        :param id: id-ul dat
        :type id: str
        :return: cartea cu id-ul dat, None daca nu exista carte cu id-ul dat
        :rtype: Book
        """
        if id in self.__books:
            return self.__books[id]

    def library(self, book):
        """
        Adauga o carte in lista
        :param book: cartea de adaugat
        :type book: Book
        :return: -; lista de carti se modifica prin adaugarea cartii date
        :rtype:
        :raises: ValueError daca exista deja cartea cu id dat
        """

        if book.getId() in self.__books:
            raise DuplicateIDException

        self.__books[book.getId()] = book

    def get_all_books(self):
        """
        Returneaza o lista cu toate produsele disponibile
        rtype: list of Book objects
        """
        return self.__books.values()

    def size(self):
        """
        Returneaza numarul de carti din lista
        :return: numarul de carti din lista
        :rtype:int
        """
        return len(self.__books)

    def delete_book(self, id):
        """
        Sterge cartea cu id-ul dat
        :param id: id dat
        :type id: str
        :return: cartea data
        :rtype: Book
        """
        if id not in self.__books:
            raise BookNotFoundException()

        book = self.__books[id]
        del self.__books[id]
        return book

    def update_book(self, id, book):
        """
        Modifica o carte
        :param id: id-ul cartii de modificat
        :type id: str
        :return: cartea modificata
        :rtype: Book
        :raises: ValueError daca nu exista carte cu identificator id in lista
        """
        if id not in self.__books:
            raise BookNotFoundException()

        self.__books[id] = book
        return book

    def get_all(self):
        """
        Returneaza o lista cu toate cartile disponibile
        :rtype: list of Book objects
        """
        return list(self.__books.values())


def setup_test_repo():
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
    b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', 0)
    b3 = Book('3', 'C', 'fsfsggg', 'Calin', 0)
    b4 = Book('4', 'D', 'fetdbff', 'Denis', 0)
    b5 = Book('5', 'E', 'fsargnm', 'Eric', 0)
    b6 = Book('6', 'F', 'xcvbnnb', 'Florin', 0)
    b7 = Book('7', 'G', 'fsnjinb', 'Georgiana', 0)
    b8 = Book('8', 'H', 'mmmfnnb', 'Horia', 0)
    b9 = Book('9', 'I', 'fsqwerb', 'Ioana', 0)
    b10 = Book('10', 'L', 'ftgfnnb', 'Laura', 0)

    test_repo = InMemoryRepository()
    test_repo.library(b1)
    test_repo.library(b2)
    test_repo.library(b3)
    test_repo.library(b4)
    test_repo.library(b5)
    test_repo.library(b6)
    test_repo.library(b7)
    test_repo.library(b8)
    test_repo.library(b9)
    test_repo.library(b10)
    return test_repo


def test_store():
    test_repo = InMemoryRepository_list()
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
    b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', 0)
    test_repo.library(b1)
    assert (test_repo.size() == 1)
    test_repo.library(b2)
    assert (test_repo.size() == 2)

    try:
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
        test_repo.library(b1)
        assert False
    except DuplicateIDException:
        assert True


def test_delete_by_id():
    test_repo = InMemoryRepository_list()
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
    b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', 0)
    test_repo.library(b1)
    test_repo.library(b2)

    deleted_b = test_repo.delete_book('1')
    assert (deleted_b.getDescriere() == 'fsdfnnb')
    assert (deleted_b.getTitlu() == 'A')
    assert (deleted_b.getAutor() == 'Ana')
    assert (deleted_b.getNr_Inchirieri() == 0)

    try:
        deleted_b = test_repo.delete_book('wrongid')
        assert False
    except BookNotFoundException:
        assert True


def test_update_product():
    test_repo = InMemoryRepository_list()
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 2)
    b2 = Book('1', 'B', 'sgdfrdg', 'Barbu', 3)
    test_repo.library(b1)

    updated_book = test_repo.update_book('1', b2)
    assert (updated_book.getDescriere() == 'sgdfrdg')
    assert (updated_book.getAutor() == 'Barbu')
    assert (updated_book.getTitlu() == 'B')
   # assert (updated_book.getNr_Inchirieri() == 3)
    assert (updated_book.getNr_Inchirieri() == 2)

    try:
        updated_book = test_repo.update_book('77', b1)
        assert False
    except BookNotFoundException:
        assert True


def test_get_all_books():
    test_repo = InMemoryRepository_list()
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
    test_repo.library(b1)

    crt_books = test_repo.get_all_books()
    assert (type(crt_books) == list)
    assert (len(crt_books) == 1)
    assert (crt_books[0].getTitlu() == 'A')

    test_repo2 = setup_test_repo()
    crt_books = test_repo2.get_all_books()
    assert (len(crt_books) == 10)


def test_repo_size():
    test_repo = InMemoryRepository_list()
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
    test_repo.library(b1)
    assert (test_repo.size() == 1)

    test_repo1 = setup_test_repo()
    assert (test_repo1.size() == 10)
    test_repo1.delete_book('4')
    test_repo1.delete_book('5')
    assert (test_repo1.size()== 8)


def test_find():
    test_repo = InMemoryRepository_list()
    b1 = Book('1', 'A', 'fsdfnnb', 'Ana', 0)
    assert (b1.getAutor() == 'Ana')
    assert (b1.getTitlu() == 'A')
    assert (b1.getDescriere() == 'fsdfnnb')

    p2 = test_repo.find('57862')
    assert (p2 is None)


test_store()
test_delete_by_id()
test_update_product()
test_get_all_books()
test_repo_size()
test_find()

class BookRepoFile:
    def __init__(self, filename):
        self.__filename = filename

    def __load_from_file(self):
        try:
            f = open(self.__filename, 'r')
            # f = io.open(self.__filename, mode='r', enconding='utf-8')
        except IOError:
            raise CorruptedFileException()

        books = []
        lines = f.readlines()
        for line in lines:
            book_id, book_titlu, book_descriere, book_autor, book_rents = [token.strip() for token in line.split(';')]
            book = Book(book_id, book_titlu, book_descriere, book_autor, book_rents)
            books.append(book)

        f.close()
        return books

    def __save_to_file(self, books_list):
        with open(self.__filename, 'w') as f:
            for book in books_list:
                book_string = str(book.getId()) + ';' + str(book.getTitlu()) + ';' + str(book.getDescriere()) + ';' + str(book.getAutor()) + ';' + str(book.getNr_Inchirieri()) + '\n'
                f.write(book_string)

    def find(self, id):
        all_books = self.__load_from_file()
        for book in all_books:
            if book.getId() == id:
                return book
        return None

    def __find_by_index(self, all_books, id):
        index = -1
        for i in range(len(all_books)):
            if all_books[i].getId() == id:
                index = i
        return index

    def librabry(self, book):
        """
        Adauga o carte in lista
        :param book: cartea de adaugat
        :type book: Book
        :return: -; lista de carti se modifica prin adaugarea cartii date
        :rtype:
        :raises: DuplicateIDException daca exista deja carte cu id dat
        """
        all_books = self.__load_from_file()
        if book in all_books:
            raise DuplicateIDException()

        all_books.append(book)
        self.__save_to_file(all_books)

    def update(self, id, new_book):
        """
        Modifica o carte
        :param id: id-ul cartii de modificat
        :type id: str
        :return: cartea modificat
        :rtype: Book
        :raises: BookNotFoundException daca nu exista carte cu id-ul dat
        """
        all_books = self.__load_from_file()
        index = self.__find_by_index(all_books, id)
        if index == -1:
            raise BookNotFoundException()

        all_books[index] = new_book
        self.__save_to_file(all_books)
        return new_book

    def delete(self, id):
        all_books = self.__load_from_file()
        index = self.__find_by_index(all_books, id)
        if index == -1:
            raise BookNotFoundException()

        deleted_book = all_books.pop(index)
        self.__save_to_file(all_books)
        return deleted_book

    def size(self):
        return len(self.__load_from_file())

    def delete_all(self):
        self.__save_to_file([])

    def get_all(self):
        return self.__load_from_file()

def test_library_file():
    test_repo = BookRepoFile('test_book.txt')
    test_repo.delete_all()
    test_repo.librabry(Book('550', 'wve', 'fesd', 'efwrd', 0))

    assert (test_repo.size() == 1)

    try:
        test_repo.librabry(Book('550', 'asdv', 'vcx', ' vcd', 2))
        assert False
    except DuplicateIDException:
        assert True

    test_repo.librabry(Book('74', 'fh', 'dfgh', 'dff', 4))
    test_repo.librabry(Book('574150', 'wve', 'sd', 'sdfv', 0))
    test_repo.librabry(Book('662', 'wve', 'sd', 'sdv', 0))
    del_book = test_repo.delete('74')
    assert (del_book.getId() == '74')
    assert (del_book.getTitlu() == 'fh')
    assert (del_book.getDescriere() == 'dfgh')
    assert (del_book.getAutor() == 'dff')
#    assert (del_book.getNr_Inchirieri() == 4)

    assert (test_repo.size() == 3)

    try:
        test_repo.delete('854')
        assert False
    except BookNotFoundException:
        assert True

    new_book = Book('574150', 'frgb', 'df', 'sdfv', 4)
    new_book2 = Book('662', 'sdfv', 'sd', 'wdfv', 0)
    upd_book = test_repo.update('574150', new_book)
    assert (upd_book.getId() == '574150')
    assert (upd_book.getTitlu() == 'frgb')
    assert (upd_book.getDescriere() == 'df')
    assert (upd_book.getAutor() == 'sdfv')
    assert (upd_book.getNr_Inchirieri() == 4)

    try:
        test_repo.update('8941', new_book2)
        assert False
    except BookNotFoundException:
        assert True


test_library_file()
