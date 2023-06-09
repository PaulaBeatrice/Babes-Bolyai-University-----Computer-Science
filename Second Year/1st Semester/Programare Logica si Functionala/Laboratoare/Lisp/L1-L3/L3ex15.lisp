; 14. Write a function that returns the number of atoms in a list, at any level.


; myCount(l)
; = 1, if l is an atom
; = myCount(l1) + myCount(l2) + ... + myCount(ln), where l is a list of the form l = l1l2...ln

(defun myCount (l)
  (cond
    ((atom l) 1)
    (t (apply '+ (mapcar #'myCount l)))
  )
)