;Sa se construiasca o functie care intoarce suma atomilor numerici dintr-o 
;lista, de la nivelul superficial.

; sum(l1l2...ln) = 
; = 0 , if n = 0
; = l1 + sum(l2...ln), if l1 is a number
; = sum(l2...ln), otherwise


(defun sum(l)
  (cond
    ((null l) 0)
    ((numberp (car l)) (+ (car l) (sum (cdr l))))
    (t (sum (cdr l)))
  )
)