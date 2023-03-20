def make_list_copy(lst):
    """
    Face o dictionarului a listei date
    :param lst: lista care se copiaza
    :type lst: list of lists
    :return: o copie a listei
    :rtype: list of lists
    """
    cpy = []

    for el in lst:
        cpy.append(el[:])
    return cpy


def add_to_list(lst, elem):
    """
    Adauga un element intr-o lista
    :param lst: lista la care se adauga
    :type lst: list
    :param elem: elementul care se adauga
    :type elem: any
    :return: lista se modifica prin adaugarea elementului
    :rtype: -
    """
    lst.append(elem)


def test_copy_list():
    test_list = [[0, 1], [3, 4]]
    test_list_copy = make_list_copy(test_list)
    assert (len(test_list) == len(test_list_copy))
    # == intre liste verifica daca elementele din lista sunt egale
    # conteaza ordinea
    assert (test_list == test_list_copy)


def test_add_list():
    test_list = [1, 2, 3]
    add_to_list(test_list, 4)
    assert (test_list == [1, 2, 3, 4])

    test_list2 = [[0, 1], [3, 4]]
    add_to_list(test_list2, [5, 6])
    assert (test_list2 == [[0, 1], [3, 4], [5, 6]])


test_add_list()
test_copy_list()
