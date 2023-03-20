#!/bin/bash

DIR="."
for FILE in $(find $DIR -type f); do
    if file $FILE | grep -q ': ASCII text'; then
        LEN=$(cat $FILE | wc -l)
        if [ $LEN -lt 10 ]; then
            echo "------------------------------------------"
            cat $FILE
            echo "------------------------------------------"
        else
            echo "------------------------------------------"
            head -5 $FILE
            echo "==============="
            tail -5 $FILE
            echo "------------------------------------------"
        fi
    fi
done
