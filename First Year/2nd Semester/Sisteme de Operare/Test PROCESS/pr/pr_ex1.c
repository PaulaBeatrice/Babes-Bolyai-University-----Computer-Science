#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(int argc, char* argv[]){
    int p2c[2];    
    
    int res = pipe(p2c);
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

        int val;

        while(1){
            read(p2c[0], &val, sizeof(int));

            if(val == 0){
                break;
            }

            if(val % 2 == 0){
                printf("%d este par\n", val);
            } else {
                printf("%d este impar\n", val);
            }
        }

        close(p2c[0]);
        exit(EXIT_SUCCESS);
    }

    // parent
    close(p2c[0]);
    
    int val;
    
    while(1){
        printf("val: ");
        scanf("%d", &val);

        write(p2c[1], &val, sizeof(int));

        sleep(2);

        if(val == 0){
            break;
        }
    }

    int status;
    wait(&status);
    printf("Status: %d\n", status);

    close(p2c[1]);

    return 0;
}
