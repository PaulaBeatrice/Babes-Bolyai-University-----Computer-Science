;Sa se citeasca de la tastatura un nume de fisier si un numar. Sa se citeasca din fisierul dat cuvintele separate prin spatii si sa se afiseze in
; consola numai cuvintele a caror numar de vocale este egal cu numarul citit de la tastatura.
    ; input.txt
    ; 3
    ; input.txt: ana
    ; avea mere si nu
    ; pere
    ; =>avea
bits 32 ; assembling for the 32 bits architecture

global start        

extern exit, scanf, fopen, fscanf, fprintf, fclose, printf               
import exit msvcrt.dll    
import scanf msvcrt.dll    
import fopen msvcrt.dll    
import fscanf msvcrt.dll    
import fprintf msvcrt.dll    
import fclose msvcrt.dll    
import printf msvcrt.dll

segment data use32 class=data
   nume_fisier db 0
   format_fisier dd "%s", 0
   
   numar dd 0
   format_numar db "%d", 0
   
   mod_acces db "r", 0
   descriptor dd -1
   
   cuvant times 32 db 0
   format_cuvant db "%s", 0
   format_af db "%s", 10, 0

segment code use32 class=code
    start:
        ;citire nume fisier si deschidere fisier
        push dword nume_fisier
        push dword format_fisier
        call[scanf]
        add esp, 4 * 2
        
        push mod_acces
        push nume_fisier
        call[fopen]
        add esp, 4 * 2
        
        mov [descriptor], eax
        
        cmp eax, 0
        je final
        
        ;citire numar
        push numar
        push format_numar
        call[scanf]
        add esp, 4 * 2
        
        
        ;citim cuvintele din fisier si numaram vocalele
    
        citire:
            push dword cuvant
            push dword format_cuvant
            push dword [descriptor]
            call [fscanf]
            add esp, 4 * 3
            
            test eax, eax
            js final_citire
            
            mov ESI, dword cuvant
            mov EBX,  0
            
            cauta_vocala:
                lodsb 
                cmp AL, 0
                je aici_2
                
                cmp AL, 'a'
                je aduna
                
                cmp AL, 'e'
                je aduna
                
                cmp AL, 'i'
                je aduna
                
                cmp AL, 'o'
                je aduna
                
                cmp AL, 'u'
                je aduna
                
                jmp cauta_vocala
                
                aduna: inc ebx
                jmp cauta_vocala
                
            aici_2:
            cmp ebx, [numar]
            jne aici
            push cuvant
            push format_af
            call[printf]
            add esp, 4 * 2
            aici:
                
            jmp citire
            
        final_citire:
       
        ;inchidem fisierul 
        push dword [descriptor]
        call [fclose]
        add esp, 4
        
        final:
        push    dword 0      
        call    [exit]       
