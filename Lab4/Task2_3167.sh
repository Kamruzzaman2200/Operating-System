#!/bin/bash
read -p "Enter the number : " n

a=1
b=1

echo -n "$a $b"

for ((i=2;i<n;i++))
do
fb=$((a+b))
echo -n "$fb"
a=$b
b=$fb
done
echo
