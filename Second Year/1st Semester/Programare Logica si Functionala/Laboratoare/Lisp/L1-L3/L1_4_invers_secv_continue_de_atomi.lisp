;Sa se scrie o functie care plecand de la o lista data ca argument, 
;inverseaza numai secventele continue de atomi. Exemplu:
;(a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))

;append(l1...ln, p1...pm) = p1...pm, n=0
;                         =l1 U append(l2...ln, p1...pm), otherwise

(defun appendA(l p)
   (cond
      ((null l) p)
      (t (cons (car l) (appendA (cdr l) p)))
   )
)


; invertCont (l1l2...ln, aux) = 
; = aux , if n = 0
; = myAppend(aux, myAppend(list(invertCont(l1, NIL)), invertCont(l2...ln, NIL))) , if l1 is a list
; = invertCont(l2...ln, myAppend(list(l1), aux)) , otherwise


(defun invertCont (l aux)
  (cond
    ((null l) aux)
    ((listp (car l)) (myAppend aux (myAppend (list (invertCont (car l) nil)) (invertCont (cdr l) nil))))
    (t (invertCont (cdr l) (myAppend (list (car l)) aux)))
  )
)
