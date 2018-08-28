function sendAjaxGet(url, func) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this);
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}

function setDefaults(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        document.getElementById("username").defaultValue = res.username;
        document.getElementById("firstName").defaultValue = res.firstName;
        document.getElementById("lastName").defaultValue = res.lastName;
        document.getElementById("email").defaultValue = res.email;
    }
}

window.onload = function () {
    sendAjaxGet("http://localhost:8084/data?entity=employee&get=current", setDefaults);
}