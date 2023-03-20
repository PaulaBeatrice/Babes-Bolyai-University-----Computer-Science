import random
import string

from domain.entities import Client


def random_string_fct(len=None):
    """genereaza string random"""
    len = len if len is not None else random.randint(1, 20)
    lit = string.ascii_letters
    str_list = [random.choice(lit) for _ in range(len)]
    random_str = "".join(str_list)
    return random_str


class ClientService:
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

    def add_client(self, id, nume, cnp):
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
        client = Client(id, nume, cnp)
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

            client = Client(id, name, cnp)
            self.__validator.validate(client)
            self.__repo.store(client)
            return client

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

    def update_client(self, id, nume, cnp):
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
        client = Client(id, nume, cnp)
        self.__validator.validate(client)
        return self.__repo.update(id, client)

    def filter_by_name(self, str_to_search, list_of_clients, poz, filtered_list):
        """
        Selecteaza clientii care contin str_to_search in nume
        :param str_to_search: string de cautat in numele clientilor
        :type str_to_search: str
        :return: lista de clienti care au str_to_search in nume
        :rtype: list of Book objects
        """
        if poz >= len(list_of_clients):
            return
        elif str_to_search in list_of_clients[poz].getNume():
            filtered_list.append(list_of_clients[poz])
            self.filter_by_name(str_to_search, list_of_clients, poz + 1, filtered_list)
        else:
            self.filter_by_name(str_to_search, list_of_clients, poz + 1, filtered_list)
