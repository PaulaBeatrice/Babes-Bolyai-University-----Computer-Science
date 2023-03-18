;Se da un sir de caractere S. Sa se construiasca sirul D care sa contina toate caracterele cifre din sirul S.
;Exemplu:
;S: '+', '4', '2', 'a', '8', '4', 'X', '5'
;D: '4', '2', '8', '4', '5'
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    s db '+', '4', '2', 'a', '8', '4', 'X', '5'; declararea sirului initial s
    lung equ $-s; stabilirea lungimea sirului initial l ; l = 8
    d times lung db 0 ; rezervarea unui spatiu de dimensiune l pentru sirul destinatie d si initializarea acestuia

; our code starts here
  
segment code use32 class=code
    start:
        mov ECX, lung; punem lungimea sirului s in ECX pentru a putea realiza bucla loop de ecx ori
        mov ESI, 0
        mov EDI, 0

        jecxz Sfarsit ; verificam daca ECX != 0
        
        Repeta:
            mov AL, [s+ESI]
            cmp AL, '0'
            jz eticheta_0_1
            jmp eticheta_0_2
            eticheta_0_1:
                    mov [d + EDI], AL 
                    inc EDI
            eticheta_0_2:
                     cmp AL, '1'
                     jz eticheta_1_1
                     jmp eticheta_1_2
                     eticheta_1_1:
                                mov [d + EDI], AL 
                                inc EDI  
                     eticheta_1_2:
                                cmp AL, '2'
                                jz eticheta_2_1
                                jmp eticheta_2_2
                                eticheta_2_1:
                                            mov [d + EDI], AL 
                                            inc EDI
                                eticheta_2_2:
                                            cmp AL, '3'
                                            jz eticheta_3_1
                                            jmp eticheta_3_2
                                            eticheta_3_1:
                                                        mov [d + EDI], AL 
                                                        inc EDI
                                            eticheta_3_2:
                                                        cmp AL, '4'
                                                        jz eticheta_4_1
                                                        jmp eticheta_4_2
                                                        eticheta_4_1:
                                                                    mov [d + EDI], AL 
                                                                    inc EDI
                                                        eticheta_4_2:
                                                                    cmp AL, '5'
                                                                    jz eticheta_5_1
                                                                    jmp eticheta_5_2
                                                                    eticheta_5_1:
                                                                                mov [d + EDI], AL 
                                                                                inc EDI
                                                                    eticheta_5_2:
                                                                                cmp AL, '6'
                                                                                jz eticheta_6_1
                                                                                jmp eticheta_6_2
                                                                                eticheta_6_1:
                                                                                            mov [d + EDI], AL 
                                                                                            inc EDI
                                                                                eticheta_6_2:
                                                                                            cmp AL, '7'
                                                                                            jz eticheta_7_1
                                                                                            jmp eticheta_7_2
                                                                                            eticheta_7_1:
                                                                                                        mov [d + EDI], AL 
                                                                                                        inc EDI
                                                                                            eticheta_7_2:
                                                                                                        cmp AL, '8'
                                                                                                        jz eticheta_8_1
                                                                                                        jmp eticheta_8_2
                                                                                                        eticheta_8_1:
                                                                                                                    mov [d + EDI], AL 
                                                                                                                    inc EDI
                                                                                                        eticheta_8_2:
                                                                                                                    cmp AL,'9'
                                                                                                                    jz eticheta_9_1
                                                                                                                    jmp eticheta_9_2
                                                                                                                    eticheta_9_1:
                                                                                                                                mov [d + EDI], AL 
                                                                                                                                inc EDI
                                                                                                                    eticheta_9_2:
                                                                                                                                mov DL, 2
            inc ESI
            
        loop Repeta; 
        
        Sfarsit:;terminarea programului
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
