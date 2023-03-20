#!/bin/bash

# 1. Să se scrie un script bash care primeşte ca argument un număr natural N şi generează N fişiere de tip text, astfel:
# - numele fişierelor vor fi de forma: file_X.txt, unde X = {1, 2, ..., N}
# - fiecare fişier generat va conţine doar liniile de la X la X + 5 ale fişierului passwd.fake

if [ $# -ne 1 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

N=$1

if !(echo $N | grep -q '^[0-9]\+$') || [ $N -lt 1 ]; then
    echo "Numar invalid"
    exit 1
fi

for X in $(seq 1 $N); do
    touch "file_${X}.txt"
    sed -n ''$X',+5p' passwd.fake >file_${X}.txt
done
