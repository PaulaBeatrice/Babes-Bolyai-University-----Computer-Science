#!/bin/bash

for f in `find $1 -type f | sort`
	do
		if file $f | grep -q 'ASCII text'
			then
				echo -n "File: "; echo $f | sed 's@.*/@@'
				
				len=`cat $f | wc -l`
				if [ $len -lt 10 ]
					then		
						cat -n $f
					else
						echo "_____Head_____"
						head -n 10 $f
						echo "_____Tail_____"
						tail -n 10 $f
				fi
		fi
	done