;Definiti o functie care determina suma a doua numere in reprezentare de 
;lista si calculeaza numarul zecimal corespunzator sumei.

; myAppend(l1l2...ln, k1k2...km) = 
; = k1k2...km, if n = 0
; = l1 U myAppend(l2...ln, k1k2...km), otherwise

(defun myAppend (L1 L2)
  (cond
    ((null L1) L2)
    (T (cons (car L1) (myAppend (cdr L1) L2)))
  )
)


; myReverse(l1l2...ln) = 
; = (), if n = 0
; = myAppend( myReverse(l2...ln), list (myReverse(l1)))  , if l1 is a list
; = myAppend( myReverse(l2...ln), list(l1)), otherwise


(defun myReverse (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (myAppend (myReverse (cdr l)) (list (myReverse (car l)))))
        (T (myAppend (myReverse (cdr l)) (list (car l))))
    )
)

; carry(e1, e2, c) = 
; = 1, if e1 + e2 + c > 9
; = 0, otherwise

(defun carry (e1 e2 c)
  (cond
    ((< 9 (+ c(+ e1 e2))) 1)
    (t 0)
  )
)


; digit(e1, e2, c) = 
; = e1 + e2 + c mod 10, if e1 + e2 + c > 9
; = e1 + e2 + c, otherwise

(defun digit (e1 e2 c)
  (cond
    ((< 9 (+ c(+ e1 e2))) (mod (+ c(+ e1 e2)) 10))
    (t (+ c (+ e1 e2)))
  )
) 
    
; sumLists(l1l2...ln, k1k2...km, c, r) = 
; = r , if n = 0 and m = 0 and c = 0
; = {c} U r , if n = 0 and m = 0 and c != 0
; = sumLists(l2...ln, k, carry(l1, 0, c), {digit(l1, 0, c)} U r) , if m = 0
; = sumLists(l, k2...km, carry(0, k1, c), {digit(0, k1, c)} U r) , if n = 0
; = sumLists(l2...ln, k2...km, carry(l1, k1, c), {digit(l1, k1, c)} U r) , otherwise


(defun sumLists (l k c r)
  (cond
    ((and (null l)(null k)(= 0 c)) r)
    ; if we reach this case it means both lists are empty,
    ; we no longer have a cary, so we simply return the result
    ((and (null l)(null k)(/= 0 c)) (cons c r))
    ; if we reach this case it means both lists are empty, but we still have a carry so which means
    ; we need to add the carry to the result ( for example we had the numbers 9 and 1
    ; after we add them we obtain both lists empty, in the result we have a 0, but we
    ; still neet to add that 1
    ((null k) (sumLists (cdr l) k (carry (car l) 0 c) (cons (digit (car l) 0 c) r)))
    ; if we reach this case, it means the second number doesn't have digits anymore
    ; so we simply add the rest of the digits of the first number to the result
    ((null l) (sumLists l (cdr k) (carry 0 (car k) c) (cons (digit 0 (car k) c) r)))
    ; if we reach this case, it means the first number doesn't have digits anymote
    ; so we simply add the rest of the digits of the second number to the result
    (t (sumLists (cdr l) (cdr k) (carry (car l) (car k) c) (cons (digit (car l) (car k) c) r)))
    ; if we reach this case, it means both numbers still have digits so we add them in order to compute the result
  )
) 

(defun main (l k)
  (cond
    (t (sumLists (myReverse l) (myReverse k) 0 ()))
  )
)