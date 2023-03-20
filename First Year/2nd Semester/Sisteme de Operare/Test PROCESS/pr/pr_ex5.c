#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(int argc, char* argv[]){
    int p2c[2], c2p[2];    
    
    int res = pipe(p2c);
    if(res == -1){
       perror("pipe()");
       exit(EXIT_FAILURE);
    }

    res = pipe(c2p);
    if(res == -1){
        perror("pipe()");
        exit(EXIT_FAILURE);
    }

    int pid = fork();
    if(pid == -1){
        perror("fork()");
        exit(EXIT_FAILURE);
    }

    if(pid == 0){
        // Child
        close(p2c[1]);
        close(c2p[0]);

        int a, b, sum;

        read(p2c[0], &a, sizeof(int));
        read(p2c[0], &b, sizeof(int));
            
        sum = a + b;
        write(c2p[1], &sum, sizeof(int));

        close(p2c[0]);
        close(c2p[1]);
        exit(EXIT_SUCCESS);
    }

    // parent
    close(p2c[0]);
    close(c2p[1]);
    
    int a, b, sum;
    FILE* fp = fopen("input.txt", "r");
    if(fp == NULL){
        perror("fopen()");
        exit(EXIT_FAILURE);
    }

    fscanf(fp, "%d %d", &a, &b);
    fclose(fp);

    write(p2c[1], &a, sizeof(int));
    write(p2c[1], &b, sizeof(int));
    
    read(c2p[0], &sum, sizeof(int));
    printf("sum: %d\n", sum);

    int status;
    wait(&status);
    printf("Status: %d\n", status);

    close(p2c[1]);
    close(c2p[0]);

    return 0;
}
