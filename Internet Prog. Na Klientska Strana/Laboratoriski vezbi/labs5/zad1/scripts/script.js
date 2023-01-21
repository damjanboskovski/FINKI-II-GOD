function calculate(){ 
    const selected = document.querySelector("input[type='radio']:checked");
    let a = document.getElementById("a").value; a = !isNaN(parseInt(a)) ? parseInt(a) : 0;
    let b = document.getElementById("b").value; b = !isNaN(parseInt(b)) ? parseInt(b) : 0;
    let res = 0;

    switch(selected?.id){
        case "plus": res = a + b; break;
        case "minus": res = a - b; break;
        case "mnoz": res = a * b; break;
        case "del": res = a / b; break;
        case "sin": res = Math.sin(a); break;
        case "cos": res = Math.cos(a); break;
        case "tg": res = Math.tan(a); break;
        case "cel": res = Math.ceil(a); break;
        case "stepen": res = Math.pow(a,b); break;
        case "koren": res = Math.sqrt(a); break;
    }

    document.querySelector("#rezultat").innerHTML = res;
}
