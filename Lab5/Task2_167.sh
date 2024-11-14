#!/bin/bash

Prime(){
local n=$1

if ((n<2));
then
echo "Not a prime number "
return
fi
for(( i=2 ; i*i<n;i++));do
if((n%i == 0)); then
echo "Number is not primenumber "
return
fi
done
echo "number is  prime number"
}
read -p "number: " nu
Prime "$nu"
