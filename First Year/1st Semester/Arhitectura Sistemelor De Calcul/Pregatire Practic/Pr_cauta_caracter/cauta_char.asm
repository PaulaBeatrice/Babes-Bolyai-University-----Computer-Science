;Se citeste de la tastatura un nume de fisier, un caracter c si un numar n. Sa se determine daca numarul de aparitii ale caracterului c in textul
; fisierului este egal cu numarul n, afisandu-se in fisier out.txt un mesaj corespunzator (formatati textul ca si in exemplu)./
bits 32 

global start        
extern exit, fopen, fread, fclose, printf, scanf,fprintf
import exit msvcrt.dll  
import fopen msvcrt.dll  
import fread msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fprintf msvcrt.dll

segment data use32 class=data
    n dd 0
    caracter dd 0
    
	format_n  db "%d", 0  
	format_c db "%s", 0
    
    nume_fisier dd 0
    format_fisier dd "%s", 0
    
    mod_acces dd "r", 0
    descriptor_fis dd -1
    
    crt times 100 db 0
    da dd "DA", 0
    nu dd "NU", 0
    
    fisier_afisare db "b.txt", 0
    mod_acces_afisare db "w", 0  
    descriptor_fis_afisare db -1

segment code use32 class=code
    start:
    
        ;deschid b.txt
        push dword mod_acces_afisare     
        push dword fisier_afisare
        call [fopen]
        add esp, 4*2  
        
        mov [descriptor_fis_afisare], eax   
        
        cmp eax, 0
        je final
        
        ;citesc nume_fisier
        ;scanf (format, nume_fisier)
        push dword nume_fisier    
        push dword format_fisier
        call [scanf]      
        add esp, 4 * 2 
        
        ;citesc c
        ;scanf (format, c)
        push dword caracter   
        push dword format_c
        call [scanf]      
        add esp, 4 * 2 
        
        ;citesc n
        ;scanf (format, n)
        push dword n  
        push dword format_n
        call [scanf]      
        add esp, 4 * 2
        
        ; apelez fopen pentru a deschide fisierul
        ; fopen(nume_fisier, mod_acces)
        push dword mod_acces     
        push dword nume_fisier
        call [fopen]
        add esp, 4*2       
        
        mov [descriptor_fis], EAX
        
        mov ebx, 0 ;pt numarul de caractere c
        
        ; citesc textul caracter cu caracter
        repeta:
            ;fread(caracter, 1, 1, descriptor_fis)
            push dword [descriptor_fis]
            push dword 1
            push dword 1
            push dword crt        
            call [fread]
            add esp, 4*4                 
            
            cmp eax, 0
            je final_1
            
            ;verific daca crt == c
            
            mov ecx, [crt]
            cmp [caracter], ecx
            jne final_
            
            inc ebx
            final_:
        jmp repeta
        final_1:       
        
        cmp ebx, [n]
        jne nu_
        
        push dword da
        push dword [descriptor_fis_afisare]
        call [fprintf]
        add esp, 4*2
        
        jmp inchide_fisier
        
        nu_:
        push dword nu
        push dword [descriptor_fis_afisare]
        call [fprintf]
        add esp, 4*2 
        
        inchide_fisier:
        push dword [descriptor_fis]
        call [fclose]
        add esp, 4
        
        push dword [descriptor_fis_afisare]
        call [fclose]
        add esp, 4
        
        final:
        push    dword 0      
        call    [exit]