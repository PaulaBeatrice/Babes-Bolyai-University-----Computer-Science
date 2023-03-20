#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

WORDS=""
for WORD in $@; do
    WORDS="${WORDS};${WORD},0"
done

WORDS=$(echo $WORDS | sed 's/^;//')

while read -p "fisier: " FILE; do
    if [ -z "$FILE" ]; then
        break
    fi

    if [ -f "$FILE" ]; then
        for WORD in $@; do
            if grep -q "$WORD" "$FILE"; then
                WORDS=$(echo $WORDS | sed "s/\($WORD,\)[01]/\11/") 
            fi
        done
    fi   
 
    NUM=$(echo $WORDS | sed 's/;/\n/g' | grep -c ',1')
    if [ $NUM -eq $# ]; then
        break
    fi
done 
