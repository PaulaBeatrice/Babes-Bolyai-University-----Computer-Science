from domain.cost import get_apartament, get_tip, get_suma, get_ziua, creare_cheltuiala, e_aceeasi_cheltuiala, \
    validare_cheltuiala
from utils.list_operations import make_list_copy, add_to_list


def generare_cheltuieli():
    return [[4, 120.50, 'apa', 14], [1, 200.80, 'gaz', 17], [6, 80.30, 'canal', 20],
        [2,  195.70, 'incalzire', 9], [8,  144.60, 'altele', 12], [1, 45.50, 'altele', 10]]


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


def modificare_cheltuiala(lista_cheltuieli, suma_noua, nrap, tip):
    """
    Modifica o cheltuiala la lista
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli:list of lists
    :param suma_noua: suma noua
    :type suma_noua: float
    :param nrap: apartamentul la care se va modifica cheltuiala
    :type nrap: int
    :return: new_list; se modifica lista prin modificarea unei cheltuieli
    :rtype:list of lists
    """
    new_list = []
    for el in lista_cheltuieli:
        if get_apartament(el) == nrap and get_tip(el) == tip:
            el[1] = suma_noua
            new_list.append(el)
        else:
            new_list.append(el)
    return new_list


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


def eliminare_cheltuieli_tip(lista_cheltuieli, tip):
    """
        Elimina din lista, cheltuielile de un anumit tip
        :param lista_cheltuieli: lista de cheltuieli
        :type lista_cheltuieli: list of lists
        :param tip: tipul cheltuielii care se va sterge
        :type tip: string
        :return: lista din care s-au sters cheltuielile de un anumit tip
        :rtype: list of lists
        """
    new_list = [cheltuiala for cheltuiala in lista_cheltuieli if get_tip(cheltuiala) != tip]
    return new_list


def eliminare_cheltuieli_mai_mici_decat_o_suma_data(lista_cheltuieli, suma):
    """
        Elimina din lista, cheltuielile mai mici decat o suma data
        :param lista_cheltuieli: lista de cheltuieli
        :type lista_cheltuieli: list of lists
        :param suma: suma data
        :type suma: float
        :return: lista din care s-au sters cheltuielile mai mici decat suma data
        :rtype: list of lists
        """
    new_list = [cheltuiala for cheltuiala in lista_cheltuieli if get_suma(cheltuiala) > suma]
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


def filtru_suma_totala_pentru_un_anumit_tip(lista_cheltuieli, tip):
    """
    Tipareste suma totală pentru un tip de cheltuială
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param tip: tipul dat
    :type tip: string
    :return: Suma totala pentru tipul de cheltuieli dat
    :rtype:float
    """
    suma = 0
    for el in lista_cheltuieli:
        if get_tip(el) == tip:
            suma = suma + get_suma(el)
    return suma


def filtru_apartamente_sortate_dupa_tip(lista_cheltuieli, tip):
    """
    Tipareste toate apartamentele sortate după un tip de cheltuială
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param tip: tipul dat
    :type tip: string
    :return: Lista apartamentelor care au cheltuieli de tipul dat
    :rtype:list
    """
    lista_apartamente = []
    for el in lista_cheltuieli:
        if get_tip(el) == tip:
            lista_apartamente.append(get_apartament(el))
    lista_apartamente.sort()
    return lista_apartamente


def filtru_suma_totala_apartament(lista_cheltuiala, apartament):
    """
    Tipărește totalul de cheltuieli pentru un apartament dat
    :param lista_cheltuiala: lista de cheltuieli
    :type lista_cheltuiala: list of lists
    :param apartament: apartamenul dat
    :type apartament: int
    :return: Totalul de cheltuieli pentru apartamentul dat
    :rtype: float
    """
    total = 0
    for el in lista_cheltuiala:
        if get_apartament(el) == apartament:
            total = total + get_suma(el)
    return total


def exista_cheltuiala(lista_cheltuiala, cheltuiala):
    """
    Verifica daca exista cheltuiala in lista
    :param lista_cheltuiala: lista de cheltuieli data
    :type lista_cheltuiala: lista de liste
    :param cheltuiala: cheltuiala pentru care verificam
    :type cheltuiala: lista
    :return: True daca cheltuiala exista deja in lista, False daca nu exista
    :rtype: bool
    """
    for p in lista_cheltuiala:
        if e_aceeasi_cheltuiala(p, cheltuiala):
            return True
    return False


def adaugare_cheltuiala_la_asociatie(asociatie, apartament, suma, tip, ziua):
    """
    Adauga o cheltuiala la asociatie
    :param asociatie:  obiect de tip asociatie
    :type asociatie: list
    :param apartament: numarul apartamentului
    :type apartament: int
    :param suma: suma cheltuielii
    :type suma: float
    :param tip: tipul cheltuielii
    :type tip: string
    :param ziua: ziua efectuarii cheltuielii
    :type ziua: int
    :return: lista curenta de cheltuieli din asociatie se modifica prin adaugarea cheltuielii date
    :raises: ValueError daca cheltuiala de adaugat este invalida
    """
    cheltuiala = creare_cheltuiala(apartament, suma, tip, ziua)
    validare_cheltuiala(cheltuiala)

    crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
    if not exista_cheltuiala(crt_lista_cheltuieli, cheltuiala):
        undo_list = get_undo_list(asociatie)

        undo_list.append(make_list_copy(crt_lista_cheltuieli))
        add_to_list(get_lista_cheltuieli(asociatie), cheltuiala)
    else:
        raise ValueError('Cheltuiala exista deja in asociatie.')

def stergere_cheltuiala1(asociatie, apartament):
    crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
    undo_list = get_undo_list(asociatie)
    undo_list.append(make_list_copy(crt_lista_cheltuieli))
    set_lista_cheltuieli(asociatie, stergere_cheltuiala_apartament(crt_lista_cheltuieli, apartament))


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


def eliminare_1(asociatie):
    """
    Elimina cheltuielile de un anumit tip
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
        set_lista_cheltuieli(asociatie, eliminare_cheltuieli_tip(crt_lista_cheltuieli, tip))
    except ValueError:
        print("Ati introdus date invalide!")
        eliminare_1(asociatie)


def eliminare_2(asociatie):
    """
    Elimina cheltuielile mai mici decat o suma data
    :param asociatie:obiect de tip asociatie
    :type asociatie: list
    :return: lista de cheltuieli curenta se modifica prin eliminarea cheltuielilor mai mici decat o suma data
    :rtype:
    """
    try:
        suma = float(input("Introduceti suma"))
        crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
        undo_list = get_undo_list(asociatie)
        undo_list.append(make_list_copy(crt_lista_cheltuieli))
        set_lista_cheltuieli(asociatie, eliminare_cheltuieli_mai_mici_decat_o_suma_data(crt_lista_cheltuieli, suma))
    except ValueError:
        print("Ati introdus date invalide!")
        eliminare_2(asociatie)


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

def test_modificare_suma():
    test_list = []
    p1 = creare_cheltuiala(2, 150.20, 'apa', 4)
    add_to_list(test_list, p1)
    modificare_cheltuiala(test_list, 100.00, 2, 'apa')
    assert(p1[1] == 100.00)


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


def test_eliminare_tip():
    test_list1 = generare_cheltuieli()
    lungime_initiala = len(test_list1)
    test_list1 = eliminare_cheltuieli_tip(test_list1, 'altele')

    assert (len(test_list1) == lungime_initiala - 2)
    assert (len([cheltuiala for cheltuiala in test_list1 if get_tip(cheltuiala) == 'altele']) == 0)

    test_list2 = []
    test_list2 = eliminare_cheltuieli_tip(test_list2, 'altele')
    assert (len(test_list2) == 0)


def test_eliminare_cheltuieli_mai_mici_decat_o_suma():
    test_list1 = generare_cheltuieli()
    lungime_initiala = len(test_list1)
    test_list1 = eliminare_cheltuieli_mai_mici_decat_o_suma_data(test_list1, 100.00)

    assert (len(test_list1) == lungime_initiala - 2)
    assert (len([cheltuiala for cheltuiala in test_list1 if get_suma(cheltuiala) < 100.00]) == 0)

    test_list2 = []
    test_list2 = eliminare_cheltuieli_mai_mici_decat_o_suma_data(test_list2, 100.00)
    assert (len(test_list2) == 0)


def test_filtru_suma():
    test_list = []
    lista_filtrata_1 = filtru_suma(test_list, 100.00)
    assert (len(lista_filtrata_1) == 0)
    test_list2 = generare_cheltuieli()
    lista_filtrata2 = filtru_suma(test_list2, 150.00)
    assert(len(lista_filtrata2) == 2)
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

    adaugare_cheltuiala_la_asociatie(test_asociatie, 4, 100.20, 'apa', 20)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 1)

    adaugare_cheltuiala_la_asociatie(test_asociatie, 3, 180.60, 'gaz', 13)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 2)


def test_undo():
    test_asociatie = setup_association(False)

    adaugare_cheltuiala_la_asociatie(test_asociatie, 4, 100.20, 'apa', 20)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 1)
    undo(test_asociatie)
    assert (len(get_lista_cheltuieli(test_asociatie)) == 0)

    test_asociatie2 = setup_association(False)

    adaugare_cheltuiala_la_asociatie(test_asociatie2, 4, 100.20, 'apa', 20)
    adaugare_cheltuiala_la_asociatie(test_asociatie2, 3, 180.60, 'gaz', 13)

    undo(test_asociatie2)
    assert (len(get_lista_cheltuieli(test_asociatie2)) == 1)
    undo(test_asociatie2)
    assert (len(get_lista_cheltuieli(test_asociatie2)) == 0)


def test_tiparire_suma_totala():
    test_list1 = generare_cheltuieli()
    suma_1 = filtru_suma_totala_pentru_un_anumit_tip(test_list1, 'altele')
    assert(suma_1 == 190.1)

    suma_2 = filtru_suma_totala_pentru_un_anumit_tip(test_list1, 'apa')
    assert (suma_2 == 120.50)

    test_list2 = []
    suma_3 = filtru_suma_totala_pentru_un_anumit_tip(test_list2, 'gaz')
    assert (suma_3 == 0)


def test_tiparire_apartamente_anumit_tip():
    test_list1 = generare_cheltuieli()
    lista_filtrata1 = filtru_apartamente_sortate_dupa_tip(test_list1, 'altele')
    assert (len(lista_filtrata1) == 2)

    lista_filtrata2 = filtru_apartamente_sortate_dupa_tip(test_list1, 'apa')
    assert (len(lista_filtrata2) == 1)

    test_list2 = []
    lista_filtrata3 = filtru_apartamente_sortate_dupa_tip(test_list2, 'gaz')
    assert (len(lista_filtrata3) == 0)


def test_tiparire_total_cheltuieli_apartament():
    test_list1 = generare_cheltuieli()
    total1 = filtru_suma_totala_apartament(test_list1, 1)
    assert (total1 == 246.3)

    total2 = filtru_suma_totala_apartament(test_list1, 4)
    assert (total2 == 120.50)

    total3 = filtru_suma_totala_apartament(test_list1, 3)
    assert (total3 == 0)


test_modificare_suma()
test_stergere_cheltuiala_apartament()
test_stergere_cheltuiala_apartamente_consecutive()
test_stergere_cheltuiala_tip()
test_eliminare_tip()
test_eliminare_cheltuieli_mai_mici_decat_o_suma()
test_filtru_suma()
test_filtru_tip()
test_filtru_suma_si_zi()
test_adaugare_cheltuiala_la_asociatie()
test_undo()
test_tiparire_suma_totala()
test_tiparire_apartamente_anumit_tip()
test_tiparire_total_cheltuieli_apartament()
