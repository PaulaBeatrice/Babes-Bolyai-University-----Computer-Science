;Definiti o functie care construiește o listă cu pozițiile elementului 
;minim dintr-o listă liniară numerică

; myMin(a, b) = 
; = nil , if a is not a number and b is not a number
; = a , if b is not a number
; = b , if a is not a number
; = a , if a < b
; = b , otherwise

(defun myMin(a b)
  (cond
    ((and (not (numberp a)) (not (numberp b))) nil)
    ((not (numberp a)) b)
    ((not (numberp b)) a)
    ((< a b) a)
    (t b)
  )
)


; minList(l1l2...ln) = 
; = l1 , is n = 1 and l1 is an atom
; = myMin(minList(l1), minList(l2...ln)), if l1 is a list; = myMin(l1, minList(l2...ln)), otherwise


(defun minList(l)
  (cond 
    ((and (null (cdr l)) (atom (car l))) (car l))
    ((listp (car l)) (myMin (minList (car l)) (minList (cdr l))))
    (t (myMin (car l) (minList (cdr l))))
  )
)


; minPos(l1l2...ln, min, pos) = 
; = nil, if n = 0
; = pos U minPos(l2...ln, min, pos + 1) , if l1 = min
; = minPos(l2...ln, min, pos + 1), otherwise


(defun minPos (l min pos)
  (cond
    ((null l) nil)
    ((equal (car l) min) (cons pos (minPos (cdr l) min (+ 1 pos))))
    (t (minPos (cdr l) min (+ 1 pos)))
  )
)

(defun mainD (l)
  (minPos l (minList l) 0)
)
