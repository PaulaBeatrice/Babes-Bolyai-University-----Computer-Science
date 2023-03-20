#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

FILE=$1

if [ ! -f "$FILE" ]; then
    echo "$FILE nu este un fisier"
    exit 1
fi

EMAILS=""
for LINE in $(cat $FILE | grep '.'); do
    if cat passwd.fake | grep -q "^$LINE:"; then
        EMAILS="${EMAILS},${LINE}@scs.ubbcluj.ro"
    fi 
done

EMAILS=$(echo $EMAILS | sed 's/^,//')
echo $EMAILS
