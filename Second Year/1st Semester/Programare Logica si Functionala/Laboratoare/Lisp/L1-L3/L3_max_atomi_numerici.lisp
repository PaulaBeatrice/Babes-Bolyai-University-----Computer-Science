;8.   Sa se construiasca o functie care intoarce maximul atomilor numericidintr-o lista, de la orice nivel.

; myMax(a, b) = 
; = nil, if a is not a number and b is not a number
; = b, if a is not a number
; = a, if b is not a number
; = a, if a > b
; = b, otherwise

(defun myMax(a b)
  (cond
    ((and (not (numberp a)) (not (numberp b))) nil)
    ((not (numberp a)) b)
    ((not (numberp b)) a)
    ((> a b) a)
    (t b)
  )
)


(defun maxList(l)
  (cond
    ((null l) nil)
    (t (myMax (car l) (maxList (cdr l))))
  )
)

(defun maxForList(l)
  (cond
    ((numberp l) l)
    ((atom l) nil)
    (t (apply #'maxList (list (mapcar #'maxForList l))))
  )
)