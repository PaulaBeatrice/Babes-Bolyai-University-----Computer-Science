;Definiti o functie care substituie un element E prin elementele unei liste 
;L1 la toate nivelurile unei liste date L.
;myAppend(l1l2...ln,k1k2...km) = 
; k1k2...km, if n = 0
; l1 U myAppend(l2...ln, k1k2...km), otherwise

(defun myAppend (L1 L2)
  (cond
    ((null L1) L2)
    (T (cons (car L1) (myAppend (cdr L1) L2)))
  )
)


;replaceList (E, R, l1l2..ln) = {
; (), if n = 0
; {replace(E, R, l1)} U replace(E, R, l2..ln), if l1 is a list
; R U replace(E, R, l2..ln), if E = l1
; {l1} U replace(E, R, l2..ln), otherwise

(defun replaceList (E R L)
    (cond
        ((null L) ())
        ((listp (car L)) (cons (replaceList E R (car L)) (replaceList E R (cdr L))))
        ((equal E (car L)) (myAppend R (replaceList E R (cdr L))))
        (t (cons (car L) (replaceList E R (cdr L))))
    )
)


; (replaceList 3 (list ) (list 3 (list 3 4) (list 3) (list 1 3 4 (list 5 3 3) (list 4 5 6))))
; (replaceList 1 (list 5 6 7) (list 1 (list 2 2 (list 1 (list 5 1))) (list 1 3 4 5)))