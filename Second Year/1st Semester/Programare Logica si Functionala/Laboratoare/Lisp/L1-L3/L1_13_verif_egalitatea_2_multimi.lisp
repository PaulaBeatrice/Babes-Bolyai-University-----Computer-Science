;Sa se scrie o functie care testeaza egalitatea a doua multimi, fara sa se 
;faca apel la diferenta a doua multimi.


; removeFirstOcc(l1l2...ln, elem) = 
; = nil, if n = 0
; = l2...ln, if elem = l1
; = {l1] U removeFirstOcc(l2...ln, elem), otherwise


(defun removeFirstOcc(l elem)
  (cond
    ((null l) nil)
    ((equal elem (car l)) (cdr l))
    (t (cons (car l) (removeFirstOcc (cdr l) elem)))
  )
)

; equalitySets(l1l2...ln, p1p2...pm) = 
; = true, if n = 0 and m = 0
; = false, if n = 0 or m = 0
; = equalitySets(l2...ln, removeFirstOcc(p1p2...pm, l1)), otherwise

(defun equalitySets(l p)
  (cond
    ((and (null l) (null p)) T)
    ((or (null l) (null p)) nil)
    (t (equalitySets (cdr l) (removeFirstOcc p (car l))))
  )
)