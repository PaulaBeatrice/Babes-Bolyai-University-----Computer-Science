#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

DIR=$1

if [ ! -d "$DIR" ]; then
    echo "$DIR nu este director"
    exit 1
fi

FILES=$(find "$DIR" -type f)
for FILE1 in $FILES; do
    checksum1=$(md5sum $FILE1 | awk '{print $1}')
    echo "Duplicate pentru ${FILE1}:"
    N=0
    for FILE2 in $FILES; do 
        checksum2=$(md5sum $FILE2 | awk '{print $1}')     
        if [ "$checksum1" = "$checksum2" ]; then
            echo "$FILE2"
            N=$((N + 1))
        fi
    done
    
    if [ $N -eq 0 ]; then
        echo "Nu exista"
    fi
done
