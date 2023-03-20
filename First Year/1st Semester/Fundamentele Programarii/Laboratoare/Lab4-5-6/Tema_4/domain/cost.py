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


def validare_cheltuiala(p):
    """
    Valideaza o cheltuiala data
    :param p: cheltuiala data
    :type p: obiect de tip cheltuiala
    :return:
    :rtype:
    :raises: ValueError daca datele cheltuielii sunt invalide
    """
    errors = []
    if get_apartament(p) < 0:
        errors.append("Numarul apartamentului nu poate fi negativ.")
    if get_tip(p) == '':
        errors.append("Tipul cheltuielii nu poate fi vid.")
    if get_suma(p) < 0:
        errors.append("Suma nu poate fi negativa")
    if get_ziua(p) < 1 or get_ziua(p) > 31:
        errors.append("Ziua trebuie sa fie cuprinsa intre 1 si 31")
    if len(errors) > 0:
        error_string = '\n'.join(errors)
        raise ValueError(error_string)


def e_aceeasi_cheltuiala(p1, p2):
    """
    Verifica daca cheltuielile date sunt identice
    :param p1: prima cheltuiala
    :type p1: obiect de tip cheltuiala(lista)
    :param p2: a doua cheltuiala
    :type p2: obiect de tip cheltuiala(lista)
    :return: True daca cheltuielile sunt identice, False altfel
    :rtype: bool
    """
    if get_apartament(p1) == get_apartament(p2) and get_tip(p1) == get_tip(p2) and get_suma(p1) == get_suma(p2) and get_ziua(p1) == get_ziua(p2):
        return True
    return False


#getters


def get_apartament(cheltuieli):
    return cheltuieli[0]


def get_suma(cheltuieli):
    return cheltuieli[1]


def get_tip(cheltuieli):
    return cheltuieli[2]


def get_ziua(cheltuieli):
    return cheltuieli[3]

#setters


def set_apartament(cheltuiala, apartament_nou):
    cheltuiala[0] = apartament_nou


def set_suma(cheltuiala, suma_noua):
    cheltuiala[1] = suma_noua


def set_tip(cheltuiala, tip_nou):
    cheltuiala[2] = tip_nou


def set_ziua(cheltuiala, zi_noua):
    cheltuiala[3] = zi_noua


def test_creare():
    p1 = creare_cheltuiala(2, 150.20, 'apa', 4)
    assert (type(p1) == list)
    assert (p1[0] == 2)
    assert (p1[1] == 150.20)
    assert (p1[2] == 'apa')
    assert (p1[3] == 4)


def test_validare_cheltuiala():
    p1 = creare_cheltuiala(-2, 100.20, 'apa', 12)
    try:
        validare_cheltuiala(p1)
    except ValueError as ve:
        assert (str(ve) == "Numarul apartamentului nu poate fi negativ.")

    p2 = creare_cheltuiala(2, -3, 'gaz', 15)
    try:
        validare_cheltuiala(p2)
    except ValueError as ve:
        assert (str(ve) == "Suma nu poate fi negativa")

    p3 = creare_cheltuiala(2, 103.60, '', 6)
    try:
        validare_cheltuiala(p3)
    except ValueError as ve:
        assert (str(ve) == "Tipul cheltuielii nu poate fi vid.")

    p4 = creare_cheltuiala(10, 50.40, 'altele', 36)
    try:
        validare_cheltuiala(p4)
    except ValueError as ve:
        assert (str(ve) == "Ziua trebuie sa fie cuprinsa intre 1 si 31")


def test_e_aceesi_cheltuiala():
    p1 = creare_cheltuiala(2, 100.80, 'incalzire', 10)
    p2 = creare_cheltuiala(2, 100.80, 'incalzire', 10)
    assert (e_aceeasi_cheltuiala(p1, p2) == True)

    p3 = creare_cheltuiala(2, 100.80, 'apa', 8)
    assert (e_aceeasi_cheltuiala(p1, p3) == False)

    p4 = creare_cheltuiala(2, 256.80, 'altele', 16)
    assert (e_aceeasi_cheltuiala(p1, p4) == False)


test_e_aceesi_cheltuiala()
test_validare_cheltuiala()
test_creare()
