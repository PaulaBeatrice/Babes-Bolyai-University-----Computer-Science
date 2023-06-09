;4.   Sa se construiascao functie care intoarce suma atomilor numerici dintr-o lista, de la orice nivel.

; mySum(l) = 
; = l , if l is a number
; = 0, if l is an atom
; = mySum(l1) + mySum(l2) + ... + mySum(ln), otherwise (if l is a list of the form l1l2...ln)

(defun mySum(l)
  (cond
    ((numberp l) l)
    ((atom l) 0)
    (t (apply '+ (mapcar #'mySum l)))
  )
)