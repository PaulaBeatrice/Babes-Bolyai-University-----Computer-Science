import unittest

from domain.validators import BookValidator
from exceptions.exceptions import ValidationException, BookNotFoundException
from repository.book_repo import BookInMemoryRepo
from service.book_service import BookService


class TestCaseBookService(unittest.TestCase):
    def setUp(self) -> None:
        repo = BookInMemoryRepo()
        val = BookValidator()
        self.__book_srv = BookService(repo, val)

    def test_add_book(self):
        book = self.__book_srv.add_book('1', 'A', 'asd', 'Ana')
        self.assertEqual(book.getTitlu(), 'A')
        self.assertEqual(book.getDescriere(), 'asd')

        self.assertEqual(len(self.__book_srv.get_all_books()), 1)
        self.assertRaises(ValidationException, self.__book_srv.add_book, '2', '', 'sd', 'Bau')

    def test_get_all_books(self):
        added_books = self.__book_srv.add_book('1', 'E', 'sdf', 'Erica')
        self.assertIsInstance(self.__book_srv.get_all_books(), list)
        self.assertEqual(len(self.__book_srv.get_all_books()), 1)

        crt_books = self.__book_srv.get_all_books()
        self.assertEqual(crt_books[0].getTitlu(), 'E')

        added_book2 = self.__book_srv.add_book('2', 'F', 'dc', 'Flaviu')
        self.assertEqual(len(self.__book_srv.get_all_books()), 2)

        try:
            self.__book_srv.add_book('1', 'sdc', 'sdc', 'sd')
        except:
            pass

        self.assertEqual(len(self.__book_srv.get_all_books()), 2)
        self.__book_srv.delete_by_id('1')
        crt_books = self.__book_srv.get_all_books()
        self.assertEqual(len(self.__book_srv.get_all_books()), 1)
        self.assertEqual(crt_books[0].getTitlu(), 'F')

        self.__book_srv.update_book('2', 'Z', 'z', 'Z', False)
        crt_books = self.__book_srv.get_all_books()
        self.assertEqual(len(self.__book_srv.get_all_books()), 1)
        self.assertEqual(crt_books[0].getTitlu(), 'Z')

    def test_delte_book(self):
        self.__book_srv.add_book('1', 'A', 'acs', 'sd')
        self.__book_srv.add_book('2', 'B', 'df', 'sd')
        self.__book_srv.add_book('3', 'C', 'd', 'sd')

        deleted_book1 = self.__book_srv.delete_by_id('1')

        self.assertEqual(len(self.__book_srv.get_all_books()), 2)

        self.assertEqual(deleted_book1.getTitlu(), 'A')
        self.assertEqual(deleted_book1.getDescriere(), 'acs')

        self.assertRaises(BookNotFoundException, self.__book_srv.delete_by_id, 'WRONG')

    def test_update_book(self):
        self.__book_srv.add_book('1', 'A', 'acs', 'sd')
        self.__book_srv.add_book('2', 'B', 'df', 'sd')
        self.__book_srv.add_book('3', 'C', 'd', 'sd')

        updated_book = self.__book_srv.update_book('1', 'S', 'asdc', 'wsd', False)

        self.assertEqual(updated_book.getTitlu(), 'S')
        self.assertEqual(updated_book.getAutor(), 'wsd')

    def test_filter(self):
        self.__book_srv.add_book('1', 'Asd', 'acs', 'sd')
        self.__book_srv.add_book('2', 'ABs', 'df', 'sd')
        self.__book_srv.add_book('3', 'Csws', 'd', 'sd')

        filtered_list = self.__book_srv.filter_by_title('A')
        assert (len(filtered_list) == 2)

        filtered_list = self.__book_srv.filter_by_title('ded')
        assert (len(filtered_list) == 0)

        filtered_list = self.__book_srv.filter_by_title('Cs')
        assert (len(filtered_list) == 1)
