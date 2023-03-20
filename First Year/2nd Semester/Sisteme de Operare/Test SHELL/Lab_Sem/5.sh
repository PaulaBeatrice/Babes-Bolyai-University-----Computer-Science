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

SUM=0
NUM=0
for FILE in $(find $DIR -type f); do
    if file $FILE | grep -q ': ASCII text'; then
        LINE=$(wc -l < $FILE)
        SUM=$((SUM + LINE))
        NUM=$((NUM + 1))
    fi
done

AVG=$(echo "scale=2; $SUM/$NUM" | bc)
echo $AVG
