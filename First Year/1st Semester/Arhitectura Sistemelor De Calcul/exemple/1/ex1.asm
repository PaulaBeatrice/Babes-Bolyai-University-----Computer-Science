bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
;toate variabilele folosite de noi
a DB 0A2h ;se declara variabila a de tip BYTE si se initializeaza cu valoarea 0A2h
b DW 'ab' ;se declara variabila a de tip WORD si se initializeaza cu valoarea 'ab'
c DD 12345678h ;se declara variabila a de tip DOUBLE WORD si se initializeaza cu valoarea 12345678h
d DQ 1122334455667788h ;se declara variabila a de tip QUAD WORD si se initializeaza cu valoarea 1122334455667788h

e RESB 1 ;se rezerva 1 octet
f RESB 64 ;se rezerva 64 octeti
g RESW 1 ;se rezerva 1 word

h db -1

zece EQU 10 ;se defineste constanta zece care are valoarea 10

; our code starts here
segment code use32 class=code
    start:
    ;aici scriem instructiuni
    mov EBX, 12345678h
    mov EDX, 01010101h
    add EDX,EBX;aduna la EDX pe ENX -> EDX = EDX + EBX
    inc byte [c];aduna 1 la variabila c -> byte, word, dword
    neg byte [h]
    mov AL, 7
    mov AL, 12
        ; ...

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
