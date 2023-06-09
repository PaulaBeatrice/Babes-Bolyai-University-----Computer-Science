;Definiti o functie care sorteaza cu pastrarea dublurilor o lista liniara.

; insertOk(l1l2...ln, elem) = 
; = list(elem), if n = 0
; = {elem} U l1l2...ln, if elem < l1 
; = {l1} U insertOk(l2...ln, elem) , otherwise


(defun insertOk(l elem)
  (cond
    ((null l) (list elem))
    ((< elem (car l)) (cons elem l))
    (t (cons (car l) (insertOk (cdr l) elem)))
  )
)

; mySort(l1l2...ln) = 
; = nil , if n = 0
; = mySort(insertOk(l2...ln, l1)) , otherwise


(defun mySort(l)
  (cond
    ((null l) nil)
    (t (insertOk (mySort (cdr l)) (car l)))
  )
)