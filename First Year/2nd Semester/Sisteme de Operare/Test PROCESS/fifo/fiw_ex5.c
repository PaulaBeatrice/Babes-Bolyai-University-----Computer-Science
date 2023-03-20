#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc, char* argv[]){
    FILE* fp = fopen("input.txt", "r");    
    if(fp == NULL){
        perror("fopen()");
        exit(EXIT_FAILURE);
    }

    int res = mkfifo("p2c", 0600);
    if(res == -1){
        perror("mkfifo()");
        exit(EXIT_FAILURE);
    }
    res = mkfifo("c2p", 0600);
    if(res == -1){
        perror("mkfifo()");
        exit(EXIT_FAILURE);
    }

    int p2c = open("p2c", O_WRONLY);
    if(p2c == -1){
        perror("open()");
        exit(EXIT_FAILURE);
    }
    int c2p = open("c2p", O_RDONLY); 
    if(c2p == -1){
        perror("open()");
        exit(EXIT_FAILURE);
    }

    int a, b, sum;
    fscanf(fp, "%d %d", &a, &b);        

    write(p2c, &a, sizeof(int));
    write(p2c, &b, sizeof(int));

    read(c2p, &sum, sizeof(int));
    printf("Sum: %d\n", sum);
    
    fclose(fp);
    close(p2c);
    unlink("p2c");
    close(c2p);
    unlink("c2p");
    return 0;
}
