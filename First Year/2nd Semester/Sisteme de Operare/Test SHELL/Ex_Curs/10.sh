#!/bin/bash

cat last.fake |\
sed '$d' |\
awk '{print $1}' |\
sort |\
uniq -c |\
sort -nr |\
while read NUM USER; do
    FULLNAME=$(cat passwd.fake | grep "^${USER}:" | awk -F: '{print $5}')
    echo "$NUM $FULLNAME"
done | less
