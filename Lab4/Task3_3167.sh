#!/bin/bash
read -p "Enter a year: " n

if ((n % 400 == 0 || (n % 4== 0 && n % 100 !=0 )))
then
echo "$n is leap Year"
else
echo "$n is not leap year"
fi
