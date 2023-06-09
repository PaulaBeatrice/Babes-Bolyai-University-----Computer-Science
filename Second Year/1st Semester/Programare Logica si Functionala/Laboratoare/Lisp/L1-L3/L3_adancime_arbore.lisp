;14.  Definiti o functie care da adancimea unui arbore n-ar reprezentat sub forma (radacinalista_noduri_subarb1...lista_noduri_subarbn) Ex: adancimea arborelui este(a (b (c)) (d) (e (f))) este 3

; myMax(a, b) = 
; = nil, if a is not a number and b is not a number
; = a, if b is not a number
; = b, if a is not a number
; = a, if a > b
; = b, otherwise

(defun myMax(a b)
  (cond
    ((and (not (numberp a)) (not (numberp b))) nil)
    ((not (numberp b)) a)
    ((not (numberp a)) b)
    ((> a b) a)
    (t b)
  )
)


; findMax(l1l2...ln) = 
; = nil, if n = 0
; = myMax(l1, findMax(l2...ln)), otherwise

(defun findMax(l)
  (cond
    ((null l) nil)
    (t (myMax (car l) (findMax (cdr l))))
  )
)


; findDepthTree(tree, counter)
; = counter, if tree is an atom
; = findMax(findDepthTree(tree1, counter + 1), findDepth(tree2, counter + 1), ... , findDepth(treen, counter + 1)), otherwise

(defun findDepthTree(tree counter)
  (cond
    ((atom tree) counter)
    (t (apply #'findMax (list (mapcar #' (lambda (a) (findDepthTree a (+ 1 counter))) tree))))
  )
)

(defun main(l)
  (findDepthTree l 0)
)