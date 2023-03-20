

from domain.entities import Book, Client
from exceptions.exceptions import ValidationException


class BookValidator:
    """
    clasa pentru incapsularea algoritmului de validare
    """
    def validate(self, book):
        errors = []
        if book.getTitlu() == '':
            errors.append("Titlul cartii nu poate fi vid.")
        if book.getDescriere() == '':
            errors.append("Descrierea cartii nu poate fi vid.")
        if book.getAutor() == '':
            errors.append("Autorul cartii nu poate fi vid.")
        if book.getId() == '':
            errors.append("Id-ul cartii nu poate fi vid")
        if book.getNr_Inchirieri() == '':
            errors.append("Nr de inchirieri nu poate fi vid")
        if book.getNr_Inchirieri() < 0:
            errors.append("Nr de inchirieri nu poate fi mai mic ca 0")
        if len(errors) > 0:
            raise ValidationException(errors)

        #  error_string = '\n'.join(errors)


def test_validate_book():
    validator = BookValidator()
    b = Book('1', 'ABC', 'abcdef', 'Ana', 3)
    validator.validate(b)

    b1 = Book('1', '', 'abcdef', 'Ana', 0)
    try:
        validator.validate(b1)
        assert False
    except ValidationException:
        assert True

    b2 = Book('1', 'ABC', '', 'Ana', 4)
    try:
        validator.validate(b2)
        assert False
    except ValidationException:
        assert True

    b3 = Book('', 'ABC', '', 'Ana', 4)
    try:
        validator.validate(b3)
        assert False
    except ValidationException:
        assert True

    b4 = Book('wedfv', 'wdc', 'wd', 'sd', -9)
    try:
        validator.validate(b4)
        assert False
    except ValidationException:
        assert True


test_validate_book()


class ClientValidator:
    """
    clasa pentru incapsularea algoritmului de validare
    """
    def validate(self, client):
        errors = []
        if client.getId() == '':
            errors.append("Id-ul clientului nu poate fi vid.")
        if client.getNume() == '':
            errors.append("Numele clientului nu poate fi vid.")
        if client.getCnp() == '':
            errors.append("Cnp-ul clientului nu poate fi vid.")
        if client.getNrCarti() == '':
            errors.append("Nr de carti inchiriate nu poate fi vid")
        if client.getNrCarti() < 0:
            errors.append("Nr de carti inchiriate nu poate fi mai mic ca 0")
        if len(client.getCnp()) != 13:
            errors.append("Cnp-ul clientului trebuie sa contina 13 cifre")
        #cnp_list = []
        cnp_list = client.getCnp()
        ok = True
        for el in cnp_list:
            if el.isalpha():
                ok = False
        if ok == False:
            errors.append("Cnp-ul clientului trebuie sa contina doar cifre")

        if len(errors) > 0:
            #error_string = '\n'.join(errors)
            raise ValidationException(errors)


def test_validate_client():
    validator = ClientValidator()
    c = Client('1', 'Ana', '1456988775602', 0)
    validator.validate(c)

    c1 = Client('2', '', '1456988775602', 1)
    try:
        validator.validate(c1)
        assert False
    except ValidationException:
        assert True

    c2 = Client('3', 'ABC', '1456988775', 4)
    try:
        validator.validate(c2)
        assert False
    except ValidationException:
        assert True

    c3 = Client('4', 'Alex', '', 4)
    try:
        validator.validate(c3)
        assert False
    except ValidationException:
        assert True

    c4 = Client('5', 'Dan', '1456t887s56a2', 7)
    try:
        validator.validate(c4)
        assert False
    except ValidationException:
        assert True

    c5 = Client('', 'Sorina', '1456988775602', 2)
    try:
        validator.validate(c5)
        assert False
    except ValidationException:
        assert True

    c6 = Client('845', 'Ana', '3698521478965', -8)
    try:
        validator.validate(c6)
        assert False
    except ValidationException:
        assert True


test_validate_client()


class RentItemValidator:
    def validate(self, rent_item):
        errors = []
        if len(errors) > 0:
            raise ValidationException(errors)


