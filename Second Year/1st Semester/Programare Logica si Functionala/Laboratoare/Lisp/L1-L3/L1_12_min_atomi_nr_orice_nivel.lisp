;Sa se construiasca o functie care intoarce minimul atomilor numerici
;dintr-o lista, de la orice nivel.

; myMin(a, b) = 
; = nil , if a is not a number and b is not a numbe
; = a , if b is not a numbe
; = b , if a is not a number
; = a , if a < b
; = b , otherwise

(defun myMin(a b)
  (cond
    ((and (not (numberp a)) (not (numberp b))) nil)
    ((not (numberp a)) b)
    ((not (numberp b)) a)
    ((< a b) a)
    (t b)
  )
)


; findMin(l1l2...ln) = 
; = l1 , if n = 1 and l1 is an atom
; = myMin(findMin(l1), findMin(l2...ln)) , if l1 is a list
; = myMin(l1, findMin(l2...ln)) , otherwise


(defun findMin(l)
  (cond
    ((and (null (cdr l)) (atom (car l))) (car l))
    ((listp (car l)) (myMin (findMin (car l)) (findMin (cdr l))))
    (t (myMin (car l) (findMin (cdr l))))
  )
)
