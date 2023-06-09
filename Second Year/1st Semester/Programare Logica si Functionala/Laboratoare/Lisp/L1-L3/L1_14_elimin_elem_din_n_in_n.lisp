; Dandu-se o lista liniare, se cere sa se elimine elementele din N in N.

; ----Here is solved the problem with the wrong understanding!!!
; removeFromNtoN(l1l2...ln, N, C1, C2) = 
; = nil , if n = 0
; = {l1} U removeFromNtoN(l2...ln, N, C1 + 1, C2), if C1 != N or C1 = C2 + 1= N
; = removeFromNtoN(l2...ln, N, C1, C2 + 1), if C2 != N + 1 and C1 = N

;(defun removeFromNtoN(l n c1 c2)
;  (cond
;    ((null l) nil)
;    ((or (not (equal c1 n)) (and (equal c1 (+ 1 c2)) (equal (+ 1 c2) n))) (cons (car l) (removeFromNtoN (cdr l) n (+ c1 1) c2)))
;    ((and (not (equal c2 (+ 1 n))) (equal c1 n)) (removeFromNtoN (cdr l) n c1 (+ c2 1)))
;  )
;)

;(defun mainA(l n)
;  (removeFromNtoN l n 0 0)
;)



; removeFromNtoN(l1l2...ln, n, k) = 
; = nil, if n = 0 
; = removeFromNtoN(l2...ln, n, n), if k = 1
; = {l1} U removeFromNtoN(l2...ln, n, k - 1) , otherwise

(defun removeFromNtoN(l n k)
  (cond 
    ((null l) nil)
    ((equal k 1) (removeFromNtoN (cdr l) n n))
    (t (cons (car l) (removeFromNtoN (cdr l) n (- k 1))))
  )
)

(defun mainA(l n)
  (removeFromNtoN l n n)
)