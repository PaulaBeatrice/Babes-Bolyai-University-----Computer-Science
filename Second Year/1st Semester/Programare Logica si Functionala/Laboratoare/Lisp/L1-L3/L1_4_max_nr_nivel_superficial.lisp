;Sa se construiasca o functie care intoarce maximul atomilor numerici 
;dintr-o lista, de la nivelul superficial.

; myMax(a,b) = 
; = a , if b is not a number
; = b , if a is not a number
; = a , if a > b
; = b , otherwise


(defun myMax (a b)
  (cond
    ((not (numberp a)) b)
    ((not (numberp b)) a)
    ((> a b) a)
    (t b)
  )
)


; maxForList (l1l2...ln) = 
; = NIL , if n = 0
; = myMax(l1, maxForList(l2...ln)) , otherwise


(defun maxForList(l)
  (cond
    ((null l) nil)
    (t (myMax (car l) (maxForList (cdr l))))
  )
)