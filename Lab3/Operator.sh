#!/bin/bash
read -p "Enter 1st Number:" a
echo -e $a
read -p "Enter 2nd Number:" b
echo -e $b
echo -e "Sum :"$(($a+$b))
echo -e "Sub :"$(($a-$b)) 
echo -e "Multi :"$(($a*$b)) 
echo -e "Div :"$(($a/$b)) 
