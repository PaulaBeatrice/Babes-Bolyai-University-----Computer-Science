;Sa se inlocuiasca fiecare sublista a unei liste cu ultimul ei element.
;Prin sublista se intelege element de pe primul nivel, care este lista. 
;Exemplu: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c f)
;(a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm), otherwise

(defun myAppend(l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)

; myReverse(l1l2...ln) = 
; = nil , if n = 0
; = myAppend(myReverse(l2...ln), list(myReverse(l1))) , if l1 is a list
; = myAppend(myReverse(l2...ln), list(l1)) , otherwise

(defun myReverse (l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (myReverse (cdr l)) (list (myReverse (car l)))))
    (t (myAppend (myReverse (cdr l)) (list (car l))))
  )
)

;-- the function above for reverse works for non-linear lists as well


; my_reverse(l1l2...ln) = 
; = nil , if n = 0
; = myAppend(my_reverse(l2...ln) , list(l1)) , otherwise


(defun my_reverse(l)
  (cond
    ((null l) nil)
    (t (myAppend (my_reverse (cdr l)) (list (car l))))
  )
)

(defun last_element (l)
	(if (listp l) 
        (last_element (car (my_reverse l)))
        l
    )
)

; myReduce(l1l2...ln) = 
; = nil , if n = 0
; = last_element(l1) U myReduce(l2...ln), if l1 is a list
; = {l1} U myReduce(l2...ln) , otherwise


(defun myReduce (l)
  (cond
    ((null l) nil)
    ((listp l) (cons (last_element (car l)) (myReduce (cdr l))))
    (t (cons (car l) (myReduce (cdr l))))
  )
)