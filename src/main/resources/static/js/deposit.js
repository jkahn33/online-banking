function submitDeposit(){
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            //localStorage.setItem("checking","$500.00");
            window.location.replace("homepage.html");
        }
    };
    xmlhttp.open("POST", "http://localhost:8080/deposit");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    if(document.getElementById("target").value === "Checking"){
        xmlhttp.send(JSON.stringify({routing:document.getElementById("routing").value, account:document.getElementById("account").value,
                                     holder:document.getElementById("holder").value, amount:document.getElementById("amount").value,
                                     user:localStorage.getItem("userName"), target:1}));
    }
    else{
        xmlhttp.send(JSON.stringify({routing:document.getElementById("routing").value, account:document.getElementById("account").value,
            holder:document.getElementById("holder").value, amount:document.getElementById("amount").value,
            user:localStorage.getItem("userName"), target:2}));
    }
}