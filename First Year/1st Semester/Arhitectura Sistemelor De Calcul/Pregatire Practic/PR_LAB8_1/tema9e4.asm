bits 32


global start        


extern exit, fopen, fprintf, scanf, fclose, printf
import exit msvcrt.dll
import fopen msvcrt.dll
import fprintf msvcrt.dll
import scanf msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    nume db "tema9e4.txt", 0 ; numele fisierului
    mode db "w", 0 ; fisierul va fi deschis in modul scriere
    
    desc dd 0 ; variabila in care se va afla descriptorul de fisier
    
    len equ 100 ; lungimea maxima a unui cuvant citit de la tastatura
    cuv times len db 0 ; variabila in care se afla cuvantul citit de la tastatura
    db 0
    
    print db "Introduceti un sir de maximum 100 de caractere: ", 0
    format_scan db "%100s", 0

; Se da un nume de fisier (definit in segmentul de date). Sa se creeze un fisier cu numele dat, apoi sa se citeasca de la tastatura cuvinte pana cand se citeste de la tastatura caracterul '$'. Sa se scrie in fisier doar cuvintele care contin cel putin o cifra.
segment code use32 class=code
    start:
        push mode
        push nume
        call [fopen] ; se deschide fisierul in modul scriere
        add esp, 4*2
        
        mov [desc], eax ; dupa deschidere se depune descriptorul in desc
        
        cmp eax, 0 ; daca s-a efectuat o eroare la citire, se incheie executia programului
        je stop
        
        repeta:
        
            push print
            call [printf] ; se afiseaza promptul
            add esp, 4*1
            
            push cuv
            push format_scan
            call [scanf] ; se citesc in cuv de la tastatura 100 de caractere
            add esp, 4*2
            
            mov esi, cuv ; esi = adresa primului caracter din cuv 
            cld
            
            mov dl, 0 ; ok = 0, devine 1 cand intalnim o cifra
            
            char:
                lodsb ; al = cod ascii caracter din cuv
                
                cmp al, 0 ; daca s-a ajuns la capatul cuvantului, se trece la partea de afisare in fisier
                je fisier
                
                cmp al, '$' ; daca s-a citit caracterul '$' se inchide fisierul si se incheie executia programului
                je close
                
                cmp al, '0' ; se verifca daca caracterul curent este o cifra
                je cifra
                
                cmp al, '1'
                je cifra
                
                cmp al, '2'
                je cifra
                
                cmp al, '3'
                je cifra
                
                cmp al, '4'
                je cifra
                
                cmp al, '5'
                je cifra
                
                cmp al, '6'
                je cifra
                
                cmp al, '7'
                je cifra
                
                cmp al, '8'
                je cifra
                
                cmp al, '9'
                je cifra
                
                jmp nextchar
                
                cifra:
                    mov dl, 1 ; ok = 1 daca am gasit o cifra
                    
                nextchar:
                    jmp char
                
            fisier:
            cmp dl, 1 ; daca ok = 1. se afiseaza cuvantul in fisier
            jne final
            
            push dword cuv
            push dword [desc]
            call [fprintf]
            add esp, 4*2
            
            final:
                jmp repeta
    close:
        push dword [desc]
        call [fclose] ; se inchide fisierul
        add esp, 4*1
        
    stop:    
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
