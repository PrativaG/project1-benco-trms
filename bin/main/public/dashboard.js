window.onload = function(){

    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9090/event";

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
                


                //logic to add to event table
                if(xhr.status == 400){

                   addMessageToRow();
                }

                if(xhr.status == 200){

                    let eventList = JSON.parse(xhr.responseText);

                    eventList.forEach(ev => {
                        addEventToRow(ev);
                    });
                }

                break; 
        }
    }

    xhr.open("GET", url, true);

    xhr.send();

}

let addMessageToRow = function(){

    let table = document.getElementById("event-table");

    let tableRow = document.createElement("tr");

    tableRow.innerHTML = "No request made for reimbursement yet!!"; 

    table.appendChild(tableRow);

}

let addEventToRow = function(event){

    let table = document.getElementById("event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let reimAmtCol = document.createElement("td");
    let dsApproveCol = document.createElement("td");
    let hodApprovalCol = document.createElement("td");
    let coordApprovalCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let removeCol = document.createElement("td");
    let updateCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(reimAmtCol);  
    tableRow.appendChild(dsApproveCol);
    tableRow.appendChild(hodApprovalCol);
    tableRow.appendChild(coordApprovalCol);
    tableRow.appendChild(detailsCol);
    tableRow.appendChild(gradeCol); 
    tableRow.appendChild(removeCol);
    tableRow.appendChild(updateCol);

    table.appendChild(tableRow);

    //creating form for details of the event
    let detailForm = document.createElement("form");
    detailForm.action = "detailsOfEvent.html";
    let input1 = document.createElement("input");
    let input2 = document.createElement("input");

    input1.type = "hidden";
    input1.name = "eventId";
    input1.value = event.id;

    input2.type = "submit";
    input2.value = "See more...";

    detailForm.appendChild(input1);
    detailForm.appendChild(input2);

    //creating link and button for add grades
    let addGradeLink = document.createElement("a");
    let gradelink = document.createTextNode("Add Grade"); 
    addGradeLink.appendChild(gradelink);
    addGradeLink.title = "Grade";
    addGradeLink.href = "addGrade.html";

    let addGrade = document.createElement("button");
    addGrade.appendChild(addGradeLink);

    //creating form and button for removing request
    let removeForm = document.createElement("form");
    removeForm.action = "http://localhost:9090/event/" +event.id;
    removeForm.method = "DELETE";
    input1 = document.createElement("input");
    input2 = document.createElement("input");

    input1.type = "hidden";
    input1.name = "eventId";
    input1.value = event.id;

    input2.type = "submit";
    input2.value = "Delete";

    removeForm.appendChild(input1);
    removeForm.appendChild(input2);

    //creating link and button for updating request
    let updateForm = document.createElement("form");
    updateForm.action = "updateEvent.html";
    let inputx = document.createElement("input");
    let inputy = document.createElement("input");

    inputx.type = "hidden";
    inputx.name = "eventId";
    inputx.value = event.id;

    inputy.type = "submit";
    inputy.value = "Update";

    updateForm.appendChild(inputx);
    updateForm.appendChild(inputy);

    typeCol.innerHTML  = event.type;
    reimAmtCol.innerHTML = event.eligibleAmount;
    dsApproveCol.innerHTML = event.dsApproval;
    hodApprovalCol.innerHTML = event.hodApproval;
    coordApprovalCol.innerHTML = event.coordinatorApproval;
    detailsCol.appendChild(detailForm);
    gradeCol.appendChild(addGrade);
    removeCol.appendChild(removeForm);
    updateCol.appendChild(updateForm);
}



