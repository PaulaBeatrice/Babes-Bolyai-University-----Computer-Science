;Sa se scrie o functie care testeaza daca o lista liniara este o multime.

; checkSet(l1l2...ln, r) = 
; = true, if n = 0
; = false, if checkExistence(r, l1) = true
; = checkSet(l2...ln, {l1} U r) , otherwise


(defun checkSet(l r)
  (cond
    ((null l) t)
    ((checkExistence r (car l)) nil)
    (t (checkSet (cdr l) (myAppend r (list (car l)))))
  )
)