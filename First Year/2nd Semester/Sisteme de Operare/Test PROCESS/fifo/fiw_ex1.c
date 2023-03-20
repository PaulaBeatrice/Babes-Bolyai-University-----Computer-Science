#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

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

    int val;
    while(1){
        printf("val: ");
        scanf("%d", &val);
        
        write(fd, &val, sizeof(int));
        if(val == 0){
            break;
        }
    }
    
    close(fd);
    unlink("fifo");
    return 0;
}
