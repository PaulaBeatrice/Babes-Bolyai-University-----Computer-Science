;Sa se intercaleze un element pe pozitia a n-a a unei liste liniare.

; insertAtPos(l1l2...ln, pos, elem) = 
; = nil, if n = 0
; = {elem} U l1l2...ln, if pos = 0
; = {l1} U insertAtPos(l2...ln, pos - 1, elem) , otherwise

(defun insertAtPos(l pos elem)
  (cond
    ((null l) nil)
    ((equal pos 0 ) (cons elem l))
    (t (cons (car l) (insertAtPos (cdr l) (- pos 1) elem)))
  )
)