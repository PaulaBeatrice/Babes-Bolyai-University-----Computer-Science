;Definiti o functie care substituie prima aparitie a unui element intr-o 
lista data.

; replaceFirstOcc(l1l2...ln, elem, newElem) = 
; = nil , if n = 0
; = {newElem} U (l2...ln), if elem = l1
; = {l1} U replaceFirstOcc(l2...ln), otherwise

(defun replaceFirstOcc(l elem newElem)
  (cond
    ((null l) nil)
    ((equal elem (car l)) (cons newElem (cdr l)))
    (t (cons (car l) (replaceFirstOcc (cdr l) elem newElem)))
  )
)

;--- the function from above works only for a linear list


; searchElement(l1l2...ln, elem) = 
; = nil (false) , if n = 0
; = true , if l1 = elem
; = searchElement(l2...ln), otherwise

(defun searchElement(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) t)
    (t (searchElement (cdr l) elem))
  )
)


; firstOccReplace(l1l2...ln, elem, newElem) = 
; = nil , if n = 0
; = {newElem} U (l2...ln) , if elem = l1
; = firstOccReplace(l1, elem, newElem) U (l2...ln), if l1 is a list and searchElement(l1, elem) = true
; = {l1} U firstOccReplace(l2...ln, elem, newElem), otherwise


(defun firstOccReplace(l elem newElem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) (cons newElem (cdr l)))
    ((and (listp (car l)) (searchElement (car l) elem)) (cons (firstOccReplace (car l) elem newElem) (cdr l)))
    (t (cons (car l) (firstOccReplace (cdr l) elem newElem)))
  )
)

;--- the funtion above works for a non linear list