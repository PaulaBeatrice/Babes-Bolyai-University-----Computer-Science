;6.   Sa se construiasca o functie care intoarce produsul atomilor numerici dintr-o lista, de la orice nivel.

; myProduct(l) = 
; = l, if l is a number
; = 1, if l is an atom
; = myProduct(l1) * myProduct(l2) * ... * myProduct(ln), otherwise (if l is a list of the type l = l1l2...ln)

(defun myProduct(l)
  (cond
    ((numberp l) l)
    ((atom l) 1)
    (t (apply '* (mapcar #'myProduct l)))
  )
)