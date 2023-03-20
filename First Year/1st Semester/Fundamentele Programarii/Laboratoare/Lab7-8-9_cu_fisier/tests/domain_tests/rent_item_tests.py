import unittest

from domain.entities import Book, Client, RentItem


class TestCaseRentItem(unittest.TestCase):
    def setUp(self) -> None:
        pass

    def test_create_rent(self):
        b1 = Book('1', 'A', 'dfsd', 'ffsd', False)
        c1 = Client('1', 'dfd', '1479789851254')
        rent_item = RentItem(b1, c1)

        assert (rent_item.getBook() == b1)
        assert (rent_item.getClient() == c1)

        b2 = Book('2', 'B', 'as', 'asd', False)
        rent_item.setBook(b2)
        assert (rent_item.getBook() == b2)

    def test_equal_rents(self):
        b1 = Book('1', 'A', 'dfsd', 'ffsd', False)
        c1 = Client('1', 'dfd', '1479789851254')

        rent_item = RentItem(b1, c1)
        rent_item2 = RentItem(b1, c1)

        assert (rent_item == rent_item2)

        b2 = Book('2', 'B', 'fsvdf', 'wedf', False)
        rent_item3 = RentItem(b2, c1)
        assert (rent_item != rent_item3)


