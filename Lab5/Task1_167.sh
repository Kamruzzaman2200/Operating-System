#!/bin/bash

echo "Enter numbers: "
read -a arr

min=${arr[0]}
max=${arr[0]}
for num in "${arr[@]}"; do
    if (( num < min )); then
        min=$num
    fi
    if (( num > max )); then
        max=$num
    fi
done

expected_sum=$(( (max * (max + 1) / 2) - ((min - 1) * min / 2) ))

actual_sum=0
for num in "${arr[@]}"; do
    actual_sum=$((actual_sum + num))
done

missing_number=$((expected_sum - actual_sum))
echo "The missing number is: $missing_number"

