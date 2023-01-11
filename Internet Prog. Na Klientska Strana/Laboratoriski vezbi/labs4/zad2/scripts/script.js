document.getElementById("show-button").addEventListener("click", function() {

    let items = document.getElementById("items").value;
    let itemArray = items.split(",");
    let type = document.getElementById("type").value;
    let form = document.getElementsByTagName("form")[0];

    for (let i = 0; i < itemArray.length; i++) {
      let input = document.createElement("input");
      input.type = type;
      input.name = "options";
      input.value = itemArray[i];
      
      let label = document.createElement("label");
      label.innerHTML = itemArray[i];
      
      form.appendChild(input);
      form.appendChild(label);
    }
  });