;9. Sa se converteasca un arbore de tipul (1) la un arbore de tipul (2).

; (A 2 B 0 C 2 D 0 E 1 F 0) -> (A (B) (C (D) (E (F))))

; pargurg_st(l1l2...ln, nrNoduri, nrMuchii) = 
; = nil, if n = 0
; = nil, if nrNoduri = 1 + nrMuchii
; = {l1} U {l2} U parcurg_st(l3...ln, nrNoduri + 1, l2 + nrMuchii), otherwise

(defun parcurg_st (l nrNoduri nrMuchii)
  (cond
    ((null l) nil)
    ((= nrNoduri ( + 1 nrMuchii)) nil)
    (t (cons (car l) (cons (cadr l) (parcurg_st (cddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))))
  )
)

; parcurg_dr(l1l2...ln, nrNoduri, nrMuchii) =
; = nil, if n = 0
; = l1l2...ln, if nrNoduri = 1 + nrMuchii
; = parcurg_dr(l3...ln, nrNoduri + 1, nrMuchii + l2), otherwise


(defun parcurg_dr (l nrNoduri nrMuchii)
  (cond
    ((null l) nil)
    ((= nrNoduri (+ 1 nrMuchii)) l)
    (t (parcurg_dr (cddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))
  )
)

;stang(l1l2...ln) = 
; = parcurg_st(l3...ln, 0,0)

(defun stang(l)
  (parcurg_st (cddr l) 0 0)
)


;drept(l1l2...ln) =
; = parcurg_dr(l3...ln, 0, 0)

(defun drept(l)
  (parcurg_dr (cddr l) 0 0)
)

; myAppend(l1l2...ln, p1p2...pm) = 
; = p1p2...pm, if n = 0
; = {l1} U myAppend(l2...ln, p1p2...pm), otherwise

(defun myAppend(l p)
  (cond
    ((null l) p)
    (t (cons (car l) (myAppend (cdr l) p)))
  )
)

; convert_back(l1l2..ln) = 
; = nil , if n = 0
; = myAppend(list(l1), convert_back(stang(l1l2...ln)) U list(convert_back(drept(l1l2...ln)))), if l2 = 1
; = myAppend(list(l1), list(convert_back(stang(l1l2...ln)))), if l2 = 1
; = list(l1), if l2 = 0

(defun convert_back(l)
  (cond
    ((null l) nil)
    ((equal (cadr l) 2) (myAppend (list (car l)) (cons (convert_back(stang l)) (list (convert_back(drept l))))))
    ((equal (cadr l) 1) (myAppend (list (car l)) (list (convert_back(stang l)))))
    (t (list (car l)))
  )
)