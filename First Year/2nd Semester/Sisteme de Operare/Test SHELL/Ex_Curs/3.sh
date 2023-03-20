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

for FILE in $(find "$DIR" -type f | grep '\.log$'); do
    sort $FILE > "${FILE}2"
    mv "${FILE}2" $FILE
done
