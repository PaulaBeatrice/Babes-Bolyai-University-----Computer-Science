;Definiti o functie care inverseaza o lista impreuna cu toate sublistele 
;sale de pe orice nivel.

;append(l1...ln, p1...pm) = p1...pm, n=0
;                         =l1 U append(l2...ln, p1...pm), otherwise

(defun appendA(l p)
   (cond
      ((null l) p)
      (t (cons (car l) (appendA (cdr l) p)))
   )
)


; myReverse(l1l2...ln) = 
; = nil , if n = 0
; = myAppend(myReverse(l2...ln), list(myReverse(l1))), if l1 is a list
; = myAppend(myReverse(l2...ln), list(l1)), otherwise


(defun myReverse(l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (myReverse (cdr l)) (list (myReverse (car l)))))
    (t (myAppend (myReverse (cdr l)) (list (car l))))
  )
)