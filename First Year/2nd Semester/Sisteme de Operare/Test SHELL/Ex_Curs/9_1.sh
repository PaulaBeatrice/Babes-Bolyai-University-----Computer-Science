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

DUPL=""
FILES=$(find "$DIR" -type f)
for FILE1 in $FILES; do
    checksum1=$(md5sum $FILE1 | awk '{print $1}')
    
    for FILE2 in $FILES; do 
        checksum2=$(md5sum $FILE2 | awk '{print $1}')     
        if [ "$checksum1" = "$checksum2" ]; then
            DUPL="$DUPL|$FILE2"
        fi
    done
done

echo $DUPL | sed 's/|/\n/g' | sort | uniq | tail -n +2
