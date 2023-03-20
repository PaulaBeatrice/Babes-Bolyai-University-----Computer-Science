#!/bin/bash

for USER in $(cat /etc/passwd | awk -F: '{print $1}'); do
    SUM_PID=0
    PIDS=$(ps -ef | tail -n +2 | grep "^$USER " | awk '{print $2}')
    NUM=$(echo $PIDS | wc -w)
    for PID in $PIDS; do
        SUM_PID=$((SUM_PID + PID))
    done
    if [ $NUM -ne 0 ]; then 
        echo "$USER - $(echo "scale=2; $SUM_PID/$NUM" | bc)"
    fi
done
