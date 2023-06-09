;3.   Sa se construiasca o functie care verifica daca un atom e membru alunei liste nu neaparat liniara.

; checkExistence(l elem) = 
; = true, if l = elem and l is an atom
; = false, if l != elem and l is an atom
; = checkExistence(l1 elem) or checkExistence(l2 elem) or ... or checkExistence(ln elem) , otherwise


(defun checkExistence(l elem)
  (cond
    ((and (atom l) (equal l elem)) t)
    ((atom l) nil) 
    (t (some #'identity (mapcar #'(lambda (a) (checkExistence a elem)) l)))
  )
)