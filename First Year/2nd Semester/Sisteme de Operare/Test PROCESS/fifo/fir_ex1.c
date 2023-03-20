#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char* argv[]){
    int fd = open("fifo", O_RDONLY);     

    if(fd == -1) {
        perror("open()");
        exit(EXIT_FAILURE);
    }

    int val;    

    while(1){
        read(fd, &val, sizeof(int));
    
        if(val == 0){
            break;
        }

        if(val % 2 == 0){
            printf("%d este par\n", val);
        } else {
            printf("%d este impar\n", val);
        }
    }

    close(fd);
    return 0;
}
