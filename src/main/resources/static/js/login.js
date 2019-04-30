function submitLogin(){
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if(xmlhttp.status === 200){
                localStorage.setItem("userName",document.getElementById("inputUser").value);
                window.location.replace("homepage.html");
            }
            else{
                let error = '<div class="alert alert-danger" role="alert" style="margin: 100px;">Invalid Credentials</div>'
                appendHtml(document.body, error);
            }
        }
    };
    xmlhttp.open("POST", "http://localhost:8080/login");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({user:document.getElementById("inputUser").value, pass:document.getElementById("inputPassword").value}));
}
function appendHtml(el, str) {
    let div = document.createElement('div');
    div.innerHTML = str;
    while (div.children.length > 0) {
        el.appendChild(div.children[0]);
    }
}