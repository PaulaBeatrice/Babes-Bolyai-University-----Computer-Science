import unittest

from domain.entities import Book
from domain.validators import BookValidator
from exceptions.exceptions import ValidationException


class TestCaseBook(unittest.TestCase):
    def setUp(self) -> None:
        self.__validator = BookValidator()


    def test_create_book(self):
        b1 = Book('1', 'A', 'abc', 'Ana', False)
        assert (b1.getId() == '1')
        assert (b1.getTitlu() == 'A')
        assert (b1.getDescriere() == 'abc')
        assert (b1.getAutor() == 'Ana')
        assert (b1.getInchiriata() is False)

        b1.setId('W')
        b1.setTitlu('Q')
        b1.setDescriere('da')
        b1.setAutor('B')
        b1.setInchiriata(True)
        assert (b1.getId() == 'W')
        assert (b1.getTitlu() == 'Q')
        assert (b1.getDescriere() == 'da')
        assert (b1.getAutor() == 'B')
        assert (b1.getInchiriata() is True)


    def test_egual_books(self):
        b1 = Book('1', 'A', 'sdv', 'sc', False)
        b2 = Book('1', 'B', 'sdv', 'sd', False)
        b3 = Book('2', 'C', 'dfg', 'er', False)

        assert (b1 == b2)
        assert (b1 != b3)
        assert (b2 != b3)

    def test_validate_book(self):
        validator = BookValidator()
        b = Book('1', 'ABC', 'abcdef', 'Ana', False)
        validator.validate(b)

        b1 = Book('1', '', 'abcdef', 'Ana', False)
        try:
            validator.validate(b1)
            assert False
        except ValidationException:
            assert True

        b2 = Book('1', 'ABC', '', 'Ana', False)
        try:
            validator.validate(b2)
            assert False
        except ValidationException:
            assert True

        b3 = Book('', 'ABC', '', 'Ana', 'sd')
        try:
            validator.validate(b3)
            assert False
        except ValidationException:
            assert True

        b4 = Book('', 'ABC', '', 'Ana', '')
        try:
            validator.validate(b4)
            assert False
        except ValidationException:
            assert True
