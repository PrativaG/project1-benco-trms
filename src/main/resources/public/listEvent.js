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

                //logic to add to event table
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

    let table = document.getElementById("event-table");

    let tableRow = document.createElement("tr");

    let typeCol = document.createElement("td");
    let descriptionCol = document.createElement("td");
    let detailsCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let removeCol = document.createElement("td");
    let updateCol = document.createElement("td");

    tableRow.appendChild(typeCol);
    tableRow.appendChild(descriptionCol);
    tableRow.appendChild(detailsCol);
    tableRow.appendChild(gradeCol); 
    tableRow.appendChild(removeCol);
    tableRow.appendChild(updateCol);

    table.appendChild(tableRow);

    //creating details link 
    let detailLink = document.createElement("a");
    let link = document.createTextNode("See more..."); 
    detailLink.appendChild(link);
    detailLink.title = "Details";
    detailLink.href = "#";

    //creating link and button for add grades
    let addGradeLink = document.createElement("a");
    let gradelink = document.createTextNode("Add Grade"); 
    addGradeLink.appendChild(gradelink);
    addGradeLink.title = "Grade";
    addGradeLink.href = "#";

    let addGrade = document.createElement("button");
    addGrade.appendChild(addGradeLink);

    //creating link and button for removing request
    let removeReqLink = document.createElement("a");
    let removelink = document.createTextNode("Delete"); 
    removeReqLink.appendChild(removelink);
    removeReqLink.title = "Remove";
    removeReqLink.href = "#";

    let deleteEvent = document.createElement("button");
    deleteEvent.appendChild(removeReqLink);

    //creating link and button for removing request
    let updateReqLink = document.createElement("a");
    let updatelink = document.createTextNode("Update"); 
    updateReqLink.appendChild(updatelink);
    updateReqLink.title = "Update";
    updateReqLink.href = "#";

    let updateEvent = document.createElement("button");
    updateEvent.appendChild(updateReqLink);

    typeCol.innerHTML  = event.type;
    descriptionCol.innerHTML = event.description;
    detailsCol.appendChild(detailLink);
    gradeCol.appendChild(addGrade);
    removeCol.appendChild(deleteEvent);
    updateCol.appendChild(updateEvent);
}