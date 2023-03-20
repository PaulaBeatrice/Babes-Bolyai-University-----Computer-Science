from domain.dtos import ClientBooks, BookRents, ListRentBooks
from domain.entities import RentItem, Book
from exceptions.exceptions import BookNotFoundException, ClientNotFoundException, BookAlreadyRent, \
    RentItemAlreadyReturned, RentItemNotFoundException, RentAlreadyAssignedException


class RentItemService:
    def __init__(self, rent_repo, rent_val, book_repo, client_repo):
        self.__rent_repo = rent_repo
        self.__rent_val = rent_val
        self.__book_repo = book_repo
        self.__client_repo = client_repo

    def add_rent_item(self, book_id, client_id):
        """
        Adauga un RentItem
        :param book_id: id-ul cartii
        :type book_id: str
        :param client_id: id-ul clientului
        :type client_id:str
        :return: RentItem adaugat
        :rype: RentItem
        ::raises:BookNotFoundException daca nu exista carte cu id dat
                 ClientNotFoundException daca nu exista client cu id dat
                 ValidationException daca item-ului nu e valid
                 RentItemAlreadyAssigned daca item-ul deja exista pentru cartea si clientul dat
                 BookAlreadyRent daca cartea este deja inchiriata de alt client
        """
        book = self.__book_repo.find(book_id)
        if book is None:
            raise BookNotFoundException()
        else:
            client = self.__client_repo.find(client_id)
            if client is None:
                raise ClientNotFoundException()
            else:
                if self.__book_repo.find(book_id).getInchiriata() == 'True':
                    raise BookAlreadyRent()
                else:
                    rent_item = RentItem(book, client)
                    self.__rent_val.validate(rent_item)
                    r = self.__rent_repo.find(rent_item)
                    if r is not None:
                        raise RentAlreadyAssignedException()
                    book = rent_item.getBook()
                    new_book = Book(book.getId(), book.getTitlu(), book.getDescriere(), book.getAutor(), True)
                    self.__book_repo.update(book.getId(), new_book)
                    book.setInchiriata(True)
                    self.__rent_repo.store(rent_item)
                    return rent_item

    def get_all(self):
        return self.__rent_repo.get_all()

    def return_rent_item(self, book_id, client_id):
        """
        Clientul cu id ul dat returneaza o carte cu id-ul dat
        :param book_id: id-ul cartii returnate
        :type book_id:str
        :param client_id: id-ul clientului dat
        :type client_id: str
        :return: RentItem modificat
        :rtype: RentItem
        :raises: ValueError daca nu exista inchirierea cu id urile date
        """
        book = self.__book_repo.find(book_id)
        if book is None:
            raise BookNotFoundException()

        client = self.__client_repo.find(client_id)
        if client is None:
            raise ClientNotFoundException()

        rent_item = RentItem(book, client)
        self.__rent_val.validate(rent_item)
        found = self.__rent_repo.find(rent_item)
        if found is None:
            raise RentItemNotFoundException()
        if rent_item.getBook().getInchiriata() == 'False':
            raise RentItemAlreadyReturned()
        rent_item.getBook().setInchiriata(False)
        book = rent_item.getBook()
        new_book = Book(book.getId(), book.getTitlu(), book.getDescriere(), book.getAutor(), False)
        self.__book_repo.update(book.getId(), new_book)

    def get_number_of_rent_books_sorted_by_books(self):
        """
        Calculeaza numarul de carti inchiriate de fiecare client
        :return: lista de ClientBooks DTO
        :rtype: list of ClientBooks objects
        """
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            client = self.__client_repo.find(id_client)
            if client.getId() not in situatie_clienti:
                situatie_clienti[id_client] = 1
            else:
                situatie_clienti[id_client] += 1
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            nr_carti_citite = situatie_clienti[cheie_situatie_clienti]
            client_books_dtos = ClientBooks(client_name, nr_carti_citite)
            res.append(client_books_dtos)

        res.sort(key=lambda x: x.getNumarCartiInchiriate(), reverse=False)
        return res

    def top_clients(self):
        """
        Primii 20% clienti care au inchiriat cele mai multe carti
        """
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            client = self.__client_repo.find(id_client)
            if client.getId() not in situatie_clienti:
                situatie_clienti[id_client] = 1
            else:
                situatie_clienti[id_client] += 1
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            nr_carti_citite = situatie_clienti[cheie_situatie_clienti]
            client_books_dtos = ClientBooks(client_name, nr_carti_citite)
            res.append(client_books_dtos)

        #res.sort(key=lambda x: x.getNumarCartiInchiriate(), reverse=True)
        rezultat = self.shake_sort_with_key(res, lambda x: x.getNumarCartiInchiriate())
        return rezultat[:len(rezultat) // 5]

    def get_number_of_rent_books_sorted_by_name(self):
        """
        Calculeaza numarul de carti inchiriate de fiecare client
        :return: lista de ClientBooks DTO
        :rtype: list of ClientBooks objects
        """
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            client = self.__client_repo.find(id_client)
            if client.getId() not in situatie_clienti:
                situatie_clienti[id_client] = 1
            else:
                situatie_clienti[id_client] += 1
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            nr_carti_citite = situatie_clienti[cheie_situatie_clienti]
            client_books_dtos = ClientBooks(client_name, nr_carti_citite)
            res.append(client_books_dtos)

        #res.sort(key=lambda x: x.getNumeClient(), reverse=False)
        rezultat = self.selection_sort_with_key(res, lambda x: x.getNumeClient())
        return rezultat

    def get_number_of_rents(self):
        """
        Calculeaza numarul de inchirieri pentru fiecare carte
        :return: lista de ClientBooks DTO
        :rtype: list of ClientBooks objects
        """
        all_rent_items = self.__rent_repo.get_all()
        books_rents_dtos = {}
        for rent_item in all_rent_items:
            if rent_item.getBook().getId() not in books_rents_dtos:
                books_rents_dtos[rent_item.getBook().getId()] = BookRents(rent_item.getBook().getTitlu())
            else:
                books_rents_dtos[rent_item.getBook().getId()].IncreaseNumberRents()
        return list(books_rents_dtos.values())

    def report_by_clients(self, client_id):
        """Afiseaza lista de carti inchiriate de un client cu un id dat"""
        all_rent_items = self.__rent_repo.get_all()
        list_books = []
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            if id_client == client_id:
                list_books.append(self.__book_repo.find(id_book).getTitlu())
        return list_books

    def get_raport(self):
        """lista cu clientii care inchiriaza cea mai inchiriata carte, si cartile pe care le inchiriaza"""
        lista = self.get_number_of_rents()
        the_best_book_title = lista[0].getTitluCarte()
        all_rent_items = self.__rent_repo.get_all()
        situatie_clienti = {}
        for rent_item in all_rent_items:
            id_book = rent_item.getBook().getId()
            id_client = rent_item.getClient().getId()
            book = self.__book_repo.find(id_book)
            if book.getTitlu() == the_best_book_title:
                if book.getTitlu() not in situatie_clienti:
                    situatie_clienti[id_client] = book.getId()
        res = []
        for cheie_situatie_clienti in situatie_clienti:
            client_id = self.__client_repo.find(cheie_situatie_clienti).getId()
            client_name = self.__client_repo.find(cheie_situatie_clienti).getNume()
            carti_citite = self.report_by_clients(client_id)
            read_books_dtos = ListRentBooks(client_id, carti_citite, client_name)
            res.append(read_books_dtos)

        return res

    def selection_sort_with_key(self, my_list, key=lambda x: x, cmp=lambda x, y: x <= y, reverse=False):
        for i in range(0, len(my_list) - 1):
            ind = i
            for j in range(i + 1, len(my_list)):
                if cmp(key(my_list[j]), key(my_list[ind])):
                    ind = j
            if i < ind:
                aux = my_list[i]
                my_list[i] = my_list[ind]
                my_list[ind] = aux
        if reverse:
            my_list.reverse()
        return my_list

    def shake_sort_with_key(self, my_list, key=lambda x: x, cmp=lambda x, y: x > y, reverse=False):
        # This sorting algorithm is a modification of the bubble sort algorithm. The algorithm remembers if a swap was
        # necessary and where it was. It also sorts bi-directional.
        # pot sa nu fie definite ambele (key, comparator)
        left = 0
        right = len(my_list) - 1
        lastSwap = 0

        while left < right:
            for i in range(right, left, -1):
                if cmp(key(my_list[i]), key(my_list[i - 1])):
                    aux = my_list[i]
                    my_list[i] = my_list[i - 1]
                    my_list[i - 1] = aux
                    lastSwap = i
            left = lastSwap

            for i in range(left, right):
                if cmp(key(my_list[i + 1]), key(my_list[i])):
                    aux = my_list[i]
                    my_list[i] = my_list[i + 1]
                    my_list[i + 1] = aux
                    lastSwap = i
            right = lastSwap

        return my_list

