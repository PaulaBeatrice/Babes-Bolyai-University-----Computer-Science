bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

extern reversed

; declare external functions needed by our program
extern exit, scanf, printf, Sleep   ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll   ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import printf msvcrt.dll
import Sleep kernel32.dll
; our data is declared here (the variables needed by our program)

;Read a sentence from the keyboard. For each word, obtain a new one by taking the letters in reverse order and print each new word.


segment data use32 class=data
    ; ...
    ; sir db "Ana are mere", 0 
    array times 101 dd 0
    reversed_word resd 33
    format db "%[^",10,"]%s", 0
    format2 db "%s", 0
    
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword array
        push dword format
        call [scanf]
        add esp, 4 * 2
        
        mov esi, array
        mov ebx, array
        
        loop_:
                lodsb
                cmp al, 0
                je exit_
                cmp al, ' '
                je cuvant
                
                jmp continue
                
                cuvant:
                        ; we obtain the reversed word
                        ; Ana are mere
                        pushad
                        sub esi, ebx
                        sub esi, 2 ; spatiul si indexul 0 si \n 
                        add esi, reversed_word
                        push esi 
                        push ebx
                        call reversed
                        
                        add esp, 4 * 2
                        popad
                        
                        ; print the result
                        cld
                        push dword reversed_word
                        push dword format2
                        call [printf]
                        add esp, 4 * 2
                        
                        mov ebx, esi
                        
                continue:
        loop loop_
         
        
        exit_: 
                pushad
                sub esi, ebx
                sub esi, 2 ; spatiul si indexul 0 si \n 
                add esi, reversed_word
                push esi 
                push ebx
                call reversed
                
                add esp, 4 * 2
                popad
                
                ; print the result
                cld
                push dword reversed_word
                push dword format2
                call [printf]
                add esp, 4 * 2
                       
            
        push 5000
        call [Sleep]
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
