;Citim din fisier cifre. La final afisam numarul de cifre impare din fisier
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, fopen, fscanf, fclose
import exit msvcrt.dll
import printf msvcrt.dll
import fopen msvcrt.dll
import fscanf msvcrt.dll
import fclose msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data

    max_num equ 100                 ; numarul maxim de intregi
        
    descr_fis dd -1                 ; descriptorul fisierului
    mod_acces db "r", 0             ; modul de acces la fisier
    nume_fisier db "date.txt", 0   ; numele fisierului
    
    n dd 0                          ; numarul intreg citit din fisier
    format db '%d', 0               ; formatul de citire din fisier
    
    mesaj db 'Numarul de cifre impare din fisier este:', 0
    format_afisare db ' %d', 0      ; formatul de afisare la consola

; our code starts here
segment code use32 class=code
    start:
        push dword mod_acces
        push dword nume_fisier
        call [fopen]
        add esp,4*2
        
        mov [descr_fis], eax
        cmp eax,0
        je final
        
        mov ecx, max_num
        jecxz final
        cld
        ;mov edi,sir_numere
        mov ebx,0
        citire:
            push dword n
            push dword format
            push dword [descr_fis]
            call [fscanf]
            add esp,4*3
            
            cmp eax,1
            jne afisare
            
            mov eax,[n]
            and eax,1
            jz par
            add ebx,1
            
            par:
        loop citire
        
        afisare:
            push dword mesaj
            call [printf]
            add esp,4*1
            
            push dword ebx
            push dword format_afisare
            call [printf]
            add esp,4*2
        
        final:
            push    dword 0      ; push the parameter for exit onto the stack
            call    [exit]       ; call exit to terminate the program
