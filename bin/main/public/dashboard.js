window.onload = function(){

    makeHttpRequestForEvent();
    makeHttpRequestForEmployee();
}

let makeHttpRequestForEvent = function(){
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

                //logic to add to event table
                if(xhr.status == 400){

                   addMessageToRow();
                }

                if(xhr.status == 200){

                    let eventList = JSON.parse(xhr.responseText);

                    eventList.forEach(ev => {

                        if(ev.dsApproval == "Accepted"
                            && ev.hodApproval == "Accepted"
                            && ev.coordinatorApproval == "Accepted"){

                                addEventToPastTable(ev);
                            }

                        if(ev.dsApproval == "Denied"
                        || ev.hodApproval == "Denied"
                        || ev.coordinatorApproval == "Denied"){

                            addEventToPastTable(ev);
                        }
                        else
                            addEventToRow(ev);
                    });
                }

                break; 
        }
    }

    xhr.open("GET", url, true);

    xhr.send();

  //  showClaimAmt(empClaimAmt);

}

let makeHttpRequestForEmployee = function(){
    
    let xhr = new XMLHttpRequest();

    const url2 = "http://localhost:9090/employee";

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

                    let employee = JSON.parse(xhr.responseText);
                   
                    sessionStorage.setItem("titleOfLoggedIn", employee.title);
                    sessionStorage.setItem("idOfLoggedIn", employee.employeeId);

                    showEmployeeInfo(employee);
                }

                break; 
        }
    }
    xhr.open("GET", url2, true);

    xhr.send();
}

let showEmployeeInfo = function(em){

    let heading1 = document.getElementById("name");
    heading1.innerHTML = "Welcome, " +em.firstName; 
    
    let heading2 = document.getElementById("department");
    heading2.innerHTML = "Department: " +em.department;

    let heading3 = document.getElementById("title");
    heading3.innerHTML = "Position: " +em.title;

    let heading4 = document.getElementById("claimAmtHeading");
    heading4.innerHTML = "Available Claim Amount: " +em.remainingClaimAmt;
}

let addEventToPastTable = function(event){
    let table = document.getElementById("past-event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let totalCost = document.createElement("td");
    let reimAmtCol = document.createElement("td");
    let dsApproveCol = document.createElement("td");
    let hodApprovalCol = document.createElement("td");
    let coordApprovalCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let removeCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(totalCost);

    tableRow.appendChild(reimAmtCol);  
    tableRow.appendChild(dsApproveCol);
    tableRow.appendChild(hodApprovalCol);
    tableRow.appendChild(coordApprovalCol);
    tableRow.appendChild(detailsCol);
    tableRow.appendChild(gradeCol); 
    tableRow.appendChild(removeCol);

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
     let addGradeLink = document.createElement("form");
     addGradeLink.action = "addGrade.html";
     let inputX = document.createElement("input");
     let inputY = document.createElement("input");
 
     inputX.type = "hidden";
     inputX.name = "eventId";
     inputX.value = event.id;
 
     inputY.type = "submit";
     inputY.value = "Add Grade";
 
     addGradeLink.appendChild(inputX);
     addGradeLink.appendChild(inputY);

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

    typeCol.innerHTML  = event.type;
    totalCost.innerHTML = event.cost;
    reimAmtCol.innerHTML = event.eligibleAmount;
    dsApproveCol.innerHTML = event.dsApproval;
    hodApprovalCol.innerHTML = event.hodApproval;
    coordApprovalCol.innerHTML = event.coordinatorApproval;
    detailsCol.appendChild(detailForm);
    gradeCol.appendChild(addGradeLink);
    removeCol.appendChild(removeForm);
}

let addEventToRow = function(event){

    let table = document.getElementById("event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let totalCost = document.createElement("td");
    let reimAmtCol = document.createElement("td");
    let dsApproveCol = document.createElement("td");
    let hodApprovalCol = document.createElement("td");
    let coordApprovalCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let removeCol = document.createElement("td");
    let updateCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(totalCost);

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
    let addGradeLink = document.createElement("form");
    addGradeLink.action = "addGrade.html";
    let inputX = document.createElement("input");
    let inputY = document.createElement("input");

    inputX.type = "hidden";
    inputX.name = "eventId";
    inputX.value = event.id;

    inputY.type = "submit";
    inputY.value = "Add Grade";

    addGradeLink.appendChild(inputX);
    addGradeLink.appendChild(inputY);

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
    totalCost.innerHTML = event.cost;
    reimAmtCol.innerHTML = event.eligibleAmount;
    dsApproveCol.innerHTML = event.dsApproval;
    hodApprovalCol.innerHTML = event.hodApproval;
    coordApprovalCol.innerHTML = event.coordinatorApproval;
    detailsCol.appendChild(detailForm);
    removeCol.appendChild(removeForm);
    updateCol.appendChild(updateForm);

    if(event.grade.gradeId != 0 ){
        gradeCol.appendChild(addGradeLink);
    }
}



