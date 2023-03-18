bits 32 
;Se da un nume de fisier (definit in segmentul de date). Sa se creeze un fisier cu numele dat apoi sa se citeasca de 
;la tastatura cuvinte pana cand se citeste de la tastatura caracterul '$'. Sa se scrie in fisier doar cuvintele care
; contin cel putin o cifra.
global start        

extern exit,fopen,fclose,fprintf,printf,scanf  
import exit msvcrt.dll
import fopen msvcrt.dll
import scanf msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
        nume_fisier db "file.txt",0
        mod_acces db "w",0
        descriptor dd -1
        format db "%s",0
        format_af db "%s ", 10, 0
        cuvant times 32 db 0
        nr_cifre dd 0
        
segment code use32 class=code
    start:
        push mod_acces
        push nume_fisier
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
            
            mov EAX,dword cuvant
            
            
            mov esi,dword cuvant
            cld
            cauta_cifre:
                lodsb
                cmp Al, 0
                je aici
                cmp AL , '$'
                je afara
                cmp AL, '0'
                jb cauta_cifre
                cmp AL, '9'
                ja cauta_cifre
                
                
                
                push dword cuvant
                push dword [descriptor]
                call [fprintf]
                add ESP, 4 * 2
                
         
            aici:
            jmp read
        afara:
        push dword[descriptor]
        call [fclose]
        add ESP, 4
        final:
        push    dword 0      
        call    [exit]