;Sa se scrie o functie care intoarce intersectia a doua multimi.
; contains(elem, l1l2...ln) = 
; = NIL, if n = 0
; = true, if l1 = elem
; = contains(elem, l2...ln), otherwise


(defun contains(e l)
  (cond
    ((null l) nil)
    ((equal (car l) e) t)
    (t (contains e (cdr l)))
  )
)


; intersection(l1l2...ln, p1p2...pm) = 
; = NIL , if n = 0
; = {l1} U intersection(l2...ln, p1p2...pm) , if contains(l1, p1p2...pm) is true
; = intersection(l2...ln, p1p2...pm) , otherwise


(defun intersection_(l p)
  (cond
    ((null l) NIL)
    ((contains (car l) p) (cons (car l) (intersection_ (cdr l) p)))
    (t (intersection_ (cdr l) p))
  )
)