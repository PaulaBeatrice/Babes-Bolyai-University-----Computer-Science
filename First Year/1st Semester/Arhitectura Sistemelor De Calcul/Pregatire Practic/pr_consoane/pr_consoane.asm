; Se citesc n si m de la tastatura, apoi m cuvinte si afisati in fisier cuvintele cu cel mult n consoane
bits 32
global start        


extern exit,scanf,fopen,fclose,fprintf             
import exit msvcrt.dll   
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll        
              
                         
segment data use32 class=data
     nume_fisier db "preg.txt",0
     mod_acces db "w",0
     descriptor dd -1
     format_citire db "%d %d",0
     format_cit_cuv db "%s",0
     format_afis db "%s ",0
     cuvant times 32 db 0
     consoane dd 0
     n dd 0
     m dd 0
     
segment code use32 class=code
    start:
        ;deschid fisier pt afisare
        push mod_acces
        push nume_fisier
        call[fopen]
        add ESP, 4 * 2
        
        mov [descriptor], EAX
        
        ;citirea
        ;scanf(format,n,m)
        push dword m
        push dword n
        push format_citire
        call [scanf]
        add ESP, 4 * 3
        
        mov ecx, dword[m]
        
        repeta:
            push ECX
            push dword cuvant
            push format_cit_cuv
            call [scanf]
            add ESP,4 * 2
            
            mov ESI, dword cuvant
            mov dword[consoane], 0
            verif_litere:
                lodsb  
                cmp AL, 0
                je afara
                
                cmp AL, "a"
                je verif_litere
                jne ver_e
                ver_e:
                    cmp AL, "e"
                    je verif_litere
                ver_i:
                    cmp AL, "i"
                    je verif_litere
                ver_o:
                    cmp AL, "o"
                    je verif_litere
                ver_u:
                    cmp AL,"u"
                    je verif_litere
                nr_cons:
                    mov EBX, dword [consoane]
                    inc EBX
                    mov dword [consoane], EBX
               
                jmp verif_litere
            afara:
                 mov EAX, dword[n]
                 cmp dword[consoane], EAX
                 jbe afisare
                 ja aici
            afisare:
                push dword cuvant
                push format_afis
                push dword [descriptor]
                call [fprintf]
                add ESP, 4 * 3
            aici:
            pop ECX
            dec ECX
            jnz repeta
        
        final:
        push dword [descriptor]
        call [fclose]
        add ESP, 4
        push    dword 0     
        call    [exit]; Se citesc n si m de la tastatura, apoi m cuvinte si afisati in fisier cuvintele cu cel mult n consoane
bits 32
global start        


extern exit,scanf,fopen,fclose,fprintf             
import exit msvcrt.dll   
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll        
              
                         
segment data use32 class=data
     nume_fisier db "preg.txt",0
     mod_acces db "w",0
     descriptor dd -1
     format_citire db "%d %d",0
     format_cit_cuv db "%s",0
     format_afis db "%s ",0
     cuvant times 32 db 0
     consoane dd 0
     n dd 0
     m dd 0
     
segment code use32 class=code
    start:
        ;deschid fisier pt afisare
        push mod_acces
        push nume_fisier
        call[fopen]
        add ESP, 4 * 2
        
        mov [descriptor], EAX
        
        ;citirea
        ;scanf(format,n,m)
        push dword m
        push dword n
        push format_citire
        call [scanf]
        add ESP, 4 * 3
        
        mov ecx, dword[m]
        
        repeta:
            push ECX
            push dword cuvant
            push format_cit_cuv
            call [scanf]
            add ESP,4 * 2
            
            mov ESI, dword cuvant
            mov dword[consoane], 0
            verif_litere:
                lodsb  
                cmp AL, 0
                je afara
                
                cmp AL, "a"
                je verif_litere
                jne ver_e
                ver_e:
                    cmp AL, "e"
                    je verif_litere
                ver_i:
                    cmp AL, "i"
                    je verif_litere
                ver_o:
                    cmp AL, "o"
                    je verif_litere
                ver_u:
                    cmp AL,"u"
                    je verif_litere
                nr_cons:
                    mov EBX, dword [consoane]
                    inc EBX
                    mov dword [consoane], EBX
               
                jmp verif_litere
            afara:
                 mov EAX, dword[n]
                 cmp dword[consoane], EAX
                 jbe afisare
                 ja aici
            afisare:
                push dword cuvant
                push format_afis
                push dword [descriptor]
                call [fprintf]
                add ESP, 4 * 3
            aici:
            pop ECX
            dec ECX
            jnz repeta
        
        final:
        push dword [descriptor]
        call [fclose]
        add ESP, 4
        push    dword 0     
        call    [exit]