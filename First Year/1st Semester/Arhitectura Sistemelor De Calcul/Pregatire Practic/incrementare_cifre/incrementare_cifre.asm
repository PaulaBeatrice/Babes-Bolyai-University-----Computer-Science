bits 32 
global start        
;Se citeste de la tastatura numele unui fisier si un sir de caractere care contine litere mari si mici,spatii si cifre.
;Sa se scrie in fisierul cu numele citit de la tastatura sirul de caractere modificat astfel
;Toate cifrele se vor incrementa cu o unitate
extern exit, fopen, scanf, fclose,fprintf,printf,gets            
import exit msvcrt.dll    
import fopen msvcrt.dll
import scanf msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import printf msvcrt.dll
import gets msvcrt.dll

segment data use32 class=data
    nume_fisier times 32 db 0
    mod_acces db "w",0
    descriptor dd -1
    format db "%s",0
    cuvant times 100 db 0
    format_af db "%s ",0
    propozitie times 100 db 0
    cnt dd 0
    
    
segment code use32 class=code
    start:
        ;citesc numele fisierului
        push dword nume_fisier
        push dword format
        call [scanf]
        add ESP, 4 * 2
        
        ;deschid fisierul
        push dword mod_acces
        push dword nume_fisier
        call [fopen]
        add ESP, 4 * 2
        
        mov [descriptor], EAX
        
        cmp EAX, 0
        je final
        
        read:
            push dword cuvant
            push format
            call [scanf]
            add ESP, 4 * 2
            
            mov EAX, dword cuvant
          
            
            mov ESI, dword cuvant
            cld
            cauta_cifre:
                lodsb
                cmp AL, 0
                je aici
                cmp AL, "$"
                je afara
                cmp AL, "0"
                jb aici_
                cmp AL, "8"
                ja aici_
                
                inc AL
                
                
                aici_:
                ;stosb
                mov [cuvant], AL
               
                push dword cuvant
                push dword format_af
                push dword [descriptor]
                call [fprintf]
                add ESP, 4 * 3
                
         
            aici:
            jmp read
        afara:

        ;inchid fisierul
        push dword[descriptor]
        call [fclose]
        add ESP, 4
        final:
        push    dword 0      
        call    [exit]