;1.   Sa se construiasca o functie care intoarce adancimea unei liste.

; myMax(a, b)
; = nil, if a is not a number and b is not a number
; = a, if b is not a number
; = b, if a is not a number
; = a, if a > b
; = b, otherwise

(defun myMax(a b)
  (cond
    ((and (not (numberp a)) (not (numberp b))) nil)
    ((not (numberp b))a)
    ((not (numberp a)) b)
    ((> a b) a)
    (t b)
  )
)

; findMax(l1l2...ln)
; = nil, if n = 0
; = myMax(l1, findMax(l2...ln)), otherwise

(defun findMax(l)
  (cond
    ((null l) nil)
    (t (myMax (car l) (findMax (cdr l))))
  )
)


; findDepth(l1l2...ln, counter) = 
; = counter, if l is an atom
; = max(findDepth(l1, counter), findDepth(l2, counter), ..., findDepth(ln, counter)), otherwise


(defun findDepth(l counter)
  (cond
    ((atom l) counter)
    (t (apply #'findMax (list (mapcar #' (lambda (a) (findDepth a (+ 1 counter))) l))))
  )
)


(defun main(l)
  (findDepth l -1)
)