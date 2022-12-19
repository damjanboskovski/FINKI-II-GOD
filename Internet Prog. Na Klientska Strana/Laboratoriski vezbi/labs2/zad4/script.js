function sum(){
    let firstNum = document.querySelector(".first-num").value;
    let secondNum = document.querySelector(".second-num").value;
    let thirdNum = document.querySelector(".third-num").value;
    let fourthNum = document.querySelector(".fourth-num").value;

    let resOne = parseInt(firstNum) + parseInt(thirdNum)
    let resTwo = parseInt(secondNum) + parseInt(fourthNum);

    document.querySelector(".res-one").value = resOne;
    document.querySelector(".res-two").value = resTwo;
}