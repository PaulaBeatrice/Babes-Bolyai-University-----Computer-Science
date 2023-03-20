import unittest

from domain.entities import Client, Book, RentItem
from exceptions.exceptions import RentAlreadyAssignedException
from repository.book_repo import BookRepoFile, BookInMemoryRepo
from repository.client_repo import ClientRepoFile
from repository.rent_item_repo import RentItemInMemoryRepo, RentItemRepoFile


class TestCaseRentItemInMemoryRepo(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = RentItemInMemoryRepo()
        self.__repo_book = BookRepoFile('books_test.txt')

    def test_store(self):
        c1 = Client('455', 'dsdf', '3699885522023')
        b1 = Book('965', 'asd', 'wsc', 'wsd', False)
        r1 = RentItem(b1, c1)
        self.__repo.store(r1)

        self.assertEqual(len(self.__repo.get_all()), 1)
        self.assertRaises(RentAlreadyAssignedException, self.__repo.store, r1)

    def test_find(self):
        rent_item = RentItem(Book('1', 'A', 'dc', 'Alice', False), Client('1', 'Andra', '1478523698520'))
        self.__repo.store(rent_item)
        rent_item2 = RentItem(Book('2', 'A', 'dc', 'Alice', False), Client('2', 'Andra', '1478523698520'))

        self.assertEqual(self.__repo.finditem(rent_item), rent_item)
        self.assertIs(None, self.__repo.finditem(rent_item2))

    def test_get_all(self):
        rent_item = RentItem(Book('1', 'A', 'dc', 'Alice', False), Client('1', 'Andra', '1478523698520'))
        self.__repo.store(rent_item)

        crt_rent_items = self.__repo.get_all()
        self.assertIsInstance(crt_rent_items, list)
        self.assertEqual(len(crt_rent_items), 1)
        self.assertEqual(crt_rent_items[0], rent_item)

    def test_size(self):
        rent_item = RentItem(Book('1', 'A', 'dc', 'Alice', False), Client('1', 'Andra', '1478523698520'))
        self.__repo.store(rent_item)
        self.assertEqual(self.__repo.size(), 1)


class TestCaseRentItemRepoFile(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = RentItemRepoFile('rent_items_test.txt')
        self.__repo_book = BookRepoFile('books_test.txt')
        self.__repo_client = ClientRepoFile('clients_test.txt')

    def test_store(self):
        self.__repo.delete_all()
        self.__repo_client.delete_all()
        self.__repo_book.delete_all()
        c1 = Client('455', 'dsdf', '3699885522023')
        b1 = Book('965', 'asd', 'wsc', 'wsd', False)
        r1 = RentItem(b1, c1)
        self.__repo_book.store(b1)
        self.__repo_client.store(c1)
        self.__repo.store(r1)

        self.assertEqual(len(self.__repo.get_all()), 1)

    def test_find(self):
        self.__repo.delete_all()
        self.__repo_client.delete_all()
        self.__repo_book.delete_all()
        c1 = Client('455', 'dsdf', '3699885522023')
        c2 = Client('87', 'dsdf', '3699885522023')
        b1 = Book('965', 'asd', 'wsc', 'wsd', False)
        r1 = RentItem(b1, c1)

        self.__repo_client.store(c1)
        self.__repo_client.store(c2)
        self.__repo_book.store(b1)
        self.__repo.store(r1)
        rent_item2 = RentItem(b1, c2)

        self.assertEqual(self.__repo.find(r1), r1)
        self.assertIs(None, self.__repo.find(rent_item2))

    def test_get_all(self):
        self.__repo.delete_all()
        self.__repo_client.delete_all()
        self.__repo_book.delete_all()
        c1 = Client('455', 'dsdf', '3699885522023')
        b1 = Book('965', 'asd', 'wsc', 'wsd', False)
        r1 = RentItem(b1, c1)

        self.__repo_client.store(c1)
        self.__repo_book.store(b1)
        self.__repo.store(r1)

        crt_rent_items = self.__repo.get_all()
        self.assertIsInstance(crt_rent_items, list)
        self.assertEqual(len(crt_rent_items), 1)
        self.assertEqual(crt_rent_items[0], r1)

    def test_size(self):
        self.__repo.delete_all()
        self.__repo_client.delete_all()
        self.__repo_book.delete_all()
        c1 = Client('455', 'dsdf', '3699885522023')
        b1 = Book('965', 'asd', 'wsc', 'wsd', False)
        r1 = RentItem(b1, c1)

        self.__repo_client.store(c1)
        self.__repo_book.store(b1)
        self.__repo.store(r1)
        self.assertEqual(self.__repo.size(), 1)

