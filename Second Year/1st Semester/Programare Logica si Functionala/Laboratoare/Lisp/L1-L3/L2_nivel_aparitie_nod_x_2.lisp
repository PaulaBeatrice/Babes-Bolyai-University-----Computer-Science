;10. Se da un arbore de tipul (2). Sa se precizeze nivelul pe care apare un nod
x in arbore. Nivelul radacii se considera a fi 0. 

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

; inorder(l1l2l3) = 
; = nil, if n = 0 
; = myAppend(inorder(l2), myAppend(list(l1), inorder(l3))), otherwise


; findLevel(l1l2l3, elem, counter) = 
; = 0, if l1l2l3 is empty
; = counter, if elem = l1
; = findLevel(l2) + findLevel(l3), otherwise


(defun findLevel(l elem counter)
  (cond
    ((null l) 0)
    ((equal elem (car l)) counter)
    (t (+ (findLevel (cadr l) elem (+ 1 counter)) (findLevel (caddr l) elem (+ 1 counter))))
  )
)


; nodesFromLevel(l1l2l3, level, counter)
; = nil, if l1l2l3 is empty
; = l1 , if counter = level
; = myAppend((list (nodesFromLevel(l2, level, counter + 1))) (list (nodesFromLevel(l3, level, counter + 1)))), otherwise

(defun nodesFromLevel(l level counter)
  (cond
    ((null l) nil)
    ((equal counter level) (list (car l)))
    (t (myAppend (nodesFromLevel (cadr l) level (+ 1 counter)) (nodesFromLevel (caddr l) level (+ 1 counter))))
  )
)


; checkExistence(l1l2...ln, elem) = 
; = true, if elem = l1
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



(defun main(l elem)
  (cond
    ((checkExistence l elem) (nodesFromLevel l (findLevel l elem 0) 0))
    (t nil)
  ) 
)