bits 32
global start        

extern exit, scanf, printf, lungime, convert2_8               

import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    format_cerere db "Introduceti un numar in baza 2: ", 0
    format_citire db "%s", 0
    format_scriere db "Numarul cerut, scris in baza 8 este %s",13,10,0
    a times 100 db 0
    b db 0FFh
    d times 100 db 0
    
; Se citesc mai multe numere de la tastatura, in baza 2. Sa se afiseze aceste numere in baza 8.
segment code use32 class=code
    start:
        ; ...
        mov ESI, a
        mov EDI, d
        
        main_loop:
            
            push ESI
            push EDI
            
            push dword format_cerere
            call [printf]
            add esp, 4 * 1
            
            
            push dword a
            push dword format_citire
            call [scanf]
            add esp, 4 * 2
            
            push dword a
            call lungime
            add esp, 4
            
            pushf
            
            push EAX
            push dword d
            push dword a
            call convert2_8
            add esp, 4 * 3
            
            
            
            push dword d
            push dword format_scriere
            call [printf]
            add esp, 4 * 2
            
            popf
            
            
            pop EDI
            pop ESI
            
            ;stosb
        jmp main_loop
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
    