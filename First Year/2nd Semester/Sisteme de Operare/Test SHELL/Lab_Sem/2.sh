#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

for F in $@; do
    if [ -f $F ]; then
        echo "Numele: $(echo $F | awk -F/ '{print $NF}')"
        echo "Numarul de caractere: $(cat $F | wc -m)"
        echo "Numarul de linii: $(cat $F | wc -l)"
    elif [ -d $F ]; then
        echo "Numele: $(echo $F | sed 's,/$,,' | awk -F/ '{print $NF}')" 
        echo "Numarul de fisiere: $(find $F -type f | wc -l)"
    fi
done
