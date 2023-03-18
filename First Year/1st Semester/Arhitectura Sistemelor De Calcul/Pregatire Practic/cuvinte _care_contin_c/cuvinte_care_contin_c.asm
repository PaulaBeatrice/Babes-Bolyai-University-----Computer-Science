; write a program that will read from keyboard a filename input.txt and a character c. the input file input.txt contains words separated by space. 
; write in the file output.txt only the words that contain the character c
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

    caracter dd 0
    nume_fisier dd 0
    
    format dd "%s", 0
    format_c db "%s", 0
    mod_acces_citire db "r", 0
    descriptor_fis_1 dd -1
    
    nume_fisier_afisare db "output.txt", 0
    mod_acces_afisare db "w", 0
    descriptor_fis_2 dd -1
    format_afisare db "%s ",0
    
    cuvant times 32 db 0

segment code use32 class=code
    start:
        ;citire nume fisier(input.txt)
        push dword nume_fisier
        push dword format
        call [scanf]
        add ESP, 4 * 2
        
        ;deschidere input.txt
        push dword mod_acces_citire
        push dword nume_fisier
        call [fopen]
        add ESP, 4 * 2
        
        mov [descriptor_fis_1], EAX
        
        cmp EAX, 0
        je final
        
        ;citire c
        push dword caracter
        push dword format_c
        call [scanf]
        add ESP, 4 * 2
        
        push dword caracter
        push dword format_c
        call[printf]
        add esp, 4 * 2
        
        ;deschire output.txt
        push dword mod_acces_afisare
        push dword nume_fisier_afisare
        call[fopen]
        add ESP, 4 * 2
        
        mov [descriptor_fis_2], EAX
        
        cmp EAX, 0
        je final
        
        ;citire cuvinte din input.txt
        citeste_cuvant:
            ;citire cuvant
            push dword cuvant
            push dword format_c
            push dword [descriptor_fis_1]
            call[fscanf]
            add ESP, 4 * 3
            
            test eax,eax
            js final_citire
            
            ;verificam daca contine litera c
            mov ESI, dword cuvant
            verif_litera:
            
                lodsb; AL, primeste litera
                cmp AL, 0
                je gata
                cmp AL, [caracter]
                jne aici
                
                ;scriem  cuvantul in output.txt
                push dword cuvant 
                push dword format_afisare
                push dword [descriptor_fis_2]
                call[fprintf]
                add ESP, 4 * 2
                jmp citeste_cuvant
                
                aici:
                jmp verif_litera
            gata:
            
            jmp citeste_cuvant
        
        final_citire:
            
        ;inchidem input.txt
        push dword [descriptor_fis_1]
        call[fclose]
        add ESP, 4
        
        ;inchidem output.txt
        push dword [descriptor_fis_2]
        call[fclose]
        add ESP, 4
        
        final:

        push    dword 0
        call    [exit]      
; write a program that will read from keyboard a filename input.txt and a character c. the input file input.txt contains words separated by space. 
; write in the file output.txt only the words that contain the character c
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

    caracter dd 0
    nume_fisier dd 0
    
    format dd "%s", 0
    format_c db "%s", 0
    mod_acces_citire db "r", 0
    descriptor_fis_1 dd -1
    
    nume_fisier_afisare db "output.txt", 0
    mod_acces_afisare db "w", 0
    descriptor_fis_2 dd -1
    format_afisare db "%s ",0
    
    cuvant times 32 db 0

segment code use32 class=code
    start:
        ;citire nume fisier(input.txt)
        push dword nume_fisier
        push dword format
        call [scanf]
        add ESP, 4 * 2
        
        ;deschidere input.txt
        push dword mod_acces_citire
        push dword nume_fisier
        call [fopen]
        add ESP, 4 * 2
        
        mov [descriptor_fis_1], EAX
        
        cmp EAX, 0
        je final
        
        ;citire c
        push dword caracter
        push dword format_c
        call [scanf]
        add ESP, 4 * 2
        
        push dword caracter
        push dword format_c
        call[printf]
        add esp, 4 * 2
        
        ;deschire output.txt
        push dword mod_acces_afisare
        push dword nume_fisier_afisare
        call[fopen]
        add ESP, 4 * 2
        
        mov [descriptor_fis_2], EAX
        
        cmp EAX, 0
        je final
        
        ;citire cuvinte din input.txt
        citeste_cuvant:
            ;citire cuvant
            push dword cuvant
            push dword format_c
            push dword [descriptor_fis_1]
            call[fscanf]
            add ESP, 4 * 3
            
            test eax,eax
            js final_citire
            
            ;verificam daca contine litera c
            mov ESI, dword cuvant
            verif_litera:
            
                lodsb; AL, primeste litera
                cmp AL, 0
                je gata
                cmp AL, [caracter]
                jne aici
                
                ;scriem  cuvantul in output.txt
                push dword cuvant 
                push dword format_afisare
                push dword [descriptor_fis_2]
                call[fprintf]
                add ESP, 4 * 2
                jmp citeste_cuvant
                
                aici:
                jmp verif_litera
            gata:
            
            jmp citeste_cuvant
        
        final_citire:
            
        ;inchidem input.txt
        push dword [descriptor_fis_1]
        call[fclose]
        add ESP, 4
        
        ;inchidem output.txt
        push dword [descriptor_fis_2]
        call[fclose]
        add ESP, 4
        
        final:

        push    dword 0
        call    [exit]      
