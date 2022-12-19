function removecolor(){    
    let options = document.querySelectorAll("#colorSelect option");
    let colorValue = document.querySelector("#colorSelect").value;

    options.forEach(e => { if(e.value === colorValue){ e.remove(); } });
}