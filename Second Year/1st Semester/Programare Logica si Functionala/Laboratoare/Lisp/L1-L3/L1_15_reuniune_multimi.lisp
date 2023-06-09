;Sa se scrie o functie care intoarce reuniunea a doua multimi.

; removeFirstOcc(l1l2...ln, elem) =
; = nil, if n = 0
; = l2...ln, if l1 = elem
; = {l1} U removeFirstOcc(l2...ln, elem) , otherwise

(defun removeFirstOcc(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) (cdr l))
    (t (cons (car l) (removeFirstOcc (cdr l) elem)))
  )
)

; unionSets(l1l2...ln, p1p2...pm) = 
; = nil if n = 0 and m = 0
; = l1l2...ln, if m = 0
; = p1p2...pm, if n = 0
; = {l1} U unionSets(l2...ln, removeFirstOcc(p1p2...pm, l1)) , otherwise

(defun unionSets(l1 l2)
  (cond
    ((and (null l1) (null l2)) nil)
    ((null l1) l2)
    ((null l2) l1)
    (t (cons (car l1) (unionSets (cdr l1) (removeFirstOcc l2 (car l1)))))
  )
)