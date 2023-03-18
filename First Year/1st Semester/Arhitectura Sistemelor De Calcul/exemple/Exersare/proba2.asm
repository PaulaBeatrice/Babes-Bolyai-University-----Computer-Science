;Se citesc de la tastatura trei numere a, m si n (a: word, 0 <= m, n <= 15, m > n). Sa se izoleze bitii de la m-n ai lui a si sa se
;afiseze numarul intreg reprezentat de acesti bitii in baza 10.
;Exemplu: a = 1001 1111 0101 1110 = 9F5Eh = 40798, m = 8, n = 4
; Se izoleaza bitii 8-4 => a devine 0000 0001 0101 0000 => Numarul intreg reprezentat de acesti biti in baza 10 este 21
; Rezultat: 21

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, scanf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

import printf msvcrt.dll
import scanf msvcrt.dll                          
                          
; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a dw 0
    spatiu db 0, 0
    m dd 0
    n dd 0
    mesaj1 db "a=", 0
    mesaj2 db "m=", 0
    mesaj3 db "n=", 0
    format db "%d", 0
    format_afisare db "%d",0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;printf("a=")
        ;punem parametrii pe stiva de la dreapta la stanga
        push dword mesaj1
        call[printf]; apelam functia printf pentru afisare
        add ESP, 4 * 1; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 1 = nr de parametrii
        
        mov EAX, 0
        mov AX, [a]
        
        ;scanf("%d",a)
        push dword a
        push dword format
        call[scanf]
        add ESP, 4 * 2
        
        ;print("m=")
        ;punem parametrii pe stiva de la dreapta la stanga
        push dword mesaj2
        call[printf]; apelam functia printf pentru afisare
        add ESP, 4 * 1; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 1 = nr de parametrii
        
        ;scanf("%d",m)
        ;punem parametrii pe stiva de la dreapta la stanga
        push dword m
        push dword format
        call[scanf]
        add ESP, 4 * 2; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 2 = nr de parametrii
        
        ;print("n=")
        ;punem parametrii pe stiva de la dreapta la stanga
        push dword mesaj3
        call[printf]; apelam functia printf pentru afisare
        add ESP, 4 * 1; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 1 = nr de parametrii
        
        ;scanf("%d",n)
        ;punem parametrii pe stiva de la dreapta la stanga
        push dword n
        push dword format
        call[scanf]
        add ESP, 4 * 2; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 2 = nr de parametrii
        
      ;  mov AL, [m]; AL = m
     ;   sub AL, [n] ; AL = m - n
       ; mov BX, 1111111111111111b
       ; mov CL, 16
      ;  sub CL, [m]; CL = 16-(m-n) (Numarul de shiftari necesare pentru a ajunge la primii m-n biti)
      ;  sub CL, 1
     ;   shl AX, CL
      ;;  mov CL, 16
       ; sub CL, [m]
       ; add CL, [n]
       ; add CL, 1
     ;   shr AX, CL
      ;  mov EAX, 0; EAX = 0
      ;  mov AX, BX; AX = BX
        mov EAX,0
        mov AX,[a]
        mov CL,[n]
        shr AX,CL
        
        mov ECX,0
        mov CL,[m]
        sub CL,[n]
        mov EBX,1
        strt:
            shl EBX,1
            add EBX,1
        loop strt
         and EAX,EBX
        
        ;printf("%d", EAX)
        push EAX
        push format_afisare
        call [printf]; apelam functia printf
        add ESP, 4 * 2; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 2 = nr de parametrii
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
