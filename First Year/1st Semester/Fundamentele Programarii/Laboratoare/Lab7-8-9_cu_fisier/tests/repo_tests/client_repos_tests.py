import unittest

from domain.entities import Client
from exceptions.exceptions import DuplicateIDException, ClientNotFoundException
from repository.client_repo import ClientInMemoryRepo, ClientRepoFile


class TestCaseClientInMemoryRepo(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = ClientInMemoryRepo()

    def test_store(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.assertEqual(self.__repo.size(), 1)
        self.__repo.store(c2)
        self.assertEqual(self.__repo.size(), 2)
        self.assertRaises(DuplicateIDException, self.__repo.store, c1)

    def test_delete_by_id(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.__repo.store(c2)

        deleted_c = self.__repo.delete('1')
        self.assertEqual(deleted_c.getNume(), 'Alexa')
        self.assertEqual(deleted_c.getCnp(), '1479583002658')
        self.assertRaises(ClientNotFoundException, self.__repo.delete, 'wrongid')

    def test_update(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('1', 'Beatrice', '1479581478658')
        self.__repo.store(c1)

        updated_client = self.__repo.update('1', c2)
        self.assertEqual(updated_client.getNume(), 'Beatrice')
        self.assertEqual(updated_client.getCnp(), '1479581478658')

        self.assertRaises(ClientNotFoundException, self.__repo.update, 'www', c1)

    def test_get_all(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        self.__repo.store(c1)

        crt_clients = self.__repo.get_all()
        self.assertIsInstance(crt_clients, list)
        self.assertEqual(len(crt_clients), 1)
        self.assertEqual(crt_clients[0], c1)

    def test_repo_size(self):
        self.__repo.store(Client('1', 'Alexa', '1479583002658'))
        self.assertEqual(self.__repo.size(), 1)

    def test_find(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('1', 'Beatrice', '1479581478658')
        self.__repo.store(c1)

        c = self.__repo.find('1')
        self.assertEqual(c.getCnp(), '1479583002658')
        self.assertEqual(c.getNume(), 'Alexa')

        c2 = self.__repo.find('WRONGID')
        self.assertIs(c2, None)


class TestCaseClientRepoFile(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = ClientRepoFile('clients_test.txt')
        self.__repo.delete_all()

    def test_store(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.assertEqual(self.__repo.size(), 1)
        self.__repo.store(c2)
        self.assertEqual(self.__repo.size(), 2)
        self.assertRaises(DuplicateIDException, self.__repo.store, c1)

    def test_delete_by_id(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('2', 'Beatrice', '1479581478658')
        self.__repo.store(c1)
        self.__repo.store(c2)

        deleted_c = self.__repo.delete('1')
        self.assertEqual(deleted_c.getNume(), 'Alexa')
        self.assertEqual(deleted_c.getCnp(), '1479583002658')
        self.assertRaises(ClientNotFoundException, self.__repo.delete, 'wrongid')

    def test_update(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('1', 'Beatrice', '1479581478658')
        self.__repo.store(c1)

        updated_client = self.__repo.update('1', c2)
        self.assertEqual(updated_client.getNume(), 'Beatrice')
        self.assertEqual(updated_client.getCnp(), '1479581478658')

        self.assertRaises(ClientNotFoundException, self.__repo.update, 'www', c1)

    def test_get_all(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        self.__repo.store(c1)

        crt_clients = self.__repo.get_all()
        self.assertIsInstance(crt_clients, list)
        self.assertEqual(len(crt_clients), 1)
        self.assertEqual(crt_clients[0], c1)

    def test_repo_size(self):
        self.__repo.store(Client('1', 'Alexa', '1479583002658'))
        self.assertEqual(self.__repo.size(), 1)

    def test_find(self):
        c1 = Client('1', 'Alexa', '1479583002658')
        c2 = Client('1', 'Beatrice', '1479581478658')
        self.__repo.store(c1)

        c = self.__repo.find('1')
        self.assertEqual(c.getCnp(), '1479583002658')
        self.assertEqual(c.getNume(), 'Alexa')

        c2 = self.__repo.find('WRONGID')
        self.assertIs(c2, None)




