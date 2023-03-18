bits 32 


global start        


extern exit,printf,scanf               
import exit msvcrt.dll   
import printf msvcrt.dll
import scanf msvcrt.dll 
                         
segment data use32 class=data
    ; ...
    n dd 0
    format db "%d", 0
    mesaj db "numerele:",0
    cel_mai_mic dd 0
    len equ 100

; Se citesc de la tastatura numere (in baza 10) pana cand se introduce cifra 0. Determinaţi şi afişaţi cel mai mic număr dintre cele citite.
segment code use32 class=code
    start:
        ; afisam mesaj (numerele: )
        push dword mesaj
        call [printf]
        add esp, 4*1
        
        ;citim primul numar, daca e 0 iesim si nu afisam nimic (nu s-a citit nici un nr)
        push dword n 
        push dword format
        call [scanf]
        add esp, 4*2
        
        mov eax, [n]
        cmp eax, 0
        je final1
        
        ;salvam primul numar citit ca fiind cel mai mic momentan
        mov [cel_mai_mic],eax 
        
        ;citim maxim len numere de la tastatura 
        mov ecx,len 
        repeta:
            push dword n
            push dword format
            call [scanf]
            add esp, 4*2
            mov eax, [n]
            cmp eax, 0
            je final        ;daca am citit 0 iesim din bucla pt citire
            cmp eax, [cel_mai_mic]
            jg sari         ;daca numarul citit e mai mare decat cel_mai_mic nu facem nimic 
            mov [cel_mai_mic], eax    ;daca e mai mic sau egal modificam cel_mai_mic
            sari:
        loop repeta
        
        final:
        ;afisam cel_mai_mic numar 
        push dword [cel_mai_mic] 
        push dword format
        call [printf]
        add esp, 4*2
        
        
    
        final1:
        push    dword 0      
        call    [exit]      
