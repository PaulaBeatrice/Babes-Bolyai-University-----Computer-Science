#!/bin/bash

#for FILESYS in $(cat df.fake | tail -n +2 | awk 'BEGIN{OFS=":"} {print $2, $5, $6}'); do
#    SIZE=$(echo $FILESYS | awk -F: '{print $1}' | sed 's/M//')
#    USE=$(echo $FILESYS | awk -F: '{print $2}' | sed 's/%//')
#    if [ $SIZE -lt 1024 ] || [ $USE -gt 80 ]; then
#        echo "$(echo $FILESYS | awk -F: '{print $3}')"
#    fi
#done

cat df.fake |\
tail -n +2 |\
awk '{print $2, $5, $6}' |\
while read SIZE USE MNT; do
    SIZE=$(echo $SIZE | sed 's/M//')
    USE=$(echo $USE | sed 's/%//')
    if [ $SIZE -lt 1024 ] || [ $USE -gt 80 ]; then
        echo "$MNT"
    fi
done
