;Sa se scrie o functie care determina numarul de aparitii ale unui atom dat 
;intr-o lista neliniara.
; nrOfOccurrences(l1l2...ln, c, elem) = 
; = c , if n = 0
; = nrOfOccurences(l1, 0, elem) + nrOfOccurences(l2...ln, c, elem) , if l1 is a list
; = nrOfOccurences(l2...ln, c + 1, elem) , if l1 = elem
; = nrOfOccurences(l2...ln, c, elem) , otherwise


(defun nrOfOccurences(l c elem)
  (cond
    ((null l) c)
    ((listp (car l)) (+ (nrOfOccurences (car l) 0 elem) (nrOfOccurences (cdr l) c elem)))
    ((equal (car l) elem) (nrOfOccurences (cdr l) (+ 1 c) elem))
    (t (nrOfOccurences (cdr l) c elem))
  )
)


(defun mainD(l elem)
  (nrOfOccurences l 0 elem)
)