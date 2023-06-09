;9.  Definiti o functie care substituie un element Eprin elementeleunei liste L1la toate nivelurile unei liste date L.

; mySubstitute(l, elem, p) =
; = l , if l != elem and l is an atom
; = p , if l = elem and l is an atom
; = mySubstitute(l1, elem, p) U mySubstitute(l2, elem, p) U ... U mySubstitute(ln, elem, p), otherwise (if l is a list of the
; form l = l1l2...ln)


(defun mySubstitute(l elem p)
  (cond
    ((and (not (equal l elem)) (atom l))  l)
    ((and (equal l elem) (atom l)) p)
    (t (mapcar #' (lambda (l) (mySubstitute l elem p)) l))
  )
)