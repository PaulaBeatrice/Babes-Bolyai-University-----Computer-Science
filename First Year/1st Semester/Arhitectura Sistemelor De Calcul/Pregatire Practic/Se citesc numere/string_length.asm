bits 32

segment code use32 public code

global lungime
global get_new_length


lungime:
    ; [ESP] - adresa de return
    ; [ESP + 4] - adresa sirului
    
    mov EAX, 0
    mov ESI, [ESP + 4]
    .bucla:
        mov BL, [ESI]
        cmp BL, 0
        je .final
        inc EAX
        inc ESI
    jmp .bucla
    .final:            
    ret
        
get_new_length:
    ; Genereaza lungimea numarului dupa conversia in baza 8
    ;[ESP] - adresa de return
    ;[ESP + 4] - lungime veche
    
    mov EAX, [ESP + 4]
    mov EDX, 0
    
    mov CL, 3
    div CL
    mov CL, AH
    cbw
    cwde
    cmp CL, 0
    jnz .skip
    dec EAX
    .skip:
    ret