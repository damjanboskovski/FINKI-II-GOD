function submit(){

    document.body.style.fontFamily = "Arial";

    let name = prompt("Enter name:"); 
    document.querySelector("#name").innerHTML = name;
    let index = prompt("Enter index:");
    document.querySelector("#index").innerHTML = index;
    let hometown = prompt("Enter hometown:");
    document.querySelector("#hometown").innerHTML = hometown;

    document.querySelectorAll("ul li").forEach(element =>{
        element.style.backgroundColor = "red";
    });
}