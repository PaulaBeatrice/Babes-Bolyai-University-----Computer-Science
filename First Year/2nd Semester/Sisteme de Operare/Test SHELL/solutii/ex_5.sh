#!/bin/bash

for p in $*
	do
		#echo $p
		if [ -d $p ]
			then
				echo "$p is a directory"
		elif [ -f $p ]
			then
				#echo "$p is a regular file"
				echo "File: " $p
				echo "Chars: " `wc -m <$p`	# `cat $p | wc -m`
				echo "Lines: " `wc -l <$p`
		fi
	done