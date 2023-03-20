import unittest

from domain.entities import Book
from exceptions.exceptions import DuplicateIDException, BookNotFoundException
from repository.book_repo import BookInMemoryRepo, BookRepoFile


class TestCaseBookInMemoryRepo(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = BookInMemoryRepo()

    def test_store(self):
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', False)
        self.__repo.store(b1)
        self.assertEqual(self.__repo.size(), 1)
        self.__repo.store(b2)
        self.assertEqual(self.__repo.size(), 2)
        self.assertRaises(DuplicateIDException, self.__repo.store, b1)

    def test_delete_by_id(self):
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', False)
        self.__repo.store(b1)
        self.__repo.store(b2)

        deleted_b = self.__repo.delete('1')
        self.assertEqual(deleted_b.getTitlu(), 'A')
        self.assertEqual(deleted_b.getAutor(), 'Ana')
        self.assertRaises(BookNotFoundException, self.__repo.delete, 'wrongid')

    def test_update(self):
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', False)
        self.__repo.store(b1)

        updated_book = self.__repo.update('1', b2)
        self.assertEqual(updated_book.getTitlu(), 'B')
        self.assertEqual(updated_book.getDescriere(), 'sgdfrdg')

        self.assertRaises(BookNotFoundException, self.__repo.update, 'we', b1)

    def test_get_all(self):
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        self.__repo.store(b1)

        crt_books = self.__repo.get_all()
        self.assertIsInstance(crt_books, list)
        self.assertEqual(len(crt_books), 1)
        self.assertEqual(crt_books[0], b1)

    def test_repo_size(self):
        self.__repo.store(Book('1', 'A', 'fsdfnnb', 'Ana', False))
        self.assertEqual(self.__repo.size(), 1)

    def test_find(self):
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        self.__repo.store(b1)

        b = self.__repo.find('1')
        self.assertEqual(b.getTitlu(), 'A')
        self.assertEqual(b.getAutor(), 'Ana')

        b2 = self.__repo.find('WRONGID')
        self.assertIs(b2, None)


class TestCaseBookRepoFile(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = BookRepoFile('books_test.txt')

    def test_store(self):
        self.__repo.delete_all()
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', False)
        self.__repo.store(b1)
        self.assertEqual(self.__repo.size(), 1)
        self.__repo.store(b2)
        self.assertEqual(self.__repo.size(), 2)
        self.assertRaises(DuplicateIDException, self.__repo.store, b1)

    def test_delete_by_id(self):
        self.__repo.delete_all()
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', False)
        self.__repo.store(b1)
        self.__repo.store(b2)

        deleted_b = self.__repo.delete('1')
        self.assertEqual(deleted_b.getTitlu(), 'A')
        self.assertEqual(deleted_b.getAutor(), 'Ana')
        self.assertRaises(BookNotFoundException, self.__repo.delete, 'wrongid')

    def test_update(self):
        self.__repo.delete_all()
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        b2 = Book('2', 'B', 'sgdfrdg', 'Barbu', False)
        self.__repo.store(b1)

        updated_book = self.__repo.update('1', b2)
        self.assertEqual(updated_book.getTitlu(), 'B')
        self.assertEqual(updated_book.getDescriere(), 'sgdfrdg')

        self.assertRaises(BookNotFoundException, self.__repo.update, 'we', b1)

    def test_get_all(self):
        self.__repo.delete_all()
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        self.__repo.store(b1)

        crt_books = self.__repo.get_all()
        self.assertIsInstance(crt_books, list)
        self.assertEqual(len(crt_books), 1)
        self.assertEqual(crt_books[0], b1)

    def test_repo_size(self):
        self.__repo.delete_all()
        self.__repo.store(Book('1', 'A', 'fsdfnnb', 'Ana', False))
        self.assertEqual(self.__repo.size(), 1)

    def test_find(self):
        self.__repo.delete_all()
        b1 = Book('1', 'A', 'fsdfnnb', 'Ana', False)
        self.__repo.store(b1)

        b = self.__repo.find('1')
        self.assertEqual(b.getTitlu(), 'A')
        self.assertEqual(b.getAutor(), 'Ana')

        b2 = self.__repo.find('WRONGID')
        self.assertIs(b2, None)







