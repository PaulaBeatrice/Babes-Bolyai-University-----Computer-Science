#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

for FILE in $@; do
    if [ -f "$FILE" ]; then
        WORDS=$(cat "$FILE" | head -n 1 | wc -w)
        SIZE=$(du -b "$FILE" | awk '{print $1}')
        echo "$FILE: $WORDS cuvinte, $SIZE bytes"
    fi
done
