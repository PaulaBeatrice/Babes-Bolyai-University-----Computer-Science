; a)Definiti  o  functie  care  selecteaza  al  n-lea  element  al  unei  liste,  sau NIL, daca nu exista.

; NElem(l1l2...lm, n, pos) =                        ;main(l1...ln, n)=NElem(l1...ln, n, 1)
; = nil , if m = 0
; = l1 , if n = pos
; = NElem(l2...lm, n, pos + 1) , altfel

;NElem(L:Lista, n:Intreg, poz:Intreg)               ;main(L:Lista, n:Intreg)
;L = Lista care se parcurge                         ;L = Lista care se parcurge
;n = Pozitia a carui element este cerut             ;n = Pozitia a carui element este cerut
;poz = Pozitia curenta


(defun NElem(l n pos)
  (cond
    ((null l) nil)
    ((= n pos) (car l))
    (t (NElem (cdr l) n (+ pos 1)))
  )
)

(defun main(l n)
  (NElem l n 1)
)

;(main '(1 2 3 4 5) '3)=>3
;(main '(3 6 4 8 6) '2)=>6


; b)Sa se construiasca o functie care verifica daca un atom e membru al unei liste nu neaparat liniara.


; verifAtom(l1l2...ln, elem) = 
; = nil , n = 0
; = true , l1 este atom si l1 = elem
; = verifAtom(l1, elem) or verifAtom(l2...ln, elem) , l1 = list
; = verifAtom(l2...ln, elem) , altfel

;verifAtom(L:List, elem:Intreg)
;L=lista in care se verifica daca elem apartine de ea
;elem = elementul cautat

(defun verifAtom(l elem)
  (cond
    ((null l) nil)
    ((and (atom (car l)) (equal (car l) elem)) T)
    ((listp (car l)) (or (verifAtom (car l) elem) (verifAtom (cdr l) elem))) 
    (T (verifAtom (cdr l) elem))
  )
)

;(verifAtom '(1 2 (3 4) 5) '3)=>T

;c)Sa se construiasca lista tuturor sublistelor unei liste. Prin sublista se intelege fie lista insasi, fie un element de pe orice nivel, care este lista. 
;Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) => ( (1 2 (3 (4 5) (6 7)) 8 (9 10)) (3 (4 5) (6 7)) (45) (6 7) (9 10) ).

;mapcar function &rest lists+ => result-list

;allSublists(l) = 
; = nil, l=atom
; = append( l, allSubsets(l2...ln)), if T

;allSublists(L:List)
;L = Lista in care se lucreaza

(defun allSublists (l)
  (cond
    ((atom l) nil)
    (T (apply 'append (list l) (mapcar 'allSublists l)))
  )
)


;d)Sa se scrie o functie care transforma o lista liniara intr-o multime.

; transformMultime(l1l2...ln) =                                           ; removeAparente(l1l2...ln, elem) = 
; = (), n = 0                                                             ; = nil ,  n = 0
; = {l1} U transformMultime(removeApparences(l2...ln, l1)) , altfel       ; = removeAparente(l2...ln, elem) ,  l1 = elem
                                                                          ; = {l1} U removeAparente(l2...ln, elem) , altfel

(defun transformMultime(l)
  (cond
    ((null l) nil)
    (t (cons (car l) (transformMultime (removeAparente (cdr l) (car l)))))
  )
)

(defun removeAparente(l e)
  (cond 
    ((null l) nil)
    ((= (car l) e) (removeAparente (cdr l) e))
    (t (cons (car l) (removeAparente (cdr l) e)))
  )
)