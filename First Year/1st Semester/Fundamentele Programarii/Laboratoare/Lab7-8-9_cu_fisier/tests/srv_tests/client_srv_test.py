import unittest

from domain.validators import ClientValidator
from exceptions.exceptions import ValidationException, ClientNotFoundException
from repository.client_repo import ClientInMemoryRepo
from service.client_service import ClientService


class TestCaseClientService(unittest.TestCase):
    def setUp(self) -> None:
        repo = ClientInMemoryRepo()
        val = ClientValidator()
        self.__client_srv = ClientService(repo, val)

    def test_add_clients(self):
        client = self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.assertEqual(client.getNume(), 'Laura')
        self.assertEqual(client.getCnp(), '1234567890123')

        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)
        self.assertRaises(ValidationException, self.__client_srv.add_client, '2', '', '7778889996665')

    def test_get_all_clients(self):
        client = self.__client_srv.add_client('1', 'Laura', '1234567890123')
        self.assertIsInstance(self.__client_srv.get_all_clients(), list)
        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)

        crt_clients = self.__client_srv.get_all_clients()
        self.assertEqual(crt_clients[0].getNume(), 'Laura')

        added_client2 = self.__client_srv.add_client('2', 'ZUZU', '1999567890123')
        self.assertEqual(len(self.__client_srv.get_all_clients()), 2)

        try:
            self.__client_srv.add_client('2', 'sdc', '1478523698524')
        except:
            pass

        self.assertEqual(len(self.__client_srv.get_all_clients()), 2)

        self.__client_srv.delete_by_id('2')
        crt_clients = self.__client_srv.get_all_clients()
        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)
        self.assertEqual(crt_clients[0].getNume(), 'Laura')

        self.__client_srv.update_client('1', 'ZUZI', '1478523698526')
        crt_clients = self.__client_srv.get_all_clients()
        self.assertEqual(len(self.__client_srv.get_all_clients()), 1)
        self.assertEqual(crt_clients[0].getNume(), 'ZUZI')

    def test_delete_client(self):
        client1 = self.__client_srv.add_client('1', 'Laura', '1234567890123')
        client2 = self.__client_srv.add_client('2', 'Mara', '1234567894423')
        client3 = self.__client_srv.add_client('3', 'Nora', '1234500090123')

        deleted_client = self.__client_srv.delete_by_id('3')

        self.assertEqual(len(self.__client_srv.get_all_clients()), 2)

        self.assertEqual(deleted_client.getNume(), 'Nora')
        self.assertEqual(deleted_client.getCnp(), '1234500090123')

        self.assertRaises(ClientNotFoundException, self.__client_srv.delete_by_id, 'WRONG')

    def test_update_client(self):
        client1 = self.__client_srv.add_client('1', 'Laura', '1234567890123')
        client2 = self.__client_srv.add_client('2', 'Mara', '1234567894423')
        client3 = self.__client_srv.add_client('3', 'Nora', '1234500090123')

        updated_client = self.__client_srv.update_client('3', 'Crina', '1234567894423')

        self.assertEqual(updated_client.getNume(), 'Crina')
        self.assertEqual(updated_client.getCnp(), '1234567894423')

    def test_filter(self):
        client1 = self.__client_srv.add_client('1', 'Laura', '1234567890123')
        client2 = self.__client_srv.add_client('2', 'Lara', '1234567894423')
        client3 = self.__client_srv.add_client('3', 'Nora', '1234500090123')

        filtered_list = self.__client_srv.filter_by_name('La')
        self.assertEqual(len(filtered_list), 2)

        filtered_list = self.__client_srv.filter_by_name('z')
        self.assertEqual(len(filtered_list), 0)

        filtered_list = self.__client_srv.filter_by_name('Nor')
        self.assertEqual(len(filtered_list), 1)


