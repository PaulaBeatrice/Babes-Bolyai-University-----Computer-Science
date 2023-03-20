from domain.entities import Client
from exceptions.exceptions import CorruptedFileException, DuplicateIDException, ClientNotFoundException


class ClientRepoFile:
    def __init__(self, filename):
        self.__filename = filename

    def __load_from_file(self):
        """
        Incarca datele din fisier
        """
        try:
            f = open(self.__filename, 'r')
        except IOError:
            CorruptedFileException()

        clients = []
        lines = f.readlines()
        for line in lines:
            client_id, client_nume, client_cnp = [token.strip() for token in line.split(';')]
            client = Client(client_id, client_nume, client_cnp)
            clients.append(client)

        f.close()
        return clients

    def __save_to_file(self, client_list):
        """
        Salveaza in fisier
        :param client_list: lista de clienti
        :type client_list: list of Clients
        :return: -; clientii sunt salvati in fisier
        """
        with open(self.__filename, 'w') as f:
            for client in client_list:
                client_string = client.getId() + ';' + client.getNume() + ';' + client.getCnp()
                f.write(client_string)
                f.write('\n')

    def find(self, id):
        """
        Cauta un client dupa un id dat
        :param id: id ul dupa care cautam clientul
        :type id: str
        :return: un obiect de tp Client daca gaseste client cu id ul dat sau None altfel
        """
        all_clients = self.__load_from_file()
        for client in all_clients:
            if client.getId() == id:
                return client
        return None

    def __find_by_index(self, all_clients, id):
        """
        Cauta un client intr o lista de carti dupa un id dat
        :param all_clients: lista cu clienti
        :type all_clients: lista de Client objects
        :param id: id ul dupa care cautam clientul
        :type id: str
        :return: index ul clientului daca il gaseste, sau -1 altfel
        """
        index = -1
        for i in range(len(all_clients)):
            if all_clients[i].getId() == id:
                index = i
        return index

    def store(self, client):
        """
        Adauga un client
        :param client: clientul de adaugat
        :type client: Client
        :return: -; lista de clienti se modifica prin adaugarea clientului dat
        :raises: DuplicateIDException daca exista deja cartea cu id dat
        """
        all_clients = self.__load_from_file()
        if client in all_clients:
            raise DuplicateIDException()

        all_clients.append(client)
        self.__save_to_file(all_clients)

    def update(self, id, new_client):
        """
        Modifica un client
        :param id: id ul clientului de modificat
        :type id: str
        :param new_client: clientul modificat
        :type new_client: Client
        :return: clientul modificat
        :rtype: Client
        :raise: ClientNotFoundException daca nu exista client cu id-ul dat
        """
        all_clients = self.__load_from_file()
        index = self.__find_by_index(all_clients, id)
        if index == -1:
            raise ClientNotFoundException()

        all_clients[index] = new_client
        self.__save_to_file(all_clients)
        return new_client

    def delete(self, id):
        """
        Sterge un client
        :param id: id ul clientului de sters
        :type id: str
        :return: clientul sters
        :rtype: Client
        :raise: ClientNotFoundException daca nu exista client cu id-ul dat
        """
        all_clients = self.__load_from_file()
        index = self.__find_by_index(all_clients, id)
        if index == -1:
            raise ClientNotFoundException()

        deleted_client = all_clients.pop(index)
        self.__save_to_file(all_clients)
        return deleted_client

    def size(self):
        """
        Returneaza numarul de obiecte de tip Client din fisier
        """
        return len(self.__load_from_file())

    def delete_all(self):
        """
        Sterge tot continutul fisierului
        """
        self.__save_to_file([])

    def get_all(self):
        """
        Returneaza tot continutul fisierului
        """
        return self.__load_from_file()


class ClientInMemoryRepo:
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

        self.__clients[id] = client
        return client

    def get_all(self):
        """
        Returneaza o lista cu toti clientii
        :rtype: list of Client objects
        """
        return list(self.__clients.values())


class ClientRepoFileInheritance(ClientInMemoryRepo):
    def __init__(self, filename):
        ClientInMemoryRepo.__init__(self)
        self.__filename = filename
        self.__load_from_file()

    def __load_from_file(self):
        try:
            f = open(self.__filename, 'r')
        except IOError:
            raise CorruptedFileException()

        lines = f.readlines()
        for line in lines:
            if line == '\n':
                break
            client_id, client_nume, client_cnp = [token.strip() for token in line.split(';')]
            client = Client(client_id, client_nume, client_cnp)
            ClientInMemoryRepo.store(self, client)
        f.close()

    def __save_to_file(self):
        client_list = ClientInMemoryRepo.get_all(self)
        with open(self.__filename, 'w') as f:
            for client in client_list:
                client_string = client.getId() + ';' + client.getNume() + ';' + client.getCnp()
                f.write(client_string)
                f.write('\n')

    def store(self, product):
        ClientInMemoryRepo.store(self, product)
        self.__save_to_file()

    def update(self, id, new_client):
        modified_client = ClientInMemoryRepo.update(self, id, new_client)
        self.__save_to_file()
        return modified_client

    def delete(self, id):
        deleted_client = ClientInMemoryRepo.delete(self, id)
        self.__save_to_file()
        return deleted_client

    def find(self, id):
        return ClientInMemoryRepo.find(self, id)

    def get_all(self):
        return ClientInMemoryRepo.get_all(self)

    def size(self):
        return ClientInMemoryRepo.size(self)
