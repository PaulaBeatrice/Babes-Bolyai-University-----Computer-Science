;Definiti o functie care intoarce cel mai mare divizor comun al numerelor 
;dintr-o lista neliniara.
;myGCD(a, b) =
; = a , if b is not a number
; = b , if a is not a number
; = a , if b = 0
; = myGCD(b, a%b) , otherwise


(defun myGCD(a b)
  (cond
    ((not (numberp a)) b)
    ((not (numberp b)) a)
    ((= 0 b) a)
    (t (myGCD b (mod a b)))
  )
)

;The following function will return NIL if there is no number and True otherwise
; lookForNumbers(l1l2...ln) = 
; = NIL , if n = 0
; = lookForNumbers(l1) or lookForNumbers(l2...ln) , if l1 is a list
; = true , if l1 is a number
; = lookForNumbers(l2...ln) , otherwise

(defun lookForNumbers(l)
  (cond
    ((null l) nil)
    ((listp (car l)) (or (lookForNumbers (car l)) (lookForNumbers (cdr l))))
    ((numberp (car l)) T)
    (t (lookForNumbers (cdr l)))
  )
)

; gcdForAllNumbers(l1l2...ln) = 
; = l1 , if n = 1
; = myGCD(gcdForAllNumbers(l1), gcdForAllNumbers(l2...ln)) , if l1 is a list
; = myGCD(l1, gcdForAllNumbers(l2...ln)) , otherwise


(defun gcdForAllNumbers(l)
  (cond
    ((null (cdr l)) (car l))
    ((listp (car l)) (myGCD (gcdForAllNumbers (car l)) (gcdForAllNumbers (cdr l))))
    (t (myGCD (car l) (gcdForAllNumbers (cdr l))))
  )
)

;If we don't have any numbers in the list we simply return 1
;Otherwise we go look for the gcd
(defun main(l)
  (cond
    ((lookForNumbers l) (gcdForAllNumbers l))
    (t 1)
  )
)