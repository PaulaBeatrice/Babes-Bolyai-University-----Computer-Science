bits 32 ; assembling for the 32 bits architecture
global reversed

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...

; our code starts here
segment code use32 class=code
    reversed:
        ; return address - [esp]
        ; cdecl p1 - [esp + 4] , p2 - [esp + 8] etc
        
        ; cv input - [esp + 4]
        ; cv output - [esp + 8]
        
        mov esi, [esp + 4]
        mov edi, [esp + 8]
        
        .loop_:
                cld
                lodsb
                cmp al, ' '
                je .exit_
                
                cmp al, 0
                je .exit_
                
                std
                stosb
                
        jmp .loop_
        .exit_:
                mov edi, [esp + 8]
                cld
                inc edi
                mov al, 10
                stosb
                
                mov al, 0
                stosb
                
                ret
        