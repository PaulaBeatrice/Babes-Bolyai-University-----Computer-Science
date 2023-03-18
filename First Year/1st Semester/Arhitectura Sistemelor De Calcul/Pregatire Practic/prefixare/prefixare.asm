; Se citeste de la tastatura un nume de fisier, un caracter special s (orice caracter in afara de litere si cifre) si un numar n reprezentat pe octet.
; Fisierul contine cuvinte separate prin spatiu. Sa se scrie in fisierul output.txt ultimele n caractere din fiecare cuvant.
; (Daca numarul de caractere al cuvantului este mai mic decat n, cuvantul se va prefixa cu caracterul special s).

;Exemplu:
;nume fisier:input.txt
;continut fisier: mere pere banane mandarine
;s: +
;n: 6
;output.txt: ++mere ++pere banana darine

bits 32

global start        


extern exit, printf, fprintf, scanf, fscanf, fopen, fclose              
import exit msvcrt.dll    
import printf msvcrt.dll  
import fprintf msvcrt.dll  
import scanf msvcrt.dll  
import fscanf msvcrt.dll  
import fopen msvcrt.dll 
import fclose msvcrt.dll 

segment data use32 class=data
    nume_fisier dd 0 
    format_fisier dd "%s", 0
    descriptor_1 dd -1
    mod_acces db "r", 0
    
    caracter_special db 0
    format_caracter dd "%s", 0
    
    numar db 0
    format_nr dd "%d", 0
    
    fisier db "output.txt", 0
    descriptor_2 dd -1
    mod_acces_2 db "w", 0
    format_afisare db "%s", 10, 0
    
    cuvant times 32 db 0
    format_cuvant db "%s", 0
    sir times 100 db 0

segment code use32 class=code
    start:
        ;citire si deschide fisier
        push dword nume_fisier
        push dword format_fisier
        call[scanf]
        add esp, 4 * 2
        
        push dword mod_acces
        push dword nume_fisier
        call[fopen]
        add esp, 4 * 2
        
        mov [descriptor_1], eax
        
        cmp eax, 0
        je final
        
        ;citire caracter special
        push dword caracter_special
        push dword format_caracter
        call [scanf]
        add esp, 4 * 2
        
        ;citire numar
        push dword numar
        push dword format_nr
        call[scanf]
        add esp, 4 * 2
        
        ;deschidere fisier_af
        push dword mod_acces_2
        push dword fisier
        call[fopen]
        add esp, 4 * 2
        
        mov [descriptor_2], eax
        
        cmp eax, 0
        je final
        
        citire_cuvant:
            ;citim cuvantul, si punem in fisier prefixul sau
            push dword cuvant
            push dword format_cuvant
            push dword [descriptor_1]
            call[fscanf]
            
            test eax, eax
            js gata
            
            mov esi, cuvant
            
            mov ebx, 0; numaram literele cuvantului citit
            
            numarare:
                lodsb
                cmp al, 0
                je gata_numarare
                inc ebx
                jmp numarare
            gata_numarare:
            
            cmp ebx, [numar]
            jae afis_cuv
            ;afisam caracter + cuvant
            mov ecx, [numar]
            sub ecx, ebx
            mov byte[sir], 0
            mov edx, 0
            mov al, byte[caracter_special]
            
            repeta:
                mov byte[sir + edx], al
                inc edx
                loop repeta
                
            mov ecx, ebx
            sub esi, ebx
            sub esi, 1
            mov edi, 0
            repeta_:
                ;stosb
                mov al, [esi + edi]
                mov byte[sir+edx], al
                inc edx
                inc edi
                loop repeta_
            mov [sir+edx+1], esi
                    
            ;mov [cuvant], edi
                            
            push dword sir
            push dword format_afisare
            push dword [descriptor_2]
            call[fprintf]
            add esp, 4 *3
            
            jmp aici
            afis_cuv:
                sub esi, [numar]
                sub esi, 1
                mov byte[sir], 0
                mov ecx, [numar]
                mov edx, 0
                repeta_2:
                    lodsb
                    mov byte[sir + edx], al
                    inc edx
                    loop repeta_2
                
                ;mov [cuvant], edi
                
                push dword sir
                push dword format_afisare
                push dword [descriptor_2]
                call[fprintf]
                add esp, 4 *3
            
            aici:
            jmp citire_cuvant
        gata:
        
        ;inchidem fisierele
        push dword [descriptor_1]
        call[fclose]
        add esp, 4
        
        push dword[descriptor_2]
        call[fclose]
        add esp, 4
        
        final:
        push    dword 0      
        call    [exit]       
