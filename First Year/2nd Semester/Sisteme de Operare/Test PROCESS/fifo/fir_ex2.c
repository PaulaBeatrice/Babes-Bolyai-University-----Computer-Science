#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

int prim(int n){
    if(n <= 1){
        return 0;
    }

    if(n == 2){
        return 1;
    }

    for(int d = 2; d * d <= n; d++){
        if(n % d == 0){
            return 0;
        }
    }

    return 1;
} 

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

        if(prim(val)){
            printf("%d este prim\n", val);
        } else {
            printf("%d este neprim\n", val);
        }
    }

    close(fd);
    return 0;
}
