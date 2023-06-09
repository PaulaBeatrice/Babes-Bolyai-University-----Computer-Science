;Definiti o functie care, dintr-o lista de atomi, produce o lista de 
;perechi (atom n), unde atom apare in lista initiala de n ori. De ex:
;(A B A B A C A) --> ((A 4) (B 2) (C 1)).


; count(l1l2...ln, elem) = 
; = 0 , if n = 0
; = count(l1, elem) + count(l2...ln, elem) , if l1 is a list
; = 1 + count(l2...ln, elem) , if l1 = elem
; = count(l2...ln, elem), otherwise


(defun myCount(l elem)
  (cond
    ((null l) 0)
    ((listp (car l)) (+ (myCount (car l) elem) (myCount (cdr l) elem)))
    ((equal (car l) elem) (+ 1 (myCount (cdr l) elem)))
    (t (myCount (cdr l) elem))
  )
)

; removeElem(l1l2...ln, elem) = 
; = nil , if n = 0
; = myAppend(list(removeElem(l1, elem)), removeElem(l2...ln, elem)), if l1 is a list
; = removeElem(l2, elem) , if l1 = elem
; = l1 U removeElem(l2, elem) , otherwise

(defun removeElem(l elem)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (list (removeElem (car l) elem)) (removeElem (cdr l) elem)))
    ((equal (car l) elem) (removeElem (cdr l) elem))
    (t (cons (car l) (removeElem (cdr l) elem)))
  )
)

; myPairs(l1l2...ln, r1r2...rm) = 
; = r1r2...rm, if n = 0
; = myAppend(


; -- at a superficial level
(defun myPairs (l)
  (cond
    ((null l) nil)
    (t (cons (list (car l) (myCount l (car l))) (myPairs (removeElem (cdr l) (car l)))))
  )
)

; -- in the sublists as well
; -- didn't manage to do that