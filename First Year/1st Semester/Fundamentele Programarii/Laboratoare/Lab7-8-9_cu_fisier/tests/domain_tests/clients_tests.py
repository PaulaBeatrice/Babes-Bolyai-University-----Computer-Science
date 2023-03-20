import unittest

from domain.entities import Client
from domain.validators import ClientValidator
from exceptions.exceptions import ValidationException


class TestCaseClient(unittest.TestCase):
    def setUp(self) -> None:
        self.__validator = ClientValidator()

    def test_create_client(self):
        c1 = Client('1', 'A', '0148523698520')
        assert (c1.getId() == '1')
        assert (c1.getNume() == 'A')
        assert (c1.getCnp() == '0148523698520')

        c1.setId('2')
        c1.setNume('B')
        c1.setCnp('1234567890123')
        assert (c1.getId() == '2')
        assert (c1.getNume() == 'B')
        assert (c1.getCnp() == '1234567890123')

    def test_equal_clients(self):
        c1 = Client('1', 'A', '0148523698520')
        c2 = Client('1', 'A', '0148525555520')
        c3 = Client('2', 'A', '0148527896520')

        assert (c1 == c2)
        assert (c1 != c3)
        assert (c2 != c3)

    def test_validate_client(self):
        validator = ClientValidator()
        c1 = Client('1', 'A', '1478523698520')
        validator.validate(c1)

        c2 = Client('1', '', '0147852369856')
        try:
            validator.validate(c2)
            assert False
        except ValidationException:
            assert True

        c3 = Client('4', 'A', 's147852369852')
        try:
            validator.validate(c3)
            assert False
        except ValidationException:
            assert True

        c4 = Client('4', 'A', '23')
        try:
            validator.validate(c4)
            assert False
        except ValidationException:
            assert True

        c5 = Client('', 'A', '7147852369852')
        try:
            validator.validate(c5)
            assert False
        except ValidationException:
            assert True

        c6 = Client('', 'A', '1478523698520')
        try:
            validator.validate(c6)
            assert False
        except ValidationException:
            assert True

