from exceptions.exceptions import ValidationException


class BookValidator:
    """
    Clasa pentru incapsularea algoritmului de validare a unui produs de tip Book
    """
    def validate(self, book):
        errors = []

        if book.getId() == '':
            errors.append('Id ul cartii nu poate fi vid')
        if book.getTitlu() == '':
            errors.append('Titlul cartii nu poate fi vid')
        if book.getDescriere() == '':
            errors.append('Descrierea cartii nu poate fi vida')
        if book.getAutor() == '':
            errors.append('Autorul cartii nu poate fi vid')
        if book.getInchiriata() == '':
            errors.append('Atributul Inchiriata al cartii nu poate fi vid')

        if type(book.getInchiriata()) is not bool:
            errors.append('Atributul Inchiriata al cartii trebuie sa fie de tip bool')

        if len(errors) > 0:
            raise ValidationException(errors)


class ClientValidator:
    """
    Clasa pentru incapsularea algoritmului de validare a unui obiect de tip client
    """
    def validate(self, client):
        errors = []

        if client.getId() == '':
            errors.append('Id ul clientului nu poate fi vid')
        if client.getNume() == '':
            errors.append('Numele clientului nu poate fi vid')
        if client.getCnp() == '':
            errors.append('Cnp ul clientului nu poate fi vid')

        cnp_list = client.getCnp()
        if len(cnp_list) != 13:
            errors.append('Cnp ul clientului trebuie sa contina exact 13 cifre')
        ok = True
        for el in cnp_list:
            if el.isalpha():
                ok = False
        if ok is False:
            errors.append('Cnp ul clientului trebuie sa contina doar cifre!')

        if len(errors) > 0:
            raise ValidationException(errors)


class RentItemValidator:
    def validate(self, rent_item):
        errors = []
        if len(errors) > 0:
            raise ValidationException(errors)
