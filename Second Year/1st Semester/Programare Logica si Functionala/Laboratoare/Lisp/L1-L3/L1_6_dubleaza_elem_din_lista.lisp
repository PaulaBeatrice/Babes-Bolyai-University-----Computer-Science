;Sa se scrie de doua ori elementul de pe pozitia a n-a a unei liste 
;liniare. De exemplu, pentru (10 20 30 40 50) si n=3 se va produce (10 20 
;30 30 40 50).

; twiceNthElem(l1l2...lm, n, pos) = 
; = nil , if m = 0
; = {l1} U {l1} U twiceNthElem(l2...lm, n, pos + 1) , if n = pos
; = {l1} U twiceNthElem(l2...lm, n, pos + 1) , otherwise


(defun twiceNthElem(l n pos)
  (cond
    ((null l) nil)
    ((equal n pos) (cons (car l) (cons (car l) (twiceNthElem (cdr l) n (+ 1 pos)))))
    (t (cons (car l) (twiceNthElem (cdr l) n (+ 1 pos))))
  )
)

(defun mainA(l n)
  (twiceNthElem l n 0)
)
