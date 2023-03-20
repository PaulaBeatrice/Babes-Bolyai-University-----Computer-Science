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
