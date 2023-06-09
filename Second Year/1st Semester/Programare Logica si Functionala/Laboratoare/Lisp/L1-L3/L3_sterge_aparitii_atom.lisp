;11.  Sa se scrie o functie care sterge toate aparitiile unui atom de latoate nivelurile unei liste.

; removeAux(l1l2...ln) = 
; = [], if n = 0
; = removeAux(l2...ln), if l1 is an atom and l1 = []
; = {l1} U removeAux(l2...ln), if l1 is an atom
; = removeAux(l1) U removeAux(l2...ln), otherwise

(defun removeAux(l)
  (cond
    ((null l) nil)
    ((and (atom (car l)) (equal (car l) nil)) (removeAux (cdr l)))
    ((atom (car l)) (cons (car l) (removeAux (cdr l))))
    (t (cons (removeAux (car l)) (removeAux (cdr l))))
  )
)

; removeElem(l, elem) = 
; = l , if l is an atom and l != elem
; = nil, if l is an atom and l = elem
; = removeElem(l1, elem) U removeElem(l2, elem) U ... U removeElem(ln, elem) , otherwise


(defun removeElem(l elem)
  (cond
    ((and (atom l) (equal l elem)) nil)
    ((atom l) l)
    (t (apply #'removeAux (list (mapcar #' (lambda (a) (removeElem a elem)) l))))
  )
)