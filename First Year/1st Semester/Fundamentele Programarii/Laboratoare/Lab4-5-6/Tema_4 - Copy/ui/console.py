from domain.association import adaugare_cheltuiala_la_asociatie, stergere_cheltuiala_1, stergere_cheltuiala_2, \
    stergere_cheltuiala_3, undo, setup_association, get_lista_cheltuieli, filtru_suma, filtru_tip, filtru_suma_si_zi
from domain.cost import get_apartament, get_suma, get_tip, get_ziua, creare_cheltuiala


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


def print_lista_cheltuieli(lista_cheltuieli):
    for i, cheltuiala in enumerate(lista_cheltuieli):
        print(i, 'Apartament:', get_apartament(cheltuiala), 'Suma: ', get_suma(cheltuiala), 'Tip cheltuiala: ',
              get_tip(cheltuiala), 'Ziua efetuarii cheltuielii: ', get_ziua(cheltuiala))


def adaugare_cheltuiala_ui(asociatie):
    try:
        apartament = int(input("Introduceti apartamentul:"))
        suma = float(input("Introduceti suma cheltuielii:"))
        tip = input("Introduceti tipul cheltuielii:")
        ziua = int(input("Introduceti ziua efectuarii cheltuielii:"))
        if ziua >= 1 and ziua <= 31:
            p1 = creare_cheltuiala(apartament, suma, tip, ziua)
            adaugare_cheltuiala_la_asociatie(asociatie, p1)
            print("Adaugare realizata cu succes.")
        else:
            print('Introduceti o data calendaristica valida!')
            adaugare_cheltuiala_ui(asociatie)
    except ValueError:
        print("Ati introdus date invalide!")
        adaugare_cheltuiala_ui(asociatie)


def stergere_cheltuieli1_ui(asociatie):
    stergere_cheltuiala_1(asociatie)


def stergere_cheltuieli2_ui(asociatie):
    stergere_cheltuiala_2(asociatie)


def stergere_cheltuieli3_ui(asociatie):
    stergere_cheltuiala_3(asociatie)


def filtru1_ui(asociatie):
    try:
        suma = float(input('Suma este: '))
        lista_cheltuieli = get_lista_cheltuieli(asociatie)
        filtered_list = filtru_suma(lista_cheltuieli, suma)
        print_lista_cheltuieli(filtered_list)
    except ValueError:
        print('Ati introdus date invalide!')
        filtru1_ui(asociatie)


def filtru2_ui(asociatie):
    tip = input('Tipul cheltuielii este: ')
    lista_cheltuieli = get_lista_cheltuieli(asociatie)

    filtered_list = filtru_tip(lista_cheltuieli, tip)
    print_lista_cheltuieli(filtered_list)


def filtri3_ui(asociatie):
    try:
        suma = float(input('Suma este: '))
        zi = int(input('Ziua este: '))
        if zi >= 1 and zi <= 31:
            lista_cheltuieli = get_lista_cheltuieli(asociatie)

            filtered_list = filtru_suma_si_zi(lista_cheltuieli, suma, zi)
            print_lista_cheltuieli(filtered_list)
        else:
            print('Ati introdus date invalide!')
            filtri3_ui(asociatie)
    except ValueError:
        print('Ati introdus date invalide!')
        filtri3_ui(asociatie)


def undo_ui(asociatie):
    try:
        undo(asociatie)
        print('Undo realizat cu succes')
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
            print('ok')
        elif option == '3':
            stergere_cheltuieli1_ui(asociatie)
            print('Stergerea s-a efectuat cu succes')
        elif option == '4':
            stergere_cheltuieli2_ui(asociatie)
            print('Stergerea s-a efectuat cu succes')
        elif option == '5':
            stergere_cheltuieli3_ui(asociatie)
            print('Stergerea s-a efectuat cu succes')
        elif option == '6':
            filtru1_ui(asociatie)
        elif option == '7':
            filtru2_ui(asociatie)
        elif option == '8':
            filtri3_ui(asociatie)
        elif option.lower() == 'p':
            print_lista_cheltuieli(get_lista_cheltuieli(asociatie))
        elif option == '9':
            finished = True
        elif option == '10':
            undo_ui(asociatie)
        elif option == '11':
            pass
        elif option == '12':
            pass
        elif option == '13':
            pass
        elif option == '14':
            pass
        elif option == '15':
            pass
        else:
            print('Optiunea este invalida!')
