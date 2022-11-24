let cnt = 0;
function generate(){
    let name = document.getElementById("name");
    let surname = document.getElementById("surname");
    let reg = document.getElementById("regtable");
    let age = document.getElementById("age");
    let radioYes = document.getElementById("yes");
    let radioNo = document.getElementById("no");

    if(name.value == "" || surname.value == "" || reg.value == "" || age.value == "" ||
    (radioYes.checked == false && radioNo.checked == false)){
        alert("Vnesete gi site podatoci");
        return;
    }
    if(isNaN(age.value)){
        alert("Vnesi broj za godinite!");
    }

    if(regFormat(reg.value)){
        alert("Nevalidna tablica");
        return;
    }

    let pay = 3000;
    if(regGold(reg.value)){ pay += 1000; }

    let accident;
    if(radioYes.checked === true){
        pay += 500;
        accident = "Има";
    }else if(radioNo.checked === true){
        accident = "Нема";
    }
    
    let mainList = document.getElementById("bills");
    let newDiv = document.createElement("div");
    newDiv.innerHTML = "<p>" + name.value + "</p>" +
                       "<p>" + reg.value + "</p>" +
                       "<p>Сообраќајки: " + accident + "</p>" + 
                       "<p>Старост на вило: " + age.value + "</p>" + 
                       "<p>За пкаќање: " + pay + "</p>" + 
                       "<button onclick='pay(this)'>Плати</button><button onclick='remove(this)'>Избриши</button>"
    newDiv.setAttribute("class", "new");

    if(age.value > 30){
        newDiv.style.backgroundColor = "red";
    }else{
        newDiv.style.backgroundColor = "lightgreen";
    }

    mainList.append(newDiv); cnt++;
    let counter = document.getElementById("bills-payed");
    counter.innerHTML = cnt;
}

function regFormat(registration){
    let regex = /^[A-Z][A-Z]-[0-9][0-9][0-9][0-9]-[A-Z][A-Z]$/
    if(registration.length != 10){ return true; }
    if(regex.test(registration)){ return false; }
    else { return true; }
}

function regGold(registration){
    let regSplit = registration.split("-");
    let num = regSplit[1];
    if(num[0] == num[1] && num[0] == num[2] && num[0] == num[3]){ return true; }
    else { return false; }
}

function pay(btn){
    btn.disabled = true;
    btn.nextElementSibling.disabled = true;
}

function remove(btn){
    let parent = btn.parentNode;
    parent.remove();
    cnt--;
    let element = document.getElementById("bills-payed");
    element.innerHTML = cnt;
}