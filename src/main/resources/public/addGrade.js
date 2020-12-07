window.onload = function(){

    let parameters = location.search.substring(1).split("?");
    let param = parameters[0].split("=");

    let eventId = param[1];
   
    let input = document.getElementById("hiddenInput");

    input.value = eventId;
}