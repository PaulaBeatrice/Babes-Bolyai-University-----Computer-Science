#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

typedef struct buf {
    char c;
    char s[11];
} buf;

int main(int argc, char* argv[]){
    int res = mkfifo("fifo", 0600);
    if(res == -1){
        perror("mkfifo()");
        exit(EXIT_FAILURE);
    }

    int fd = open("fifo", O_WRONLY);
    if(fd == -1){
        perror("open()");
        exit(EXIT_FAILURE);
    }

    buf data;
    while(1){
        printf("c: ");
        data.c = getchar();
        printf("s: ");
        getchar();
        fgets(data.s, 11, stdin);
        
        write(fd, &data, sizeof(buf));
        if(data.c == '\n'){
            break;
        }
    }
    
    close(fd);
    unlink("fifo");
    return 0;
}
