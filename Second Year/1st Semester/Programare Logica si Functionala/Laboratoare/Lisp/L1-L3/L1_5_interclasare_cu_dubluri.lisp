;Definiti o functie care interclaseaza cu pastrarea dublurilor doua liste 
;liniare sortate.


;mymerge(l1l2...ln,k1k2...km)=
;{ k1k2...km, if n = 0
;{ l1l2...ln, if m = 0
;{ l1 U mymerge(l2...ln, k1k2...km) , if l1 <= k1
;{ k1 U mymerge(l1l2...ln, k2...km) , if k1 < l1 (otherwise case)

(defun mymerge(l k)
  (cond
    ((null l) k)
    ((null k) l)
    ((<=(car l)(car k)) (cons (car l) (mymerge (cdr l) k) ))
    (t (cons (car k) (mymerge l (cdr k))))
  )
)