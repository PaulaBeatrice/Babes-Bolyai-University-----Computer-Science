;Sa se determine cel mai mic multiplu comun al valorilor numerice dintr-o lista neliniara.

; myGCD(a, b) = 
; = a , if b is not a number
; = b , if a is not a number
; = a , if b = 0
; = myGCD(b, a%b) , otherwise

(defun myGCD(a b)
  (cond
    ((not (numberp b)) a)
    ((not (numberp a)) b)
    ((equal 0 b) a)
    (t (myGCD b (mod a b)))
  )
)

; myLCM(a, b) = 
; = a , if b is not a number
; = b , if a is not a number
; = a * b / myGCD(a, b) , otherwise


(defun myLCM(a b)
  (cond
    ((not (numberp b)) a)
    ((not (numberp a)) b)
    (t (/ (* a b) (myGCD a b)))
  )
)


; lookForNumbers(l1l2...ln) = 
; nil , if n = 0
; lookForNumbers(l1) or lookForNumbers(l2...ln) , if l1 is a list
; true , if l1 is a number
; lookForNumbers(l2...ln) , otherwise


(defun lookForNumbers (l)
  (cond
    ((null l) nil)
    ((listp (car l)) (or (lookForNumbers (car l)) (lookForNumbers (cdr l))))
    ((numberp (car l)) t)
    (t (lookForNumbers (cdr l)))
  )
)

; lcmForList(l1l2...ln) = 
; = l1 , if n = 1
; = myLCM(lcmForList(l1) , lcmForList(l2...ln)) , if l1 is a list
; = myLCM(l1, lcmForList(l2...ln) , otherwise  


(defun lcmForList(l)
  (cond
    ((null (cdr l)) (car l))
    ((listp (car l)) (myLCM (lcmForList (car l)) (lcmForList (cdr l))))
    (t (myLCM (car l) (lcmForList (cdr l))))
  )
)

; First we check if we have any numbers in the list, if we don't have any numbers , we return 1, otherwise we go
; look for the least common multiple in the entire list on each level
(defun mainA(l)
  (cond
    ((lookForNumbers l) (lcmForList l))
    (t 1)
  )
)

