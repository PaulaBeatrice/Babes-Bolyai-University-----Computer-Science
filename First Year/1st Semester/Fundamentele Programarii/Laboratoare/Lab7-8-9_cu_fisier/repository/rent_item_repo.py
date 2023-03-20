from domain.entities import RentItem, Book, Client
from exceptions.exceptions import RentAlreadyAssignedException, CorruptedFileException


class RentItemRepoFile:
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

        rent_items = []
        lines = f.readlines()
        for line in lines:
            if line == '\n':
                break
            book_id, book_titlu, book_descriere, book_autor, client_id, client_nume, client_cnp = [token.strip() for token in line.split(';')]
            book = Book(book_id, book_titlu, book_descriere, book_autor, True)
            client = Client(client_id, client_nume, client_cnp)
            rent_item = RentItem(book, client)
            rent_items.append(rent_item)

        f.close()
        return rent_items

    def __save_to_file(self, rent_item_list):
        """
        Salveaza in fisier
        :param rent_item_list: lista de rent_item uri
        :type rent_item_list: list of RentItems
        :return: -; rent item ul este salvat in fisier sunt salvati in fisier
        """
        with open(self.__filename, 'w') as f:
            for rent_item in rent_item_list:
                rent_item_string = rent_item.getBook().getId() + ';' + rent_item.getBook().getTitlu() + ';' +  rent_item.getBook().getDescriere() \
                        + ';' + rent_item.getBook().getAutor() + ';' + rent_item.getClient().getId()\
                        + ';' + rent_item.getClient().getNume() + ';' + rent_item.getClient().getCnp()
                f.write(rent_item_string)
                f.write('\n')

    def find(self, this_rent_item):
        """
        Cauta un rent item in lista
        :param this_rent_item: rentitem ul care se cauta
        :type this_rent_item: RentItem
        :return: un obiect de tip RentItem daca gaseste RentItemul cu id urile date sau None altfel
        """
        all_rent_items = self.__load_from_file()
        for rent_item in all_rent_items:
            if rent_item.getBook().getId() == this_rent_item.getBook().getId() and rent_item.getClient().getId() == this_rent_item.getClient().getId():
                return rent_item
        return None

    def store(self, rent_item):
        """
        Adauga un rent item
        :param rent_item: rent item de adaugat
        :type rent_item: RentItem
        :return:-; se adauga RentItem in lista
        :raises: RentAlreadyAssignedException daca exista deja item pentru cartea si clientul dat
        """
        all_rent_items = self.__load_from_file()

        all_rent_items.append(rent_item)
        self.__save_to_file(all_rent_items)

    def return_book(self, new_rent_item):
        """
        Returneaza o carte
        :param new_rent_item: Clientul care returneaza o carte
        :type new_rent_item: RentItem
        :return: rentitem ul
        :rtype: RentItem
        :raises: RentItemNotFoundException daca nu exista rentitem-ul, sau RentItemAlreadyReturned daca a fost deja returnat
        """
        return new_rent_item

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


class RentItemInMemoryRepo:
    def __init__(self):
        self.__items = []

    def finditem(self, rent_item):
        """
        Cauta un rent item in lista
        :param rent_item: rent item cautat
        :type rent_item: RentItem
        :return: RentItem daca exista in lista, None altfel
        :rtype: RentItem
        """
        for item in self.__items:
            if item == rent_item:
                return item
        return None

    def store(self, rent_item):
        """
        Adauga un rent item
        :param rent_item: rent item de adaugat
        :type rent_item: RentItem
        :return:-; se adauga RentItem in lista
        :raises: RentAlreadyAssignedException daca exista deja item pentru carte, biblioteca data
        """
        s = self.finditem(rent_item)
        if s is not None:
            raise RentAlreadyAssignedException()
        rent_item.getBook().setInchiriata(True)
        self.__items.append(rent_item)

    def get_all(self):
        """
        Returneaza toti itemii din lista
        :return: lista de itemi
        :rtype: list of RentItem objects
        """
        return self.__items

    def size(self):
        return len(self.__items)


class RentItemRepoFileInherritance(RentItemInMemoryRepo):
    def __init__(self, filename):
        RentItemInMemoryRepo.__init__(self)
        self.__filename = filename
        self.__load_from_file()

    def __load_from_file(self):
        """
        Incarca rent items din fisier
        """
        try:
            f = open(self.__filename, 'r')
        except IOError:
            return

        lines = f.readlines()
        for line in lines:
            if line == '\n':
                break
            book_id, book_titlu, book_descriere, book_autor, client_id, client_nume, client_cnp = [token.strip() for
                                                                                                   token in
                                                                                                   line.split(';')]
            book = Book(book_id, book_titlu, book_descriere, book_autor, True)
            client = Client(client_id, client_nume, client_cnp)
            rent_item = RentItem(book, client)
            RentItemInMemoryRepo.store(self, rent_item)

        f.close()

    def __save_to_file(self):
        """
        Salveaza rent items in fisier
        """
        all_items = RentItemInMemoryRepo.get_all(self)
        f = open(self.__filename, 'w')
        for item in all_items:
            item_string = str(item.getBook().getId()) + ';' + str(item.getBook().getTitlu) + ';' + str(
                item.getBook().getDescriere()) + ';' + str(item.getBook().getAutor()) + ';' + str(
                item.getBook().getInchiriata()) + ';' + str(item.getClient().getId()) + ';' + str(
                item.getClient().getNume()) + ';' + str(item.getClient().getCnp()) + '\n'
            f.write(item_string)

    def store(self, rent_item):
        """
        Adauga un rent item
        :param rent_item: rent_item de adaugat
        :type rent_item: rent_item
        :return:-; se adauga rent_item in lista si in fisier
        :raises: RentItemAlreadyAssignedException daca exista deja item pentru produs, magazinul dat
        """
        RentItemInMemoryRepo.store(self, rent_item)
        self.__save_to_file()

    def find(self, sale_item):
        return RentItemInMemoryRepo.find(self, sale_item)

    def get_all(self):
        return RentItemInMemoryRepo.get_all(self)

    def delete_all(self):
        """
        Sterge toti rent items din lista (si fisier)
        """
        RentItemInMemoryRepo.delete_all(self)
        self.__save_to_file()
