;4. Sa se converteasca un arbore de tipul (2) la un arbore de tipul (1).

; (A (B) (C (D) (E (F)))) -> (A 2 B 0 C 2 D 0 E 1 F 0) 


; convert_tree(l1l2l3) = 
; = nil, if l1l2l3 is null
; = myAppend(list(l1), myAppend(list(2), myAppend(convert_tree(l2), convert_tree(l3)))), if l2 != null, and l3 != null
; = myAppend(list(l1), myAppend(list(1), convert_tree(l2))), if l2 != null
; = myAppend(list(l1), myAppend(list(1), convert_tree(l3))), if l3 != null
; = myAppend(list(l1), list(0)), otherwise

(defun convert_tree(l)
    (cond
         ((null l) nil)
         ((and (not (null (cadr l))) (not (null (caddr l)))) (append (list (car l)) '(2) (convert_tree(cadr l))
                                                                     (convert_tree(caddr l))))
         ((not (null (cadr l))) (append (list (car l)) '(1) (convert_tree(cadr l)) ))
         ((not (null (caddr l))) (append (list (car l)) '(1) (convert_tree(caddr l)) ))
         (T (append (list (car l)) '(0)))
    )
)