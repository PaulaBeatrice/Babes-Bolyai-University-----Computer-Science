;13.  Definiti o functie care substituie un element prin altul latoate nivelurile unei liste date.

; substitute(l, elem, newElem) = 
; = {l}, if l != elem and l is an atom
; = {newElem}, if l = elem and l is an atom
; = substitute(l1) U substitute(l2) U ... U substitute(ln), where i = 1,n

(defun mySubstitute(l elem newElem)
  (cond
    ((and (atom l) (not (equal l elem))) l)
    ((and (atom l) (equal l elem)) newElem)
    (t (mapcar #' (lambda (a) (mySubstitute a elem newElem)) l))
  )
) 