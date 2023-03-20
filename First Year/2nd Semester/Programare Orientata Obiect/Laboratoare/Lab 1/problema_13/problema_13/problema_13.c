#include <stdio.h>

//13. Descompune in factori primi un numar natural nenul dat.

int main()
{
	int n, d = 2;
	printf("Introduceti un numar: ");
	scanf_s("%d", &n); //citim numarul n
	printf("Descompunerea in factori primi a lui %d este: ", n);
	while (n != 1) //efectuam descompunerea lui in factori primi
		if (n % d == 0)
			while (n % d == 0)
			{
				printf("%d ", d); //afisam factorul prim
				n = n / d;
			}
		else d++;
}