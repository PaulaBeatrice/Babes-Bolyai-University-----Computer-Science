;Sa se scrie o functie care intoarce multimea tuturor sublistelor unei 
;liste date. Ex: Ptr. lista ((1 2 3) ((4 5) 6)) => ((1 2 3) (4 5) ((4 5) 
;6))

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm) , otherwise

(defun myAppend (l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)

; sublists(l1l2...ln) = 
; = nil, if n = 0
; = myAppend(list(l1), myAppend(sublists(l1), sublists(l2...ln))), if l1 is a list
; = sublists(l2...ln), otherwise

(defun sublists (l) 
    (cond
        ((null l) nil)
        ((listp (car l)) (myAppend (list (car l)) (myAppend (sublists (car l)) (sublists (cdr l)))))
        (t (sublists (cdr l)))
    )
)
