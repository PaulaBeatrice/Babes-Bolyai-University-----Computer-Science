;Definiti o functie care intoarce suma a doi vectori.
; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm), otherwise


(defun myAppend (l1 l2)
  (cond
    ((null l1) l2)
    (t (cons (car l1) (myAppend (cdr l1) l2)))
  )
)

; myReverse(l1l2...ln) = 
; = NIL , if n = 0
; = myAppend(myReverse(l2...ln), myReverse(l1)) , if l1 is a list
; = myAppend(myReverse(l2...ln), list(l1)) , otherwise

(defun myReverse (l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (myReverse (cdr l)) (list(myReverse (car l)))))
    (t (myAppend (myReverse (cdr l)) (list (car l))))
  )
)


; sumVectors (l1l2...ln, p1p2...pm) = 
; = p1p2...pm , if n = 0 
; = {l1 + p1} U sumVectors(l2...ln, p2...pm), otherwise

(defun sumVectors (v1 v2)
  (cond
    ((null v1) v2)
    (t (cons (+ (car v1) (car v2)) (sumVectors (cdr v1) (cdr v2))))
  )
)