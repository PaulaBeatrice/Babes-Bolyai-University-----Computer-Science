#!/bin/bash

while read -p "fisier: " FILE && [ -n "$FILE" ]; do
    if [ -f "$FILE" ]; then
        WORDS=$(cat "$FILE" | head -n 1 | wc -w)
        SIZE=$(du -b "$FILE" | awk '{print $1}')
        echo "$FILE: $WORDS cuvinte, $SIZE bytes"
    fi
done

