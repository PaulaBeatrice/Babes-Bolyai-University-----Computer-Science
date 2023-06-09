; Sa se construiasca o functie care intoarce adancimea unei liste.
; myMax(a, b) = 
; = a , if a > b
; = b ,  otherwise


(defun myMax (a b)
  (cond
    ((> a b) a)
    (t b)
  )
)


; findDepth(l1l2...ln, c) = 
; = c , if n = 0
; = myMax(findDepth(l1,c+1), findDepth(l2...ln, c)) , if l1 is a list
; = findDepth(l2...ln, c) , otherwise


(defun findDepth (l c)
  (cond
    ((null l) c)
    ((listp (car l)) (myMax (findDepth (car l) (+ c 1)) (findDepth (cdr l) c)))
    (t (findDepth (cdr l) c))
  )
)

; main(l1l2...ln) = 
; = findDepth(l1l2...ln, 1)

(defun main(l)
  (cond
    (t (findDepth l 1))
  )
)