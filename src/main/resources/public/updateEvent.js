window.onload = function(){

    let parameters = location.search.substring(1).split("?");
    let param = parameters[0].split("=");

    let eventId = param[1];
    console.log(parameters);

    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9090/event/" +eventId;

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

                    let event = JSON.parse(xhr.responseText);

                    populateFormWithEvent(event);
                }

                break; 
        }
    }
    xhr.open("GET", url, true);

    xhr.send();
}

let populateFormWithEvent = function(event){

    console.log(event.startDate);


    let form = document.getElementById("updateEventForm");
    form.action = "http://localhost:9090/event/" +event.id;

    let inputDescription = document.getElementsByName("description");
    inputDescription[0].value = event.description;

    //converting java localdate to javascript date
    // let day = event.startDate.dayOfMonth;
    // let month = event.startDate.month;
    // let year =  event.startDate.year;

    // let startD = new Date(Date.UTC(year, month, day));

    // let inputStartDate = document.getElementsByName("startDate");
    // inputStartDate[0].value = startD;

    // //converting endDate
    // day = event.endDate.dayOfMonth;
    // month = event.endDate.month;
    // year =  event.endDate.year;

    // let endD = new Date(Date.UTC(year, month, day));

    // DateTimeForm
    // console.log(endD);
    // let inputEndDate = document.getElementsByName("endDate");
    // inputEndDate[0].value = endD;

    let inputCost = document.getElementsByName("cost");
    inputCost[0].value = event.cost;
}


