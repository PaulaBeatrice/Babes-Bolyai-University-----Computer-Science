;Sa se scrie o functie care realizeaza o lista de asociere cu cele doua
;liste pe care le primeste. De ex: (A B C) (X Y Z) --> ((A.X) (B.Y) 
;(C.Z)).

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm), otherwise

(defun myAppend(l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)

; association(l1l2...ln, p1p2...pm) = 
; = nil, if n = 0 and m = 0
; = myAppend(association(nil, p2...pm), list({nil} U {p1})), if n = 0
; = myAppend(association(l2...ln, nil), list({l1} u {nil})), if m = 0
; = myAppend(association(l2...ln, p2...pm), list({l1} U {p1})), otherwise


(defun association(l p)
  (cond
    ((and (null l) (null p)) nil)
    ((null l) (myAppend (list (cons nil (car p))) (association nil (cdr p) )))
    ((null p) (myAppend (list (cons (car l) nil)) (association (cdr l) nil )))
    (t (myAppend (list (cons (car l) (car p))) (association (cdr l) (cdr p) )))
  )
)