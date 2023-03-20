#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

typedef struct buf {
    char c;
    char s[11];
} buf;

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

        buf data;

        while(1){
            read(p2c[0], &data, sizeof(buf));

            if(data.c == '\n'){
                break;
            }
            
            int nr = 0;
            for(int i = 0; i < strlen(data.s); i++){
                if(data.s[i] == data.c){
                    nr++;
                }
            }
            printf("Nr: %d\n", nr);
        }

        close(p2c[0]);
        exit(EXIT_SUCCESS);
    }

    // parent
    close(p2c[0]);
    
    buf data;
    
    while(1){
        printf("c: ");
        data.c = getchar();
        printf("sir: ");
        getchar();
        fgets(data.s, 11, stdin);
        
        write(p2c[1], &data, sizeof(buf));

        sleep(2);

        if(data.c == '\n'){
            break;
        }
    }

    int status;
    wait(&status);
    printf("Status: %d\n", status);

    close(p2c[1]);

    return 0;
}
