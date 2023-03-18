; Fie un fisier -numele dat in segmentul de date- ce contine mai multe note separate prin spatiu (notele sunt de la 1 la 10). Sa se citeasca aceste 
; note, sa se calculeze suma si diferenta lor si sa se scrie la finalul fisierului.
bits 32
global start        

extern exit,fopen,fclose,fscanf,fprintf             
import exit msvcrt.dll   
import fopen msvcrt.dll
import fclose msvcrt.dll
import fscanf msvcrt.dll
import fprintf msvcrt.dll
                         
segment data use32 class=data
    nume_fisier db "nume.txt",0
    descriptor dd -1
    mod_acces db "a+",0
    nota dd 0
    suma dd 0
    diferenta dd 0
    format db "%d",0
    frm_af db "%d",10,0
    
segment code use32 class=code
    start:
        push dword mod_acces
        push dword nume_fisier
        call [fopen]
        add ESP,4 * 2
        
        mov [descriptor], EAX
        cmp EAX, 0
        je final
        
        citeste:
            push dword nota
            push format
            push dword[descriptor]
            call [fscanf]
            add ESP, 4 * 3
            
            test EAX, EAX
            js afara
            
            ;calc suma
            mov EBX, dword[suma]
            add EBX, dword[nota]
            mov dword[suma], EBX
            
            ;calc diferenta
            mov EAX,dword[diferenta]
            mov EDX, 0
            sub EAX,dword[nota]
            sbb EDX, 0
            mov dword[diferenta],EAX
            
           
            jmp citeste
       
        afara:
        
        push dword[suma]
        push frm_af
        push dword[descriptor]
        call [fprintf]
        add ESP, 4 * 3
        
        push dword[diferenta]
        push frm_af
        push dword[descriptor]
        call [fprintf]
        add ESP, 4 * 3
        
        push dword[descriptor]
        call [fclose]
        add ESP, 4
        final:
        push    dword 0     
        call    [exit]