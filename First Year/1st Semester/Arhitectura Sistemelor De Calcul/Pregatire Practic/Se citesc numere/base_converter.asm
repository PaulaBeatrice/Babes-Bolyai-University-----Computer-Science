bits 32

segment code use32 public code

global convert2_8
extern get_new_length
extern convert_digit

convert2_8:
    ;Face conversia propriu-zisa.
    
    ; [ESP] - adresa de return
    ; [ESP + 4] - adresa numarului in baza 2
    ; [ESP + 8] - adresa numarului in baza 8  
    ; [ESP + 12] - lungimea sirului
    mov EBX, 0
    mov ESI, [ESP + 4]
    add ESI, [ESP + 12]
    sub ESI, 1
        
    push dword [ESP + 12]
    call get_new_length
    add ESP, 4 * 1
        
        
    mov EDI, [ESP + 8]
    add EDI, EAX
        
    mov byte[EDI + 1], 0
        
    mov ECX, [ESP + 12]
    std
    .bucla:
            
        ;pusha
        lodsb
        cbw
        cwde
        push EAX
        
        dec ECX
        jnz .cont1
        
        push dword 30h
        jmp .next1
        
        .cont1:
        
        lodsb
        cbw
        cwde
        push EAX
        
        
        dec ECX
        .next1:
        
        jnz .cont2
        
        push dword 30h
        jmp .next2
        
        .cont2:
        
        lodsb
        cbw
        cwde
        push EAX
        
        .next2:
        
        call convert_digit
        add ESP, 4 * 3
        stosb
        ;stosb
        
        ;popa
        
        cmp ECX, 0
        je .forced_stop
    loop .bucla
    .forced_stop:
    ret

