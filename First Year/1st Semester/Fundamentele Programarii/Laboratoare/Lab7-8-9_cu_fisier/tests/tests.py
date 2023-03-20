from domain.entities import Book, Client, RentItem
from domain.validators import BookValidator, ClientValidator, RentItemValidator
from exceptions.exceptions import ValidationException, DuplicateIDException, BookNotFoundException, \
    ClientNotFoundException, RentAlreadyAssignedException, BookAlreadyRent

#ENTITIES
from repository.book_repo import BookRepoFile
from repository.client_repo import ClientRepoFile
from repository.rent_item_repo import RentItemRepoFile
from service.book_service import BookService
from service.client_service import ClientService
from service.rent_item_service import RentItemService


def test_create_book():
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


def test_egual_books():
    b1 = Book('1', 'A', 'sdv', 'sc', False)
    b2 = Book('1', 'B', 'sdv', 'sd', False)
    b3 = Book('2', 'C', 'dfg', 'er', False)

    assert (b1 == b2)
    assert (b1 != b3)
    assert (b2 != b3)


def test_create_client():
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


def test_equal_clients():
    c1 = Client('1', 'A', '0148523698520')
    c2 = Client('1', 'A', '0148525555520')
    c3 = Client('2', 'A', '0148527896520')

    assert (c1 == c2)
    assert (c1 != c3)
    assert (c2 != c3)


def test_create_rent():
    b1 = Book('1', 'A', 'dfsd', 'ffsd', False)
    c1 = Client('1', 'dfd', '1479789851254')
    rent_item = RentItem(b1, c1)

    assert (rent_item.getBook() == b1)
    assert (rent_item.getClient() == c1)

    b2 = Book('2', 'B', 'as', 'asd', False)
    rent_item.setBook(b2)
    assert (rent_item.getBook() == b2)


def test_equal_rents():
    b1 = Book('1', 'A', 'dfsd', 'ffsd', False)
    c1 = Client('1', 'dfd', '1479789851254')

    rent_item = RentItem(b1, c1)
    rent_item2 = RentItem(b1, c1)

    assert(rent_item == rent_item2)

    b2 = Book('2', 'B', 'fsvdf', 'wedf', False)
    rent_item3 = RentItem(b2, c1)
    assert (rent_item != rent_item3)


#VALIDATORS
def test_validate_book():
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

def test_validate_client():
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
        validator.validate(c2)
        assert False
    except ValidationException:
        assert True

#REPOSITORY

def test_repo_book():
    test_repo = BookRepoFile('books_test.txt')
    test_repo.delete_all()
    test_repo.store(Book('1', 'A', 'scs', 'Ana', False))

    assert (test_repo.size() == 1)

    try:
        test_repo.store(Book('1', 'A', 'scs', 'Ana', False))
        assert False
    except DuplicateIDException:
        assert True

def test_repo_client():
    test_repo = ClientRepoFile('clients_test.txt')
    test_repo.delete_all()
    test_repo.store(Client('1', 'A', '1234567899876'))

    assert (test_repo.size() == 1)

    try:
        test_repo.store(Client('1', 'B', '1232112354345'))
        assert False
    except DuplicateIDException:
        assert True

def test_repo_rent_item():
    book_repo = BookRepoFile('books_test.txt')
    test_repo = RentItemRepoFile('rent_items_test.txt')
    test_repo.delete_all()
    b1 = Book('1', 'A', 'wsdfv', 'Andrei', False)
    c1 = Client('1', 'Daria', '1478523698520')

    r1 = RentItem(b1, c1)
    test_repo.store(r1)

    assert (test_repo.size() == 1)

#SERVICE


def test_add_books():
    test_repo = BookRepoFile('books_test.txt')
    test_val = BookValidator()
    test_repo.delete_all()

    test_srv = BookService(test_repo, test_val)

    book = test_srv.add_book('123', 'ABC', 'abcdef', 'Ana')
    assert (book.getTitlu() == 'ABC')
    assert (book.getDescriere() == 'abcdef')

    assert (len(test_srv.get_all_books()) == 1)

    try:
        test_srv.add_book('2', '', 'abcdef', 'Ana')
        assert False
    except ValidationException:
        assert True


def test_get_all_books():
    test_repo = BookRepoFile('books_test.txt')
    test_validator = BookValidator()
    test_repo.delete_all()

    test_srv = BookService(test_repo, test_validator)
    added_book = test_srv.add_book('2', 'Abecedar', 'abcdef', 'Mara')
    assert (len(test_srv.get_all_books()) == 1)
    crt_books = test_srv.get_all_books()
    assert (crt_books[0].getTitlu() == 'Abecedar')

    added_book2 = test_srv.add_book('7', 'Alba-ca-zapada', 'fwdasw', 'Doru')
    assert (len(test_srv.get_all_books()) == 2)
    assert (added_book2.getDescriere() == 'fwdasw')
    assert (added_book2.getAutor() == 'Doru')

    try:
        test_srv.add_book('2', 'wfe', 'fs', 'sffds')
        assert False
    except DuplicateIDException:
        assert True
    assert (len(test_srv.get_all_books()) == 2)

    test_srv.delete_by_id('7')
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 1)
    assert (crt_books[0].getTitlu() == 'Abecedar')

    test_srv.update_book('2', 'ABC', 'sfdfsef', 'Dana', True)
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 1)
    assert (crt_books[0].getTitlu() == 'ABC')


def test_delete_book():
    test_repo = BookRepoFile('books_test.txt')
    test_val = BookValidator()
    test_repo.delete_all()

    test_srv = BookService(test_repo, test_val)
    test_srv.add_book('1', 'carte1', 'wfsv', 'fwes')
    test_srv.add_book('2', 'carte2', 'fer', 'weri')
    test_srv.add_book('3', 'roman', 'fgs', 'werf')

    deleted_book1 = test_srv.delete_by_id('1')
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 2)
    assert (deleted_book1.getTitlu() == 'carte1')
    assert (deleted_book1.getDescriere() == 'wfsv')
    assert (deleted_book1.getAutor() == 'fwes')

    deleted_book2 = test_srv.delete_by_id('2')
    crt_books = test_srv.get_all_books()
    assert (len(crt_books) == 1)
    assert (deleted_book2.getTitlu() == 'carte2')
    assert (deleted_book2.getDescriere() == 'fer')
    assert (deleted_book2.getAutor() == 'weri')

    try:
        test_srv.delete_by_id('dve')
        assert False
    except BookNotFoundException:
        assert True
    assert (len(crt_books) == 1)


def test_update_book():
    test_repo = BookRepoFile('books_test.txt')
    test_val = BookValidator()
    test_repo.delete_all()


    test_srv = BookService(test_repo, test_val)
    test_srv.add_book('1', 'carte1', 'wfsv', 'fwes')
    test_srv.add_book('2', 'carte2', 'fer', 'weri')
    test_srv.add_book('3', 'roman', 'fgs', 'werf')

    updated_book = test_srv.update_book('2', 'CARTE', 'fer', 'weri', True)

    assert (updated_book.getAutor() == 'weri')
    assert (updated_book.getDescriere() == 'fer')
    assert (updated_book.getTitlu() == 'CARTE')
    assert (updated_book.getInchiriata() == True)

    try:
        test_srv.update_book('INVALID ID', 'CARTEEE', 'wfsv', 'fwes', False)
        assert False
    except BookNotFoundException:
        assert True


def test_filter_by_title():
    test_repo = BookRepoFile('books_test.txt')
    test_val = BookValidator()
    test_repo.delete_all()

    test_srv = BookService(test_repo, test_val)
    test_srv.add_book('1', 'carte1', 'wfsv', 'fwes')
    test_srv.add_book('2', 'carte2', 'fer', 'weri')
    test_srv.add_book('3', 'roman', 'fgs', 'werf')

    filtered_list = test_srv.filter_by_title('carte')
    assert (len(filtered_list) == 2)

    filtered_list = test_srv.filter_by_title('ded')
    assert (len(filtered_list) == 0)

    filtered_list = test_srv.filter_by_title('ro')
    assert (len(filtered_list) == 1)


def test_add_clients():
    test_repo = ClientRepoFile('clients_test.txt')
    test_val = ClientValidator()
    test_repo.delete_all()

    test_srv = ClientService(test_repo, test_val)

    client = test_srv.add_client('4', 'Elena', '1458918873067')
    assert (client.getId() == '4')
    assert (client.getNume() == 'Elena')
    assert (client.getCnp() == '1458918873067')

    assert (len(test_srv.get_all_clients()) == 1)

    try:
        test_srv.add_client('5', '', '1368900873667')
        assert False
    except ValidationException:
        assert True

    try:
        test_srv.add_client('4', 'as', '1368900873667')
        assert False
    except DuplicateIDException:
        assert True


def test_get_all_clients():
    test_repo = ClientRepoFile('clients_test.txt')
    test_val = ClientValidator()
    test_repo.delete_all()

    test_srv = ClientService(test_repo, test_val)
    added_client = test_srv.add_client('1', 'Ana', '2589631478526')
    assert (len(test_srv.get_all_clients()) == 1)
    crt_clients = test_srv.get_all_clients()
    assert (crt_clients[0].getNume() == 'Ana')

    added_client2 = test_srv.add_client('2', 'AnaMaria', '2589001478526')
    assert (len(test_srv.get_all_clients()) == 2)
    assert (added_client2.getNume() == 'AnaMaria')
    assert (added_client2.getCnp() == '2589001478526')

    try:
        test_srv.add_client('2', 'frsgs', '2589001478526')
        assert False
    except DuplicateIDException:
        assert True
    assert (len(test_srv.get_all_clients()) == 2)

    test_srv.delete_by_id('1')
    crt_clients = test_srv.get_all_clients()
    assert (len(crt_clients) == 1)
    assert (crt_clients[0].getNume() == 'AnaMaria')

    try:
        test_srv.delete_by_id('2345')
        assert False
    except ClientNotFoundException:
        assert True

    test_srv.update_client('2', 'Crina', '1478523690159')
    crt_clients = test_srv.get_all_clients()
    assert (len(crt_clients) == 1)
    assert (crt_clients[0].getNume() == 'Crina')


def test_delete_client():
    test_repo = ClientRepoFile('clients_test.txt')
    test_val = ClientValidator()
    test_repo.delete_all()

    test_srv = ClientService(test_repo, test_val)
    test_srv.add_client('1', 'Ana', '2589631478526')
    test_srv.add_client('2', 'AnaMaria', '2589001478526')
    test_srv.add_client('3', 'Daria', '2589631476626')

    deleted_client1 = test_srv.delete_by_id('1')
    crt_clients = test_srv.get_all_clients()
    assert (len(crt_clients) == 2)
    assert (deleted_client1.getNume() == 'Ana')
    assert (deleted_client1.getCnp() == '2589631478526')

    deleted_client2 = test_srv.delete_by_id('2')
    crt_clients = test_srv.get_all_clients()
    assert (len(crt_clients) == 1)
    assert (deleted_client2.getNume() == 'AnaMaria')
    assert (deleted_client2.getCnp() == '2589001478526')

    try:
        test_srv.delete_by_id('asv')
        assert False
    except ClientNotFoundException:
        assert True
    assert (len(crt_clients) == 1)


def test_update_client():
    test_repo = ClientRepoFile('clients_test.txt')
    test_val = ClientValidator()
    test_repo.delete_all()

    test_srv = ClientService(test_repo, test_val)
    test_srv.add_client('1', 'Ana', '2589631478526')
    test_srv.add_client('2', 'AnaMaria', '2589001478526')
    test_srv.add_client('3', 'Daria', '2589631476626')

    updated_client = test_srv.update_client('2', 'Denisa', '2589001478526')

    assert (updated_client.getNume() == 'Denisa')
    assert (updated_client.getCnp() == '2589001478526')

    try:
        test_srv.update_client('INVALID ID', 'Carina', '2589631478526')
        assert False
    except ClientNotFoundException:
        assert True


def test_filter_by_name():
    test_repo = ClientRepoFile('clients_test.txt')
    test_val = ClientValidator()
    test_repo.delete_all()

    test_srv = ClientService(test_repo, test_val)
    test_srv.add_client('1', 'Ana', '2589631478526')
    test_srv.add_client('2', 'AnaMaria', '2589001478526')
    test_srv.add_client('3', 'Daria', '2589631476626')

    filtered_list = test_srv.filter_by_name('Ana')
    assert (len(filtered_list) == 2)

    filtered_list = test_srv.filter_by_name('ds')
    assert (len(filtered_list) == 0)

    filtered_list = test_srv.filter_by_name('Dar')
    assert (len(filtered_list) == 1)


def test_rent_and_return_book():
    book_repo = BookRepoFile('books_test.txt')
    client_repo = ClientRepoFile('clients_test.txt')
    rent_repo = RentItemRepoFile('rent_items_test.txt')
    rent_val = RentItemValidator()
    rent_repo.delete_all()
    client_repo.delete_all()
    book_repo.delete_all()

    test_srv = RentItemService(rent_repo, rent_val, book_repo, client_repo)
    b1 = Book('1', 'A', 'wsdfv', 'Andrei', False)
    c1 = Client('1', 'Daria', '1478523698520')
    c2 = Client('2', 'Eric', '1478523698520')
    c3 = Client('3', 'Flavia', '1478523698520')
    book_repo.store(b1)
    client_repo.store(c1)
    client_repo.store(c2)
    client_repo.store(c3)
    test_srv.add_rent_item('1', '1')
    assert (len(test_srv.get_all()) == 1)
    assert (book_repo.find(b1.getId()).getInchiriata() == 'True')

    test_srv.return_rent_item('1', '1')
    assert (book_repo.find(b1.getId()).getInchiriata() == 'False')

    try:
        test_srv.add_rent_item('1', '1')
        assert False
    except RentAlreadyAssignedException:
        assert True

    try:
        test_srv.add_rent_item('100', '1')
        assert False
    except BookNotFoundException:
        assert True

    try:
        test_srv.add_rent_item('1', '100')
        assert False
    except ClientNotFoundException:
        assert True

    assert (book_repo.find(b1.getId()).getInchiriata() == 'False')
    test_srv.add_rent_item('1', '3')

    try:
        test_srv.add_rent_item('1', '2')
        assert False
    except BookAlreadyRent:
        assert True
