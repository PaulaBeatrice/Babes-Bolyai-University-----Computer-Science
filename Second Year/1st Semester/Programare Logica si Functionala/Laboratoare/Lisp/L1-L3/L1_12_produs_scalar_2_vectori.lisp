;Definiti o functie care intoarce produsul scalar a doi vectori.

; dotProduct(l1l2...ln, k1k2...km) = 
; = 0 , if n = 0
; = l1*l2 + dotProduct(l2...ln, k2...km) , otherwise

(defun dotProduct(l k)
  (cond
    ((null l) 0)
    (t (+ (* (car l) (car k)) (dotProduct (cdr l) (cdr k))))
  )
)