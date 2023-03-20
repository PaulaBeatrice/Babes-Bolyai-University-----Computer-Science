#!/bin/bash

while true; do
    clear
    if [ $# -eq 0 ]; then
        ps -ef | awk '{print $1}' | tail -n +2 | sort | uniq -c | sort -nr
    else
        CONTENT=""
        for USER in $@; do
            if grep -q "^$USER:" /etc/passwd; then
                PROC=$(ps -ef | grep "^$USER " | awk '{print $1}')
                CONTENT="$CONTENT $PROC"
            fi
        done
        echo $CONTENT | sed 's/ /\n/g' | sort | uniq -c | sort -nr
    fi
    sleep 1
done
