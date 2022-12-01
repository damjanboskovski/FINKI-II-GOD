let num = prompt("Enter a 3 digit number: "), numRev = '';

if(num.length !== 3){ alert("The number has either more or less then 3 digits"); }
else{
    for(let i=num.length-1; i>=0; i--){ numRev += num[i]; }
    if(num === numRev){ alert("The number is a palindrome"); }
    else { alert("The number is not a palindrome"); }
}
