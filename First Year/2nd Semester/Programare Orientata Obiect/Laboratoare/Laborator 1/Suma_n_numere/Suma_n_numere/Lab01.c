#include <stdio.h>

//Scrieti un program care citeste n numere de la tastatura si tipareste suma lor.

int main()
{
	int n, x, i, suma = 0;
	scanf_s("%d", &n); //citim numarul n
	for (i = 1; i <= n; i++)
	{
		scanf_s("%d", &x); //citim cele n numere
		suma = suma + x; //calculam suma numerelor citite
	}
	printf("Suma numerelor este: %d", suma); //afisam suma numerelor

	return 0;
}