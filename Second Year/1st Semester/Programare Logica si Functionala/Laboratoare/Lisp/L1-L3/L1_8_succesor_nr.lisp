;Definiti o functie care determina succesorul unui numar reprezentat cifra
;cu cifra intr-o lista. De ex: (1 9 3 5 9 9) --> (1 9 3 6 0 0)


; carry(a b c) = 
; = 1 , if a + b + c > 9
; = 0 , if a + b + c <= 9

(defun carry(a b c)
  (cond
    (( > (+(+ a b) c) 9) 1)
    (t 0)
  )
)

; digit(a b c) = 
; (a + b + c) mod 10 , if a + b + c > 9
; a + b + c , if a + b + c <=9

(defun digit(a b c)
  (cond
    (( > (+ (+ a b) c) 9) (mod (+ (+ a b) c) 10))
    (t (+ (+ a b) c))
  )
)

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm) , otherwise

(defun myAppend(l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)

; myReverse(l1l2...ln) = 
; = nil , if n = 0
; = myAppend(myReverse(l2...ln), list(myReverse(l1))), if l1 is a list
; = myAppend(myReverse(l2...ln), list(l1)), otherwise


(defun myReverse(l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (myReverse (cdr l)) (list (myReverse (car l)))))
    (t (myAppend (myReverse (cdr l)) (list (car l))))
  )
)

; myAdd(l1l2...ln, p1p2...pm, c, r) = 
; = c , if n = 0 and c = 1
; = nil , if n = 0 and c = 0
; = myAdd(l2...ln, nil, carry(l1, 0, c), {digit(l1, 0, c)} U r) , if m = 0
; = myAdd(nil, p2...pn, carry(0, p1, c), {digit(0, p1, c)} U r) , if n = 0
; = myAdd(l2...ln, p2...pn, carry(l1, p1, c), {digit(l1, p1, c)} U r) , otherwise

(defun myAdd(l p c r)
  (cond 
    ((and (and (null l) (null p)) (equal c 1)) (cons c r))
    ((and (and (null l) (null p)) (equal c 0)) r)
    ((null p) (myAdd (cdr l) nil (carry (car l) 0 c) (cons (digit (car l) 0 c) r)))
    ((null l) (myAdd nil (cdr p) (carry 0 (car p) c) (cons (digit 0 (car p) c) r)))
    (t (myAdd (cdr l) (cdr p) (carry (car l) (car p) c) (cons (digit (car l) (car p) c) r)))
  )
)

(defun mySuccessor(l)
  (myAdd (myReverse l) (list 1) 0 (list ))
)