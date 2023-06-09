;Sa se construiasca o functie care intoarce produsul atomilor numerici
;dintr-o lista, de la nivelul superficial.

; myProduct(l1l2...ln) = 
; = 1, if n = 0
; = l1 * myProduct(l2...ln) , if l1 is a number
; = myProduct(l2...ln) , otherwise


(defun myProduct (l)
  (cond
    ((null l) 1)
    ((numberp (car l)) (* (car l) (myProduct (cdr l))))
    (t (myProduct (cdr l)))
  )
)
