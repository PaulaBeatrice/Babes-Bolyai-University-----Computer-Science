;Sa se determine rezultatul unei expresii aritmetice memorate in preordine
;pe o stiva. Exemple:
;(+ 1 3) ==> 4 (1 + 3)
;(+ * 2 4 3) ==> 11 ((2 * 4) + 3)
;(+ * 2 4 - 5 * 2 2) ==> 9 ((2 * 4) + (5 - (2 * 2))

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


; evaluate(l1l2...ln, s1s2...sm) = 
; s1 , if n = 0
; evaluate(l2...ln, {l1} U s1s2...sm)  , if l1 is a number
; evaluate(l2...ln, {s1 * s2} U s3...sm) , if l1 = "*"
; evaluate(l2...ln, {s1 + s2} U s3...sm) , if l1 = "+"
; evaluate(l2...ln, {s1 / s2} U s3...sm) , if l1 = "/"
; evaluate(l2...ln, {s1 - s2} U s3...sm) , if l1 = "-"



(defun evaluate(l s)
  (cond
    ((null l) (car s))
    ((numberp (car l)) (evaluate (cdr l) (cons (car l) s)))
    ((string= "*" (car l)) (evaluate (cdr l) (cons (* (car s) (cadr s)) (cddr s))))
    ((string= "+" (car l)) (evaluate (cdr l) (cons (+ (car s) (cadr s)) (cddr s))))
    ((string= "/" (car l)) (evaluate (cdr l) (cons (floor (car s) (cadr s)) (cddr s))))
    (t (evaluate (cdr l) (cons (- (car s) (cadr s)) (cddr s))))
  )
)


; mainC(l1l2...ln) = 
; = evaluate(myReverse(l1l2...ln) ()) 

(defun mainC(l)
  (cond
    (t (evaluate (myReverse l) ()))
  )
)
