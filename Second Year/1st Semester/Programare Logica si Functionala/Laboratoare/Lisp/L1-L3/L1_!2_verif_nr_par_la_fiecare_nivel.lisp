;Sa se scrie o functie care intoarce T daca o lista are numar par de 
;elemente pe primul nivel si NIL in caz contrar, fara sa se numere 
;elementele listei.

; evenLength(l1l2...ln) =
; = true , if n = 0 
; = false , if n = 1
; = evenLength(l3...ln) , otherwise


(defun evenLength(l)
  (cond
    ((null l) T)
    ((null (cdr l)) nil)
    (t (evenLength (cddr l)))
  )
)