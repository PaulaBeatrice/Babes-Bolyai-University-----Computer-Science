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

    def test_add_rent_item(self):
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


