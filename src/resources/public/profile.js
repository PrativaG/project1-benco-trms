window.onload = function(){

    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9090/employee" ;

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

let addEventToList = function(e){

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
    let newLine = document.createElement("br");

    //assigning values to li
    l1.innerHTML = e.firstName + " " + e.lastName;
    l2.innerHTML = "Title: " +e.title;
    l8.innerHTML = "Department : " +e.dept;
    // l3.innerHTML = "Start Date: " +event.startDate;
    // l4.innerHTML = "End Date: " +event.endDate;
    // l5.innerHTML = "Request Date: " +event.requestDate;
    l6.innerHTML = "Contact : " +e.contact;
    l7.innerHTML = "Total claim amount left: " +e.remainingClaimAmt;

    //adding li to ul
    unorderedList.appendChild(l1);
    unorderedList.appendChild(newLine);
    unorderedList.appendChild(newLine);
    unorderedList.appendChild(l2);
    unorderedList.appendChild(newLine);
    unorderedList.appendChild(newLine);
    // unorderedList.appendChild(l3);
    // unorderedList.appendChild(l4);
    // unorderedList.appendChild(l5);
    unorderedList.appendChild(l6); 
    unorderedList.appendChild(newLine);
    unorderedList.appendChild(newLine);  
    unorderedList.appendChild(l7);
    unorderedList.appendChild(newLine);
    unorderedList.appendChild(newLine); 
    unorderedList.appendChild(l8);
    unorderedList.appendChild(newLine);
    unorderedList.appendChild(newLine); 

    //adding ul to div
    div.appendChild(unorderedList);

}