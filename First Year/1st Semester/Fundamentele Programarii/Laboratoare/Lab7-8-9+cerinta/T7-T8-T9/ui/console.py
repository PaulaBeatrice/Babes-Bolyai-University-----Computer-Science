import random


from termcolor import colored

from exceptions.exceptions import ValidationException, DuplicateIDException, BookNotFoundException, ClientNotFoundException, RentAlreadyAssignedException, RentItemNotFoundException, RentItemAlreadyReturned


import string

from domain.entities import Client


class Console:
    def __init__(self, book_srv, client_srv, rent_item_srv):
        self.__book_srv = book_srv
        self.__client_srv = client_srv
        self.__srv_rent_item = rent_item_srv

    def __show_all_books(self):
        """
        Afiseaza toate cartile disponibile
        """
        books = self.__book_srv.get_all_books()
        if len(books) == 0:
            print(colored('Nu exista carti in lista.', 'magenta'))
        else:
            print('Lista de carti este:')
            for book in books:
                print('Id:', colored(book.getId(), 'cyan'), '-Titlu:', colored(book.getTitlu(), 'cyan'), '-Descriere:', colored(book.getDescriere(), 'cyan'), '-Autor:', colored(book.getAutor(), 'cyan'))

    def __show_all_clients(self):
        """
        Afiseaza toti clientii bibliotecii
        """
        clients = self.__client_srv.get_all_clients()
        if len(clients) == 0:
            print(colored('Nu exista clienti in lista.', 'magenta'))
        else:
            print('Lista de clienti este:')
            for client in clients:
                print('Id:', colored(client.getId(), 'cyan'), '-Nume:', colored(client.getNume(), 'cyan'), '-Cnp:', colored(client.getCnp(), 'cyan'))

    def __show_list_of_books(self, books):
        """
        Afiseaza cartile dintr-o lista data
        """
        if len(books) == 0:
            print(colored('Nu exista carti in lista care sa contina stringul dat in titlu.', 'magenta'))
        else:
            print('Lista de carti este:')
            for book in books:
                print('Id:', colored(book.getId(), 'cyan'), '-Titlu:', colored(book.getTitlu(), 'cyan'), '-Descriere:', colored(book.getDescriere(), 'cyan'), '-Autor:', colored(book.getAutor(), 'cyan'), 'NrInchirieri:', colored(book.getNr_Inchirieri(), 'cyan'))

    def __show_list_of_clients(self, clients):
        """
        Afiseaza clientii dintr-o lista data
        """
        if len(clients) == 0:
            print(colored('Nu exista clienti in lista care sa contina stringul dat in nume.', 'magenta'))
        else:
            print('Lista de clienti este:')
            for client in clients:
                print('Id:', colored(client.getId(), 'cyan'), '-Nume:', colored(client.getNume(), 'cyan'), '-Cnp:', colored(client.getCnp(), 'cyan'),  '-NrCartiInchiriate: ', colored(client.getNrCarti(), 'cyan'))


    def __show_all_rent_items(self, rent_items):
        if len(rent_items) == 0:
            print(colored('Nu exista rent items in lista.', 'magenta'))
        else:
            print('Lista de rent_items este:')
            for rent_item in rent_items:
                print('Book: [', colored(str(rent_item.getBook().getTitlu()), 'cyan'), '; ', colored(str(
                    rent_item.getBook().getAutor()), 'cyan'),
                      '] Client: [' + colored(str(rent_item.getClient().getNume()), 'magenta'), '; ' + colored(str(
                        rent_item.getClient().getCnp()), 'magenta') + ']')

    def __add_book(self):
        """
        Adauga o carte
        """
        titlu = input('Titlu cartii:')
        descriere = input('Descrierea cartii:')
        autor = input('Autorul cartii:')
        id = input('Id-ul cartii:')
        try:
            added_book = self.__book_srv.add_book(id, titlu, descriere, autor)
            print('Cartea', added_book, 'a fost adaugata cu succes')
        except ValidationException as ve:
            print(colored(str(ve), 'red'))
        except DuplicateIDException:
            print(colored('Exista deja o carte cu id-ul dat.', 'red'))


    def __add_client(self):
        """
        Adauga un client
        """
        nume = input('Numele clientului:')
        cnp = input('Cnp-ul clientului:')
        id = input('Id-ul clientului:')
        try:
            added_client = self.__client_srv.add_client(id, nume, cnp)
            print('Clientul', added_client, 'a fost adaugat cu succes')
        except ValidationException as ve:
            print(colored(str(ve), 'red'))
        except DuplicateIDException:
            print(colored('Exista deja un client cu id-ul dat.', 'red'))

    def __delete_book(self):
        id = input('ID carte: ')

        try:
            deleted_book = self.__book_srv.delete_by_id(id)
            print('Cartea', deleted_book, 'a fost stearsa cu succes')
        except BookNotFoundException as e:
            print(colored(str(e), 'red'))

    def __delete_client(self):
        id = input('ID carte: ')

        try:
            deleted_client = self.__client_srv.delete_by_id(id)
            print('Clientul', deleted_client, ' a fost sters cu succes')
        except ClientNotFoundException as e:
            print(colored(str(e), 'red'))

    def __update_book(self):
        """
        Modifica o carte
        """
        id = input('ID carte:')
        titlu = input('Titlul cartii: ')
        descriere = input('Descrierea cartii: ')
        autor = input('Autorul cartii: ')
        try:
            nr_inchirieri = int(input('Nr Inchirieri:'))
            try:
                updated_book = self.__book_srv.update_book(id, titlu, descriere, autor, nr_inchirieri)
                print('Cartea', updated_book, 'a fost modificata cu succes')
            except ValidationException as ve:
                print(colored(str(ve), 'red'))
            except BookNotFoundException as e:
                print(colored(str(e), 'red'))
        except ValueError as ve:
            print(colored('Ati introdus date invalide!', 'red'))

    def __update_client(self):
        """
        Modifica un client
        """
        id = input('ID client:')
        nume = input('Numele clientului: ')
        cnp = input('Cnp-ul clientului: ')
        try:
            nr_carti = int(input('Nr carti inchiriate:'))
            try:
                updated_client = self.__client_srv.update_client(id, nume, cnp, nr_carti)
                print('Clientul', updated_client, 'a fost modificat cu succes')
            except ValidationException as ve:
                print(colored(str(ve), 'red'))
            except ClientNotFoundException as e:
                print(colored(str(e), 'red'))
        except ValueError as ve:
            print(colored('Ati introdus date invalide!', 'red'))

    def __add_rent_item(self):
        """
        Adauga un rent item
        """
        book_id = input('ID book:')
        client_id = input('ID client:')
        try:
            rent_item = self.__srv_rent_item.add_rent_item(book_id, client_id)
            print('Rent item-ul', rent_item, 'a fost adauga cu succes')
        except ValidationException as ve:
            print(colored(str(ve), 'red'))
        except BookNotFoundException as e:
            print(colored(str(e), 'red'))
        except ClientNotFoundException as e:
            print(colored(str(e), 'red'))
        except RentAlreadyAssignedException as e:
            print(colored(str(e), 'red'))


    def __filter_by_title(self):
        """
        Afiseaza toate cartile care contin un string dat in titlu
        """
        substring = input('String-ul dupa care se cauta ')
        filtered_list = self.__book_srv.filter_by_title(substring)
        self.__show_list_of_books(filtered_list)

    def __filter_by_name(self):
        """
        Afiseaza toti clienti care contin un string dat in nume
        """
        substring = input('String-ul dupa care se cauta ')
        filtered_list = self.__client_srv.filter_by_name(substring)
        self.__show_list_of_clients(filtered_list)

    def __random_client(self):
        try:
            number = random.randint(1, 10)
            self.__client_srv.generate_client(number)
        except ValueError as ve:
            print(colored(ve, 'red'))
        self.__show_all_clients()

    def __random_book(self):
        try:
            number = random.randint(1, 10)
            self.__book_srv.generate_book(number)
        except ValueError as ve:
            print(colored(ve, 'red'))
        self.__show_all_books()

    def __return_book(self):
        book_id = input('ID book:')
        client_id = input('ID client:')
        try:
            returned_rent_item = self.__srv_rent_item.return_rent_item(book_id, client_id)
            print('RentItemul', returned_rent_item, 'a fost returnat cu succes')
        except BookNotFoundException as ve:
            print(colored(str(ve), 'red'))
        except ClientNotFoundException as ve:
            print(colored(str(ve), 'red'))
        except RentItemNotFoundException as ve:
            print(colored(str(ve), 'red'))
        except RentItemAlreadyReturned as e:
            print(colored(str(e), 'red'))

    def __top_books(self):
        list_cl = self.__srv_rent_item.get_number_of_rents()
        cnt = 1
        for ob in list_cl:
            print(str(cnt) + ') Carte: [Titlu]:', colored(ob.getTitluCarte(),'cyan'), 'a fost inchiriata de ', colored(ob.getNumarInchirieri(), 'cyan'), 'clienti')
            cnt += 1

    def __rent_report_books(self):
        list_cl = self.__srv_rent_item.get_number_of_rent_books_sorted_by_books()
        cnt = 1
        for ob in list_cl:
            print(str(cnt) + ') Client: [Nume]:', colored(ob.getNumeClient(), 'cyan'), 'a inchiriat ',
                  colored(ob.getNumarCartiInchiriate(), 'cyan'), 'carti')
            cnt += 1

    def __rent_report_name(self):
        list_cl = self.__srv_rent_item.get_number_of_rent_books_sorted_by_name()
        cnt = 1
        for ob in list_cl:
            print(str(cnt) + ') Client: [Nume]:', colored(ob.getNumeClient(), 'cyan'), 'a inchiriat ',
                  colored(ob.getNumarCartiInchiriate(), 'cyan'), 'carti')
            cnt += 1

    def __top_clients(self):
        list_cl = self.__srv_rent_item.top_clients()
        cnt = 1
        for ob in list_cl:
            print(str(cnt) + ') Client: [Nume]:', colored(ob.getNumeClient(), 'cyan'), 'a inchiriat ',
                  colored(ob.getNumarCartiInchiriate(), 'cyan'), 'carti')
            cnt += 1

    def __raport_lab(self):
        list_cl = self.__srv_rent_item.get_raport()
        cnt = 1
        for ob in list_cl:
            print(str(cnt) + ') Client: [Nume]:', colored(ob.getNumeClient(), 'cyan'), 'a inchiriat: ',
                  colored(ob.getListaCarti(), 'cyan'), 'carti')
            cnt += 1

    def show_ui(self):
        # command-driven menu (just to practice something different)
        # Lab7-9 oricare varianta (print-menu + optiuni/comenzi) este ok
        # dar si la comenzi sa existe un user guide, ce comenzi sunt dispobibile, cum le folosim
        while True:
            print('Comenzi disponibile:')
            print('ENTITY COMMANDS:')
            print('BOOK: add_book, find_book, delete_book_by_id, show_all_books, update_book')
            print('CLIENT: add_client, find_client, delete_client_by_id, show_all_clients, update_client')
            print('INCHIERE CARTE COMENZI: rent_book, return_book, top_books, rent_report_name, rent_report_books, top_clients, raport_lab, show_all_rent_items')
            print('RANDOM: random_book (adaugare n carti random), random_client(adaugare n clienti random)')
            print('exit')
            cmd = input('Comanda este: ')
            cmd = cmd.lower().strip()
            if cmd == 'add_book':
                self.__add_book()
            elif cmd == 'add_client':
                self.__add_client()
            elif cmd == 'show_all_books':
                self.__show_list_of_books(self.__book_srv.get_all_books())
            elif cmd == 'show_all_clients':
                self.__show_list_of_clients(self.__client_srv.get_all_clients())
            elif cmd == 'delete_book_by_id':
                self.__delete_book()
            elif cmd == 'delete_client_by_id':
                self.__delete_client()
            elif cmd == 'update_book':
                self.__update_book()
            elif cmd == 'update_client':
                self.__update_client()
            elif cmd == 'find_book':
                self.__filter_by_title()
            elif cmd == 'find_client':
                self.__filter_by_name()
            elif cmd == 'random_client':
                self.__random_client()
            elif cmd == 'random_book':
                self.__random_book()
            elif cmd == 'rent_book':
                self.__add_rent_item()
            elif cmd == 'return_book':
                self.__return_book()
            elif cmd == 'show_all_rent_items':
                self.__show_all_rent_items(self.__srv_rent_item.get_all())
            elif cmd == 'top_books':
                self.__top_books()
            elif cmd == 'rent_report_name':
                self.__rent_report_name()
            elif cmd == 'rent_report_books':
                self.__rent_report_books()
            elif cmd == 'top_clients':
                self.__top_clients()
            elif cmd == 'raport_lab':
                self.__raport_lab()
            elif cmd == 'exit':
                return
            else:
                print(colored('Comanda invalida', 'red'))

