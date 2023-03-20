import unittest

from exceptions.exceptions import RentItemAlreadyReturned, RentItemNotFoundException


class TestCaseBook(unittest.TestCase):
    def setUp(self) -> None:
        self.__validator = BookValidator()

    def test_create_book(self):
        b1 = Book('1', 'A', 'abc', 'Ana', False)
        self.assertEqual(b1.getId(), '1')
        self.assertEqual(b1.getTitlu(), 'A')
        self.assertEqual(b1.getDescriere(), 'abc')
        self.assertEqual(b1.getAutor(), 'Ana')
        self.assertEqual(b1.getInchiriata(), False)

        b1.setId('W')
        b1.setTitlu('Q')
        b1.setDescriere('da')
        b1.setAutor('B')
        b1.setInchiriata(True)
        self.assertEqual(b1.getId(), 'W')
        self.assertEqual(b1.getTitlu(), 'Q')
        self.assertEqual(b1.getDescriere(), 'da')
        self.assertEqual(b1.getAutor(), 'B')
        self.assertEqual(b1.getInchiriata(), True)

    def test_egual_books(self):
        b1 = Book('1', 'A', 'sdv', 'sc', False)
        b2 = Book('1', 'B', 'sdv', 'sd', False)
        b3 = Book('2', 'C', 'dfg', 'er', False)

        self.assertEqual(b1, b2)
        self.assertNotEqual(b1, b3)
        self.assertNotEqual(b2, b3)

    def test_validate_book(self):
        b = Book('1', 'ABC', 'abcdef', 'Ana', False)
        self.__validator.validate(b)

        b1 = Book('1', '', 'abcdef', 'Ana', False)
        self.assertRaises(ValidationException, self.__validator.validate, b1)

        b2 = Book('1', 'ABC', '', 'Ana', False)
        self.assertRaises(ValidationException, self.__validator.validate, b2)

        b3 = Book('', 'ABC', '', 'Ana', 'sd')
        self.assertRaises(ValidationException, self.__validator.validate, b3)

        b4 = Book('', 'ABC', '', 'Ana', '')
        self.assertRaises(ValidationException, self.__validator.validate, b4)

        b5 = Book('2', 'ABC', 'asdf', '', False)
        self.assertRaises(ValidationException, self.__validator.validate, b5)


import unittest


class TestCaseClient(unittest.TestCase):
    def setUp(self) -> None:
        self.__validator = ClientValidator()

    def test_create_client(self):
        c1 = Client('1', 'A', '0148523698520')
        self.assertEqual(c1.getId(), '1')
        self.assertEqual(c1.getNume(), 'A')
        self.assertEqual(c1.getCnp(), '0148523698520')

        c1.setId('2')
        c1.setNume('B')
        c1.setCnp('1234567890123')
        self.assertEqual(c1.getId(), '2')
        self.assertEqual(c1.getNume(), 'B')
        self.assertEqual(c1.getCnp(), '1234567890123')

    def test_equal_clients(self):
        c1 = Client('1', 'A', '0148523698520')
        c2 = Client('1', 'A', '0148525555520')
        c3 = Client('2', 'A', '0148527896520')

        self.assertEqual(c1, c2)
        self.assertNotEqual(c1, c3)
        self.assertNotEqual(c2, c3)

    def test_validate_client(self):
        c1 = Client('1', 'A', '1478523698520')
        self.__validator.validate(c1)

        c2 = Client('1', '', '0147852369856')
        self.assertRaises(ValidationException, self.__validator.validate, c2)

        c3 = Client('4', 'A', 's147852369852')
        self.assertRaises(ValidationException, self.__validator.validate, c3)

        c4 = Client('4', 'A', '23')
        self.assertRaises(ValidationException, self.__validator.validate, c4)

        c5 = Client('2', 'A', '14785236985207777')
        self.assertRaises(ValidationException, self.__validator.validate, c5)

        c6 = Client('', 'A', '1478523698520')
        self.assertRaises(ValidationException, self.__validator.validate, c6)

        c7 = Client('105', 'A', '147852piu985P0')
        self.assertRaises(ValidationException, self.__validator.validate, c7)

        c8 = Client('1', 'A', '')
        self.assertRaises(ValidationException, self.__validator.validate, c8)


import unittest


class TestCaseRentItem(unittest.TestCase):
    def setUp(self) -> None:
        pass

    def test_create_rent(self):
        b1 = Book('1', 'A', 'dfsd', 'ffsd', False)
        c1 = Client('1', 'dfd', '1479789851254')
        rent_item = RentItem(b1, c1)
        self.assertEqual(rent_item.getIdBook(), b1.getId())
        self.assertEqual(rent_item.getIdClient(), c1.getId())

        b2 = Book('2', 'B', 'as', 'asd', False)
        c2 = Client('2', 'C', '1478523698523')
        rent_item.setBook(b2)
        rent_item.setClient(c2)
        self.assertEqual(rent_item.getIdBook(), b2.getId())
        self.assertEqual(rent_item.getIdClient(), c2.getId())

    def test_equal_rents(self):
        b1 = Book('1', 'A', 'dfsd', 'ffsd', False)
        c1 = Client('1', 'dfd', '1479789851254')

        rent_item = RentItem(b1, c1)
        rent_item2 = RentItem(b1, c1)

        self.assertEqual(rent_item, rent_item2)

        b2 = Book('2', 'B', 'fsvdf', 'wedf', False)
        rent_item3 = RentItem(b2, c1)
        self.assertNotEqual(rent_item, rent_item3)


import unittest


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
        lista = self.__repo.get_all()

        b = self.__repo.find('1', 0, lista)
        self.assertEqual(b.getTitlu(), 'A')
        self.assertEqual(b.getAutor(), 'Ana')

        b2 = self.__repo.find('WRONGID', 0, lista)
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


import unittest

from exceptions.exceptions import DuplicateIDException


class TestCaseClientInMemoryRepo(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = ClientInMemoryRepo()

    def test_store(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.assertEqual(self.__repo.size(), 1)
        self.__repo.store(c2)
        self.assertEqual(self.__repo.size(), 2)
        self.assertRaises(DuplicateIDException, self.__repo.store, c1)

    def test_delete_by_id(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.__repo.store(c2)

        deleted_c = self.__repo.delete('1')
        self.assertEqual(deleted_c.getNume(), 'Alexa')
        self.assertEqual(deleted_c.getCnp(), '1479583002658')
        self.assertRaises(ClientNotFoundException, self.__repo.delete, 'wrongid')

    def test_update(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('1', 'Beatrice', '1479581478658')
        self.__repo.store(c1)

        updated_client = self.__repo.update('1', c2)
        self.assertEqual(updated_client.getNume(), 'Beatrice')
        self.assertEqual(updated_client.getCnp(), '1479581478658')

        self.assertRaises(ClientNotFoundException, self.__repo.update, 'www', c1)

    def test_get_all(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        self.__repo.store(c1)

        crt_clients = self.__repo.get_all()
        self.assertIsInstance(crt_clients, list)
        self.assertEqual(len(crt_clients), 1)
        self.assertEqual(crt_clients[0], c1)

    def test_repo_size(self):
        self.__repo.store(Client('1', 'Alexa', '1479583002658'))
        self.assertEqual(self.__repo.size(), 1)

    def test_find(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        self.__repo.store(c1)

        c = self.__repo.find('1')
        self.assertEqual(c.getCnp(), '1479583002658')
        self.assertEqual(c.getNume(), 'Alexa')

        c2 = self.__repo.find('WRONGID')
        self.assertIs(c2, None)


class TestCaseClientRepoFile(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = ClientRepoFile('clients_test.txt')
        self.__repo.delete_all()

    def test_store(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.assertEqual(self.__repo.size(), 1)
        self.__repo.store(c2)
        self.assertEqual(self.__repo.size(), 2)
        self.assertRaises(DuplicateIDException, self.__repo.store, c1)

    def test_delete_by_id(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.__repo.store(c2)

        deleted_c = self.__repo.delete('1')
        self.assertEqual(deleted_c.getNume(), 'Alexa')
        self.assertEqual(deleted_c.getCnp(), '1479583002658')
        self.assertRaises(ClientNotFoundException, self.__repo.delete, 'wrongid')

    def test_update(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('1', 'Beatrice', '1479581478658')
        self.__repo.store(c1)

        updated_client = self.__repo.update('1', c2)
        self.assertEqual(updated_client.getNume(), 'Beatrice')
        self.assertEqual(updated_client.getCnp(), '1479581478658')

        self.assertRaises(ClientNotFoundException, self.__repo.update, 'www', c1)

    def test_get_all(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        self.__repo.store(c1)

        crt_clients = self.__repo.get_all()
        self.assertIsInstance(crt_clients, list)
        self.assertEqual(len(crt_clients), 1)
        self.assertEqual(crt_clients[0], c1)

    def test_repo_size(self):
        self.__repo.store(Client('1', 'Alexa', '1479583002658'))
        self.assertEqual(self.__repo.size(), 1)

    def test_find(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        self.__repo.store(c1)

        c = self.__repo.find('1')
        self.assertEqual(c.getCnp(), '1479583002658')
        self.assertEqual(c.getNume(), 'Alexa')

        c2 = self.__repo.find('WRONGID')
        self.assertIs(c2, None)


import unittest

from domain.entities import RentItem
from exceptions.exceptions import RentAlreadyAssignedException
from repository.rent_item_repo import RentItemInMemoryRepo


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


import unittest

from domain.validators import BookValidator
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
        self.__book_srv.add_book('1', 'E', 'sdf', 'Erica')
        self.assertIsInstance(self.__book_srv.get_all_books(), list)
        self.assertEqual(len(self.__book_srv.get_all_books()), 1)

        crt_books = self.__book_srv.get_all_books()
        self.assertEqual(crt_books[0].getTitlu(), 'E')

        self.__book_srv.add_book('2', 'F', 'dc', 'Flaviu')
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

    def test_random_book(self):
        b1 = self.__book_srv.generate_book(1)
        list_books = self.__book_srv.get_all_books()
        self.assertEqual(list_books[0].getId, b1.getId)
        self.assertEqual(list_books[0].getTitlu, b1.getTitlu)
        self.assertEqual(list_books[0].getDescriere, b1.getDescriere)
        self.assertEqual(list_books[0].getAutor, b1.getAutor)
        self.assertEqual(list_books[0].getInchiriata, b1.getInchiriata)


import unittest

from domain.validators import ClientValidator
from exceptions.exceptions import ValidationException, ClientNotFoundException
from repository.client_repo import ClientInMemoryRepo
from service.client_service import ClientService


class TestCaseClientService(unittest.TestCase):
    def setUp(self) -> None:
        repo = ClientInMemoryRepo()
        val = ClientValidator()
        self.__client_srv = ClientService(repo, val)

    def test_add_clients(self):
        client = self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.assertEqual(client.getNume(), 'Laura')
        self.assertEqual(client.getCnp(), '1234567890123')

        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)
        self.assertRaises(ValidationException, self.__client_srv.add_client, '2', '', '7778889996665')

    def test_get_all_clients(self):
        self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.assertIsInstance(self.__client_srv.get_all_clients(), list)
        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)

        crt_clients = self.__client_srv.get_all_clients()
        self.assertEqual(crt_clients[0].getNume(), 'Laura')

        self.__client_srv.add_client('2', 'ZUZU', '1999567890123')
        self.assertEqual(len(self.__client_srv.get_all_clients()), 2)

        try:
            self.__client_srv.add_client('2', 'sdc', '1478523698524')
        except:
            pass

        self.assertEqual(len(self.__client_srv.get_all_clients()), 2)

        self.__client_srv.delete_by_id('2')
        crt_clients = self.__client_srv.get_all_clients()
        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)
        self.assertEqual(crt_clients[0].getNume(), 'Laura')

        self.__client_srv.update_client('1', 'ZUZI', '1478523698526')
        crt_clients = self.__client_srv.get_all_clients()
        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)
        self.assertEqual(crt_clients[0].getNume(), 'ZUZI')

    def test_delete_client(self):
        self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.__client_srv.add_client('2', 'Mara', '1234567894423')
        self.__client_srv.add_client('3', 'Nora', '1234500090123')

        deleted_client = self.__client_srv.delete_by_id('3')

        self.assertEqual(len(self.__client_srv.get_all_clients()), 2)

        self.assertEqual(deleted_client.getNume(), 'Nora')
        self.assertEqual(deleted_client.getCnp(), '1234500090123')

        self.assertRaises(ClientNotFoundException, self.__client_srv.delete_by_id, 'WRONG')

    def test_update_client(self):
        self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.__client_srv.add_client('2', 'Mara', '1234567894423')
        self.__client_srv.add_client('3', 'Nora', '1234500090123')

        updated_client = self.__client_srv.update_client('3', 'Crina', '1234567894423')

        self.assertEqual(updated_client.getNume(), 'Crina')
        self.assertEqual(updated_client.getCnp(), '1234567894423')

    def test_filter(self):
        self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.__client_srv.add_client('2', 'Lara', '1234567894423')
        self.__client_srv.add_client('3', 'Nora', '1234500090123')
        lista_clienti = self.__client_srv.get_all_clients()
        filtered_list = []

        self.__client_srv.filter_by_name('La', lista_clienti, 0, filtered_list)
        self.assertEqual(len(filtered_list), 2)

        filtered_list = []
        self.__client_srv.filter_by_name('z', lista_clienti, 0, filtered_list)
        self.assertEqual(len(filtered_list), 0)

        filtered_list = []
        self.__client_srv.filter_by_name('Nor', lista_clienti, 0, filtered_list)
        self.assertEqual(len(filtered_list), 1)

    def test_random_client(self):
        b1 = self.__client_srv.generate_client(1)
        list_clients = self.__client_srv.get_all_clients()
        self.assertEqual(list_clients[0].getId, b1.getId)
        self.assertEqual(list_clients[0].getNume, b1.getNume)
        self.assertEqual(list_clients[0].getCnp, b1.getCnp)


import unittest

from domain.entities import Book, Client
from domain.validators import RentItemValidator
from exceptions.exceptions import BookAlreadyRent, BookNotFoundException
from repository.book_repo import BookRepoFile
from repository.client_repo import ClientRepoFile
from repository.rent_item_repo import RentItemRepoFile
from service.rent_item_service import RentItemService


class TestCaseRentItemService(unittest.TestCase):
    def setUp(self) -> None:
        rent_repo = RentItemRepoFile('rent_item_srv_test.txt')
        rent_repo.delete_all()
        rent_val = RentItemValidator()

        book_repo = BookRepoFile('book_srv_test.txt')
        client_repo = ClientRepoFile('client_srv_test.txt')
        self.__rent_item_service = RentItemService(rent_repo, rent_val, book_repo, client_repo)
        self.__book_repo = book_repo
        self.__client_repo = client_repo

    def test_add_and_return_rent_item(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'C', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.assertEqual(len(self.__rent_item_service.get_all()), 1)
        self.assertRaises(BookAlreadyRent, self.__rent_item_service.add_rent_item, '1', '2')
        self.assertRaises(BookNotFoundException, self.__rent_item_service.add_rent_item, 'wed', '2')
        self.assertRaises(ClientNotFoundException, self.__rent_item_service.add_rent_item, '2', 'scs')
        self.__rent_item_service.return_rent_item('1', '1')
        self.assertRaises(RentAlreadyAssignedException, self.__rent_item_service.add_rent_item, '1', '1')

        self.assertRaises(BookNotFoundException, self.__rent_item_service.return_rent_item, 'cs', '2')
        self.assertRaises(ClientNotFoundException, self.__rent_item_service.return_rent_item, '1', 'asvd')
        self.assertRaises(RentItemAlreadyReturned, self.__rent_item_service.return_rent_item, '1', '1')
        self.assertRaises(RentItemNotFoundException, self.__rent_item_service.return_rent_item, '1', '2')

    def test_get_number_of_rent_books_sorted_by_books(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'C', 'adsdc', 'Amy', False)
        b3 = Book('3', 'C', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        c3 = Client('3', 'Patric', '9999587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__book_repo.store(b3)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__client_repo.store(c3)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.return_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.add_rent_item(b1.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b2.getId(), c2.getId())

        lista_carti = self.__rent_item_service.get_number_of_rent_books_sorted_by_books()
        self.assertEqual(len(lista_carti), 2)
        self.assertEqual(lista_carti[0].getNumeClient(), 'Raul')
        self.assertEqual(lista_carti[1].getNumarCartiInchiriate(), 2)

    def test_top_clients(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'C', 'adsdc', 'Amy', False)
        b3 = Book('3', 'e', 'adsdc', 'Amy', False)
        b4 = Book('4', 't', 'adsdc', 'Amy', False)
        b5 = Book('5', 'y', 'adsdc', 'Amy', False)
        b6 = Book('6', 'w', 'adsdc', 'Amy', False)
        b7 = Book('7', 'u', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        c3 = Client('3', 'Patric', '9999500412580')
        c4 = Client('4', 'Darius', '9999587666680')
        c5 = Client('5', 'Mariana', '9339587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__book_repo.store(b3)
        self.__book_repo.store(b4)
        self.__book_repo.store(b5)
        self.__book_repo.store(b6)
        self.__book_repo.store(b7)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__client_repo.store(c3)
        self.__client_repo.store(c4)
        self.__client_repo.store(c5)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.return_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.add_rent_item(b1.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b2.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b3.getId(), c3.getId())
        self.__rent_item_service.add_rent_item(b4.getId(), c4.getId())
        self.__rent_item_service.add_rent_item(b5.getId(), c5.getId())
        self.__rent_item_service.add_rent_item(b6.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b7.getId(), c4.getId())

        lista_top = self.__rent_item_service.top_clients()
        self.assertEqual(len(lista_top), 1)
        self.assertEqual(lista_top[0].getNumeClient(), 'Beatrice')
        self.assertEqual(lista_top[0].getNumarCartiInchiriate(), 3)

    def test_get_number_of_rent_books_sorted_by_name(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'C', 'adsdc', 'Amy', False)
        b3 = Book('3', 'C', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        c3 = Client('3', 'Patric', '9999587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__book_repo.store(b3)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__client_repo.store(c3)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.return_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.add_rent_item(b1.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b2.getId(), c2.getId())

        lista_carti = self.__rent_item_service.get_number_of_rent_books_sorted_by_name()
        self.assertEqual(len(lista_carti), 2)
        self.assertEqual(lista_carti[0].getNumeClient(), 'Beatrice')
        self.assertEqual(lista_carti[1].getNumarCartiInchiriate(), 1)

    def test_get_number_of_rents(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'C', 'adsdc', 'Amy', False)
        b3 = Book('3', 'C', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        c3 = Client('3', 'Patric', '9999587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__book_repo.store(b3)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__client_repo.store(c3)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.return_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.add_rent_item(b1.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b2.getId(), c2.getId())

        lista_inchirieri = self.__rent_item_service.get_number_of_rents()
        self.assertEqual(len(lista_inchirieri), 2)

    def test_report_by_clients(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'B', 'adsdc', 'Amy', False)
        b3 = Book('3', 'C', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        c3 = Client('3', 'Patric', '9999587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__book_repo.store(b3)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__client_repo.store(c3)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.return_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.add_rent_item(b1.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b2.getId(), c2.getId())

        lista_client_1 = self.__rent_item_service.report_by_clients('1')
        self.assertEqual(len(lista_client_1), 1)
        self.assertEqual(lista_client_1[0], 'A')

        lista_client_2 = self.__rent_item_service.report_by_clients('2')
        self.assertEqual(len(lista_client_2), 2)
        self.assertEqual(lista_client_2[0], 'A')
        self.assertEqual(lista_client_2[1], 'B')

        lista_client_3 = self.__rent_item_service.report_by_clients('3')
        self.assertEqual(len(lista_client_3), 0)

    def test_raport_lab(self):
        self.__book_repo.delete_all()
        self.__client_repo.delete_all()
        b1 = Book('1', 'A', 'asdc', 'Amy', False)
        b2 = Book('2', 'B', 'adsdc', 'Amy', False)
        b3 = Book('3', 'C', 'adsdc', 'Amy', False)
        c1 = Client('1', 'Raul', '9632888412580')
        c2 = Client('2', 'Beatrice', '9632587412580')
        c3 = Client('3', 'Patric', '9999587412580')
        self.__book_repo.store(b1)
        self.__book_repo.store(b2)
        self.__book_repo.store(b3)
        self.__client_repo.store(c1)
        self.__client_repo.store(c2)
        self.__client_repo.store(c3)
        self.__rent_item_service.add_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.return_rent_item(b1.getId(), c1.getId())
        self.__rent_item_service.add_rent_item(b1.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b2.getId(), c2.getId())
        self.__rent_item_service.add_rent_item(b3.getId(), c3.getId())
        lista_raport = self.__rent_item_service.get_raport()
        #1 1
        #1 2
        #2 2
        #3 3
        self.assertEqual(len(lista_raport), 2)
        self.assertEqual(lista_raport[0].getNumeClient(), 'Raul')
        self.assertEqual(lista_raport[1].getNumeClient(), 'Beatrice')
        self.assertEqual(len(lista_raport[0].getListaCarti()), 1)
        self.assertEqual(len(lista_raport[1].getListaCarti()), 2)
