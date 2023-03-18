;Se citesc de la tastatură 2 numere m si n, apoi se citesc m numere fără semn. Se cere să se scrie într-un fișier output.txt numerele care au cel 
;puțin n cifre zecimale pare    
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fclose, scanf, printf, fprintf           ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import fopen msvcrt.dll
import fclose msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll
import fprintf msvcrt.dll

segment data use32 class=data
    nume_fisier db "ana.txt", 0
    descriptor_fis dd -1
    mod_acces db "w", 0
    format db "%u", 0
    format_af db "%u", 10, 0
    m dd 0
    n dd 0
    numar dd 0
    doi db 2
    
segment code use32 class=code
    start:
        push dword mod_acces
        push dword nume_fisier
        call [fopen]
        add ESP, 4 * 2
        
        mov [descriptor_fis], EAX
        cmp EAX, 0
        je final
    
        push dword n
        push dword m
        push dword format
        call [scanf]
        add ESP, 4 * 3
        
        push dword n
        push dword format
        call [scanf]
        add ESP, 4 * 2
        
        mov ECX, dword[m]
 
        citeste:
            push ECX
        
            push dword numar
            push dword format
            call [scanf]
            add ESP, 4 * 2
            
            mov EAX, [numar]
            mov EDX, 0
            mov ECX, 0
            
            descompunere:
                mov EBX, 10
                div EBX
                
                push EAX
                
                mov EAX, EDX
                div byte[doi]
                cmp AH, 0
                jne aici
                inc ECX
                aici:
                
                mov EDX, 0
                pop EAX
                cmp EAX, 0
                je gata
                jmp descompunere
            
            gata:
            
            cmp ECX, [n]
            jb nu_afisa
            
            push dword [numar]
            push format_af
            push dword[descriptor_fis]
            call [fprintf]
            add ESP, 4 * 3
            
            nu_afisa:     
            pop ECX
        loop citeste
    
        
       
        final:
        
        ;fclose(descriptor_fis)
        push dword [descriptor_fis]
        call [fclose]
        add ESP, 4
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
