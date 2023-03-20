#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

DIR=$1

if [ ! -d "$DIR" ]; then
    echo "$DIR nu este un director"
    exit 1
fi

N=0
for FILE in $(find "$DIR" -type f | grep '\.c$'); do
    LEN=$(wc -l < $FILE)
    if [ $LEN -gt 500 ]; then
        echo $FILE
        N=$((N + 1))     
    fi
    
    if [ $N -eq 2 ]; then
        break
    fi
done
