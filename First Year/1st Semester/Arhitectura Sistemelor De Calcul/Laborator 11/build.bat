nasm -fobj modul.asm
nasm -fobj main.asm
alink main.obj modul.obj -oPE -subsys console -entry start
main