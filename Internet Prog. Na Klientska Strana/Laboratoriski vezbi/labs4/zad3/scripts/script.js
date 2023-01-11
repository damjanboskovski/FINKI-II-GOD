document.getElementById("change-button").addEventListener("click", function(){
    let bgOption = document.querySelector('input[name="bg-option"]:checked').value;
    let bgValue = document.getElementById("bg-value").value;
    if(bgOption == "color"){
        document.body.style.backgroundColor = bgValue;
    } else if(bgOption == "image"){
        document.body.style.backgroundImage = "url(" + bgValue + ") ";
    }
});