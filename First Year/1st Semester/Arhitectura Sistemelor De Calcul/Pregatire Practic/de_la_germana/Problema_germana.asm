bits 32 ; assembling for the 32 bits architecture
;Este dat fisierul pruefung.txt. Fisierul contine un text de maxim 220 semne-litere mici,mari spatii libere si puncte.
; Afisati pe ecran, pe randuri diferite, cuv cu un numar impar de litere.
; interschimband semnul din mijloc cu un spatiu liber.

global start        


extern exit,fopen,fscanf,fclose,printf              
import exit msvcrt.dll   
import fopen msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll
import fscanf msvcrt.dll

segment data use32 class=data
    nume_fisier db "pruefung.txt",0
    mod_acces db "r",0
    descriptor dd -1
    cuvant times 32 db 0
    format db "%s",0
    format_afisare db "%s ",0
    cnt dd 0
    newline db 10,13,0
 
segment code use32 class=code
    start:
        push dword mod_acces
        push dword nume_fisier
        call [fopen]
        add ESP, 4 * 2
        
        mov [descriptor], EAX
        
        cmp EAX,0
        je final
        
        repeta:
            pushad
            push dword cuvant
            push format
            push dword [descriptor]
            call [fscanf]
            add ESP, 4 * 3
            
            test EAX, EAX
            js afara
            
            mov dword[cnt],0
            
            mov ESI, dword cuvant
            
            nr_lit:
                lodsb
                cmp AL, 0
                je verifica_imp
                
                
                mov EDX,dword[cnt]
                inc EDX
                mov dword[cnt], EDX
                jmp nr_lit
            verifica_imp:
                mov EAX, dword [cnt] 
                test EAX, 01h
                jne afiseaza
                je aici
            afiseaza:
            
                ;calculam pozitia din mijloc a cuvantului
                mov EAX, dword[cnt]
                mov EDX, 0
                mov EBX, 2
                div EBX
                ;punem spatiu pe acea pozitie
                mov byte[cuvant + EAX], " "
                ;afisam cuvantul
                push dword cuvant
                push format_afisare
                call [printf]
                add ESP, 4 * 2
                ;separat cu newline
                push dword newline
                call [printf]
                add ESP, 4
            aici:
            popad
           
            jmp repeta
        
        afara:
            ;inchiderea fisierului
            push dword [descriptor]
            call [fclose]
            add ESP, 4
        final:
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program