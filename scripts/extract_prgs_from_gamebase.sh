#!/bin/bash
rm -rf c64
unzip c64a.zip 1>/dev/null
cd c64
rm *.T64 *.TAP *.G64 *.CRT *.NFO 
for i in *.D64; do
	echo $i
	mkdir "x$i"
	mv $i "x$i/$i"
	cd "x$i"
	c1541 -attach $i -dir > "dir.txt"
	num=`c1541 -attach $i -dir | grep prg | wc -l`
	#echo "$i number of prgs $num"
	# extract only disks containing 1-3 files (easy to parse)
	if [ "$num" -gt "0" -a "$num" -lt 4 ]; then
		c1541 -attach $i -extract >> "dir.txt"
		cd ..
		mv "x${i}" "${num}_${i%.D64}"
	else
		cd ..
		rm -rf "x$i"
	fi
done
cd ..
