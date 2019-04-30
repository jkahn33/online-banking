function submitTransfer(){
    if(document.getElementById("from-account").value === ""){
        let error = '<div id="error-alert" class="alert alert-danger alert-dismissible" role="alert" style="margin-top: -40px; margin-left: 15%; margin-right: 15%">From account must have a value.</div>'
        appendHtml(document.body, error);
        setTimeout(function() {
            const element = document.getElementById("error-alert");
            element.parentNode.removeChild(element);
        }, 5000);
    }
    else if(document.getElementById("to-account").value === ""){
        let error = '<div id="error-alert" class="alert alert-danger alert-dismissible" role="alert" style="margin-top: -40px; margin-left: 15%; margin-right: 15%">To account must have a value.</div>'
        appendHtml(document.body, error);
        setTimeout(function() {
            const element = document.getElementById("error-alert");
            element.parentNode.removeChild(element);
        }, 5000);
    }
    else if(document.getElementById("from-account").value === document.getElementById("to-account").value){
        let error = '<div id="error-alert" class="alert alert-danger alert-dismissible" role="alert" style="margin-top: -40px; margin-left: 15%; margin-right: 15%">Accounts must differ.</div>'
        appendHtml(document.body, error);
        setTimeout(function() {
            const element = document.getElementById("error-alert");
            element.parentNode.removeChild(element);
        }, 5000);
    }
    else if(isNaN(document.getElementById("amount").value)){
        let error = '<div id="error-alert" class="alert alert-danger alert-dismissible" role="alert" style="margin-top: -40px; margin-left: 15%; margin-right: 15%">Amount must be valid.</div>'
        appendHtml(document.body, error);
        setTimeout(function() {
            const element = document.getElementById("error-alert");
            element.parentNode.removeChild(element);
        }, 5000);
    }
    else{
        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function(){
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                const data = JSON.parse(xmlhttp.responseText);
                if(!data.success){
                    let error = '<div id="error-alert" class="alert alert-danger alert-dismissible" role="alert" style="margin-top: -40px; margin-left: 15%; margin-right: 15%">' + data.message + '</div>'
                    appendHtml(document.body, error);
                }
                else{
                    window.location.replace("homepage.html");
                }
            }
        };
        xmlhttp.open("POST", "http://localhost:8080/transfer");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        let fromAcc;
        let toAcc;
        if(document.getElementById("from-account").value === "Checking"){
            fromAcc = 1;
        }
        else{
            fromAcc = 2;
        }
        if(document.getElementById("to-account").value === "Checking"){
            toAcc = 1;
        }
        else{
            toAcc = 2;
        }
        xmlhttp.send(JSON.stringify({from:fromAcc, to:toAcc, amount:document.getElementById("amount").value, user:localStorage.getItem("userName")}));
    }
}
function appendHtml(el, str) {
    console.log("appending error");
    let div = document.createElement('div');
    div.innerHTML = str;
    while (div.children.length > 0) {
        el.appendChild(div.children[0]);
    }
}