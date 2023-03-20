#!/bin/bash

declare -i sum=0
declare -i cnt=0

# find -type f | ( while read f
# 	do
#     if file "$f" | grep -q "ASCII text"; then
#         c=`cat "$f"|wc -l`
#         sum=$(($sum+$c))
#         cnt=$(($cnt+1))
#     fi
# 	done
# 	echo -e "Sum: $sum\tCount: $cnt\tAverage: $(($sum/$cnt))" )

for f in `find $1 -type f`
	do
		if file $f | grep -q 'ASCII text'
			then
				echo $f
				c=`cat $f | wc -l`
        		sum=$(($sum+$c))
        		cnt=$(($cnt+1))
		fi
	done

echo -e "Sum: $sum\tCount: $cnt\tAverage: $(($sum/$cnt))"


