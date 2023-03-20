"""Creați o aplicație pentru gestiunea cheltuielilor lunare de la apartamentele unui bloc de locuințe. Aplicația stochează cheltuielile pentru fiecare apartament: suma și
tipul cheltuielii (tip poate fi una dintre: apa, canal, încălzire, gaz, altele).
Aplicația permite:
1. Adăugare
• adaugă cheltuială pentru un apartament
• modifică cheltuială
2. Ștergere
• Șterge toate cheltuielile de la un apartament
• Șterge cheltuielile de la apartamente consecutive (Ex. se dau două numere de apartament 2 și 5 și se șterg toate cheltuielile de la apartamentele 1,2,3,4 și 5)
• Șterge cheltuielile de un anumit tip de la toate apartamentele
3. Căutări
• Tipărește toate apartamentele care au cheltuieli mai mari decât o sumă dată
• Tipărește cheltuielile de un anumit tip de la toate apartamentele
• Tipărește toate cheltuielile efectuate înainte de o zi și mai mari decât o sumă (se dă suma și ziua)

1. Adaugare cheltuiala (apartament, suma, tip cheltuiala, ziua efectuare cheltuieli)
2. Modificare cheltuiala
3. Stergere cheltuieli apartament
4. Stergere cheltuieli de la apartamente consecutive
5. Stergere cheltuieli de un anumit tip de la toate apartamentele
6. Tiparirea tuturor apartamentelor care au cheltuieli mai mari decât o sumă dată
7. Tiparirea cheltuielilor de un anumit tip de la toate apartamentele
8. Tiparirea cheltuielilor efectuate înainte de o zi și mai mari decât o sumă (se dă suma și ziua)
P. Afisarea listei curente de cheltuieli
9. Iesirea din aplicatie
10. Elimină toate cheltuielile mai mici decât o sumă dată

Se va adauga o optiune pentru printarea listei curente (sau se va printa dupa fiecare operatie).
Se cere folosirea procesului de dezvoltare incrementala bazata pe
 functionalitati si dezvoltarea dirijata de teste.
"""

def generare_cheltuieli():
    return [[4, 120.50, 'apa', 14], [1, 200.80, 'gaz', 17], [6, 80.30, 'canal', 20], [2, 195.70, 'incalzire', 9], [8, 144.60, 'altele', 12], [1, 45.00, 'altele', 10]]

def get_apartament(cheltuieli):
    return cheltuieli[0]

def get_suma(cheltuieli):
    return cheltuieli[1]

def get_tip(cheltuieli):
    return cheltuieli[2]

def get_ziua(cheltuieli):
    return cheltuieli[3]

def creare_cheltuiala(apartament, suma, tip, ziua):
    """
    Creeaza o cheltuiala cu atributele date
    :param apartament: numarul apartamentului
    :type apartament: int
    :param suma: suma cheltuielii
    :type suma: float
    :param tip: tipul cheltuielii
    :type tip: string
    :param ziua: ziua efectuarii cheltuielilor
    :type ziua: int
    :return: cheltuiala cu atributele date
    :rtype: list (len(lst)=4, lst[0] = apartament, lst[1] = suma, lst[2]= tip, lst[3] = ziua)
    """
    return [apartament, suma, tip, ziua]

def adaugare_cheltuiala_la_lista(lista_cheltuieli, cheltuiala):
    """
    Adauga o cheltuiala la lista
    :param lista_cheltuiala: lista de cheltuieli
    :type lista_cheltuiala:list of lists
    :param cheltuiala: cheltuiala care se adauga
    :type cheltuiala: list
    :return: -; se modifica lista prin adaugarea unei cheltuieli
    :rtype:
    """
    lista_cheltuieli.append(cheltuiala)

def modificare_cheltuiala(lista_cheltuieli, suma_noua, nrap):
    """
    Modifica o cheltuiala la lista
    :param lista_cheltuiala: lista de cheltuieli
    :type lista_cheltuiala:list of lists
    :param suma_noua: suma noua
    :type suma_noua: float
    :param nrap: apartamentul la care se va modifica cheltuiala
    :type nrap: int
    :return: new_list; se modifica lista prin modificarea unei cheltuieli
    :rtype:list of lists
    """
    new_list = []
    for el in lista_cheltuieli:
        if(get_apartament(el) == nrap):
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

def stergere_cheltuiala_apartamente_consecutive(lista_cheltuieli, apartament1,apartament2):
    """
    Sterge din lista, cheltuielile de la un anumit apartament la alt apartament
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param apartament1: apartamentul de la care se vor sterge cheltuielile
    :type apartament: int
    :param apartament2: apartamentul pana la care se vor sterge cheltuielile
    :type apartament: int
    :return: lista din care s-au sters cheltuielile de la un apartament dat pana la alt apartament dat
    :rtype: list of lists
    """
    new_list = [cheltuiala for cheltuiala in lista_cheltuieli if get_apartament(cheltuiala) < apartament1 or get_apartament(cheltuiala) > apartament2]
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
        if(get_suma(el) > suma_data):
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
    if tip_dat !=  '':
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
    if suma_data !=  0 and zi_data > 0 and zi_data < 32:
        for el in lista_cheltuieli:
            if get_suma(el) > suma_data and get_ziua(el) < zi_data:
                lista_filtrata.append(el)
    return lista_filtrata

def eliminare_cheltuieli_mai_mici_decat_o_suma(lista_cheltuieli, suma_data):
    """
    Gaseste cheltuielile mai mici decat o suma data 
    :param lista_cheltuieli: lista de cheltuieli
    :type lista_cheltuieli: list of lists
    :param suma_data: suma data
    :type suma_data: float
    :return: lista cu cheltuielile mai mici decat o suma data 
    :rtype: list of lists
    """
    lista_filtrata = []
    for el in lista_cheltuieli:
        if get_suma(el) >= suma_data:
            lista_filtrata.append(el)
    return lista_filtrata

def print_meniu():
    print("1. Adaugare cheltuiala (apartament, suma, tip cheltuiala, ziua efectuare cheltuieli)")
    print("2. Modificare cheltuiala")
    print("3. Stergere cheltuieli apartament")
    print("4. Stergere cheltuieli de la apartamente consecutive")
    print("5. Stergere cheltuieli de un anumit tip de la toate apartamentele")
    print("6. Tiparirea tuturor apartamentelor care au cheltuieli mai mari decât o sumă dată")
    print("7. Tiparirea cheltuielilor de un anumit tip de la toate apartamentele")
    print("8. Tiparirea cheltuielilor efectuate înainte de o zi și mai mari decât o sumă (se dă suma și ziua)")
    print("P. Afisarea listei curente de cheltuieli")
    print("9. Iesirea din aplicatie")
    print("10. Elimină toate cheltuielile mai mici decât o sumă dată")

def print_lista_cheltuieli(lista_cheltuieli):
    for i, cheltuiala in enumerate(lista_cheltuieli):
        print(i, 'Apartament:', get_apartament(cheltuiala), 'Suma: ', get_suma(cheltuiala), 'Tip cheltuiala: ', get_tip(cheltuiala), 'Ziua efetuarii cheltuielii: ', get_ziua(cheltuiala))

def adaugare_cheltuiala(lista_cheltuieli):
    try:
        apartament = int(input("Introduceti apartamentul:"))
        suma = float(input("Introduceti suma cheltuielii:"))
        tip = input("Introduceti tipul cheltuielii:")
        ziua = int(input("Introduceti ziua efectuarii cheltuielii:"))
        p1 = creare_cheltuiala(apartament, suma, tip, ziua)
        adaugare_cheltuiala_la_lista(lista_cheltuieli, p1)
    except ValueError:
        print("Ati introdus date invalide!")
        adaugare_cheltuiala(lista_cheltuieli)

def modificare_lista(lista_cheltuieli):
    try:
        apartament = int(input("Apartamentul la care vom modifica cheltuiala este:"))
        suma = float(input("Introduceti suma noua:"))
        lista_cheltuieli = modificare_cheltuiala(lista_cheltuieli, suma, apartament)
        return lista_cheltuieli
    except ValueError:
        print("Ati introdus date invalide!")
        modificare_lista(lista_cheltuieli)

def stergere_cheltuiala1(lista_cheltuieli):
    try:
        apartament = int(input("Introduceti numarul apartamentului: "))
        lista_cheltuieli = stergere_cheltuiala_apartament(lista_cheltuieli, apartament)
        return lista_cheltuieli
    except ValueError:
        print("Ati introdus date invalide!")
        stergere_cheltuiala1(lista_cheltuieli)

def stergere_cheltuiala2(lista_cheltuieli):
    try:
        ap1 = int(input("Introduceti numarul primului apartament: "))
        ap2 = int(input("Introduceti numarul celui de-al doilea apartament: "))
        if(ap1 > ap2):
            lista_cheltuieli = stergere_cheltuiala_apartamente_consecutive(lista_cheltuieli, ap1, ap2)
        else:
            lista_cheltuieli = stergere_cheltuiala_apartamente_consecutive(lista_cheltuieli, ap2, ap1)
        return lista_cheltuieli
    except ValueError:
        print("Ati introdus date invalide!")
        stergere_cheltuiala2(lista_cheltuieli)

def stergere_cheltuiala3(lista_cheltuieli):
    try:
        tip = input("Introduceti tipul cheltuielii: ")
        lista_cheltuieli = stergere_cheltuiala_tip(lista_cheltuieli, tip)
        return lista_cheltuieli
    except ValueError:
        print("Ati introdus date invalide!")
        stergere_cheltuiala3(lista_cheltuieli)

def filtru_1(lista_cheltuieli):
    try:
        sum = float(input('Suma este:'))
        filtered_list = filtru_suma(lista_cheltuieli, sum)
        print_lista_cheltuieli(filtered_list)
    except ValueError:
        print("Ati introdus date invalide!")
        filtru_1(lista_cheltuieli)

def filtru_2(lista_cheltuieli):
    try:
        tip = input('Tipul este:')
        filtered_list = filtru_tip(lista_cheltuieli, tip)
        print_lista_cheltuieli(filtered_list)
    except ValueError:
        print("Ati introdus date invalide!")
        filtru_2(lista_cheltuieli)

def filtru_3(lista_cheltuieli):
    try:
        sum = float(input('Suma este: '))
        zi = int(input('Ziua este: '))
        if(zi <= 31):
            filtered_list = filtru_suma_si_zi(lista_cheltuieli, sum, zi)
            print_lista_cheltuieli(filtered_list)
        else:
            print("Ati introdus date invalide!")
            filtru_3(lista_cheltuieli)
    except ValueError:
        print("Ati introdus date invalide!")
        filtru_3(lista_cheltuieli)

def eliminare_1(lista_cheltuieli):
    try:
        sum = float(input('Suma este: '))
        filtered_list = eliminare_cheltuieli_mai_mici_decat_o_suma(lista_cheltuieli, sum)
        print_lista_cheltuieli(filtered_list)
    except ValueError:
        print("Ati introdus date invalide!")
        eliminare_1(lista_cheltuieli)

def start():
    lista_curenta_cheltuieli = generare_cheltuieli()
    finished = False
    while not finished:
        print_meniu()
        option = input("Optiunea este:")
        if option == '1':
            adaugare_cheltuiala(lista_curenta_cheltuieli)
            print('Adaugarea s-a efectuat cu succes.')
        elif option == '2':
            lista_curenta_cheltuieli = modificare_lista(lista_curenta_cheltuieli)
            print('Modificarea a fost efectuata')
        elif option == '3':
            lista_curenta_cheltuieli = stergere_cheltuiala1(lista_curenta_cheltuieli)
            print('Stergerea a fost efectuata cu succes')
        elif option == '4':
            lista_curenta_cheltuieli = stergere_cheltuiala2(lista_curenta_cheltuieli)
            print('Stergerea a fost efectuata cu succes')
        elif option == '5':
            lista_curenta_cheltuieli = stergere_cheltuiala3(lista_curenta_cheltuieli)
            print('Stergerea a fost efectuata cu succes')
        elif option == '6':
            lista_curenta_cheltuieli = filtru_1(lista_curenta_cheltuieli)
        elif option == '7':
            lista_curenta_cheltuieli = filtru_2(lista_curenta_cheltuieli)
        elif option == '8':
            lista_curenta_cheltuieli = filtru_3(lista_curenta_cheltuieli)
        elif option.lower() == 'p':
            print_lista_cheltuieli(lista_curenta_cheltuieli)
        elif  option == '9':
            finished = True
        elif option == '10':
            lista_curenta_cheltuieli = eliminare_1(lista_curenta_cheltuieli)
        else:
            print("Optiunea introdusa este invalida.")

start()

def test_creare():
    p1 = creare_cheltuiala(2, 150.20, 'apa', 4)
    assert (type(p1) == list)
    assert (p1[0] == 2)
    assert (p1[1] == 150.20)
    assert (p1[2] == 'apa')
    assert (p1[3] == 4)

def test_adaugare_cheltuiala():
    test_list = []
    p1 = creare_cheltuiala(2, 150.20, 'apa', 4)
    p2 = creare_cheltuiala(7, 100.70, 'gaz', 15)
    adaugare_cheltuiala_la_lista(test_list, p1)
    assert (len(test_list) == 1)
    assert (get_apartament(test_list[0]) == 2)
    assert (get_suma(test_list[0]) == 150.20)
    assert (get_tip(test_list[0]) == 'apa')
    assert (get_ziua(test_list[0]) == 4)
    adaugare_cheltuiala_la_lista(test_list, p2)
    assert (len(test_list) == 2)
    assert (get_apartament(test_list[1]) == 7)
    assert (get_suma(test_list[1]) == 100.70)
    assert (get_tip(test_list[1]) == 'gaz')
    assert (get_ziua(test_list[1]) == 15)

def test_modificare_suma():
    test_list = []
    p1 = creare_cheltuiala(2, 150.20, 'apa', 4)
    adaugare_cheltuiala_la_lista(test_list, p1)
    modificare_cheltuiala(test_list, 100.00, 2)
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
    assert(len([cheltuiala for cheltuiala in test_list1 if get_apartament(cheltuiala) >= 1 and get_apartament(cheltuiala) <= 3]) == 0)
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

def test_eliminare_cheltuieli_mai_mici_decat_o_suma():
    test_list = []
    lista_filtrata_1 = eliminare_cheltuieli_mai_mici_decat_o_suma(test_list, 100.00)
    assert(len(lista_filtrata_1) == 0)

    test_list2 = generare_cheltuieli()
    lista_filtrata2 = eliminare_cheltuieli_mai_mici_decat_o_suma(test_list2, 100.00)
    assert(len(lista_filtrata2) == 4)
    assert(get_apartament(lista_filtrata2[0]) == 4)
    lista_filtrata3 = eliminare_cheltuieli_mai_mici_decat_o_suma(test_list2, 10.50)
    assert(len(lista_filtrata3) == 0)

test_creare()
test_adaugare_cheltuiala()
test_modificare_suma()
test_stergere_cheltuiala_apartament()
test_stergere_cheltuiala_apartamente_consecutive()
test_stergere_cheltuiala_tip()
test_filtru_suma()
test_filtru_tip()
test_filtru_suma_si_zi()
test_eliminare_cheltuieli_mai_mici_decat_o_suma()
