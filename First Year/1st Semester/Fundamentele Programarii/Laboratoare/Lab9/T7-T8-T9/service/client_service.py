import string
import random

from domain.entities import Client
from domain.validators import ClientValidator
from repository.client_repo import InMemoryRepository_c
from exceptions.exceptions import ValidationException, ClientNotFoundException


def random_string_fct(len=None):
    """genereaza string random"""
    len = len if len is not None else random.randint(1, 20)
    lit = string.ascii_letters
    str_list = [random.choice(lit) for _ in range(len)]
    random_str = "".join(str_list)
    return random_str


class ClientService:
    """
        GRASP Controller
        Responsabil de efectuarea operatiilor cerute de utilizator
        Coordoneaza operatiile necesare pentru a realiza actiunea declansata de utilizator
        (i.e. declansare actiune: utilizator -> ui-> obiect tip service in ui -> service -> service coordoneaza
        operatiile folosind alte obiecte (e.g. repo, validator) pentru a realiza efectiv operatia)
    """

    def __init__(self, repo, validator):
        """
        Initializeaza service
        :param repo: obiectul de tip repo care ne ajuta sa gestionam lista de clienti
        :type repo: InMemoryRepository
        :param validator: validatorul pentru verificarea cartilor
        :type validator: ClientValidator
        """
        self.__repo = repo
        self.__validator = validator

    def add_client(self, id, nume, cnp, nrcarti):
        """
        Adauga client
        :param id: id ul clientului
        :param id: str
        :param nume: numele clientului
        :type nume: str
        :param cnp: cnp-ul clientului
        :type cnp: str
        :return: clientul adaugat in lista
        :rtype: ClientService
        :raises: ValueError daca clientul e invalid
        """
        client = Client(id, nume, cnp, nrcarti)
        self.__validator.validate(client)
        self.__repo.store(client)
        return client

    def generate_client(self, number):
        for i in range(number):
            id = random_string_fct(random.randint(1, 5))
            name = random_string_fct(random.randint(1, 20))

            nr = string.digits
            number_list = [random.choice(nr) for n in range(13)]
            cnp = "".join(number_list)
            nrcarti = 0

            client = Client(id, name, cnp, nrcarti)
            self.__validator.validate(client)
            self.__repo.store(client)

    def get_all_clients(self):
        """
        Returneaza o lista cu toti clientii
        :return: lista de clienti
        :rtype: list of Client objects
        """
        return self.__repo.get_all()

    def delete_by_id(self, id):
        """
        Sterge clientul cu id-ul dat
        :param id: id-ul dat
        :type id: str
        :return: clientul sters
        :rtype: Client
        :raises: ValueError daca nu exista client cu id-ul dat
        """
        return self.__repo.delete(id)

    def update_client(self, id, nume, cnp, nrcarti):
        """
        Modifica clientul cu id ul dat cu datele date
        :param id: id-ul dat
        :type id:str
        :param nume: noul nume al clientului
        :type nume: str
        :param cnp: noul cnp al clientului
        :type cnp: str
        :return: clientul modificat
        :rtype: Client
        :raises: ValueError daca nu exista clientul cu id dat
        """
        client = Client(id, nume, cnp, nrcarti)
        self.__validator.validate(client)
        return self.__repo.update(id, client)

    def filter_by_name(self, str_to_search):
        """
        Selecteaza clientii care contin str_to_search in nume
        :param str_to_search: string de cautat in numele clientilor
        :type str_to_search: str
        :return: lista de clienti care au str_to_search in nume
        :rtype: list of Book objects
        """
        all_clients = self.get_all_clients()
        filtered_list = [client for client in all_clients if str_to_search in client.getNume()]
        return filtered_list


def test_add_clients():
    test_repo = InMemoryRepository_c()
    test_val = ClientValidator()

    test_srv = ClientService(test_repo, test_val)

    client = test_srv.add_client('4', 'Elena', '1458918873067', 0)
    assert (client.getId() == '4')
    assert (client.getNume() == 'Elena')
    assert (client.getCnp() == '1458918873067')

    assert (len(test_srv.get_all_clients()) == 1)

    try:
        test_srv.add_client('5', '', '1368900873667', 0)
        assert False
    except ValidationException:
        assert True


def test_get_all_clients():
    test_repo = InMemoryRepository_c()
    test_val = ClientValidator()

    test_srv = ClientService(test_repo, test_val)
    added_client = test_srv.add_client('1', 'Ana', '2589631478526', 0)
    #assert (type(test_srv.get_all_clients()) == list)
    assert (len(test_srv.get_all_clients()) == 1)
    crt_clients = test_srv.get_all_clients()
    assert (crt_clients[0].getNume() == 'Ana')

    added_client2 = test_srv.add_client('2', 'AnaMaria', '2589001478526', 0)
    assert (len(test_srv.get_all_clients()) == 2)
    assert (added_client2.getNume() == 'AnaMaria')
    assert (added_client2.getCnp() == '2589001478526')

    try:
        test_srv.add_client('2', 'frsgs', '2589001478526', 0)
    except:
        pass
    assert (len(test_srv.get_all_clients()) == 2)

    test_srv.delete_by_id('1')
    crt_clients = test_srv.get_all_clients()
    assert (len(crt_clients) == 1)
    assert (crt_clients[0].getNume() == 'AnaMaria')

    test_srv.update_client('2', 'Crina', '1478523690159', 0)
    crt_clients = test_srv.get_all_clients()
    assert (len(crt_clients) == 1)
    assert (crt_clients[0].getNume() == 'Crina')


def test_delete_book():
    test_repo = InMemoryRepository_c()
    test_val = ClientValidator()

    test_srv = ClientService(test_repo, test_val)
    test_srv.add_client('1', 'Ana', '2589631478526', 0)
    test_srv.add_client('2', 'AnaMaria', '2589001478526', 0)
    test_srv.add_client('3', 'Daria', '2589631476626', 0)

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
    except ValueError:
        assert True
    assert (len(crt_clients) == 1)


def test_update_client():
    test_repo = InMemoryRepository_c()
    test_val = ClientValidator()

    test_srv = ClientService(test_repo, test_val)
    test_srv.add_client('1', 'Ana', '2589631478526', 0)
    test_srv.add_client('2', 'AnaMaria', '2589001478526', 0)
    test_srv.add_client('3', 'Daria', '2589631476626', 0)

    updated_client = test_srv.update_client('2', 'Denisa', '2589001478526', 0)

    assert (updated_client.getNume() == 'Denisa')
    assert (updated_client.getCnp() == '2589001478526')

    try:
        test_srv.update_client('INVALID ID', 'Carina', '2589631478526', 0)
        assert False
    except ClientNotFoundException:
        assert True


def test_filter_by_name():
    test_repo = InMemoryRepository_c()
    test_val = ClientValidator()

    test_srv = ClientService(test_repo, test_val)
    test_srv.add_client('1', 'Ana', '2589631478526', 0)
    test_srv.add_client('2', 'AnaMaria', '2589001478526', 0)
    test_srv.add_client('3', 'Daria', '2589631476626', 0)

    filtered_list = test_srv.filter_by_name('Ana')
    assert (len(filtered_list) == 2)

    filtered_list = test_srv.filter_by_name('ds')
    assert (len(filtered_list) == 0)

    filtered_list = test_srv.filter_by_name('Dar')
    assert (len(filtered_list) == 1)


test_add_clients()
test_filter_by_name()
test_update_client()
test_get_all_clients()
