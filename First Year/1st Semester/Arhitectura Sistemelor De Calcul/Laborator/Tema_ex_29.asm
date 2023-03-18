bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, scanf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll   
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ;format db " %d",0
    format db "%d %d %d",0
    format_afisare db '%d',0
    a dw 0
    m db 0
    n db 0
; our code starts here
segment code use32 class=code
    start:
    ;scanf(%d, %d, %d, a, m, n)
        push dword n
        push dword m
        push dword a
        push format
        call [scanf]
        
        
        ; add esp, 4*4
        ; mov al,4
        ; mov [n],al
        ; mov al,7
        ; mov [m], al
        ; mov ax,174
        ; mov [a],ax
        
        mov al,[m]
        sub al,[n] ; al=m-n
        mov bx,1111111111111111b
        mov cl,16
        sub cl,al ; cl = 16-(m-n) (Numarul de shiftari necesare pentru a ajunge la primii m-n biti
        shr bx,cl
        and bx,[a]
        mov eax,0
        mov ax,bx
        push eax
        push format_afisare
        call [printf]
        
        
        ; mov ecx,al
        ; dec ecx
        ; jecxz final
        ; mov ebx,1b       
        ; repeta
        
        ; loop repeta
        
        ; pushad
        ; mov eax,0
        ; mov edx,0
        ; mov ecx,0
        ; mov al,[a]
        ; mov cl,[m]
        ; mov dl,[n]
        
        ; push edx
        ; push ecx
        ; push eax
        ; push format
        ; call [printf]
        ; add esp,4*4
        
        ; exit(0)
        final:
            push    dword 0      ; push the parameter for exit onto the stack
            call    [exit]       ; call exit to terminate the program
