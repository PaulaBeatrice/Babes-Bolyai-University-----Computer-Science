; Numele unui fisier e definit in sg de date. A ae inlocuiasca toata caracaterele scrise cu litere mici din fisier cu codul lor ascii(se va creea
; nou fisier de output in care vor fi facute aceste inlocuiri)
; EX: MariaC -> M9711410597C
bits 32

global start
extern exit, fopen, fscanf, fprintf
import exit msvcrt.dll
import fopen msvcrt.dll
import fscanf msvcrt.dll
import fprintf msvcrt.dll

segment data use32 class=data
    nume_fisier_input db "a.in", 0
    nume_fisier_output db "a.out", 0
    mod_acces1 db "r", 0
    mod_acces2 db "w", 0
    descriptor_in dd -1
    descriptor_out dd -1
    format_caracter db "%c", 0
    format_uint db "%u", 0
    buffer dd 0
    
segment code use32 class=code
start:

    ; fopen(nume_fisier, mod_acces)
    push dword mod_acces1                           ; se deschide primul fisier cu modul citire
    push dword nume_fisier_input
    call [fopen]
    add ESP, 4 * 2
    cmp EAX, 0
    je final
    mov [descriptor_in], EAX
    
    
    push dword mod_acces2                           ; se deschide al doilea fisier cu modul scriere
    push dword nume_fisier_output
    call [fopen]
    add ESP, 4 * 2
    cmp EAX, 0
    je final
    mov [descriptor_out], EAX
    
    
    mov ecx, -1                                     ; loop-ul se va incheia de fapt cand eax != 1 (in fisier nu mai sunt caractere ramase de citit)
    repeta:

        ; fscanf(descriptor, format, buffer)
        push dword buffer
        push dword format_caracter
        push dword [descriptor_in]
        call [fscanf]
        add ESP, 4 * 3                                    ; rezultatul va fi in buffer (dword)
        cmp EAX, 1                                      ; daca eax != 1 => s-a terminat de citit din fisier
        jne final
        
        cmp dword [buffer], 'a'
        jae comp2
        jmp pastrare

        comp2:
            cmp dword [buffer], 'z'
            ja pastrare
        
        ; fprintf(descriptor, format, var1, var2, ..)
        push dword [buffer]
        push dword format_uint
        push dword [descriptor_out]
        call [fprintf]
        add ESP, 4 * 3
        jmp urmatorul
        
        pastrare:                                       ; caracterul curent nu este litera mica => ramane neschimbat in rezultatul final
            push dword [buffer]
            push dword format_caracter
            push dword [descriptor_out]
            call [fprintf]
            add ESP, 4 * 3
        
        urmatorul:
    loop repeta
    
final:

    push dword 0
    call [exit]