13. Seda un arbore de tipul (2). Sa se afiseze calea de la radacina pana la un nod xdat.

; (car l) - the first element of the list is the root of the tree
; (cadr l) - the second element of the list, at superficial level, is the left subtree
; (caddr l) - the third element of the list, at the superficial level, is the right subtree


; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm), otherwise


(defun myAppend (l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)


; checkExistence(l1l2...ln, elem) = 
; = true, if l1 = elem
; = false, if n = 0
; = checkExistence(l1, elem) or checkExistence(l2...ln, elem), if l1 is a list
; = checkExistence(l2...ln, elem), otherwise


(defun checkExistence(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) t)
    ((listp (car l)) (or (checkExistence (car l) elem) (checkExistence (cdr l) elem)))
    (t (checkExistence (cdr l) elem))
  )
)


; path(l1l2l3, elem) = 
; = nil, if l1l2l3 is empty
; = l1, if elem = l1
; = path(l2), if checkExistence(l2) = true
; = path(l3), if checkExistence(l3) = true

(defun path(l elem)
  (cond 
    ((null l) nil)
    ((equal (car l) elem) (list (car l)))
    ((checkExistence (cadr l) elem) (cons (car l) (path (cadr l) elem)))
    ((checkExistence (caddr l) elem) (cons (car l) (path (caddr l) elem)))
  )
)