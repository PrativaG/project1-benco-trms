window.onload = function(){

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

let addEventToRow = function(event){

    let table = document.getElementById("approve-event-table");

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
    input2.value = "Deny";
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

let popFormForDeny = function(){

    let div = document.createElement("div");
    div.className = "form-popup";
    div.id = "myForm";

    let denyForm = document.createElement("form");
    denyForm.className = "form-contianer";
    denyForm.action = "http://localhost:9090/event/";
    denyForm.method = "POST";
    let input1 = document.createElement("input");
    let input2 = document.createElement("input");
    let input3 = document.createElement("input");
    let label = document.createElement("label");

    input1.type = "hidden";
    input1.name = "eventId";
    // input1.value = event.id;

    label.innerHTML = "Please provide reason for your denial: "
    input2.type = "text";
    input2.name = "reason";

    input3.type = "submit";
    input3.className = "btn";
    input3.value = "Submit!";

    denyForm.appendChild(input1);
    denyForm.appendChild(label);
    denyForm.appendChild(input2);
    denyForm.appendChild(input3);

    div.appendChild(denyForm);

}







