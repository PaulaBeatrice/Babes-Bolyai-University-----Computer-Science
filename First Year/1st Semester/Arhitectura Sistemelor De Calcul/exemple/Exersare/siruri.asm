;Se da un sir de octeti S. Sa se construiasca sirul D ale carui elemente reprezinta suma fiecaror doi octeti consecutivi din sirul S.
;Exemplu:
;S: 1, 2, 3, 4, 5, 6
;D: 3, 5, 7, 9, 11
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        
extern exit,printf ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    s db 1, 2, 3, 4, 5, 6
    lung equ $-s
    d times lung db 0
    ptPrint db '%c', 0

; our code starts here
segment code use32 class=code
start:
    mov ECX, lung - 1
    mov esi, 0
    Repeta:
        mov AL, [s+esi]
        mov BL, [s + esi + 1]
        add AL, BL
        mov [d + esi], AL
        inc esi
    loop Repeta
    mov EAX,0
    mov AL, [s+1]
    push EAX
    push dword ptPrint șadresa unde incepe in mem
    call [printf]
    add ESP,4*2
    Sfarsit:;terminarea programului
    push    dword 0      ; push the parameter for exit onto the stack
    call    [exit]       ; call exit to terminate the program
