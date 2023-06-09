;Sa se elimine elementul de pe pozitia a n-a a unei liste liniare.

; eliminateNthElem(l1l2...ln, n, pos) =
; = nil , if n = 0
; = eliminateNthElem(l2...ln, n, pos + 1), if  n = pos
; = {l1} U eliminateNthElem(l2...ln, n, pos + 1), otherwise


(defun eliminateNthElem(l n pos)
  (cond
    ((null l) nil)
    ((equal n pos) (eliminateNthElem (cdr l) n (+ 1 pos)))
    (t (cons (car l) (eliminateNthElem (cdr l) n (+ 1 pos))))
  )
)


(defun mainA(l n)
  (eliminateNthElem l n 0)
)