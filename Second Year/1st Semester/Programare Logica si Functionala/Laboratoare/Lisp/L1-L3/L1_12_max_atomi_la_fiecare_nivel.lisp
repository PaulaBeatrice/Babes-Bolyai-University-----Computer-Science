;Sa se construiasca o functie care intoarce maximul atomilor numerici 
;dintr-o lista, de la orice nivel.


; lookForNumbers(l1l2...ln, c) = 
; = 0 , if n = 0 and c = 0
; = 1 , if n = 0 and c > 0
; = lookForNumbers(l2...ln, c + 1) , if l1 is a number
; = lookForNumbers(l1, c) + lookForNumbers(l2...ln, c), if l1 is a list
; = lookForNumbers(l2...ln, c) , otherwise


(defun lookForNumbers(l c)
  (cond
    ((and (null l)(= c 0)) 0)
    ((and (null l)(> c 0)) 1)
    ((numberp (car l) ) (lookForNumbers (cdr l) (+ 1 c)))
    ((listp (car l)) (+ (lookForNumbers (car l) c) (lookForNumbers (cdr l) c)))
    (t (lookForNumbers (cdr l) c))
  )
)


; myMax(a, b) = 
; = () , if a is not a number and b is not a number
; = b , if a is not a number
; = a , if b is not a number
; = a , if a > b
; = b , otherwise

(defun myMax(a b)
  (cond
    ((and (not (numberp a)) (not (numberp b))) nil)
    ((not (numberp a)) b)
    ((not (numberp b)) a)
    ((> a b) a)
    (t b)
  )
)  

; maxList(l1l2...ln) = 
; = l1 , if n = 1 and l1 is an atom
; = myMax(maxList(l1), maxList(l2...ln)) , if l1 is a list
; = myMax(l1, maxList(l2...ln) , otherwise

(defun maxList(l)
  (cond
    ((and (null (cdr l)) (atom (car l))) (car l))
    ((listp (car l)) (myMax (maxList (car l)) (maxList(cdr l))))
    (t (myMax (car l) (maxList (cdr l))))
  )
)


; main(l1l2...ln) = 
; 0 , if lookForNumbers = 0
; maxList(l1l2...ln) , otherwise

(defun main(l)
  (cond
    ((= (lookForNumbers l 0) 0) 0)
    (t (maxList l))
  )
) 