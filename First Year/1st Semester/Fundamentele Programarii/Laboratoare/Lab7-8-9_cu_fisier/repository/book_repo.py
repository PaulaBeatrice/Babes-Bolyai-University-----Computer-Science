from domain.entities import Book
from exceptions.exceptions import CorruptedFileException, DuplicateIDException, BookNotFoundException


class BookRepoFile:
    def __init__(self, filename):
        self.__filename = filename

    def __load_from_file(self):
        """
        Incarca datele din fisier
        """
        try:
            f = open(self.__filename, 'r')
        except IOError:
            CorruptedFileException()

        books = []
        lines = f.readlines()
        for line in lines:
            if line == '\n':
                break
            book_id, book_titlu, book_descriere, book_autor, book_inchiriata = [token.strip() for token in line.split(';')]
            book = Book(book_id, book_titlu, book_descriere, book_autor, book_inchiriata)
            books.append(book)

        f.close()
        return books

    def __save_to_file(self, books_list):
        """
        Salveaza in fisier
        :param books_list: lista de carti
        :type books_list: list of Books
        :return: -;cartile sunt salvate in fisier
        :rtype:
        """
        with open(self.__filename, 'w') as f:
            for book in books_list:
                book_string = str(book.getId()) + ';' + str(book.getTitlu()) + ';' + str(book.getDescriere()) \
                + ';' + str(book.getAutor()) + ';' + str(book.getInchiriata())
                f.write(book_string)
                f.write('\n')

    def find(self, id):
        """
        Cauta o carte in fisier dupa un id dat
        :param id: id ul dupa care cautam cartea
        :type id: str
        :return: obiect de tipul Book daca gaseste carte cu id ul dat, None altfel
        """
        all_books = self.__load_from_file()
        for book in all_books:
            if book.getId() == id:
                return book
        return None

    def __find_by_index(self, all_books, id):
        """
        Cauta o carte intr o lista de carti dupa un id dat
        :param all_books: lista cu carti
        :type all_books: lista de Book objects
        :param id: id ul dua care cautam cartea
        :type id: str
        :return: index ul cartii daca o gaseste, sau -1 altfel
        """
        index = -1
        for i in range(len(all_books)):
            if all_books[i].getId() == id:
                index = i
        return index

    def store(self, book):
        """
        Adauga o carte in lista
        :param book: cartea de adaugat
        :type book: Book
        :return: -; lista de carti se modifica prin adaugarea cartii date
        :rtype:
        :raises: DuplicateIDException daca exista deja cartea cu id dat
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
        :return: cartea modificata
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
        """
        Sterge o carte
        :param id: id-ul cartii de sters
        :type id: str
        :return: cartea stearsa
        :rtype: Book
        :raises: BookNotFoundException daca nu exista carte cu id-ul dat
        """
        all_books = self.__load_from_file()
        index = self.__find_by_index(all_books, id)
        if index == -1:
            raise BookNotFoundException()

        deleted_book = all_books.pop(index)
        self.__save_to_file(all_books)
        return deleted_book

    def size(self):
        """
        Returneaza numarul de obiecte de tip Book din fisier
        """
        return len(self.__load_from_file())

    def delete_all(self):
        """
        Sterge tot continutul fisierului
        """
        self.__save_to_file([])

    def get_all(self):
        """
        Returneaza tot continutul fisierului
        """
        return self.__load_from_file()


class BookInMemoryRepo:
    def __init__(self):
        # books - multimea de carti pe care o gestionam
        self.__books = {}

   # def find(self, id):
   #     """
   #     Cauta o carte cu id-ul dat in lista
   #     :param id: id-ul dat
   #     :type id: str
   #     :return: cartea cu id-ul dat, None daca nu exista carte cu id-ul dat
   #     :rtype: Book
   #     """
   #     if id in self.__books:
   #         return self.__books[id]

    def find(self, id, poz, lista):
        if poz >= len(lista):
            return None
        elif id == lista[poz].getId():
            return lista[poz]
        else:
            self.find(id, poz + 1, lista)

    def store(self, book):
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

    def size(self):
        """
        Returneaza numarul de carti din lista
        :return: numarul de carti din lista
        :rtype:int
        """
        return len(self.__books)

    def delete(self, id):
        """
        Sterge cartea cu id-ul dat
        :param id: id dat
        :type id: str
        :return: cartea data
        :rtype: Book
        """
        lista = self.get_all()
        if self.find(id, 0, lista) is None:
            raise BookNotFoundException()

        book = self.__books[id]
        del self.__books[id]
        return book

    def update(self, id, book):
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
        Returneaza o lista cu toate cartile
        :rtype: list of Book objects
        """
        return list(self.__books.values())


class BookRepoFileInheritance(BookInMemoryRepo):
    def __init__(self, filename):
        BookInMemoryRepo.__init__(self)
        self.__filename = filename
        self.__load_from_file()

    def __load_from_file(self):
        try:
            f = open(self.__filename, 'r')
        except IOError:
            raise CorruptedFileException()

        lines = f.readlines()
        for line in lines:
            if lines == '\n':
                break
            book_id, book_titlu, book_descriere, book_autor, book_inchiriata = [token.strip() for token in
                                                                                line.split(';')]
            book = Book(book_id, book_titlu, book_descriere, book_autor, book_inchiriata)
            BookInMemoryRepo.store(book)
        f.close()

    def __save_to_file(self):
        books_list = BookInMemoryRepo.get_all(self)
        with open(self.__filename, 'w') as f:
            for book in books_list:
                book_string = str(book.getId()) + ';' + str(book.getTitlu()) + ';' + str(book.getDescriere()) \
                              + ';' + str(book.getAutor()) + ';' + str(book.getInchiriata())
                f.write(book_string)
                f.write('\n')

    def store(self, product):
        BookInMemoryRepo.store(self, product)
        self.__save_to_file()

    def update(self, id, new_book):
        modified_book = BookInMemoryRepo.update(self, id, new_book)
        self.__save_to_file()
        return modified_book

    def delete(self, id):
        deleted_book = BookInMemoryRepo.delete(self, id)
        self.__save_to_file()
        return deleted_book

    def find(self, id):
        return BookInMemoryRepo.find(self, id)

    def get_all(self):
        return BookInMemoryRepo.get_all(self)

    def size(self):
        return BookInMemoryRepo.size(self)
