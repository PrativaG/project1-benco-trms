window.onload = function(){

    makeHttpRequestForEvents();
}

let makeHttpRequestForEvents = function(employee){
    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9090/event/dashboard";

    xhr.onreadystatechange = function(){

        switch(xhr.readyState){

            case 0:
                console.log("nothing has been initialized");
                break;
            
            case 1:
                console.log("connection established");
                break;

            case 2:
                console.log("request sent");
                break;
            
            case 3:
                console.log("waiting for response");
                break;

            case 4:
                console.log("Wait FINISHED!");
                console.log(document.cookie);
        
                if(xhr.status == 200){

                    let eventList = JSON.parse(xhr.responseText);

                    eventList.forEach(ev => {

                        if(ev.grade.gradeID != 0){

                            if(ev.grade.status == "Pending"){
                                addPendingGrade(ev);
                            }else{
                                addGradeTable(ev);
                            }
                            
                            console.log(ev);
                        }
                        
                    });
                }

                break; 
        }
    }

    xhr.open("GET", url, true);

    xhr.send();
}

let addGradeTable = function(ev){
    let table = document.getElementById("prev-grade-table");

  
    let eventCol = document.createElement("td");
    let statusCol = document.createElement("td");

    let tableRow = document.createElement("tr");

    tableRow.appendChild(eventCol);
    tableRow.appendChild(statusCol); 

   
    table.appendChild(tableRow);    

     eventCol.innerHTML = ev.type;
     statusCol.innerHTML = ev.grade.status;
}

let addPendingGrade = function(ev){

    let table = document.getElementById("grade-table");

  
    let eventCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let acceptCol = document.createElement("td");
    let denyCol = document.createElement("td");
    let statusCol = document.createElement("td");
    let tableRow = document.createElement("tr");

    tableRow.appendChild(eventCol);
    tableRow.appendChild(gradeCol);
    tableRow.appendChild(statusCol);  
    tableRow.appendChild(acceptCol); 
    tableRow.appendChild(denyCol);

   
    table.appendChild(tableRow);    

     //creating form and button for accepting grade
     let acceptForm = document.createElement("form");
     acceptForm.action = "http://localhost:9090/grade/" +ev.grade.gradeID;
     acceptForm.method = "POST";
     input1 = document.createElement("input");
     input2 = document.createElement("input");
 
     input1.type = "hidden";
     input1.name = "approval";
     input1.value = "Accepted";
 
     input2.type = "submit";
     input2.value = "Accept";
     input2.className = "smallButton";
     input2.id = "greenButton";
 
     acceptForm.appendChild(input1);
     acceptForm.appendChild(input2);
 
     //creating  button for denying grade
     let denyForm = document.createElement("form");
     denyForm.action = "http://localhost:9090/grade/" +ev.grade.gradeID;
     denyForm.method = "POST";
     input1 = document.createElement("input");
     input2 = document.createElement("input");
 
     input1.type = "hidden";
     input1.name = "approval";
     input1.value = "Denied";
 
     input2.type = "submit";
     input2.value = "Deny";
     input2.className = "smallButton";
     input2.id = "redButton";
 
     denyForm.appendChild(input1);
     denyForm.appendChild(input2);

     eventCol.innerHTML = ev.type;
     statusCol.innerHTML = ev.grade.status;

     gradeCol.innerHTML = ev.grade.file;
     acceptCol.appendChild(acceptForm);
     denyCol.appendChild(denyForm);

}