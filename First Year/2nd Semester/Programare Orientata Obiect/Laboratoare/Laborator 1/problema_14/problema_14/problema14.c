#include<stdio.h>

// 14. Descompune un numar natural par, mai mare strict ca 2, intr - o suma de doua numere prime(verificarea ipotezei lui Goldbach).

int estePrim(int n)
{
    int prim = 1, i = 2;
    while (i <= n / 2 && prim)
    {
        if (n % i == 0)
            prim = 0;
        i++;
    }
    return prim;
}

void goldbach(int n)
{
    for (int i = 1; i <= n / 2; i += 2)
        if (estePrim(i) && estePrim(n - i))
            printf("Numarul %d se poate scrie ca suma dintre: %d si %d \n", n, i, n - i);
}


int main() {
    int n;
    printf("Introduceti un numar: ");
    scanf_s("%d", &n);
    goldbach(n);
    return 0;
}
