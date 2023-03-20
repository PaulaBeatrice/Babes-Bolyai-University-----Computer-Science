#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

typedef struct buf {
    char c;
    char s[11];
} buf;

int main(int argc, char* argv[]){
    int fd = open("fifo", O_RDONLY);     

    if(fd == -1) {
        perror("open()");
        exit(EXIT_FAILURE);
    }

    buf data;    

    while(1){
        read(fd, &data, sizeof(buf));
    
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

    close(fd);
    return 0;
}
