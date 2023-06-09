;12.  Definiti o functie care inlocuieste un nod cu altul intr-un arbore n-ar reprezentat sub forma (radacina lista_noduri_subarb1...lista_noduri_subarbn) Ex: arborelele este (a (b (c)) (d) (e (f)))  si nodul 'b se inlocuieste cu nodul 'g => arborele (a (g (c)) (d) (e (f)))

; myReplace(tree, elem, newElem)
; = newElem, if tree = elem and tree is an atom
; = tree, if tree is an atom and tree != elem
; = myReplace(tree1, elem, newElem) U myReplace(tree2, elem, newElem) U ... U myReplace(treen, elem, newElem), otherwise


(defun myReplace(tree elem newElem)
  (cond
    ((and (atom tree) (equal tree elem)) newElem)
    ((atom tree) tree)
    (t (mapcar #' (lambda (a) (myReplace a elem newElem)) tree))
  )
)