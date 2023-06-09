;Sa se scrie o functie care testeaza daca o lista este liniara.

; checkLinear(l1l2...ln) = 
; = true, if n = 0
; = false, if l1 is a list
; = checkLinear(l2...ln), otherwise


(defun checkLinear(l)
  (cond
    ((null l) t)
    ((listp (car l)) nil)
    (t (checkLinear (cdr l)))
  )
)