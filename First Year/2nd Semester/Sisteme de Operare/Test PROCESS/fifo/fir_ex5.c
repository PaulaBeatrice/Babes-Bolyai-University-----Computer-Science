#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char* argv[]){
    int p2c = open("p2c", O_RDONLY);     
    if(p2c == -1) {
        perror("open()");
        exit(EXIT_FAILURE);
    }
    int c2p = open("c2p", O_WRONLY);
    if(c2p == -1){
        perror("open()");
        exit(EXIT_FAILURE);
    }
    
    int a, b, sum;    

    while(1){
        read(p2c, &a, sizeof(int));
        if(a == 0){
            break;
        }
        read(p2c, &b, sizeof(int));
        
        sum = a + b;
        write(c2p, &sum, sizeof(int));
    }

    close(p2c);
    close(c2p);
    return 0;
}
