bits 32 ; assembling for the 32 bits architecture 

  

; declare the EntryPoint (a label defining the very first instruction of the program) 

global start         

  

; declare external functions needed by our program 

extern exit               ; tell nasm that exit exists even if we won't be defining it 

import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll 

                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions 

  

; our data is declared here (the variables needed by our program) 

segment data use32 class=data 

    ; x = a + b - c, unde x, a, b, c – word 

     

    a dw 17 

    b dw 18 

    c dw 25 

    x resw 1 

  

; our code starts here 

segment code use32 class=code 

     

    start: 

         

        mov AX, [a]; AX=a=17 

        add AX, [b]; AX=a+b=17+18=35 

        sub AX, [c]; AX=a+b-c=35-25=10 

        mov [x], AX; x=a+b-c=10 <=> mov [c+2], AX 

     

        ; exit(0) 

        push    dword 0      ; push the parameter for exit onto the stack 

        call    [exit]       ; call exit to terminate the program 

 