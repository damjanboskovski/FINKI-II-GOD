let str = prompt().toString().split(' ');

if(str.length > 0){
    let max = str[0];
    for(let i=0; i<str.length; i++){
        if(str[i].length > max.length){ max = str[i]; }
    }
    alert(max);
}