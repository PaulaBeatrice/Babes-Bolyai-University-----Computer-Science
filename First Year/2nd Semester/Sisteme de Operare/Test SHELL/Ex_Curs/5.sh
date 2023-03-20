#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Numar insuficient de argumente"
    echo "Utilizare: $0 [nume fisiere...]"
    exit 1
fi

while true; do
    for PROG in $@; do
        for PID in $(cat ps.fake | grep "\<$PROG\>" | awk '{print $2}'); do
            echo "[!Forbidden program $PROG]: Killing process with PID ${PID}..."
            # kill -9 $PID
            echo "Killed process with PID $PID"
        done
    done
    sleep 2
done
