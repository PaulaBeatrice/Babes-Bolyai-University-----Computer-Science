#!/bin/bash

for USER in $(cat /etc/passwd | awk -F: '{print $1}'); do
    IPS=$(last | grep "^$USER " | sed 's/ [A-Za-z]\{3\} .*//' | awk '{print $3}' | sort | uniq)
    NUM=$(last | grep "^$USER " | sed 's/ [A-Za-z]\{3\} .*//' | awk '{print $3}' | sort | uniq | tail -n +2 | wc -l)
    if [ $NUM -ne 0 ]; then
        touch "$USER"
        echo $IPS | awk '{print $0}' > "$USER"
    fi
done
