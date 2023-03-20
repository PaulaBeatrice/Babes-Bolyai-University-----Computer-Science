#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Numar de argumente invalid"
    exit 1
fi

if [ $(( $# % 3)) -ne 0 ]; then
    echo "Numarul de argumente nu este multiplu de 3"
    exit 1
fi

while [ $# -ne 0 ]; do
    NUME=$1
    CUV=$2
    NUM=$3
    shift 3

    if [ ! -f "$NUME" ]; then
        echo "Warning: $NUME is not a file" >&2
        continue
    fi    

    awk '{num = 0; for(i=1; i<=NF; i++) if($i == "'$CUV'") num++; if(num == '$NUM') print $0}' $NUME
    echo '----------'
done
