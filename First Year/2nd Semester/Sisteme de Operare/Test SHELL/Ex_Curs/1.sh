#!/bin/bash

for U in $(cat who.fake | awk '{print $1}'); do 
    NP=$(cat ps.fake | awk '$1 == "'$U'" {print $1}' | wc -l)
    echo $U $NP
done
