;Sa se construiasca o functie care intoarce produsul atomilor numerici pari 
;dintr-o lista, de la orice nivel.

; isEven(a) = 
; = t , if a % 2 = 1
; = nil (false) , otherwise


(defun isEven (a)
  (cond
    ((equal 0 (mod a 2)) t)
    (t nil)
  )
)

; productEvenNumbers(l1l2...ln) = 
; = 1, if n = 0
; = productEvenNumbers(l1) * productEvenNumbers(l2...ln) , if l1 is a list
; = l1 * productEvenNumbers(l2...ln) , if l1 is a number and is even
; = productEvenNumbers(l2...ln) , otherwise


(defun productEvenNumbers(l)
  (cond
    ((null l) 1)
    ((listp (car l)) (* (productEvenNumbers (car l)) (productEvenNumbers (cdr l))))
    ((and (numberp (car l)) (isEven (car l))) (* (car l) (productEvenNumbers (cdr l))))
    (t (productEvenNumbers (cdr l)))
  )
)


; First we check if we have any numbers in the list, if we have then we try to compute the product
; Otherwise we simply return 0
(defun mainD(l)
  (cond
    ((lookForNumbers l) (productEvenNumbers l))
    (t 0)
  )
)