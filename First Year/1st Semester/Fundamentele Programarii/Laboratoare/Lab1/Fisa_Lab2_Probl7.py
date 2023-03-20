#Fie n un număr natural dat. Calculați produsul p al tuturor factorilor proprii ai lui n.
n = input('Introduceti numarul ')
n = int(n)
p = 1
if n >= 0:
    for i in range(2,int(n/2) + 1):
        if n % i == 0:
            p *= i
    if p != 1:
        print(p)
    else:
        print('Numarul nu are factori proprii')
else:
    for i in range(-2, int(n/2) - 1,-1):
        if n % i == 0:
           p *= i
    if p != 1:
        print(p)
    else:
        print('Numarul nu are factori proprii')


