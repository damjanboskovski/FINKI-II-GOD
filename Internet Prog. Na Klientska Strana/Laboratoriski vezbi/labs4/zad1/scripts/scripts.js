function generate(){
    let tableRef = document.getElementById('class').getElementsByTagName('tbody')[0];

    let name = document.getElementById('name');
    
    let surname = document.getElementById('surname');
    let index = document.getElementById('index');
    let subject = document.getElementById('subject');
    let grade = document.getElementById('grade');
    console.log(grade.value);

    

    let newRow = tableRef.insertRow(tableRef.rows.length);

    let newCell0 = newRow.insertCell(0);
    let newCell1 = newRow.insertCell(1);
    let newCell2 = newRow.insertCell(2);
    let newCell3 = newRow.insertCell(3);
    let newCell4 = newRow.insertCell(4);

    newCell0.innerHTML = name.value;
    newCell1.innerHTML = surname.value;
    newCell2.innerHTML = index.value;
    newCell3.innerHTML = subject.value;
    newCell4.innerHTML = grade.value;

    name.value = "";
    surname.value = "";
    index.value = "";
    subject.value = "";
    grade.value = grade[0].value;
    
}