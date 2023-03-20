def make_list_copy(lst):
    """
    Face o copie a listei date
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