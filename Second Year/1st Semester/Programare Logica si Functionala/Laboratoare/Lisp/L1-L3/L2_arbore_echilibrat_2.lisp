;16. Sa se decida daca un arbore de tipul (2) este echilibrat (diferenta dintreadancimile celor 2 subarbori nu este mai mare decat 1).

; absoluteDiff(a, b) = 
; = a - b, if a > b
; = b - a, otherwise


(defun absoluteDiff(a b)
  (cond
    ((> a b) (- a b))
    (t (- b a))
  )
)

; myMax(a, b) = 
; = a, if a > b
; = b, otherwise


(defun  myMax(a b)
  (cond
    ((> a b) a)
    (t b)
  )
)


; getDepth(l1l2l3) = 
; = 0, if l1l2l3 is null
; = 1 + myMax(getDepth(l2), getDepth(l3)), otherwise


(defun getDepth(l)
  (cond
    ((null l) 0)
    (t (+ 1 (myMax (getDepth (cadr l)) (getDepth (caddr l)))))
  )
)


; isBalanced(l1l2l3) = 
; = true, if n = 0
; =  nil, if absoluteDiff(getDepth(l2), getDepth(l3)) > 1
; = isBalanced(l2) and isBalanced(l3), otherwise


(defun isBalanced(l)
  (cond
    ((null l) t)
    ((> (absoluteDiff (getDepth (cadr l)) (getDepth (caddr l))) 1) nil)
    (t (and (isBalanced (cadr l)) (isBalanced (caddr l))))
  )
)


; (isBalanced '(A)) -> yes
; (isBalanced '(A (B (C) ()) ())) -> no
; (isBalanced '(A (B (C) ()) (D))) -> yes
; (isBalanced '(A (B (C) ()) (D (E () (F () (G))) ()))) -> no