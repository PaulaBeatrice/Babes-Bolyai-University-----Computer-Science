#!/bin/bash

for f in `find $1 -perm -ugo=w -type f`
	do
		echo $f
		mv $f $f.all
	done