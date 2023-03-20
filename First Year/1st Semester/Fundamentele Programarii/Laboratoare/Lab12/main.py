# Generați bilete la PRONOSPORT pentru un bilet cu N meciuri. Pronosticurile pentru un meci pot fi 1,X,2. Generați toate
# variantele astfel încât: pronosticul de la ultimul meci nu poate fi X și există un maxim de două meciuri cu
# pronosticul 1.


# Varianta recursiva
def Backtracking_recursiv(solutie_bilet, DIM):
    """
    Functia va genera in mod recursiv toate variantele de bilete la PRONOSPORT pentru n meciuri
    :param n: numarul de meciuri(dimensiunea solutiei)
    :return:
    """

    def validare(candidat):
        # verificam daca biletul are lungimea n si daca ultimul element este diferit de 'X'
        if candidat[len(candidat) - 1] == "X" and len(candidat) == DIM:
            return False

        # verificam daca biletul are mai mult de 2 pronosticuri egale cu '1'
        contor = 0
        for el in candidat:
            if el == "1":
                contor += 1
            if contor > 2:
                return False
        return True

    DOMENIU = "1X2"
    solutie_bilet.append(0)
    for el in DOMENIU:
        solutie_bilet[-1] = el
        if validare(solutie_bilet):
            if len(solutie_bilet) == DIM:
                print("".join(el for el in solutie_bilet))
            elif len(solutie_bilet) < DIM:
                Backtracking_recursiv(solutie_bilet, DIM)
    solutie_bilet.pop(-1)


# Varianta iterativa
def Backtracking_iterativ(solutie, DIM):
    """
    Functia va genera in mod iterativ toate variantele de bilete la PRONOSPORT pentru n meciuri
    :param n: numarul de meciuri(dimensiunea solutiei)
    :return:
    """

    def validare(candidat, DOMENIU):
        try:
            candidat = [DOMENIU[el] for el in candidat]
        except IndexError:
            return False

        # verificam daca biletul are lungimea n si daca ultimul element este diferit de 'X'
        if candidat[-1] == "X" and len(candidat) == DIM:
            return False

        # verificam daca biletul are mai mult de 2 pronosticuri egale cu '1'
        contor = 0
        for pronostic_val in candidat:
            if pronostic_val == "1":
                contor += 1
            if contor > 2:
                return False
        return True

    DOMENIU = "1X2"
    solutie.append(0)
    while len(solutie) > 0:
        el_ales = False
        while not el_ales and solutie[-1] < 3:
            solutie[-1] += 1
            el_ales = validare(solutie, DOMENIU)

        if el_ales:
            if len(solutie) == DIM:
                print("".join(DOMENIU[el] for el in solutie))
            else:
                solutie.append(-1)
        else:
            solutie = solutie[:-1]


Backtracking_recursiv([], int(input("Introduceti numarul de meciuri(generare recursiva): ")))
Backtracking_iterativ([], int(input("Introduceti numarul de meciuri(generare prin varianta iterativa): ")))

