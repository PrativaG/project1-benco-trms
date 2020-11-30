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

                    addEventToList(event);
                }

                break; 
        }
    }

    xhr.open("GET", url, true);

    xhr.send();
}

let addEventToList = function(event){

    let div = document.getElementById("main");

    //creating unordered list
    let unorderedList = document.createElement("ul");
    let l1 = document.createElement("li");
    let l2 = document.createElement("li");
    // let l3 = document.createElement("li");
    // let l4 = document.createElement("li");
    // let l5 = document.createElement("li");
    let l6 = document.createElement("li");
    let l7 = document.createElement("li");
    let l8 = document.createElement("li");

    //assigning values to li
    l1.innerHTML = "Event Type: " +event.type;
    l2.innerHTML = "Cost: " +event.cost;
    l8.innerHTML = "Eligile Amount : " +event.eligibleAmount;
    // l3.innerHTML = "Start Date: " +event.startDate;
    // l4.innerHTML = "End Date: " +event.endDate;
    // l5.innerHTML = "Request Date: " +event.requestDate;
    l6.innerHTML = "Description: " +event.description;
    l7.innerHTML = "Reason: " +event.reason

    //adding li to ul
    unorderedList.appendChild(l1);
    unorderedList.appendChild(l2);
    // unorderedList.appendChild(l3);
    // unorderedList.appendChild(l4);
    // unorderedList.appendChild(l5);
    unorderedList.appendChild(l6);   
    unorderedList.appendChild(l7);
    unorderedList.appendChild(l8);

    //adding ul to div
    div.appendChild(unorderedList);

}