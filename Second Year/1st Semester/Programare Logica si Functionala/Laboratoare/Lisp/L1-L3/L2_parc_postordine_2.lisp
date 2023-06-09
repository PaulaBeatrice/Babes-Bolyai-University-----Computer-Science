;14. Sa se construiasca lista nodurilor unui arbore de tipul (2) parcurs in postordine.

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


; postorder(l1l2l3) = 
; = nil, if l1l2l3 is empty
; = myAppend(postorder(l2), myAppend(postorder(l3, list(l1)))), otherwise


(defun postorder(l)
  (cond
    ((null l) nil)
    (t (myAppend (postorder (cadr l)) (myAppend (postorder (caddr l)) (list (car l)))))
  )
)