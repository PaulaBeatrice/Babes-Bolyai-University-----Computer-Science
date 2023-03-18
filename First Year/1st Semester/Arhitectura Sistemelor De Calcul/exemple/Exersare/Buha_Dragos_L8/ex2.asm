;Se da un fisier text. Sa se citeasca continutul fisierului, sa se contorizeze numarul de consoane si sa se afiseze aceasta 
;valoare. Numele fisierului text este definit in segmentul de date.
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fread, fclose, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import exit fopen.dll                ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import exit fread.dll  
import exit fclose.dll  
import exit printf.dll  

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    nume_fisier db "lab_8.txt", 0; numele fisierului care va fi deschis
    mod_acces db "r", 0; modul de deschidere a fisierului; r - pentru scriere. fisierul trebuie sa existe
    descriptor_fisier dd -1; variabila in care vom salva descriptorul fisierului - necesar pentru a putea face referire la fisier
    nr_caractere_citite dd 0
    text db "Numarul de consoane este: ", 0  ; mesajul afisat pe ecran
    format db "%d", 0
    
    len equ 100  ; lungimea maxima a textului citit
    text_citit resb (len + 1)  ; textul citit din fisier 
    consoane db "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ",0
    nr_consoane dd 0
    format_afisare db "%d", 0
    
; our code starts here
segment code use32 class=code
    start:
        ; afisez pe ecran mesajul "Numarul de consoane este: "
        ;printf("Numarul de consoane este: ")
        push dword text
        call [printf]; apelam functia printf
        add ESP, 4 * 1; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 1 = nr de parametrii
    
        ; apelam functia fopen pentru a dechide fisierul pentru citire
        ; functia va returna in EAX descriptorul fisierului
        ; eax = fopen(nume_fisier, mod_acces)
        push dword mod_acces
        push dword nume_fisier
        call[fopen]; apelam functia fopen
        add ESP, 4 * 2; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 2 = nr de parametrii
        
        ; compar cu 0. Daca este 0 atunci iese din program
        cmp EAX, 0
        je final
        
        
        ; citire text din fisier(citim cate 100 caractere din fisier pana cand citim tot fisierul)
        ; fread(text_citit, 1, len, fisier)
        citire:
            push dword [descriptor_fisier]
            push dword len
            push dword 1
            push dword text_citit
            call [fread]; apelam functia fread
            add ESP, 4 * 4; eliberam parametrii de pe stiva; 4 = dimensiunea unui dword; 4 = nr de parametrii
            
            cmp EAX, 0; daca nu am mai citit nimic iesim 
            je afisare; salt la eticheta afisare
            
            ; numarare consoane
            mov ESI, text_citit
            mov ECX, EAX; numarul de caractere citite 
            repeta: ;parcurgem textul citit caracter cu caracter 
                LODSB; AL <- [text_citit:ESI]
                push ECX; punem pe ECX pe stiva, pentru ca urmeaza sa il folosim pentru parcurgerea sirului de consoane
                mov ECX, 42  ; ECX = 42 -> lungimea vectorului de consoane 
                mov EBP, 0 ; EBP - 0, indexul cu care vom parcurge sirul de consoane
                cauta: ;cautam caracterul in vectorul de consoane 
                    mov BL, [consoane + EBP]; consoana curenta din sirul de consoane
                    cmp AL, BL 
                    je Consoana; daca AL = BL, adica daca AL este consoana, se face salt la eticheta Consoana
                    inc EBP; crestem indexul cu care parcurgem sirul de consoane 
                    loop cauta 
                pop ECX
                jmp Nu_e_consoana; daca a ajuns aici inseamna ca AL nu este consoana(nu se afla in sirul de consoane)
                
                Consoana: ; AL este consoana
                add dword[nr_consoane], 1; crestem cu 1 variabila in care retinem numarul de consoane din textul citit din fisier
       
                Nu_e_consoana:; daca caracterul din fisier nu este consoana, nu facem nimic
                loop repeta
        
            jmp citire 
        
       ;inchidem fisierul si afisam nr de consoane 
        afisare:
            
            push dword [nr_consoane]
            push dword format_afisare
            call [printf]
            add esp, 4*2
        
            push dword [descriptor_fisier]
            call [fclose]
            add esp, 4*1
        
        final:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

