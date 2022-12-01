let num = prompt().split(', '), sum = 1;

for(i of num){ sum *= Number(i); }
sum >= 0 ? alert("Znakot e +") : alert("Znakot e -");