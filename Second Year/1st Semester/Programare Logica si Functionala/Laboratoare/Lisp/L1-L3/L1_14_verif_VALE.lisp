;a se scrie o functie care sa testeze daca o lista liniara formata din 
;numere intregi are aspect de "vale"(o secvență se spune ca are aspect de 
;"vale" daca elementle descresc pana la un moment dat, apoi cresc. De ex. 
;10 8 6 17 19 20).


; findLength(l1l2...ln) = 
; = 0 , if n = 0
; = 1 + findLength(l2...ln) , otherwise

(defun findLength(l)
  (cond
    ((null l) 0)
    (t (+ 1 (findLength (cdr l))))
  )
)


; valley(l1l2...ln, decreasing) = 
; = nil (false), if n = 1 and decreasing = true
; = true , if n = 1 and decreasing = false
; = nil (false) , if l1 > l2 and decreasing = false
; = valley(l2...ln, false), if l1 < l2 and decreasing = true
; = valley(l2...ln, decreasing), otherwise

(defun valley (l decreasing)
	(cond
		((= (findLength l) 1) (if decreasing nil T))
		((and (> (car l) (cadr l)) (not decreasing)) nil)
		((and (< (car l) (cadr l)) decreasing) (valley (cdr l) nil))
		(T (valley (cdr l) decreasing))
	)
)

(defun mainB(l)
  (cond
    ((null l) nil)
    ((null (cadr l)) nil)
    ((null (caddr l)) nil)
    ((< (car l) (cadr l)) nil)
    (t (valley l T))
  )
)
