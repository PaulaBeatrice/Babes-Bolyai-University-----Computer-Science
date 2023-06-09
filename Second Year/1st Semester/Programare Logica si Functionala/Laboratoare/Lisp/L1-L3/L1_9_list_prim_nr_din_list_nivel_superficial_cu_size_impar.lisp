;Dandu-se o lista, sa se construiasca lista primelor elemente ale tuturor 
;elementelor lista ce au un numar impar de elemente la nivel superficial.
;Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10 11)) => (1 3 9).

; oddNrOfElems(l1l2...ln, c) =
; = true , if n = 0 and c % 2 = 1
; = false (nil) , if n = 0 and c % 2 = 0
; = oddNrOfElems(l2...ln, c + 1) , otherwise

(defun oddNrOfElems(l c)
  (cond
    ((and (null l) (equal 1 (mod c 2))) t)
    ((and (null l) (equal 0 (mod c 2))) nil)
    (t (oddNrOfElems (cdr l) (+ 1 c)))
  )
)

(defun checkOdd(l)
  (oddNrOfElems l 0)
)

; firstElem(l1l2...ln, f) = 
; = nil , if n = 0
; = myAppend(firstElem(l1, 0), firstElem(l2...ln, f)) , if l1 is a list
; = {l1} U firstElem(l2...ln, 1) , if checkOdd(l1l2...ln) is true and f = 0
; = firstElem(l2...ln, 1) , otherwise

(defun firstElem(l f)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (firstElem (car l) 0) (firstElem (cdr l) f)))
    ((and (checkOdd l) (= f 0)) (cons (car l) (firstElem (cdr l) 1)))
    (t (firstElem(cdr l) 1))
  )
)

(defun mainC(l)
  (firstElem l 0)
)