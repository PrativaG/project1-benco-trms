window.onload = function(){
 
    makeHttpRequestForEmployee();

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
                
                //logic to add to event table
                if(xhr.status == 400){

                   addMessageToRow();
                }

                if(xhr.status == 200){

                    let eventList = JSON.parse(xhr.responseText);
                    let countButton = 0;
                    eventList.forEach(ev => {       

                        if(ev.emp.employeeId == sessionStorage.getItem("idOfLoggedIn")){
                            //equivalent to continue in normal for loop
                            return;
                        }

                        if(sessionStorage.getItem("titleOfLoggedIn") == "Benefit Coordinator"){
                            if(countButton <=0){
                                addGradeViewButton(ev);
                            }
                            countButton++;
                        }
                                         
                        if(sessionStorage.getItem("titleOfLoggedIn") == "Direct Supervisor" ){

                            if( ev.dsApproval != "Pending"){
                                addEventToRSeparateTable(ev);

                            }

                            else{
                                addEventToRow(ev);
                            }
                            

                        }
                        else if(sessionStorage.getItem("titleOfLoggedIn") == "Department Head" ){

                            if( ev.dsApproval == "Denied" 
                                || ev.hodApproval != "Pending"){

                                addEventToRSeparateTable(ev);

                            }

                            else{
                                addEventToRow(ev);
                            }
                            
                        }
                        else if(sessionStorage.getItem("titleOfLoggedIn") == "Benefit Coordinator"){
                           
                            if( ev.dsApproval == "Denied" 
                                || ev.hodApproval == "Denied" 
                                || ev.coordinatorApproval != "Pending"){

                                addEventToRSeparateTable(ev);
                            }

                            else{
                                addEventToCoordinatorRow(ev);
                            }
                            
                        }
                        
                    });
                }

                break; 
        }
    }

    xhr.open("GET", url, true);

    xhr.send();
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

let addEventToRow = function(event){

    let table = document.getElementsByClassName("approve-event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let reimAmtCol = document.createElement("td");
    let dsApproveCol = document.createElement("td");
    let hodApprovalCol = document.createElement("td");
    let coordApprovalCol = document.createElement("td");
    let acceptCol = document.createElement("td");
    let denyCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let additionalInfoCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(reimAmtCol);  
    tableRow.appendChild(dsApproveCol);
    tableRow.appendChild(hodApprovalCol);
    tableRow.appendChild(coordApprovalCol);
    tableRow.appendChild(acceptCol);
    tableRow.appendChild(denyCol);
    tableRow.appendChild(detailsCol);
    tableRow.appendChild(additionalInfoCol);

    table[0].appendChild(tableRow);

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

    //creating form and button for accepting request
    let acceptForm = document.createElement("form");
    acceptForm.action = "http://localhost:9090/event/dashboard/" +event.id;
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

    //creating  button for denying request
    let denyForm = document.createElement("form");
    denyForm.action = "denyReasonForm.html";
    input1 = document.createElement("input");
    input2 = document.createElement("input");

    input1.type = "hidden";
    input1.name = "eventId";
    input1.value = event.id;

    input2.type = "submit";
    input2.value = "Denied";
    input2.className = "smallButton";
    input2.id = "redButton";

    denyForm.appendChild(input1);
    denyForm.appendChild(input2);

    //creating link and button for updating request
    let additionalInfoForm = document.createElement("form");
    additionalInfoForm.action = "addInfoReqForm.html";
    let inputx = document.createElement("input");
    let inputy = document.createElement("input");

    inputx.type = "hidden";
    inputx.name = "eventId";
    inputx.value = event.id;

    inputy.type = "submit";
    inputy.value = "Request Additional Info";

    additionalInfoForm.appendChild(inputx);
    additionalInfoForm.appendChild(inputy);

    typeCol.innerHTML  = event.type;
    reimAmtCol.innerHTML = event.eligibleAmount;
    dsApproveCol.innerHTML = event.dsApproval;
    hodApprovalCol.innerHTML = event.hodApproval;
    coordApprovalCol.innerHTML = event.coordinatorApproval;
    detailsCol.appendChild(detailForm);
    acceptCol.appendChild(acceptForm);
    denyCol.appendChild(denyForm);
    additionalInfoCol.appendChild(additionalInfoForm);
}

let addEventToCoordinatorRow = function(event){

    let table = document.getElementsByClassName("approve-event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let reimAmtCol = document.createElement("td");
    let dsApproveCol = document.createElement("td");
    let hodApprovalCol = document.createElement("td");
    let coordApprovalCol = document.createElement("td");
    let acceptCol = document.createElement("td");
    let denyCol = document.createElement("td");
    let addAmtCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let additionalInfoCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(reimAmtCol);  
    tableRow.appendChild(dsApproveCol);
    tableRow.appendChild(hodApprovalCol);
    tableRow.appendChild(coordApprovalCol);
    tableRow.appendChild(acceptCol);
    tableRow.appendChild(denyCol);
    tableRow.appendChild(detailsCol);
    tableRow.appendChild(additionalInfoCol);
    tableRow.appendChild(addAmtCol);
    table[0].appendChild(tableRow);

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

    //creating form and button for accepting request
    let acceptForm = document.createElement("form");
    acceptForm.action = "http://localhost:9090/event/dashboard/" +event.id;
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
    input2.setAttribute("readonly", true);

    acceptForm.appendChild(input1);
    acceptForm.appendChild(input2);

    //creating  button for denying request
    let denyForm = document.createElement("form");
    denyForm.action = "denyReasonForm.html";
    input1 = document.createElement("input");
    input2 = document.createElement("input");

    input1.type = "hidden";
    input1.name = "eventId";
    input1.value = event.id;

    input2.type = "submit";
    input2.value = "Deny";
    input2.className = "smallButton";
    input2.id = "redButton";

    denyForm.appendChild(input1);
    denyForm.appendChild(input2);

    //creating form for adding the additional amount to request
    let addAmtForm = document.createElement("form");
    addAmtForm.action = "addAmt.html";
    let inputA = document.createElement("input");
    let inputB = document.createElement("input");

    inputA.type = "hidden";
    inputA.name = "eventId";
    inputA.value = event.id;

    inputB.type = "submit";
    inputB.value = "Add Amount";

    addAmtForm.appendChild(inputA);
    addAmtForm.appendChild(inputB);

    //creating link and button for additional info request
    let additionalInfoForm = document.createElement("form");
    additionalInfoForm.action = "addInfoReqForm.html";
    let inputx = document.createElement("input");
    let inputy = document.createElement("input");

    inputx.type = "hidden";
    inputx.name = "eventId";
    inputx.value = event.id;

    inputy.type = "submit";
    inputy.value = "Request Additional Info";

    additionalInfoForm.appendChild(inputx);
    additionalInfoForm.appendChild(inputy);

    typeCol.innerHTML  = event.type;
    reimAmtCol.innerHTML = event.eligibleAmount;
    dsApproveCol.innerHTML = event.dsApproval;
    hodApprovalCol.innerHTML = event.hodApproval;
    coordApprovalCol.innerHTML = event.coordinatorApproval;
    detailsCol.appendChild(detailForm);
    addAmtCol.appendChild(addAmtForm);
    acceptCol.appendChild(acceptForm);
    denyCol.appendChild(denyForm);
    additionalInfoCol.appendChild(additionalInfoForm);

    

    

}

let  addGradeViewButton = function(ev){
   // creating button to view grade
    let viewGrade = document.createElement("button");
    viewGrade.className = "bigButton";
    
    let v = document.createTextNode("View Grades");
    let link = document.createElement("a");

    link.appendChild(v);
    link.href = "viewGrade.html";

    viewGrade.appendChild(link);

    let div = document.getElementById("makeRequest");
    div.appendChild(viewGrade);
}
let addEventToRSeparateTable = function(event){

    let table = document.getElementsByClassName("past-event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let reimAmtCol = document.createElement("td");
    let dsApproveCol = document.createElement("td");
    let hodApprovalCol = document.createElement("td");
    let coordApprovalCol = document.createElement("td");
    let reasonCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let additionalInfoCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(reimAmtCol);  
    tableRow.appendChild(dsApproveCol);
    tableRow.appendChild(hodApprovalCol);
    tableRow.appendChild(coordApprovalCol);
    tableRow.appendChild(reasonCol);
    tableRow.appendChild(detailsCol);
    tableRow.appendChild(additionalInfoCol);
    table[0].appendChild(tableRow);

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

    

    //creating link and button for additional info request
    let additionalInfoForm = document.createElement("form");
    additionalInfoForm.action = "addInfoReqForm.html";
    let inputx = document.createElement("input");
    let inputy = document.createElement("input");

    inputx.type = "hidden";
    inputx.name = "eventId";
    inputx.value = event.id;

    inputy.type = "submit";
    inputy.value = "Request Additional Info";

    additionalInfoForm.appendChild(inputx);
    additionalInfoForm.appendChild(inputy);

    typeCol.innerHTML  = event.type;
    reimAmtCol.innerHTML = event.eligibleAmount;
    dsApproveCol.innerHTML = event.dsApproval;
    hodApprovalCol.innerHTML = event.hodApproval;
    coordApprovalCol.innerHTML = event.coordinatorApproval;
    if(event.reason != null){
        reasonCol.innerHTML = event.reason;
    }
    detailsCol.appendChild(detailForm);
    additionalInfoCol.appendChild(additionalInfoForm);
}

//helpful functions
// let createDenyButton = function(event){
    
// }






