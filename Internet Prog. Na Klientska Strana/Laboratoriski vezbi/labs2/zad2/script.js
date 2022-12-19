let arr = [
    "https://designmodo.com/wp-content/uploads/2014/07/free-photos.jpg", 
    "https://farm5.staticflickr.com/4060/4567292488_5d3f4b05f6_z.jpg", 
    "https://wallup.net/wp-content/uploads/2017/03/28/401266-landscape-road-forest-748x421.jpg"
];

let ran = arr[Math.floor(Math.random()*arr.length)];

document.querySelectorAll("button").forEach(e => {
    e.addEventListener("click", () =>{
        let curImage = document.querySelector(".image").src;
        while(ran === curImage){ ran = arr[Math.floor(Math.random()*arr.length)]; }
        document.querySelector(".image").src = ran;
    });
});