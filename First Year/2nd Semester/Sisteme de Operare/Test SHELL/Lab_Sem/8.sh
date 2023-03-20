#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

RES=""
for FILE in $@; do
    if [ ! -f "$FILE" ]; then
        echo "Warning: $FILE is not a file"
        continue
    fi

    LINE=$(sort $FILE | uniq -c | sort -nr | head -n 1)
    RES="${LINE}|${RES}"
done

echo $RES | sed 's/| \?/\n/g' | sort -nr
