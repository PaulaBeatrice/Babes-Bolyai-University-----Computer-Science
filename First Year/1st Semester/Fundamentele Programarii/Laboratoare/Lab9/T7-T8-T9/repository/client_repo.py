from domain.entities import Client
from exceptions.exceptions import DuplicateIDException, ClientNotFoundException


class InMemoryRepository_c:
    def __init__(self):
        self.__clients = {}

    def find(self, id):
        """
        Cauta un client cu id-ul dat in lista
        :param id: id-ul dat
        :type id: str
        :return: clientul cu id-ul dat, None daca nu exista client cu id-ul cautat
        :rtype: Client
        """
        if id in self.__clients:
            return self.__clients[id]
        return None

    def store(self, client):
        """
        Adauga client in lista
        :param client: clientul de adaugat
        :type client: Client
        :return: -; lista de clienti se modifica prin adaugarea clientului dat
        :rtype:
        :raises: DuplicateIDException daca exista deja client cu id dat
        """

        if client.getId() in self.__clients:
            raise DuplicateIDException()

        self.__clients[client.getId()] = client

    def get_all(self):
        """
        Returneaza o lista cu toti clientii
        :rtype: list of Client objects
        """
        return list(self.__clients.values())

    def size(self):
        """
        Returneaza numarul de clienti din lista
        :return: numarul de clienti din lista
        :rtype:int
        """
        return len(self.__clients)

    def delete(self, id):
        """
        Sterge clientul cu id dat
        :param id: id dat
        :type id: str
        :return: clientul sters
        :rtype: Client
        :raises: ClientNotFoundException daca nu exista client cu id-ul dat
        """
        if id not in self.__clients:
            raise ClientNotFoundException()

        client = self.__clients[id]
        del self.__clients[id]
        return client

    def update(self, id, client):
        """
        Modifica un client
        :param id: id-ul clientului de modificat
        :type id: str
        :return: clientul modificat
        :rtype: Client
        :raises: ClientNotFoundException daca nu exista client cu id-ul dat
        """

        if id not in self.__clients:
            raise ClientNotFoundException()

        # old_p = self.__shops[id]
        self.__clients[id] = client
        return client


class InMemoryRepository_list:
    """
        Clasa creata cu responsabilitatea de a gestiona multimea de clienti(i.e. sa ofere un depozit persistent
        pentru obiecte de tip Client
        """

    def __init__(self):
        #clients - multimea de clienti pe care o gestionam
        self.__clients = []

    def clients(self, client):
        """
        Adauga un client in lista
        :param client: clientul de adaugat
        :type client: Client
        :return: -; lista de clienti se modifica prin adaugarea clientului dat
        :rtype:
        :raises:
        """
        c = self.find(client.getId())
        if c is not None:
            raise DuplicateIDException
        self.__clients.append(client)

    def find(self, id):
        """
        Cauta un client cu id-ul dat in lista
        :param id: id-ul dat
        :type id: str
        :return: clientul cu id-ul dat, None daca nu exista clientul cu id-ul dat
        :rtype: Client
        """
        for client in self.__clients:
            if client.getId() == id:
                return client
        return None

    def get_all_clients(self):
        """
        Returneaza o lista cu toti clientii
        rtype: list of Client objects
        """
        return self.__clients

    def size(self):
        """
        Returneaza numarul de clienti din lista
        :return: numarul de clienti din lista
        :rtype:int
        """
        return len(self.__clients)

    def delete_client(self, id):
        """
        Sterge cartea cu id-ul dat
        :param id: id dat
        :type id: str
        :return: cartea data
        :rtype: Book
        """
        client = self.find(id)
        if client is None:
            raise ClientNotFoundException
        self.__clients.remove(client)
        return client

    def update_client(self, id, client):
        """
        Modifica un client
        :param id: id-ul clientului de modificat
        :type id: str
        :return: clientul modificat
        :rtype: Client
        :raises: ValueError daca nu exista client cu identificator id in lista
        """
        c = self.find(id)
        if c is None:
            raise ClientNotFoundException
        c.setNume(client.getNume())
        c.setCnp(client.getCnp())
        return c

    def delete_all(self):
        """
        Sterge toti clientii din lista
        """
        self.__clients.clear()


class InMemoryRepoDict_c:
    def __init__(self):
        self.__clients = {}

    def find(self, id):
        """
        Cauta un client cu id-ul dat in lista
        :param id: id-ul dat
        :type id: str
        :return: clientul cu id-ul dat, None daca nu exista clientul cu id-ul dat
        :rtype: Client
        """
        if id in self.__clients:
            return self.__clients[id]
        return None

    def clients(self, client):
        """
        Adauga un client in lista
        :param client: clientul de adaugat
        :type client: Client
        :return: -; lista de clienti se modifica prin adaugarea clientului dat
        :rtype:
        :raises:
        """
        if client.getId() in self.__clients:
            raise DuplicateIDException

        self.__clients[client.getId()] = client

    def get_all_clients(self):
        """
        Returneaza o lista cu toti clientii
        rtype: list of Client objects
        """
        return self.__clients.values()

    def size(self):
        """
        Returneaza numarul de clienti din lista
        :return: numarul de clienti din lista
        :rtype:int
        """
        return len(self.__clients)

    def delete_client(self, id):
        """
        Sterge cartea cu id-ul dat
        :param id: id dat
        :type id: str
        :return: cartea data
        :rtype: Book
        """
        if id not in self.__clients:
            ClientNotFoundException()

        client = self.__clients[id]
        del self.__clients[id]
        return client

    def update_client(self, id, client):
        """
        Modifica un client
        :param id: id-ul clientului de modificat
        :type id: str
        :return: clientul modificat
        :rtype: Client
        :raises: ValueError daca nu exista client cu identificator id in lista
        """
        if id not in self.__clients:
            raise ClientNotFoundException

        self.__clients[id] = client
        return client


def setup_test_repo():
    c1 = Client('1', 'Alexa', '1479583002658', 0)
    c2 = Client('2', 'Beatrice', '1479581478658', 0)
    c3 = Client('3', 'Cezar', '1414783002658', 0)
    c4 = Client('4', 'Diana', '1479581489658', 0)
    c5 = Client('5', 'Elena', '1449632502658', 0)
    c6 = Client('6', 'Flavia', '1473333002658', 0)
    c7 = Client('7', 'George', '4789552002658', 0)
    c8 = Client('8', 'Ilinca', '7889983002658', 0)
    c9 = Client('9', 'Marina', '1479478502658', 0)
    c10 = Client('10', 'Paula', '1479587777658', 0)

    test_repo = InMemoryRepository_c()
    test_repo.store(c1)
    test_repo.store(c2)
    test_repo.store(c3)
    test_repo.store(c4)
    test_repo.store(c5)
    test_repo.store(c6)
    test_repo.store(c7)
    test_repo.store(c8)
    test_repo.store(c9)
    test_repo.store(c10)
    return test_repo


def test_clients():
    test_repo = InMemoryRepository_list()
    c1 = Client('1', 'Alexa', '1479583002658', 0)
    c2 = Client('2', 'Beatrice', '1479581478658', 0)
    test_repo.clients(c1)
    assert (test_repo.size() == 1)
    test_repo.clients(c2)
    assert (test_repo.size() == 2)

    try:
        c1 = Client('1', 'Alexa', '1479583002658', 0)
        test_repo.clients(c1)
        assert False
    except DuplicateIDException:
        assert True


def test_delete_by_id():
    test_repo = InMemoryRepository_list()
    c1 = Client('1', 'Alexa', '1479583002658', 0)
    c2 = Client('2', 'Beatrice', '1479581478658', 0)
    test_repo.clients(c1)
    test_repo.clients(c2)

    deleted_c = test_repo.delete_client('1')
    assert (deleted_c.getNume() == 'Alexa')
    assert (deleted_c.getCnp() == '1479583002658')
    assert (deleted_c.getNrCarti() == 0)

    try:
        deleted_c = test_repo.delete_client('dsfvdsd')
        assert False
    except ClientNotFoundException:
        assert True


def test_update_client():
    test_repo = InMemoryRepository_list()
    c1 = Client('1', 'Alexa', '1479583002658', 0)
    c2 = Client('1', 'Beatrice', '1479581478658', 1)
    test_repo.clients(c1)

    updated_client = test_repo.update_client('1', c2)
    assert (updated_client.getNume() == 'Beatrice')
    assert (updated_client.getCnp() == '1479581478658')
#    assert (updated_client.getNrCarti() == 1)

    try:
        updated_client = test_repo.update_client('rgb', c1)
        assert False
    except ClientNotFoundException:
        assert True


def test_get_all_clients():
    test_repo = InMemoryRepository_list()
    c1 = Client('1', 'Alexa', '1479583002658', 0)
    test_repo.clients(c1)

    crt_clients = test_repo.get_all_clients()
    assert (type(crt_clients) == list)
    assert (len(crt_clients) == 1)
    assert (crt_clients[0].getNume() == 'Alexa')

    test_repo2 = setup_test_repo()
    crt_clients = test_repo2.get_all()
    assert (len(crt_clients) == 10)


def test_repo_size():
    test_repo = InMemoryRepository_list()
    test_repo.clients(Client('1', 'Alexa', '1479583002658', 1))
    assert (test_repo.size() == 1)

    test_repo1 = setup_test_repo()
    assert (test_repo1.size() == 10)
    test_repo1.delete('7')
    test_repo1.delete('8')
    assert (test_repo1.size() == 8)


def test_find():
    test_repo = setup_test_repo()
    c = test_repo.find('1')
    assert (c.getCnp() == '1479583002658')
    assert (c.getNume() == 'Alexa')

    p2 = test_repo.find('dw89dsdf')
    assert (p2 is None)


test_clients()
test_delete_by_id()
test_update_client()
test_get_all_clients()
test_repo_size()
test_find()


class ClientRepoFile:
    def __init__(self, filename):
        self.__filename = filename

    def __load_from_file(self):
        """
        Incarca clientii din fisier
        :return: lista cu clienti
        :rtype: list of Clients
        :raises: Exception daca nu se poate citi din fisier
        """
        try:
            f = open(self.__filename, 'r')
        except IOError:
            # file doesn't exist
            return
        clients = []
        lines = f.readlines()
        for line in lines:
            client_id, client_nume, client_cnp, client_nrCarti = [word.strip() for word in line.split(';')]
            a = Client(client_id, client_nume, client_cnp, client_nrCarti)
            clients.append(a)
        f.close()
        return clients

    def __save_to_file(self, clients):
        """
        Salveaza in fisier o lista de clienti
        :param clients: lista de clienti care se salveaza
        :type clients: list of Clients
        :return: -; clientii sunt salvati in fisier
        :rtype: -
        :raises: Exception daca nu se poate scrie in fisier
        """
        with open(self.__filename, 'w') as f:
            for client in clients:
                client_string = str(client.getId()) + ';' + str(client.getNume()) + ';' + str(
                    client.getCnp()) + ';' + str(client.getNrCarti()) + '\n'
                f.write(client_string)

    def clients(self, client):
        all_clients = self.__load_from_file()
        if client in all_clients:
            raise DuplicateIDException()
        all_clients.append(client)
        self.__save_to_file(all_clients)

    def __find_by_index(self, clients_list, id):
        index = -1
        for i, c in enumerate(clients_list):
            if c.getId() == id:
                index = i
        return index

    def delete(self, id):
        all_clients = self.__load_from_file()
        poz_to_delete = self.__find_by_index(all_clients, id)
        if poz_to_delete == -1:
            raise ClientNotFoundException()

        del_client = all_clients.pop(poz_to_delete)
        self.__save_to_file(all_clients)
        return del_client

    def find(self, id):
        all_clients = self.__load_from_file()
        for client in all_clients:
            if client.getId() == id:
                return client
        return None

    def update(self, id, new_client):
        all_clients = self.__load_from_file()
        poz = self.__find_by_index(all_clients, id)
        if poz == -1:
            raise ClientNotFoundException()

        all_clients[poz] = new_client
        self.__save_to_file(all_clients)
        return new_client

    def get_all(self):
        return self.__load_from_file()

    def size(self):
        return len(self.__load_from_file())

    def delete_all(self):
        self.__save_to_file([])

def test_clients_file():
    test_repo = ClientRepoFile('test_clients.txt')
    test_repo.delete_all()
    test_repo.clients(Client('550', 'wve', '3669852011559', 0))

    assert (test_repo.size() == 1)

    try:
        test_repo.clients(Client('550', 'asdv', '0223354789978', 0))
        assert False
    except DuplicateIDException:
        assert True

    test_repo.clients(Client('74', 'fh', '1478741025369', 1))
    test_repo.clients(Client('574150', 'wve', '9630145285202', 0))
    test_repo.clients(Client('662', 'wve', '7778852013524', 3))
    del_cl = test_repo.delete('74')
    assert (del_cl.getId() == '74')
    assert (del_cl.getNume() == 'fh')
    assert (del_cl.getCnp() == '1478741025369')
#    assert (del_cl.getNrCarti() == 1)

    assert (test_repo.size() == 3)

    try:
        test_repo.delete('854')
        assert False
    except ClientNotFoundException:
        assert True

    new_cl = Client('574150', 'frgb', '0147852369555', 4)
    new_cl2 = Client('662', 'sdfv', '0045896325111', 1)
    upd_cl = test_repo.update('574150', new_cl)
    assert (upd_cl.getId() == '574150')
    assert (upd_cl.getNume() == 'frgb')
    assert (upd_cl.getCnp() == '0147852369555')
    assert (upd_cl.getNrCarti() == 4)

    try:
        test_repo.update('8941', new_cl2)
        assert False
    except ClientNotFoundException:
        assert True


test_clients_file()

