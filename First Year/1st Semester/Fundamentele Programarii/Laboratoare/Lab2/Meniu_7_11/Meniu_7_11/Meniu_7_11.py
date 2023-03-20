"""
Scrieti o aplicatie care are interfata utilizator tip consolă cu un meniu:
1 Citirea unei liste de numere intregi
2 Gasirea secventei de lungime maxima in care oricare doua elemente consecutive difera printr-un numar prim.
3 Gasirea secventei de lungime maxima care are suma elementelor maxima.
4 Gasirea secventei de lungime maxima care are toate elementele in intervalul [0, 10] dat
5 Iesire din aplicatie.
"""
import math 


def print_menu():
    print("1. Citeste de la tastatura o lista de numere intregi.")
    print("2. Gaseste secventa de lungime maxima in care oricare doua elemente consecutive difera printr-un numar prim.")
    print("3. Gaseste secventa de lungime maxima care are suma elementelor maxima.")
    print("4. Gasirea secventei de lungime maxima care are toate elementele in intervalul [0, 10] dat")
    print("5. Iesire din aplicatie.")


def print_list(message, lst):
    print(message, lst)


def prim(value):
    """
    Verifica daca diferentele dintre doua numere consecutive sunt prime
    :param value: diferenta dintre doua numere consecutive
    :type value: int
    :return: Adevarat daca diferenta e numar prim, Fals daca diferenta nu e numar prim
    :rtype: bool
    """
    ok = True
    if value < 2:
        ok = False
    elif value % 2 == 0 and value != 2:
        ok = False
    if ok == True:
        for i in range(3,value):
            if value % i == 0:
                ok = False
    return ok


def find_2(the_list):
    """
    Gaseste secventa de lungime maxima in care oricare doua elemente consecutive difera printr-un numar prim.
    :param the_list: lista data cu numere
    :type the_list: list
    :return: lista cu numerele din secventa de lungime maxima in care oricare doua elemente consecutive difera printr-un numar prim
    :rtype: list
    """
    l = 1
    lmax = 0
    st = 0
    dr = 0
    for el in range(len(the_list) - 1):
        if(prim(the_list[el] - the_list[el + 1]) == True):
            l += 1
            if(l > lmax):
                lmax = l
                dr = el + 1
                st = dr - l + 1
        if(prim(the_list[el] - the_list[el + 1]) == False):
            l = 1
    elems = []
    if(dr != 0 or st != 0):
        for el in range(len(the_list)):
            if(el >= st and el <= dr):
                elems.append(the_list[el])
    return elems


def find_3(the_list):
    """
     Gaseste secventa de lungime maxima in care suma elementelor este maxima.
    :param the_list: lista data cu numere
    :type the_list: list
    :return: lista cu numerele din secventa de lungime maxima in care suma elementelor este maxima
    :rtype: list
    """
    st = 0 
    dr = 0
    s = 0
    l = 0
    smax = 0
    for el in range(len(the_list)):
        if(s < 0):
            s = the_list[el]
            st = el
            l = 1
        else:
            s += the_list[el]
            l += 1
        if(s > smax):
            smax = s
            dr = st + l - 1 
    elems = []
    for el in range(len(the_list)):
        if(el >= st and el <= dr):
           elems.append(the_list[el])
    return elems

def find_4(the_list):
    """
    Gaseste secventa de lungime maxima care are toate elementele in intervalul [0, 10] dat
    :param the_list: lista data cu numere
    :type the_list: list
    :return: lista cu numerele din secventa de lungime maxima care are toate elementele in intervalul [0, 10] dat
    :rtype: list
    """
    l = 0
    lmax = 0
    st = 0
    dr = 0
    for el in range(len(the_list)):
        if(the_list[el] >= 0 and the_list[el] <= 10):
            l += 1
            if(l > lmax):
                lmax = l
                dr = el
                st = dr - l + 1
        if(the_list[el] < 0 or the_list[el] > 10):
            l = 0
    elems = []
    if(dr != 0 or st != 0):
        for el in range(len(the_list)):
            if(el >= st and el <= dr):
                elems.append(the_list[el])
    return elems


def read_list():
    the_list_as_string = input("Dati lista in formatul cerut:")
    list_of_strings = the_list_as_string.split(",")
    number_list = [int(elem.strip()) for elem in list_of_strings]
    return number_list


def start():
    current_list = []
    while True:
        print_menu()
        print_list('Lista curenta este:', current_list)
        option = int(input("Optiunea dumneavoastra este:"))
        if option == 1:
            current_list = read_list()
        elif option == 2:
            if(len(find_2(current_list)) > 0):
                print('Secventa de lungime maxima in care oricare doua numere consecutive difera printr-un numar prim este: ', find_2(current_list))
            else:
                print('Nu exista secventa de lungime maxima in care oricare doua numere consecutive difera printr-un numar prim')
        elif option == 3:
            print_list('Secventa de lungime maxima in care suma elementelor este maxima este: ', find_3(current_list))
        elif option == 4:
            if(len(find_4(current_list)) > 0):
                print('Secventa de lungime maxima care are toate elementele in intervalul [0, 10] dat: ', find_4(current_list))
            else:
                print('Nu exista secventa de lungime maxima care are toate elementele in intervalul [0, 10] dat')
        elif option == 5:
            return

start()




