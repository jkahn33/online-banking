function submitNewUser(){
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            //alert(xmlhttp.responseText);
            // const data = JSON.parse(xmlhttp.responseText);
            // alert(data);
            window.location.replace("index.html");
        }
    };
    xmlhttp.open("POST", "http://localhost:8080/createOnlineAccount");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({userName:document.getElementById("user-name").value, password:document.getElementById("pass").value,
        newCustomer:{name:document.getElementById("full-name").value, dateOfBirth:document.getElementById("dob").value, phone:document.getElementById("phone-number").value,
            address:{addr:document.getElementById("address").value, city:document.getElementById("city").value,
            state:document.getElementById("state").value, country:document.getElementById("country").value,
            zipcode:document.getElementById("zip").value}}}));
}