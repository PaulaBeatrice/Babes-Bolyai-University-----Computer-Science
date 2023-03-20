from domain.association import adaugare_cheltuiala_la_asociatie, stergere_cheltuiala_2, \
    stergere_cheltuiala_3, undo, setup_association, get_lista_cheltuieli, filtru_suma, filtru_tip, filtru_suma_si_zi, \
    modificare_cheltuiala, get_undo_list, set_lista_cheltuieli, eliminare_1, eliminare_2, \
    filtru_suma_totala_pentru_un_anumit_tip, filtru_apartamente_sortate_dupa_tip, filtru_suma_totala_apartament, \
    stergere_cheltuiala_apartament, stergere_cheltuiala1
from domain.cost import get_apartament, get_suma, get_tip, get_ziua
from utils.list_operations import make_list_copy, add_to_list
from termcolor import colored


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
    print("10. Undo ultima operatie")
    print("11. Elimină toate cheltuielile de un anumit tip")
    print("12. Elimină toate cheltuielile mai mici decât o sumă dată")
    print("13. Tipărește suma totală pentru un tip de cheltuială")
    print("14. Tipărește toate apartamentele sortate după un tip de cheltuială")
    print("15. Tipărește totalul de cheltuieli pentru un apartament dat")

def print_meniu_nou():
    print("Introduceti 'adauga, int, float, string, int, pentru a adauga o cheltuiala")
    print("Introduceti 'sterge, int pentru a sterge cheltuielile de la un apartament")
    print("Introduceti 'filtru, string pentru a afisa cheltuielile de un anumit tip")
    print("Introduceti 'tipareste', string' pentru a tipari totalul de cheltuieli pentru un anumit tip")

def print_lista_cheltuieli(lista_cheltuieli):
    for i, cheltuiala in enumerate(lista_cheltuieli):
        print(i, colored('Nr. apartament: ', 'magenta'), get_apartament(cheltuiala),
              colored(' Suma cheltuielii: ', 'magenta'), get_suma(cheltuiala),
              colored(' Tipul cheltuielii: ', 'magenta'), get_tip(cheltuiala),
              colored('Ziua efectuarii cheltuielii: ', 'magenta'), get_ziua(cheltuiala))


def adaugare_cheltuiala_ui(asociatie):
    try:
        apartamentul = int(input("Introduceti numarul apartamentului:"))
        suma = float(input("Introduceti suma cheltuielii:"))
        tip = input("Introduceti tipul cheltuielii:")
        ziua = int(input("Introduceti ziua efectuarii cheltuielii:"))
    except ValueError:
        print(colored("Ati introdus date invalide!", 'red'))
        adaugare_cheltuiala_ui(asociatie)

    try:
        adaugare_cheltuiala_la_asociatie(asociatie, apartamentul, suma, tip, ziua)
        print(colored("Adaugarea s-a realizat cu succes.", 'green'))
    except ValueError as ve:
        print(colored(ve, 'red'))


def modificare_cheltuiala_ui(asociatie):
    lista_cheltuieli = get_lista_cheltuieli(asociatie)
    print_lista_cheltuieli(lista_cheltuieli)
    try:
        apartament = int(input("Numarul apartamentului la care vom modifica cheltuiala este:"))
        tip = input("Introduceti tipul cheltuielii:")
        suma = float(input("Introduceti suma noua:"))
    except ValueError as ve:
        print(colored("Ati introdus date invalide!", 'red'))
        modificare_cheltuiala_ui(asociatie)
    try:
        crt_lista_cheltuieli = get_lista_cheltuieli(asociatie)
        undo_list = get_undo_list(asociatie)
        undo_list.append(make_list_copy(crt_lista_cheltuieli))
        set_lista_cheltuieli(asociatie, modificare_cheltuiala(crt_lista_cheltuieli, suma, apartament, tip))
        print(colored('Modificarea s-a efectuat cu succes.', 'green'))
    except ValueError as ve:
        print(colored("Nu s-a efectuat modificarea!", 'red'))


def stergere_cheltuieli1_ui(asociatie):
    try:
        apartament = int(input("Introduceti numarul apartamentului: "))
    except ValueError:
        print(colored("Ati introdus date invalide!"), 'red')
        stergere_cheltuieli1_ui(asociatie)
    try:
       stergere_cheltuiala1(asociatie, apartament)
    except ValueError:
        print(colored('Nu s-a efectuat stergere', 'red'))


def stergere_cheltuieli2_ui(asociatie):
    stergere_cheltuiala_2(asociatie)


def stergere_cheltuieli3_ui(asociatie):
    stergere_cheltuiala_3(asociatie)


def eliminare_cheltuieli1_ui(asociatie):
    eliminare_1(asociatie)


def eliminare_cheltuieli2_ui(asociatie):
    eliminare_2(asociatie)


def filtru1_ui(asociatie):
    try:
        suma = float(input('Suma este: '))
        lista_cheltuieli = get_lista_cheltuieli(asociatie)
        filtered_list = filtru_suma(lista_cheltuieli, suma)
        print_lista_cheltuieli(filtered_list)
    except ValueError:
        print(colored("Ati introdus date invalide!", 'red'))
        filtru1_ui(asociatie)


def filtru2_ui(asociatie):
    tip = input('Tipul cheltuielii este: ')
    lista_cheltuieli = get_lista_cheltuieli(asociatie)

    filtered_list = filtru_tip(lista_cheltuieli, tip)
    print_lista_cheltuieli(filtered_list)


def filtru3_ui(asociatie):
    try:
        suma = float(input('Suma este: '))
        zi = int(input('Ziua este: '))
        if zi >= 1 and zi <= 31:
            lista_cheltuieli = get_lista_cheltuieli(asociatie)

            filtered_list = filtru_suma_si_zi(lista_cheltuieli, suma, zi)
            print_lista_cheltuieli(filtered_list)
        else:
            print(colored("Ati introdus date invalide!", 'red'))
            filtru3_ui(asociatie)
    except ValueError:
        print(colored("Ati introdus date invalide!", 'red'))
        filtru3_ui(asociatie)


def raport_1_ui(asociatie):
    try:
        tip = input('Tipul este: ')
        lista_cheltuieli = get_lista_cheltuieli(asociatie)
        suma = filtru_suma_totala_pentru_un_anumit_tip(lista_cheltuieli, tip)
        print(colored('Suma totala este', 'green'), colored(suma, 'green'))
    except ValueError:
        print(colored("Ati introdus date invalide!", 'red'))
        raport_1_ui(asociatie)


def raport_2_ui(asociatie):
    try:
        tip = input('Tipul este: ')
        lista_cheltuieli = get_lista_cheltuieli(asociatie)
        lista_apartamente = filtru_apartamente_sortate_dupa_tip(lista_cheltuieli, tip)
        print(colored('Apartamentele care au cheltuieli de acest tip sunt:', 'green'),
              colored(lista_apartamente, 'green'))
    except ValueError:
        print(colored("Ati introdus date invalide!", 'red'))
        raport_2_ui(asociatie)


def raport_3_ui(asociatie):
    try:
        apartament = int(input('Apartamentul este: '))
        lista_cheltuieli = get_lista_cheltuieli(asociatie)
        total = filtru_suma_totala_apartament(lista_cheltuieli, apartament)
        print(colored('Totalul de cheltuieli la apartamentul dat este de:', 'green'), colored(total, 'green'))
    except ValueError:
        print(colored("Ati introdus date invalide!", 'red'))
        raport_3_ui(asociatie)


def undo_ui(asociatie):
    try:
        undo(asociatie)
        print(colored('Undo realizat cu succes', 'green'))
    except ValueError as ve:
        print(ve)


def start():
    asociatie = setup_association(True)
    finished = False
    while not finished:
        print_meniu()
        option = input("Optiunea este:")
        if option == '1':
            adaugare_cheltuiala_ui(asociatie)
        elif option == '2':
            modificare_cheltuiala_ui(asociatie)
        elif option == '3':
            stergere_cheltuieli1_ui(asociatie)
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif option == '4':
            stergere_cheltuieli2_ui(asociatie)
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif option == '5':
            stergere_cheltuieli3_ui(asociatie)
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif option == '6':
            filtru1_ui(asociatie)
        elif option == '7':
            filtru2_ui(asociatie)
        elif option == '8':
            filtru3_ui(asociatie)
        elif option.lower() == 'p':
            print_lista_cheltuieli(get_lista_cheltuieli(asociatie))
        elif option == '9':
            finished = True
        elif option == '10':
            undo_ui(asociatie)
        elif option == '11':
            eliminare_cheltuieli1_ui(asociatie)
            print(colored('Eliminarea s-a efectuat cu succes', 'green'))
        elif option == '12':
            eliminare_cheltuieli2_ui(asociatie)
            print(colored('Eliminarea s-a efectuat cu succes', 'green'))
        elif option == '13':
            raport_1_ui(asociatie)
        elif option == '14':
            raport_2_ui(asociatie)
        elif option == '15':
            raport_3_ui(asociatie)
        else:
            print(colored('Optiunea este invalida!', 'red'))


def new_start():
    asociatie = setup_association(True)
    ok = 0
    finished = False
    while not finished:
        print_meniu_nou()
        print_meniu()
        comanda = input("Comanda este: ")
        comanda_data = comanda.split()
        if comanda_data[0] == 'adauga' and len(comanda_data) > 3:
            ok = 1
            try:
                apartament = int(comanda_data[1])
                suma = float(comanda_data[2])
                zi = int(comanda_data[4])
                adaugare_cheltuiala_la_asociatie(asociatie,apartament, suma, comanda_data[3], zi)
                print(colored("Adaugarea s-a realizat cu succes.", 'green'))
            except ValueError:
                print(colored('Ati introdus o comanda invalida', 'red'))
        elif comanda_data[0] == 'sterge' and len (comanda_data) >= 1:
            ok = 1
            try:
                apartament = int(comanda_data[1])
                stergere_cheltuiala1(asociatie, apartament)
            except ValueError:
                print(colored('Ati introdus o comanda invalida', 'red'))
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif comanda_data[0] == 'filtru' and len (comanda_data) >= 1:
            ok = 1
            try:
                lista_cheltuieli = get_lista_cheltuieli(asociatie)
                filtered_list = filtru_tip(lista_cheltuieli, comanda_data[1])
                print_lista_cheltuieli(filtered_list)
            except ValueError:
                print(colored('Ati introdus o comanda invalida', 'red'))
        elif comanda_data[0] == 'tipareste' and len (comanda_data) >= 1:
            ok = 1
            try:
                lista_cheltuieli = get_lista_cheltuieli(asociatie)
                suma = filtru_suma_totala_pentru_un_anumit_tip(lista_cheltuieli, comanda_data[1])
                print(colored('Suma totala este', 'green'), colored(suma, 'green'))
            except ValueError:
                print(colored('Ati introdus o comanda invalida', 'red'))
        if comanda_data[0] == '1':
            ok = 1
            adaugare_cheltuiala_ui(asociatie)
        elif comanda_data[0] == '2':
            ok = 1
            modificare_cheltuiala_ui(asociatie)
        elif comanda_data[0] == '3':
            ok = 1
            stergere_cheltuieli1_ui(asociatie)
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif comanda_data[0] == '4':
            ok = 1
            stergere_cheltuieli2_ui(asociatie)
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif comanda_data[0] == '5':
            ok = 1
            stergere_cheltuieli3_ui(asociatie)
            print(colored('Stergerea s-a efectuat cu succes', 'green'))
        elif comanda_data[0] == '6':
            ok = 1
            filtru1_ui(asociatie)
        elif comanda_data[0] == '7':
            ok = 1
            filtru2_ui(asociatie)
        elif comanda_data[0] == '8':
            ok = 1
            filtru3_ui(asociatie)
        elif comanda_data[0].lower() == 'p':
            ok = 1
            print_lista_cheltuieli(get_lista_cheltuieli(asociatie))
        elif comanda_data[0] == '9':
            ok = 1
            finished = True
        elif comanda_data[0] == '10':
            ok = 1
            undo_ui(asociatie)
        elif comanda_data[0] == '11':
            ok = 1
            eliminare_cheltuieli1_ui(asociatie)
            print(colored('Eliminarea s-a efectuat cu succes', 'green'))
        elif comanda_data[0] == '12':
            ok = 1
            eliminare_cheltuieli2_ui(asociatie)
            print(colored('Eliminarea s-a efectuat cu succes', 'green'))
        elif comanda_data[0] == '13':
            ok = 1
            raport_1_ui(asociatie)
        elif comanda_data[0] == '14':
            ok = 1
            raport_2_ui(asociatie)
        elif comanda_data[0] == '15':
            ok = 1
            raport_3_ui(asociatie)
        elif ok == 0:
            print(colored('Optiunea este invalida!', 'red'))

