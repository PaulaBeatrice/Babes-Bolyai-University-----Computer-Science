;7.  Sa se scrie o functie care calculeaza suma numerelor pare minus sumanumerelor impare la toate nivelurile unei liste.

; findSign(a) = 
; = a, if a % 2 = 0
; = -a


(defun findSign(a)
  (cond
    ((equal 0 (mod a 2)) a)
    (t (- a))
  )
)

; totalSum(l)
; = findSign(l), if l is a number
; = 0, if l is an atom
; = totalSum(l1) + totalSum(l2) + ... + totalSum(ln), where l is a list of type l = l1l2...ln

(defun totalSum(l)
  (cond
    ((numberp l) (findSign l))
    ((atom l) 0)
    (t (apply '+ (mapcar #'totalSum l)))
  )
)