;2.  Definiti o functie care obtine dintr-o lista data lista tuturor atomilorcare apar, pe orice nivel, dar in aceeasi ordine. De exemplu (((A B) C) (D E)) --> (A B C D E)

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm), otherwise

(defun myAppend (l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)


; myAppendList(l1l2...ln)
; = nil, if n = 0
; = myAppend(l1, myAppendList(l2...ln)), otherwise


(defun myAppendList(l)
  (cond
    ((null l) nil)
    (t (myAppend (car l) (myAppendList (cdr l))))
  )
)

; myLinearize(l) = 
; = (list l), if l is an atom
; = myAppendList(myLinearize(l1), myLinearize(l2), ..., myLinearize(ln)), otherwise where l is a list of the type l = l1l2...ln

(defun myLinearize(l)
  (cond 
    ((atom l) (list l))
    (t (apply #'myAppendList (list (mapcar #'myLinearize l))))
  )
)