;5.  Definiti o functie care testeaza apartenenta unui nod intr-un arbore n-ar ;reprezentat sub forma (radacina lista_noduri_subarb1...lista_noduri__subarbn) Ex: ;arborelele este (a (b (c)) (d) (e (f))) si nodul este 'b => adevarat

;    Eg. tree is (a (b (c)) (d) (E (f))) and the node is "b" => true



; checkExistence(l, elem)
; = true, if l = elem
; = false, if l != elem
; = checkExistence(l1, elem) or checkExistence(l2,elem) or ... or checkExistence(ln,elem), otherwise (if l is a list of the form:
; l = l1l2...ln)


(defun checkExistence(l elem)
  (cond
    ((and (atom l) (equal l elem)) t)
    ((atom l) nil)
    (t (some #'identity (mapcar #'(lambda (a) (checkExistence a elem)) l)))
  )
)