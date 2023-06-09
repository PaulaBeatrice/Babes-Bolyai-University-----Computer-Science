;Sa se determine numarul tuturor sublistelor unei liste date, pe orice 
;nivel. Prin sublista se intelege fie lista insasi, fie un element de pe 
;orice nivel, care este lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) => 
;5 (lista insasi, (3 ...), (4 5), (6 7), (9 10)).

; countLists(l1l2...ln) =
; = 0 , if n = 0
; = 1 + countLists(l1) + countLists(l2...ln) , if l1 is a list
; = countLists(l2...ln) , otherwise

(defun countLists(l)
  (cond
    ((null l) 1)
    ((listp (car l)) (+ (countLists (car l)) (countLists (cdr l))))
    (t (countLists (cdr l)))
  )
)