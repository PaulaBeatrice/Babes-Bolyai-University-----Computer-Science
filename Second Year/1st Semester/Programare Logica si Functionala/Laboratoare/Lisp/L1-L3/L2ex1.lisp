; 1. Se da un arbore de tipul (1). Sa se afiseze calea de la radacina pana la un nod x dat.


; pargurg_st(l1l2...ln, nrNoduri, nrMuchii) = 
; = [], if n = 0
; = [], if nrNoduri = 1 + nrMuchii
; = {l1} U {l2} U parcurg_st(l3...ln, nrNoduri + 1, l2 + nrMuchii), altfel

;parcurg_st(L: Lista, nrNoduri: Integer, nrMuchii: Integer)
;L=Lista pe care o parcurgem
;nrNoduri=nr de noduri parcurse
;nrMuchii=nr de muchii parcurse

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
; = parcurg_dr(l3...ln, nrNoduri + 1, nrMuchii + l2), altfel

;parcurg_st(L: Lista, nrNoduri: Integer, nrMuchii: Integer)
;L=Lista pe care o parcurgem
;nrNoduri=nr de noduri parcurse
;nrMuchii=nr de muchii parcurse


(defun parcurg_dr (l nrNoduri nrMuchii)
  (cond
    ((null l) nil)
    ((= nrNoduri (+ 1 nrMuchii)) l)
    (t (parcurg_dr (cddr l) (+ 1 nrNoduri) (+ (cadr l) nrMuchii)))
  )
)


;stang(l1l2...ln) = 
; = parcurg_st(l3...ln, 0,0)
;stang(L:Lista)
;L=Lista de parcurs

(defun stang(l)
  (parcurg_st (cddr l) 0 0)
)


;drept(l1l2...ln) =
; = parcurg_dr(l3...ln, 0, 0)
;drept(L:Lista)
;L=Lista de parcurs

(defun drept(l)
  (parcurg_dr (cddr l) 0 0)
)


; verifExistenta(l1l2...ln, elem) = 
; = true, if l1 = elem
; = false , if n = 0
; = verifExistenta(l2...ln, elem), otherwise

;verifExistenta(L:Lista, elem:Element)
;L=Lista in care cautam elemtul
;elem=elementul pe care il cautam in lista


(defun verifExistenta(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) t)
    (t (verifExistenta (cdr l) elem))
  )
)

;verifExistentaLeft(L, elem) = verifExistenta( stang(L), elem)
;verifExistentaLeft(L:Lista, elem:Element)
;L=lista pe care o parcurge,
;elem=Elementul pe care il cautam

(defun verifExistentaLeft(l elem)
  (verifExistenta (stang l) elem)
)

;verifExistentaRight(L, elem) = verifExistenta( drept(L), elem)
;verifExistentaRight(L:Lista, elem:Element)
;L=lista pe care o parcurge,
;elem=Elementul pe care il cautam

(defun verifExistentaRight(l elem)
  (verifExistenta (drept l) elem)
)


; cale(l1l2...ln, elem) = 
; = [], if n = 0
; = list(elem), if elem = l1
; = {l1} U cale(drept(l1l2...ln), elem), if verifExistentaRight(l, elem) = true
; = {l1} U cale(stang(l1l2...ln), elem), if verifExistentaLeft(l, elem) = true


(defun cale(l elem)
  (cond
    ((null l) nil)
    ((equal (car l) elem) (list elem))
    ((verifExistentaRight l elem) (cons (car l) (cale (drept l) elem)))
    ((verifExistentaLeft l elem) (cons (car l) (cale (stang l) elem)))
  )
)