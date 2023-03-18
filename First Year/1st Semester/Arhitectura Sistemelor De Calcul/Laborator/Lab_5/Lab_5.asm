HOME RO EN
EXEMPLEEXERCITIITEORIE
Laborator 5 - Exemple
Instructiuni de comparare, salt conditionat si de ciclare. Operatii pe siruri.
Exemplu
	
;Se da un sir de caractere format din litere mici.
;Sa se transforme acest sir in sirul literelor mari corespunzator.
bits 32 
global start        
extern exit,printf ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll
; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
; our data is declared here (the variables needed by our program)
segment data use32 class=data
	s db 'a', 'b', 'c', 'm','n' ; declararea sirului initial s
	l equ $-s ; stabilirea lungimea sirului initial l
	d times l db 0 ; rezervarea unui spatiu de dimensiune l pentru sirul destinatie d si initializarea acestuia
    ptPrint db '%c', 0

segment code use32 class=code
start:
	mov ecx, l ;punem lungimea in ECX pentru a putea realiza bucla loop de ecx ori
	mov esi, 0     
	jecxz Sfarsit      
	Repeta:
		mov al, [s+esi]
		mov bl, 'a'-'A' ; pentru a obtine litera mare corespunzatoare literei mici, vom scadea din codul ASCII 
		; al literei mici diferenta dintre 'a'-'A'
		sub al, bl             
		mov [d+esi], al    
		inc esi
	loop Repeta
    mov EAX,0
    mov AL, [s+1]
    push EAX
    push dword ptPrint șadresa unde incepe in mem
    call [printf]
    add ESP,4*2
    Sfarsit:;terminarea programului
	; exit(0)
	push dword 0 ; push the parameter for exit onto the stack
	call [exit] ; call exit to terminate the program