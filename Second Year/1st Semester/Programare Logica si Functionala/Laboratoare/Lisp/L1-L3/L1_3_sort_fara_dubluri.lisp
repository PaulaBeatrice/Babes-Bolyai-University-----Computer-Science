;Definiti o functie care sorteaza fara pastrarea dublurilor o lista 
;liniara.
; insert(l1l2...ln, elem) =
; = list(elem) , if n = 0
; = l1l2...ln , if l1 = elem
; = {elem} U l1l2...ln, if elem < l1
; = {l1} U insert(l2...ln, elem)

(defun insert (l e)
  (cond
    ((null l) (list e))
    ((= (car l) e) l)
    ((< e (car l)) (cons e l))
    (t (cons (car l) (insert (cdr l) e)))
  )
)

; sortare(l1l2...ln) = 
; = nil , if n = 0
; = insert(sortare(l2...ln), l1) , otherwise

(defun sortare (l)
  (cond
    ((null l) nil)
    (t (insert (sortare (cdr l)) (car l)))
  )
)