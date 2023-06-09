;Sa se scrie o functie care intoarce diferenta a doua multimi.

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm) , otherwise

(defun myAppend (l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)

; checkExistence(l1l2...ln, elem) = 
; = false (nil) , if n = 0
; = true , if l1 = elem
; = checkExistence(l2...ln, elem) , otherwise

(defun checkExistence(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) t)
    (t (checkExistence (cdr l) elem))
  )
)

; diffSets(l1l2...ln, p1p2...pm, r) = 
; = p1p2...pm, if n = 0
; = diffSets(l2...ln, p1p2...pm, r) , if checkExistence(p1p2...pm, l1)
; = diffSets(l2...ln, p1p2...pm, r U {l1}) , otherwise


(defun diffSets(l p r)
  (cond
    ((null l) r)
    ((checkExistence p (car l)) (diffSets (cdr l) p r))
    (t (diffSets (cdr l) p (myAppend r (list (car l)))))
  )
)


(defun mainA(l p)
  (myAppend (diffSets l p (list )) (diffSets p l (list )))
)