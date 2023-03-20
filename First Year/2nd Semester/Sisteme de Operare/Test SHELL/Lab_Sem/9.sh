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

for FILE in $(find "$DIR" -type f); do
    NAME=$(echo $FILE | awk -F/ '{print $NF}')
    LEN=$(expr length $NAME)
    if [ $LEN -lt 8 ]; then
        echo "$NAME" 
        if file $FILE | grep -q ': ASCII text'; then
            head -10 $FILE
        fi
    fi    
done
