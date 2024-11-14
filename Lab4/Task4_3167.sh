#!/bin/bash
read -p "Enter the current year:" n

n_y=$n
p_y=$n

while true;
do
((n_y++))
 if ((n_y % 400 == 0 || (n_y %4 ==0 && n_y % 100!=0)))
then
break
fi
done

while true;
do
((p_y--))
if ((p_y % 400 == 0 || (p_y % 4 == 0 && p_y % 100!=0)))
then
break
fi
done
d_n=$((n_y - n))
d_p=$((n - p_y))

if((d_p < d_n))
then
echo"the closest leap year to $n is $p_y"
else
echo "the closest leap year to $n is $n_y"
fi

