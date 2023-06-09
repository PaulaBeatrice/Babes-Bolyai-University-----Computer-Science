;Sa se elimine toate aparitiile elementului numeric maxim dintr-o lista 
;neliniara.

; myMax(a, b) = 
; = a , if b is not a number
; = b , if a is not a number
; = a , if a > b
; = b , otherwise

(defun myMax(a b)
  (cond
    ((not (numberp b)) a)
    ((not (numberp a)) b)
    (( > a b) a)
    (t b)
  )
)

; maxForList(l1l2...ln) = 
; = nil , if n = 0
; = myMax(maxForList(l1), maxForList(l2...ln)) , if l1 is a list
; = myMax(l1, maxForList(l2...ln)) , otherwise


(defun maxForList(l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myMax (maxForList (car l)) (maxForList (cdr l))))
    (t (myMax (car l) (maxForList (cdr l))))
  )
)


; myAppend (l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm) , otherwise

(defun myAppend (l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)


; removeElem(l1l2...ln, elem) = 
; = nil , if n = 0
; = removeElem(l1, elem) U removeElem(l2...ln, elem) , if l1 is a list
; = removeElem(l2...ln, elem) , if l1 = elem
; = {l1} U removeElem(l2...ln, elem) , otherwise

(defun removeElem(l elem)
  (cond
    ((null l) nil)
    ((listp (car l)) (cons (removeElem (car l) elem) (removeElem (cdr l) elem)))
    ((equal (car l) elem) (removeElem (cdr l) elem))
    (t (cons (car l) (removeElem (cdr l) elem)))
  )
)


(defun mainC (l)
  (removeElem l (maxForList l))
)