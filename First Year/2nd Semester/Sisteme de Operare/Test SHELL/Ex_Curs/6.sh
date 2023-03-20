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

for FILE in $(find "$DIR" -type f -perm /o=w | sort); do
    PERM=$(ls -l $FILE | awk '{print $1}')
    echo $PERM $FILE
    chmod o-w $FILE
    PERM=$(ls -l $FILE | awk '{print $1}')
    echo $PERM $FILE
done
