;Definiti o functie care obtine dintr-o lista data lista tuturor atomilor 
;care apar, pe orice nivel, dar in ordine inversa. De exemplu: (((A B) C) 
;(D E)) --> (E D C B A)

;append(l1...ln, p1...pm) = p1...pm, n=0
;                         =l1 U append(l2...ln, p1...pm), otherwise

(defun appendA(l p)
   (cond
      ((null l) p)
      (t (cons (car l) (appendA (cdr l) p)))
   )
)

;getAtomi(l1...ln)=
; = nil, n=0
; = append(getAtomi(l2...ln), getAtomi(l1)), l1=lista
; = append(getAtomi(l2...ln), list(l1)), altfel

(defun getAtomi (l)
  (cond
     ((null l) nil)
     ((listp(car l)) (appendA (getAtomi (cdr l)) (getAtomi (car l))))
     (t (appendA (getAtomi (cdr l)) (list (car l))))
  )
)

;(load "L1_1_invers_list.lisp")
;(getAllAtoms '(((A B) C) (D E)))