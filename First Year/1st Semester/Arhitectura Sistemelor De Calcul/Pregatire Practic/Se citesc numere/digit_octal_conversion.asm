bits 32

segment code use32 public code

global convert_digit

convert_digit:
    ; Converteste un triplet de cifre binare intr-o cifra din baza 8
    
    ; [ESP] - adresa de return
    ; [ESP + 4] - cel mai putin semnificativ bit
    ; [ESP + 8] - bitul de mijloc
    ; [ESP + 12] - cel mai semnificativ bit
    mov Al, 0
    mov EBX, [ESP + 4]
    sub BL, '0'
    add AL, BL
    add Al, BL
    add Al, BL
    add AL, BL
        
    mov BL, [ESP + 8]
    sub BL, '0'
        
    add AL, BL
    add AL, BL
        
    mov BL, [ESP + 12]
    sub BL, '0'
    add AL, BL
        
    add AL, '0'
        
    ret
        
        