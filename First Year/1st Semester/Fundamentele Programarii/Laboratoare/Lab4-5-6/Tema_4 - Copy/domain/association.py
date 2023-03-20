from domain.cost import get_apartament, get_tip, get_suma, get_ziua, creare_cheltuiala
from utils.list_operations import make_list_copy, add_to_list


def generare_cheltuieli():
    return [[4, 120.50, 'apa', 14], [1, 200.80, 'gaz', 17], [6, 80.30, 'canal', 20],
            [2, 195.70, 'incalzire', 9], [8, 144.60, 'altele', 12], [1, 45.00, 'altele', 10]]


def setup_association(add_predefined):
    """
    Initializeaza un obiect de tip asociatoe
    :param add_predefined: indicator pentru adaugarea cheltuielilor predefinite (daca True lista initiala este cea care
    contine cheltuielile predefinite, altfel,
    lista initiala de cheltuieli este goala)
    :type add_predefined: bool
    :return: lista in care primul element reprezinta lista curenta de cheltuieli, si al doilea element lista pentru undo
    :rtype: list
    """
    if add_predefined:
        lista_cheltuieli = generare_cheltuieli()
    else:
        lista_cheltuieli = []
    undo_list = []
    return [lista_cheltuieli, undo_list]

#getters


def get_lista_cheltuieli(asociatie):
    return asociatie[0]


def get_undo_list(asociatie):
    return asociatie[1]

#setters


def set_lista_cheltuieli(asociatie, lista_noua_cheltuieli):
    asociatie[0] = lista_noua_cheltuieli


def set_undo_list(asociatie, lista_noua_undo):
    asociatie[1] = lista_noua_undo

#functions that implement domain logic


def stergere_cheltuiala_apartament(lista_cheltuieli, apartament):
    """
    Sterge din lista, cheltuielile de la un anumit apartament
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param apartament: apartamentul de la care se vor sterge cheltuielile
    :type apartament: int
    :return: lista din care s-au sters cheltuielile de la un apartament dat
    :rtype: list of lists
    """
    new_list = [cheltuiala for cheltuiala in lista_cheltuieli if get_apartament(cheltuiala) != apartament]
    return new_list


def stergere_cheltuiala_apartamente_consecutive(lista_cheltuieli, apartament1, apartament2):
    """
    Sterge din lista, cheltuielile de la un anumit apartament la alt apartament
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param apartament1: apartamentul de la care se vor sterge cheltuielile
    :type apartament1: int
    :param apartament2: apartamentul pana la care se vor sterge cheltuielile
    :type apartament2: int
    :return: lista din care s-au sters cheltuielile de la un apartament dat pana la alt apartament dat
    :rtype: list of lists
    """
    new_list = [cheltuiala for cheltuiala in lista_cheltuieli if get_apartament(cheltuiala) < apartament1 or
                get_apartament(cheltuiala) > apartament2]
    return new_list


def stergere_cheltuiala_tip(lista_cheltuieli, tip):
    """
    Sterge din lista, cheltuielile de un anumit tip
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param tip: tipul cheltuielii care se va sterge
    :type tip: string
    :return: lista din care s-au sters cheltuielile de un anumit tip
    :rtype: list of lists
    """
    new_list = [cheltuiala for cheltuiala in lista_cheltuieli if get_tip(cheltuiala) != tip]
    return new_list


def filtru_suma(lista_cheltuieli, suma_data):
    """
    Gaseste apartamentele care au cheltuieli mai mari decat o suma data
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param suma_data: suma data
    :type suma_data: float
    :return: lista cu apartamentele care au cheltuieli mai mari decat o suma data
    :rtype: list of lists
    """
    lista_filtrata = []
    for el in lista_cheltuieli:
        if get_suma(el) > suma_data:
           lista_filtrata.append(el)
    return lista_filtrata


def filtru_tip(lista_cheltuieli, tip_dat):
    """
    Gaseste cheltuielile de un anumit tip
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param tip_dat: tip dat
    :type tip_dat: string
    :return: lista cu cheltuielile de un anumit tip
    :rtype: list of lists
    """
    lista_filtrata = []
    if tip_dat != '':
        for el in lista_cheltuieli:
            if get_tip(el).find(tip_dat) != -1:
                lista_filtrata.append(el)
    return lista_filtrata


def filtru_suma_si_zi(lista_cheltuieli, suma_data, zi_data):
    """
    Gaseste apartamentele care au cheltuieli mai mari decat o suma data si sunt efectuate inainte de o zi data
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param suma_data: suma data
    :type suma_data: float
    :param zi_data: ziua data
    :type zi_data: int
    :return: lista cu apartamentele care au cheltuieli mai mari decat o suma data si sunt efectuate inainte de o zi data
    :rtype: list of lists
    """
    lista_filtrata = []
    if suma_data != 0 and zi_data > 0 and zi_data < 32:
        for el in lista_cheltuieli:
            if get_suma(el) > suma_data and get_ziua(el) < zi_data:
                lista_filtrata.append(el)
    return lista_filtrata


def adaugare_cheltuiala_la_asociatie(asociatie, cheltuiala):
    """
    Adauga o cheltuiala la asociatie
    :param asociatie: obiect de tip asociatie
    :type asociatie: list
    :param cheltuiala: cheltuiala care se aduna
    :type cheltuiala: list
    :return: lista curenta de cheltuieli din asociatie se modifica prin adaugarea cheltuielii date
    :rtype:
    """
    crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
    undo_list = get_undo_list(asociatie)

    undo_list.append(make_list_copy(crt_lista_cheltuieli))
    add_to_list(get_lista_cheltuieli(asociatie), cheltuiala)


def stergere_cheltuiala_1(asociatie):
    """
    Sterge cheltuielile de la un apartament dat
    :param asociatie:obiect de tip asociatie
    :type asociatie: list
    :return: lista de cheltuieli curenta se modifica prin eliminarea celor de la apartamentul dat
    :rtype:
    """
    try:
        apartament = int(input("Introduceti numarul apartamentului: "))
        crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
        undo_list = get_undo_list(asociatie)
        undo_list.append(make_list_copy(crt_lista_cheltuieli))
        set_lista_cheltuieli(asociatie, stergere_cheltuiala_apartament(crt_lista_cheltuieli, apartament))
    except ValueError:
        print("Ati introdus date invalide!")
        stergere_cheltuiala_1(asociatie)


def stergere_cheltuiala_2(asociatie):
    """
    Sterge cheltuielile de la un apartament dat la alt apartament dat
    :param asociatie:obiect de tip asociatie
    :type asociatie: list
    :return: lista de cheltuieli curenta se modifica prin eliminarea celor dintre cele doua apartamente date
    :rtype:
    """
    try:
        ap1 = int(input("Introduceti numarul primului apartament: "))
        ap2 = int(input("Introduceti numarul celui de-al doilea apartament: "))
        if ap1 > ap2:
            crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
            undo_list = get_undo_list(asociatie)
            undo_list.append(make_list_copy(crt_lista_cheltuieli))
            set_lista_cheltuieli(asociatie, stergere_cheltuiala_apartamente_consecutive(crt_lista_cheltuieli, ap2, ap1))
        else:
            crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
            undo_list = get_undo_list(asociatie)
            undo_list.append(make_list_copy(crt_lista_cheltuieli))
            set_lista_cheltuieli(asociatie, stergere_cheltuiala_apartamente_consecutive(crt_lista_cheltuieli, ap1, ap2))
    except ValueError:
        print("Ati introdus date invalide!")
        stergere_cheltuiala_2(asociatie)


def stergere_cheltuiala_3(asociatie):
    """
    Sterge cheltuielile de un anumit tip
    :param asociatie:obiect de tip asociatie
    :type asociatie: list
    :return: lista de cheltuieli curenta se modifica prin eliminarea cheltuielilor de tipul dat
    :rtype:
    """
    try:
        tip = input("Introduceti tipul cheltuielii")
        crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
        undo_list = get_undo_list(asociatie)
        undo_list.append(make_list_copy(crt_lista_cheltuieli))
        set_lista_cheltuieli(asociatie, stergere_cheltuiala_tip(crt_lista_cheltuieli, tip))
    except ValueError:
        print("Ati introdus date invalide!")
        stergere_cheltuiala_3(asociatie)


def undo(asociatie):
    """
    Face undo la ultima operatie de adaugare sau stergere
    :param asociatie: asociatia curenta
    :type asociatie: list
    :return:  lista curenta se modifica prin revenire la starea listei inainte de operatie
    :rtype: -;
    :raises:  ValueError daca nu se mai poate face undo
    """
    undo_list = get_undo_list(asociatie)
    if len(undo_list) == 0:
        raise ValueError("Nu se mai poate face undo.")
    else:
        previous_list = undo_list[-1]
        set_lista_cheltuieli(asociatie, previous_list)
        set_undo_list(asociatie, undo_list[:-1])


#tests


def test_stergere_cheltuiala_apartament():
    test_list1 = generare_cheltuieli()
    lungime_initiala = len(test_list1)
    test_list1 = stergere_cheltuiala_apartament(test_list1, 1)
    assert(len(test_list1) == lungime_initiala - 2)
    assert(len([cheltuiala for cheltuiala in test_list1 if get_apartament(cheltuiala) == 1]) == 0)
    lungime_curenta = len(test_list1)

    test_list1 = stergere_cheltuiala_apartament(test_list1, 1)
    assert(len(test_list1) == lungime_curenta)

    test_list2 = []
    test_list2 = stergere_cheltuiala_apartament(test_list2, 4)
    assert(len(test_list2) == 0)


def test_stergere_cheltuiala_apartamente_consecutive():
    test_list1 = generare_cheltuieli()
    lungime_initiala = len(test_list1)
    test_list1 = stergere_cheltuiala_apartamente_consecutive(test_list1, 1, 3)

    assert(len(test_list1) == lungime_initiala - 3)
    assert(len([cheltuiala for cheltuiala in test_list1 if get_apartament(cheltuiala) >= 1 and
                get_apartament(cheltuiala) <= 3]) == 0)
    lungime_curenta = len(test_list1)

    test_list1 = stergere_cheltuiala_apartamente_consecutive(test_list1, 1, 3)
    assert(len(test_list1) == lungime_curenta)

    test_list2 = []
    test_list2 = stergere_cheltuiala_apartamente_consecutive(test_list2, 2, 4)
    assert(len(test_list2) == 0)


def test_stergere_cheltuiala_tip():
    test_list1 = generare_cheltuieli()
    lungime_initiala = len(test_list1)
    test_list1 = stergere_cheltuiala_tip(test_list1, 'altele')

    assert(len(test_list1) == lungime_initiala - 2)
    assert(len([cheltuiala for cheltuiala in test_list1 if get_tip(cheltuiala) == 'altele']) == 0)
    lungime_curenta = len(test_list1)

    test_list1 = stergere_cheltuiala_tip(test_list1, 'altele')
    assert(len(test_list1) == lungime_curenta)

    test_list2 = []
    test_list2 = stergere_cheltuiala_tip(test_list2, 'altele')
    assert(len(test_list2) == 0)


def test_filtru_suma():
    test_list = []
    lista_filtrata_1 = filtru_suma(test_list, 100.00)
    assert (len(lista_filtrata_1) == 0)
    test_list2 = generare_cheltuieli()
    lista_filtrata2 = filtru_suma(test_list2, 150.00)
    assert(len(lista_filtrata2) == 2)
    assert(lista_filtrata2[0] == 1)
    lista_filtrata3 = filtru_suma(test_list2, 500.00)
    assert(len(lista_filtrata3) == 0)


def test_filtru_tip():
    test_list = []
    lista_filtrata_1 = filtru_tip(test_list, 'apa')
    assert(len(lista_filtrata_1) == 0)
    test_list2 = generare_cheltuieli()
    lista_filtrata2 = filtru_tip(test_list2, 'apa')
    assert(len(lista_filtrata2) == 1)
    assert(get_apartament(lista_filtrata2[0]) == 4)
    lista_filtrata3 = filtru_tip(test_list2, 'cablu')
    assert(len(lista_filtrata3) == 0)


def test_filtru_suma_si_zi():
    test_list = []
    lista_filtrata_1 = filtru_suma_si_zi(test_list, 200.00, 8)
    assert(len(lista_filtrata_1) == 0)

    test_list2 = generare_cheltuieli()
    lista_filtrata2 = filtru_suma_si_zi(test_list2, 100.00, 14)
    assert(len(lista_filtrata2) == 2)
    assert(get_apartament(lista_filtrata2[0]) == 2)
    lista_filtrata3 = filtru_suma_si_zi(test_list2, 300.00, 2)
    assert(len(lista_filtrata3) == 0)


def test_adaugare_cheltuiala_la_asociatie():
    test_asociatie = setup_association(False)
    p1 = creare_cheltuiala(4, 100.20, 'apa', 20)
    p2 = creare_cheltuiala(3, 180.60, 'gaz', 13)

    adaugare_cheltuiala_la_asociatie(test_asociatie, p1)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 1)

    adaugare_cheltuiala_la_asociatie(test_asociatie, p2)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 2)


def test_undo():
    test_asociatie = setup_association(False)
    p1 = creare_cheltuiala(4, 100.20, 'apa', 20)

    adaugare_cheltuiala_la_asociatie(test_asociatie, p1)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 1)
    undo(test_asociatie)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 0)

    test_asociatie2 = setup_association(False)
    p1 = creare_cheltuiala(4, 100.20, 'apa', 20)
    p2 = creare_cheltuiala(3, 180.60, 'gaz', 13)

    adaugare_cheltuiala_la_asociatie(test_asociatie2, p1)
    adaugare_cheltuiala_la_asociatie(test_asociatie2, p2)

    undo(test_asociatie2)
    assert (len(get_lista_cheltuieli(test_asociatie2)) == 1)
    undo(test_asociatie2)
    assert (len(get_lista_cheltuieli(test_asociatie2)) == 0)