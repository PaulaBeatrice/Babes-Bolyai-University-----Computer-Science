#!/bin/bash
if [ $# -ne 1 ]; then
    echo "Numar invalid de argumente"
    exit 1
fi

DIR=$1

if [ ! -d "$DIR" ]; then
    echo "$DIR nu este un director"
    exit 1
fi

for FILE in $(find "$DIR" -type f -perm 755); do
    read -p "Schimbare permisiuni pentru $FILE?(Y/N) " R
    R=${R,,}
    
    if [ "$R" = "y" ]; then
        chmod 744 $FILE
        echo "Permisiuni modificate pentru $FILE"
    fi
done
