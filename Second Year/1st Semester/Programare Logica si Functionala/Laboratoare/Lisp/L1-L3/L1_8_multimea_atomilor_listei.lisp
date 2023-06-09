;Sa se construiasca multimea atomilor unei liste.Exemplu: (1 (2 (1 3 (2 4) 
;3) 1) (1 4)) ==> (1 2 3 4)

; linearizeList(l1l2...ln) = 
; = nil, if n = 0
; = myAppend(linearizeList(l1), (linearizeList(l2...ln))), if l1 is a list
; = {l1} U linearizeList(l2...ln) , if l1 is a number
; = linearizeList(l2...ln) , otherwise


(defun linearizeList(l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (linearizeList (car l)) (linearizeList (cdr l))))
    ((atom (car l)) (cons (car l) (linearizeList (cdr l))))
    (t (linearizeList (cdr l)))
  )
)

; checkExistence(l1l2...ln, elem) = 
; = nil (false), if n = 0
; = true , if l1 = elem
; = checkExistence(l2...ln, elem) , otherwise

(defun checkExistence(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) t)
    (t (checkExistence (cdr l) elem))
  )
)

; toSet(l1l2...ln, r) = 
; = r, if n = 0
; = toSet(l2...ln, r) , if checkExistence(r, l1) = true
; = toSet(l2...ln, {l1} U r) , otherwise

(defun toSet(l r)
  (cond
    ((null l) r)
    ((checkExistence r (car l)) (toSet (cdr l) r))
    (t (toSet (cdr l) (myAppend r (list (car l)))))
  )
)

(defun mainC (l)
  (toSet (linearizeList l) (list))
)