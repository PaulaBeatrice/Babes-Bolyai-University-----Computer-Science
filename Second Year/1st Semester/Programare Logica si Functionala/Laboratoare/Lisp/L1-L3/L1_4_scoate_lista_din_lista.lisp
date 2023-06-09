;Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;care apar, pe orice nivel, dar in aceeasi ordine. De exemplu:
;(((A B) C) (D E)) --> (A B C D E)

;append(l1...ln, p1...pm) = p1...pm, n=0
;                         =l1 U append(l2...ln, p1...pm), otherwise

(defun appendA(l p)
   (cond
      ((null l) p)
      (t (cons (car l) (appendA (cdr l) p)))
   )
)

; getAllAtoms(l1l2...ln) = 
; = NIL , if n = 0
; = myAppend(getAllAtoms(l1), getAllAtoms(l2...ln)) , if l1 is a list
; = myAppend((list(l1), getAllAtoms(l2...ln)) , otherwise

(defun getAllAtoms (l)
  (cond
    ((null l) nil)
    ((listp (car l)) (myAppend (getAllAtoms(car l)) (getAllAtoms(cdr l))))
    (t (myAppend (list (car l)) (getAllAtoms(cdr l))))
  )
)