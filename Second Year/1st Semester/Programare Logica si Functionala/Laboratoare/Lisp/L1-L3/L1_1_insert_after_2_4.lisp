;1)Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea, al 6-lea,....element.

;insert(l1...ln, poz, a) = nil, n=0
;                        =  l1 U a U insert(l2..ln, poz +1,a), poz%2==0
;                        = l1 U insert(l2..ln, poz+1,a), altfel

(defun insertA(l a poz)
   (cond 
      ((null l) nil)
      ((equal (mod poz 2) 0) (cons (car l) (cons a (insertA (cdr l) a (+ 1 poz)))))
      (t (cons (car l) (insertA (cdr l) a (+ 1 poz))))
   )
)

;main(l1...ln, a) = insertA(l1...ln, 1, a)


(defun main(l a)
  (insertA l a 1)
)

