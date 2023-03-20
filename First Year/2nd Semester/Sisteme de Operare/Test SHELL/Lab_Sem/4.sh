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

for FILE in $(find $DIR -type f); do
    if grep -q '[0-9]\{6,\}' $FILE; then
        echo "$(echo $FILE | awk -F/ '{print $NF}')"
    fi
done
