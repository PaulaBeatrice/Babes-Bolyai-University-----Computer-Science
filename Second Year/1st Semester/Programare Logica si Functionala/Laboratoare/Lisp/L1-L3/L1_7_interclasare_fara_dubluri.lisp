;Definiti o functie care interclaseaza fara pastrarea dublurilor doua liste 
;liniare sortate.

; myMerge(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myMerge(l2...ln, p1p2...pm) , if l1 < p1
; = {p1} U myMerge(l1l2...ln, p2...pm) , if l1 > p1
; = {l1} U myMerge(l2...ln, p2...pm) , if l1 = p1


(defun myMerge (l p)
  (cond
    ((null l) p)
    ((null p) l)
    ((< (car l) (car p)) (cons (car l) (myMerge (cdr l) p)))
    ((> (car l) (car p)) (cons (car p) (myMerge l (cdr p))))
    ((= (car l) (car p)) (cons (car l) (myMerge (cdr l) (cdr p))))
  )
)