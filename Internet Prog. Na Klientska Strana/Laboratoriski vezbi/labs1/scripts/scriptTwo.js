let num = prompt();
num = '0' + num.substring(0,2) + "/" + num.substring(2,5) + "-" + num.substring(5,8);

switch(num[2]){
    case '0' : case '1' : case '2' : num += ' - T-mobile'; break;
    case '5': case'6': str+=' - One'; break; 
    case '7': case '8': str+=' - Vip'; break; 
}

alert(num);